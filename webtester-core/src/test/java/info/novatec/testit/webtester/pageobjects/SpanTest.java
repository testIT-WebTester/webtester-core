package info.novatec.testit.webtester.pageobjects;

import org.junit.Test;
import org.mockito.InjectMocks;

import info.novatec.testit.webtester.AbstractPageObjectTest;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;


public class SpanTest extends AbstractPageObjectTest {

    @InjectMocks
    Span cut;

    @Test
    public void testCorrectnessOfClassForWebElement_spanTag() {
        stubWebElementTag("span");
        cut.validate(webElement);
    }

    @Test(expected = WrongElementClassException.class)
    public void testCorrectnessOfClassForWebElement_otherTag() {
        stubWebElementTag("other");
        cut.validate(webElement);
    }

}
