package integration.browser;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import integration.AbstractWebTesterIntegrationTest;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pageobjects.Button;
import info.novatec.testit.webtester.pageobjects.PageObject;


public class ScrollIntoViewIntegrationTest extends AbstractWebTesterIntegrationTest {

    @Override
    protected String getHTMLFilePath() {
        return "html/browser/scroll-into-view.html";
    }

    @Test
    public void scrollIntoView() {
        Button outOfView = getBrowser().create(TestPage.class).outOfView;
        assertThat(elementIsInView(), Matchers.is(false));
        getBrowser().scrollTo(outOfView);
        assertThat(elementIsInView(), Matchers.is(true));
    }

    Boolean elementIsInView() {
        return getBrowser().javaScript().executeWithReturn("return isScrolledIntoView()");
    }

    private static class TestPage extends PageObject {

        @IdentifyUsing("outOfView")
        Button outOfView;

    }

}
