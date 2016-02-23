package info.novatec.testit.webtester.pageobjects;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class PasswordFieldTest extends AbstractPageObjectTest {

    @InjectMocks
    PasswordField cut;

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_inputTag_passwordType() {
        stubWebElementTagAndType("input", "password");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_nonInputTag() {
        stubWebElementTagAndType("other", null);
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_inputTag_nonPasswordFieldType() {
        stubWebElementTagAndType("input", "other");
        cut.validate(webElement);
    }

}
