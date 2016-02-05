package info.novatec.testit.webtester.api.pageobjects;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Validators are used to validate {@link WebElement web elements} for {@link PageObject page objects}. They 
 * provide a way to implement more complex validation logic then the {@link Mapping @Mapping} annotation con provide on it's own.
 *
 * @since 1.2.0
 */
public interface Validator {

    /**
     * Validates if the given {@link WebElement web element} is valid for being used as a specific {@link PageObject page
     * object}.
     *
     * @param webElement the web element to validate
     * @return true if the web element is valid, otherwise false
     * @since 1.2.0
     */
    boolean isValid(WebElement webElement);

    /**
     * Returns a textual description of this validator. It is used in case all validators of a page object fail to describe
     * what valida the constellations are.
     *
     * @return a textual description of this validator
     * @since 1.2.0
     */
    String describe();

}
