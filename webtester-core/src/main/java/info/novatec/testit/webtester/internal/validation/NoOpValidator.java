package info.novatec.testit.webtester.internal.validation;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.pageobjects.Validator;


/**
 * This is a {@link Validator} implementation that basically does nothing. It will return <code>true</code> for each call on
 * {@link #isValid(WebElement)}. The main reason this class exists is to use it as a default in the {@link Mapping}
 * annotation. In case this class is set on the annotation (default), the {@link MappingValidator} will use the annotation's
 * properties to generate a validator instead of using this one.
 */
@Internal
public class NoOpValidator implements Validator {

    @Override
    public boolean isValid(WebElement webElement) {
        return true;
    }

    @Override
    public String describe() {
        return "NO-OP";
    }

}
