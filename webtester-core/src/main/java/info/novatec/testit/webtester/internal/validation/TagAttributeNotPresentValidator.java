package info.novatec.testit.webtester.internal.validation;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.pageobjects.Validator;


/**
 * This {@link Validator} extends the {@link TagValidator} with a check on the NON-existence of a certain property.
 * The property's value is not examined in any way.
 * <p>
 * <b>Example:</b> <code>@Mapping(tag="select", attribute="!multiple")</code>
 */
@Internal
public class TagAttributeNotPresentValidator extends TagValidator {

    private final String attributeName;

    public TagAttributeNotPresentValidator(String tagName, String attributeName) {
        super(tagName);
        this.attributeName = attributeName;
    }

    @Override
    public boolean isValid(WebElement webElement) {
        String attributeValue = webElement.getAttribute(attributeName);
        return super.isValid(webElement) && attributeValue == null;
    }

    @Override
    public String describe() {
        return super.describe() + " and an attribute '" + attributeName + "' not being present";
    }

}
