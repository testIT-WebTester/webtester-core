package info.novatec.testit.webtester.utils.conditions;

import com.google.common.base.Predicate;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Predicate to be used in order to check if a {@link PageObject page object} is
 * present. This is determined by checking if the page object's web element can
 * be found.
 * <p>
 * <b>Note:</b> This will reset the page object's cache in order to guarantee
 * the presence of the web element!
 *
 * @since 0.9.9
 */
public class Present implements Predicate<PageObject> {

    @Override
    public boolean apply(PageObject pageObject) {
        return pageObject.isPresent();
    }

    @Override
    public String toString() {
        return "present";
    }

}
