package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class TableFieldTest extends AbstractPageObjectTest {

    @InjectMocks
    TableField cut;

    /* header field */

    @Test
    public void testThatHeaderFieldsAreCorrectlyIdentified_IsHeaderField() {
        doReturn("th").when(webElement).getTagName();
        assertThat(cut.isHeaderField(), is(true));
    }

    @Test
    public void testThatHeaderFieldsAreCorrectlyIdentified_IsNotHeaderField() {
        doReturn("td").when(webElement).getTagName();
        assertThat(cut.isHeaderField(), is(false));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_thTag() {
        stubWebElementTag("th");
        cut.validate(webElement);
    }

    @Test
    public void testCorrectnessOfClassForWebElement_tdTag() {
        stubWebElementTag("td");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

}
