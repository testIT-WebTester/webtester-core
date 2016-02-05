package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsDisabledException;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class ButtonTest extends AbstractPageObjectTest {

    @InjectMocks
    Button cut;

    /* getting of value */

    @Test
    public void testThatValueCanBeRetrieved() {
        doReturn("foo").when(webElement).getAttribute("value");
        String value = cut.getValue();
        assertThat(value, is("foo"));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoValueIsAvailable() {
        String value = cut.getValue();
        assertThat(value, is(StringUtils.EMPTY));
    }

    /* getting of label */

    @Test
    public void testThatLabelIsRetrievedFormValueAttributeForInputButtons() {
        stubWebElementTag("input");
        doReturn("label").when(webElement).getAttribute("value");
        String text = cut.getLabel();
        assertThat(text, is("label"));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoValueIsAvailableForInputButtons() {
        stubWebElementTag("input");
        String text = cut.getLabel();
        assertThat(text, is(StringUtils.EMPTY));
    }

    @Test
    public void testThatLabelIsRetrievedFormVisibleTextForNonInputButtons() {
        stubWebElementTag("button");
        doReturn("label").when(webElement).getText();
        String text = cut.getLabel();
        assertThat(text, is("label"));
    }

    @Test
    public void testThatEmptyStringIsReturnedIfNoVisibleTextIsAvailableForOtherButtons() {
        stubWebElementTag("button");
        String text = cut.getLabel();
        assertThat(text, is(StringUtils.EMPTY));
    }

    /* clicking */

    @Test
    public void testThatClickingAButtonDelegatesToCorrectMethod() {
        cut.click();
        verify(webElement).click();
    }

    @Test(expected = PageObjectIsInvisibleException.class)
    public void testThatClickingAnInvisibleButtonThrowsException() {
        elementIsInvisible();
        cut.click();
    }

    @Test(expected = PageObjectIsDisabledException.class)
    public void testThatClickingADisabledButtonThrowsException() {
        elementIsDisabled();
        cut.click();
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag() {
        stubWebElementTag("button");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_buttonType() {
        stubWebElementTagAndType("input", "button");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_resetType() {
        stubWebElementTagAndType("input", "reset");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_submitType() {
        stubWebElementTagAndType("input", "submit");
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

    /* utilities */

    private void assertThatCorrectnessOfClassIs(boolean expected) {
        assertThat(cut.isCorrectClassForWebElement(webElement), is(expected));
    }

}
