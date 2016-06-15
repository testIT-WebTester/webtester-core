package info.novatec.testit.webtester.browser.operations;

import org.openqa.selenium.JavascriptExecutor;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This browser operation offers methods related to the execution of JavaScript code.
 *
 * @see #execute(String, Object...)
 * @see #execute(String, PageObject, Object...)
 * @since 1.2
 */
public class JavaScriptExecutor extends BaseBrowserOperation {

    /**
     * Creates a new {@link JavaScriptExecutor} for the given {@link Browser}.
     *
     * @param browser the browser to use
     * @since 1.2
     */
    public JavaScriptExecutor(Browser browser) {
        super(browser);
    }

    /**
     * Executes the given JavaScript code for the given {@link PageObject}
     * (available in script as arguments[0]) with the given parameters
     * (accessible as arguments[1] - arguments[n]).
     *
     * @param script the JavaScript code to be executed on the current page
     * @param pageObject the target {@link PageObject}
     * @param parameters any of Boolean, Long, String, List, WebElement or null.
     * @see JavascriptExecutor#executeScript(String, Object...)
     * @since 1.2
     */
    public void execute(String script, PageObject pageObject, Object... parameters) {
        executeWithReturn(script, pageObject, parameters);
    }

    /**
     * Executes the given JavaScript code for the given {@link PageObject} (available in script as arguments[0])
     * with the given parameters (accessible as arguments[1] - arguments[n]).
     * <p>
     * The JavaScripts return value is returned as described in {@link JavascriptExecutor#executeScript(String, Object...)}.
     *
     * @param script the JavaScript code to be executed on the current page
     * @param pageObject the target {@link PageObject}
     * @param parameters any of Boolean, Long, String, List, WebElement or null.
     * @return the return value of the JavaScript
     * @see JavascriptExecutor#executeScript(String, Object...)
     * @since 1.2
     */
    public <T> T executeWithReturn(String script, PageObject pageObject, Object... parameters) {
        Object[] parameterArray = new Object[parameters.length + 1];
        parameterArray[0] = pageObject.getWebElement();
        System.arraycopy(parameters, 0, parameterArray, 1, parameters.length);
        return executeWithReturn(script, parameterArray);
    }

    /**
     * Executes the given JavaScript code with the given parameters (accessible as arguments[0] - arguments[n]).
     *
     * @param script the JavaScript code to be executed on the current page
     * @param parameters any of Boolean, Long, String, List, WebElement or null.
     * @see JavascriptExecutor#executeScript(String, Object...)
     * @since 1.2
     */
    public void execute(String script, Object... parameters) {
        executeWithReturn(script, parameters);
    }

    /**
     * Executes the given JavaScript code with the given parameters (accessible as arguments[0] - arguments[n]).
     * <p>
     * The JavaScripts return value is returned as described in {@link JavascriptExecutor#executeScript(String, Object...)}.
     *
     * @param script the JavaScript code to be executed on the current page
     * @param parameters any of Boolean, Long, String, List, WebElement or null.
     * @return the return value of the JavaScript
     * @see JavascriptExecutor#executeScript(String, Object...)
     * @since 1.2
     */
    @SuppressWarnings("unchecked")
    public <T> T executeWithReturn(String script, Object... parameters) {
        if (!(webDriver() instanceof JavascriptExecutor)) {
            throw new UnsupportedOperationException("WebDriver does not support JavaScript execution!");
        }
        JavascriptExecutor javascriptExecutor = ( JavascriptExecutor ) webDriver();
        return (T) javascriptExecutor.executeScript(script, parameters);
    }

}
