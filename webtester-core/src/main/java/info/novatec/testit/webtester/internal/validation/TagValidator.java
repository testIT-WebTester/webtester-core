package info.novatec.testit.webtester.internal.validation;

import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.pageobjects.Validator;


/**
 * This {@link Validator} checks the tag name of a {@link WebElement}.
 * <p>
 * <b>Example:</b> <code>@Mapping(tag="div")</code>
 */
@Internal
public class TagValidator implements Validator {

    private final String tagName;

    public TagValidator(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean isValid(WebElement webElement) {
        String elementTagName = webElement.getTagName();
        return tagName.equalsIgnoreCase(elementTagName);
    }

    @Override
    public String describe() {
        return "Element having '" + tagName + "' as it's tag";
    }

}
