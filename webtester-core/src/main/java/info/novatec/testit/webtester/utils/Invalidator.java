package info.novatec.testit.webtester.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.internal.pageobjects.LazyLoadingPageObjectList;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Utility class providing different methods to invalidate {@link PageObject
 * page objects}.
 *
 * @since 0.9.3
 * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
 */
@Deprecated
public final class Invalidator {

    // TODO: delete for v1.3

    private static final Logger LOGGER = LoggerFactory.getLogger(Invalidator.class);

    private Invalidator() {
        // utility constructor
    }

    /**
     * Invalidates a list of {@link PageObject page objects}.
     *
     * @param <T> the type of the page object list to invalidate
     * @param pageObjectList the list to invalidate
     * @return the same page object list for fluent API use
     * @since 1.1.0
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    public static <T extends PageObject> List<T> invalidate(List<T> pageObjectList) {
        LOGGER.warn("deprecated method 'invalidate(List<T> pageObjectList)' used...");
        return pageObjectList;
    }

    /**
     * Invalidates all sub elements of the given {@link PageObject page object}.
     * This is done by traversing the class hierarchy of the given page object
     * and calling {@link PageObject#invalidate() invalidate()} on each page
     * object or {@link LazyLoadingPageObjectList page object list} instance.
     *
     * @param <T> the type of the page object to invalidate
     * @param pageObject the page object whose sub elements should be invalidated
     * @return the same page object instance for fluent API use
     * @since 1.1.0
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    public static <T extends PageObject> T invalidate(T pageObject) {
        LOGGER.warn("deprecated method 'invalidate(T pageObject)' used...");
        return pageObject;
    }

}
