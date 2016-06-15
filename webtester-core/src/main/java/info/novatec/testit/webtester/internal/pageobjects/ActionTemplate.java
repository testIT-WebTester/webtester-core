package info.novatec.testit.webtester.internal.pageobjects;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallback;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.exceptions.PageObjectIsInvisibleException;
import info.novatec.testit.webtester.eventsystem.EventSystem;
import info.novatec.testit.webtester.eventsystem.events.browser.ExceptionEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This class is used to execute 'actions' of {@link PageObject page objects} and handle some error cases by default.
 * <p>
 * An action can be as simple as executing a click on a button or more complex like selecting a value of drop down menu.
 * In general these actions are executed on {@link org.openqa.selenium.WebElement web elements}. This executions throw
 * certain exceptions in special cases (like {@link StaleElementReferenceException}. In order to handle these exceptional
 * cases in a default way this template was created.
 * <p>
 * It offers two distinct methods:
 * <ol>
 * <li>{@link #executeAction(PageObjectCallback)}</li>
 * <li>{@link #executeAction(PageObjectCallbackWithReturnValue)}</li>
 * </ol>
 *
 * @since 1.1.0
 */
@Internal
@SuppressWarnings("PMD.AvoidCatchingGenericException")
public class ActionTemplate {

    private PageObject pageObject;

    public ActionTemplate(PageObject pageObject) {
        this.pageObject = pageObject;
    }

    /**
     * Execute the given callback and handle exceptional cases.
     * <ul>
     * <li>{@link StaleElementReferenceException}
     * - try to recover by invalidating the page object and retrying the same operation</li>
     * <li>{@link ElementNotVisibleException}
     * - throw new {@link PageObjectIsInvisibleException} as well as fire exception event</li>
     * <li>{@link RuntimeException}
     * - fire exception event and rethrow the original exception</li>
     * </ul>
     *
     * @param callback the callback to execute
     * @throws PageObjectIsInvisibleException in case the subject of the callback is invisible
     * @since 1.1.0
     */
    public void executeAction(PageObjectCallback callback) {
        try {
            callback.execute(pageObject);
        } catch (ElementNotVisibleException e) {
            throw fireExceptionEventAndReturn(new PageObjectIsInvisibleException(pageObject, e));
        } catch (RuntimeException e) {
            throw fireExceptionEventAndReturn(e);
        }
    }

    /**
     * Execute the given callback, returns the callbacks return value and handle exceptional cases.
     * <ul>
     * <li>{@link StaleElementReferenceException}
     * - try to recover by invalidating the page object and retrying the same operation</li>
     * <li>{@link ElementNotVisibleException}
     * - throw new {@link PageObjectIsInvisibleException} as well as fire exception event</li>
     * <li>{@link RuntimeException}
     * - fire exception event and rethrow the original exception</li>
     * </ul>
     *
     * @param callback the callback to execute
     * @return the return value of the callback
     * @throws PageObjectIsInvisibleException in case the subject of the callback is invisible
     * @since 1.1.0
     */
    public <B> B executeAction(PageObjectCallbackWithReturnValue<B> callback) {
        B value;
        try {
            value = callback.execute(pageObject);
        } catch (ElementNotVisibleException e) {
            throw fireExceptionEventAndReturn(new PageObjectIsInvisibleException(pageObject, e));
        } catch (RuntimeException e) {
            throw fireExceptionEventAndReturn(e);
        }
        return value;
    }

    private <T extends RuntimeException> T fireExceptionEventAndReturn(T exception) {
        EventSystem.fireEvent(new ExceptionEvent(pageObject, exception));
        return exception;
    }

}
