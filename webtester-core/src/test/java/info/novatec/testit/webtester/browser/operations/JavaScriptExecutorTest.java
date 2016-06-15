package info.novatec.testit.webtester.browser.operations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;


@RunWith(MockitoJUnitRunner.class)
public class JavaScriptExecutorTest {

    static final String JAVA_SCRIPT = "alert('Hello World!')";

    @Test
    public void javaScriptIsExecutedIfWebDriverSupportsIt() {
        WebDriver webDriver = javaScriptExecutingWebDriver();
        javaScriptFor(webDriver).execute(JAVA_SCRIPT);
        verify(( JavascriptExecutor ) webDriver).executeScript(JAVA_SCRIPT);
    }

    @Test
    public void justParametersWithoutReturn() {
        WebDriver webDriver = javaScriptExecutingWebDriver();
        javaScriptFor(webDriver).execute(JAVA_SCRIPT, "param1", "param2");
        verify(( JavascriptExecutor ) webDriver).executeScript(JAVA_SCRIPT, "param1", "param2");
    }

    @Test
    public void justParametersWithReturn() {
        WebDriver webDriver = javaScriptExecutingWebDriver();
        doReturn("returnValue").when(( JavascriptExecutor ) webDriver).executeScript(JAVA_SCRIPT, "param");
        String returnValue = javaScriptFor(webDriver).executeWithReturn(JAVA_SCRIPT, "param");
        assertThat(returnValue, is("returnValue"));
    }

    @Test
    public void pageFragmentAndParametersWithoutReturn() {

        WebDriver webDriver = javaScriptExecutingWebDriver();
        PageObject pageObject = pageObject();

        javaScriptFor(webDriver).execute(JAVA_SCRIPT, pageObject, "param1", "param2");

        WebElement webElement = pageObject.getWebElement();
        verify(( JavascriptExecutor ) webDriver).executeScript(JAVA_SCRIPT, webElement, "param1", "param2");

    }

    @Test
    public void pageFragmentAndParametersWithReturn() {

        WebDriver webDriver = javaScriptExecutingWebDriver();
        PageObject pageObject = pageObject();

        WebElement webElement = pageObject.getWebElement();
        doReturn("returnValue").when(( JavascriptExecutor ) webDriver).executeScript(JAVA_SCRIPT, webElement, "param");

        String returnValue = javaScriptFor(webDriver).executeWithReturn(JAVA_SCRIPT, pageObject, "param");
        assertThat(returnValue, is("returnValue"));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void executingJavaScriptThrowsExceptionIfBrowserDoesNotSupportIt() {
        WebDriver webDriver = nonJavaScriptExecutingWebDriver();
        javaScriptFor(webDriver).execute(JAVA_SCRIPT);
    }

    /* utilities */

    JavaScriptExecutor javaScriptFor(WebDriver webDriver) {
        return new JavaScriptExecutor(browserFor(webDriver));
    }

    Browser browserFor(WebDriver webDriver) {
        Browser browser = mock(Browser.class);
        doReturn(webDriver).when(browser).getWebDriver();
        return browser;
    }

    WebDriver javaScriptExecutingWebDriver() {
        return mock(WebDriver.class, withSettings().extraInterfaces(JavascriptExecutor.class));
    }

    WebDriver nonJavaScriptExecutingWebDriver() {
        return mock(WebDriver.class);
    }

    PageObject pageObject() {
        PageObject pageObject = mock(PageObject.class);
        WebElement webElement = mock(WebElement.class);
        doReturn(webElement).when(pageObject).getWebElement();
        return pageObject;
    }

}
