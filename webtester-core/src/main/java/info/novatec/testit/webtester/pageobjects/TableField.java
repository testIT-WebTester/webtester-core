package info.novatec.testit.webtester.pageobjects;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.annotations.Mappings;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;


/**
 * Represents the field of a HTML table. The following HTML elements are
 * supported:
 * <ul>
 * <li><b>tag:</b> th</li>
 * <li><b>tag:</b> td</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Mappings({ @Mapping(tag = "th"), @Mapping(tag = "td") })
public class TableField extends PageObject {

    /**
     * Returns whether or not this {@link TableField field} is is a header field
     * (TH).
     *
     * @return true if this is a header field, otherwise false
     * @since 0.9.0
     */
    public boolean isHeaderField() {
        return executeAction(new PageObjectCallbackWithReturnValue<Boolean>() {

            @Override
            public Boolean execute(PageObject pageObject) {
                return "th".equalsIgnoreCase(getWebElement().getTagName());
            }

        });
    }

}
