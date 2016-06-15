package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class RadioButtonTest extends AbstractPageObjectTest {

    @InjectMocks
    RadioButton cut;

    @Before
    public void configureWebElementMock() {
        stubWebElementTagAndType("input", "radio");
    }

    /* selection state */

    @Test
    public void testThatSelectionStateIsRetrievedFromCorrectWebElementMethod() {

        doReturn(true).when(webElement).isSelected();
        assertThat(cut.isSelected(), is(true));

        doReturn(false).when(webElement).isSelected();
        assertThat(cut.isSelected(), is(false));

    }

    /* clicking */

    @Test
    public void testThatClickingARadioButtonDelegatesToCorrectMethod() {
        cut.click();
        verify(webElement).click();
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClickingAnInvisibleRadioButtonThrowsException() {
        elementIsInvisible();
        cut.click();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClickingADisabledRadioButtonThrowsException() {
        elementIsDisabled();
        cut.click();
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_textType() {
        stubWebElementTagAndType("input", "radio");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_inputTag_nonTextFieldType() {
        stubWebElementTagAndType("input", "other");
        cut.validate(webElement);
    }

}
