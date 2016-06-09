package info.novatec.testit.webtester.api.pageobjects;

import java.util.List;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Classes implementing this {@link List} extension allow for dynamic filtering
 * of many {@link PageObject page objects}.
 *
 * @param <T> the type of the contained page objects
 */
public interface PageObjectList<T extends PageObject> extends List<T> {

    /**
     * Creates a new {@link PageObjectList page object list} based on this lists
     * current content after applying the given filter condition. The new list
     * will not be linked to the original one and changes to the original list
     * will not be reflected in the new list!
     *
     * @param condition the {@link Predicate predicate} to use as a filter
     * @return a new page object list
     */
    PageObjectList<T> filter(Predicate<? super T> condition);

    /**
     * Invalidate the list - effectively resetting its caches.
     *
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    void invalidate(); // TODO: remove in v1.3

}
