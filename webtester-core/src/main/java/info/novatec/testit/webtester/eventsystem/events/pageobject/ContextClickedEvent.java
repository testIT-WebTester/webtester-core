package info.novatec.testit.webtester.eventsystem.events.pageobject;

import static java.lang.String.format;

import info.novatec.testit.webtester.api.events.Event;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This {@link Event event} occurs whenever a context click is executed on an
 * element.
 *
 * @since 1.1.0
 */
@SuppressWarnings("serial")
public class ContextClickedEvent extends AbstractPageObjectEvent {

    public ContextClickedEvent(PageObject pageObject) {
        super(pageObject);
    }

    @Override
    public String getEventMessage() {
        return format("context clicked on %s", getSubjectName());
    }

}
