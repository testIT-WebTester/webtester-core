package info.novatec.testit.webtester.pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class TextAreaTest extends AbstractPageObjectTest {

    @InjectMocks
    TextArea cut;

    /* column count */

    @Test
    public void testThatColumnCountIsRetrievedFromTheCorrectAttribute() {
        doReturn("21").when(webElement).getAttribute("cols");
        int columnCount = cut.getColumnCount();
        assertThat(columnCount, is(21));
    }

    @Test
    public void testThatMinusOneIsReturnedIfNoColumnsValueIsAvailable() {
        doReturn(null).when(webElement).getAttribute("cols");
        int columnCount = cut.getColumnCount();
        assertThat(columnCount, is(-1));
    }

    /* row count */

    @Test
    public void testThatRowCountIsRetrievedFromTheCorrectAttribute() {
        doReturn("42").when(webElement).getAttribute("rows");
        int rowCount = cut.getRowCount();
        assertThat(rowCount, is(42));
    }

    @Test
    public void testThatMinusOneIsReturnedIfNoRowsValueIsAvailable() {
        doReturn(null).when(webElement).getAttribute("rows");
        int rowCount = cut.getRowCount();
        assertThat(rowCount, is(-1));
    }

    /* correctness of class */

    @Test
    public void testCorrectnessOfClassForWebElement_textAreaTag() {
        stubWebElementTag("textarea");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_nonTextAreTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

}
