package info.novatec.testit.webtester.internal.pageobjects;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.api.pageobjects.PageObjectList;
import info.novatec.testit.webtester.pageobjects.PageObject;


@SuppressWarnings("serial")
public class DefaultPageObjectList<E extends PageObject> extends LinkedList<E> implements PageObjectList<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPageObjectList.class);

    @Override
    public PageObjectList<E> filter(Predicate<? super E> condition) {
        DefaultPageObjectList<E> filtered = new DefaultPageObjectList<E>();
        for (E pageObject : this) {
            if (condition.apply(pageObject)) {
                filtered.add(pageObject);
            }
        }
        return filtered;
    }

    /**
     * Invalidate the list - effectively resetting its caches.
     *
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Override
    @Deprecated
    public void invalidate() {
        // TODO: remove in v1.3
        LOGGER.warn("deprecated method 'invalidate()' used...");
    }

}
