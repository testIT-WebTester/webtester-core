package info.novatec.testit.webtester.internal.validation;

import java.util.Set;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.pageobjects.Validator;


/**
 * This {@link Validator} extends the {@link TagValidator} with a check on the value of a certain property.
 * <p>
 * <b>Example:</b> <code>@Mapping(tag="input", attribute="type", values={"", text})</code>
 */
@Internal
public class TagAttributeValueValidator extends TagValidator {

    private final String attributeName;
    private final Set<String> validValues;

    public TagAttributeValueValidator(String tagName, String attributeName, String[] values) {
        super(tagName);
        this.attributeName = attributeName;
        this.validValues = ValidationUtils.toNormalizedSet(values);
    }

    @Override
    public boolean isValid(WebElement webElement) {
        String attributeValue = webElement.getAttribute(attributeName);
        String normalizedAttributeValue = ValidationUtils.normalize(attributeValue);
        // needed for text fields, since they are the default for input without type
        boolean attributeValueNonNullOrEmptyAllowed = attributeValue != null || validValues.contains("");
        return super.isValid(webElement) && attributeValueNonNullOrEmptyAllowed && validValues.contains(
            normalizedAttributeValue);
    }

    @Override
    public String describe() {
        return super.describe() + " and an attribute '" + attributeName + "' with any value of " + validValues;
    }

}
