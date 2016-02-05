package info.novatec.testit.webtester.pageobjects;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class HeadlineTest extends AbstractPageObjectTest {

    @InjectMocks
    Headline cut;

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_h1Tag() {
        stubWebElementTag("h1");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_h2Tag() {
        stubWebElementTag("h2");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_h3Tag() {
        stubWebElementTag("h3");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_h4Tag() {
        stubWebElementTag("h4");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_h5Tag() {
        stubWebElementTag("h5");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_h6Tag() {
        stubWebElementTag("h6");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

}
