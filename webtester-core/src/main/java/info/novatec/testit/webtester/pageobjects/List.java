package info.novatec.testit.webtester.pageobjects;

import java.util.Collections;

import info.novatec.testit.webtester.api.annotations.IdentifyUsing;
import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.annotations.Mappings;
import info.novatec.testit.webtester.api.callbacks.PageObjectCallbackWithReturnValue;
import info.novatec.testit.webtester.api.enumerations.Method;


/**
 * Represents a HTML list. The following HTML elements are supported:
 * <ul>
 * <li><b>tag:</b> ul</li>
 * <li><b>tag:</b> ol</li>
 * </ul>
 *
 * @since 0.9.0
 */
@Mappings({ @Mapping(tag = "ul"), @Mapping(tag = "ol") })
public class List extends PageObject {

    @IdentifyUsing(method = Method.XPATH, value = "./li")
    private java.util.List<ListItem> listItems;

    /**
     * Returns the number of {@link ListItem list items} of this {@link List
     * list}.
     *
     * @return the number of items in this list
     * @since 0.9.6
     */
    public Integer getNumberOfItems() {
        return listItems.size();
    }

    /**
     * Returns all {@link ListItem list items} of this {@link List list} as an
     * unmodifiable list (collection).
     *
     * @return list of all direct list items of this list
     * @since 0.9.6
     */
    public java.util.List<ListItem> getItems() {
        return Collections.unmodifiableList(listItems);
    }

    /**
     * Returns the {@link ListItem list item} for the given index.
     *
     * @param index of the list item to return
     * @return the list item
     * @throws IndexOutOfBoundsException if given index does not exist
     * @since 0.9.6
     */
    public ListItem getItem(final int index) {
        return executeAction(new PageObjectCallbackWithReturnValue<ListItem>() {

            @Override
            public ListItem execute(PageObject pageObject) {
                return listItems.get(index);
            }

        });
    }

    /**
     * Returns whether or not the {@link List list} is empty (does not contain
     * any {@link ListItem list items}).
     *
     * @return true if empty otherwise false
     * @since 0.9.6
     */
    public Boolean isEmpty() {
        return listItems.isEmpty();
    }

}
