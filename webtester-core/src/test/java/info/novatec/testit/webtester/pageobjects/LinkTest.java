package info.novatec.testit.webtester.pageobjects;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class LinkTest extends AbstractPageObjectTest {

    @InjectMocks
    Link cut;

    @Before
    public void configureWebElementMock() {
        stubWebElementTag("a");
    }

    /* clicking */

    @Test
    public void testThatClickingALinkDelegatesToCorrectMethod() {
        cut.click();
        verify(webElement).click();
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClickingAnInvisibleLinkThrowsException() {
        elementIsInvisible();
        cut.click();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClickingADisabledLinkThrowsException() {
        elementIsDisabled();
        cut.click();
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_aTag() {
        stubWebElementTag("a");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

}
