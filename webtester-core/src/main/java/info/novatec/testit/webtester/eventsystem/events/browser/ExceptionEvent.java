package info.novatec.testit.webtester.eventsystem.events.browser;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.eventsystem.events.AbstractEvent;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever an exception from inside the
 * framework occurred. It includes the exception as a property.
 *
 * @since 0.9.0
 */
@SuppressWarnings("serial")
public class ExceptionEvent extends AbstractEvent {

    private Throwable exception;

    public ExceptionEvent(PageObject pageObject, Throwable exception) {
        this(pageObject.getBrowser(), exception);
    }

    public ExceptionEvent(Browser browser, Throwable exception) {
        super(browser.getIdentification());
        this.exception = exception;
    }

    @Override
    public String getEventMessage() {
        return "an exception occurred: " + exception;
    }

    @Override
    public String getSubjectName() {
        return exception != null ? exception.getClass().getSimpleName() : "unknown exception";
    }

    public Throwable getException() {
        return exception;
    }

}
