package info.novatec.testit.webtester.internal.validation;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;

import info.novatec.testit.webtester.api.annotations.Internal;
import info.novatec.testit.webtester.api.annotations.Mapping;
import info.novatec.testit.webtester.api.annotations.Mappings;
import info.novatec.testit.webtester.api.exceptions.WrongElementClassException;
import info.novatec.testit.webtester.api.pageobjects.Validator;


/**
 * Allows for the validation of {@link WebElement web elements} for a {@link info.novatec.testit.webtester.pageobjects.PageObject
 * page object} instance by the {@link Mapping} annotations of it's class.
 *
 * @since 1.2.0
 */
@Internal
public class MappingValidator {

    private final Class<?> type;
    private final List<Validator> validConstellations;
    private final List<String> validConstellationDescriptions;

    public MappingValidator(Class<?> type) {
        this.type = type;
        this.validConstellations = extractValidationInformation(type);
        this.validConstellationDescriptions = getValidConstellationDescriptions();
    }

    private static List<Validator> extractValidationInformation(Class<?> type) {

        List<Mapping> mappings = new LinkedList<>();

        if (type.isAnnotationPresent(Mapping.class)) {
            mappings.add(type.getAnnotation(Mapping.class));
        }

        if (type.isAnnotationPresent(Mappings.class)) {
            for (Mapping mapping : type.getAnnotation(Mappings.class).value()) {
                mappings.add(mapping);
            }
        }

        List<Validator> validConstellations = new LinkedList<>();
        for (Mapping mapping : mappings) {
            validConstellations.add(convertToValidConstellation(mapping));
        }
        return validConstellations;

    }

    private static Validator convertToValidConstellation(Mapping mapping) {
        String tag = mapping.tag();
        String attribute = mapping.attribute();
        String[] values = mapping.values();
        if (mapping.validator() != NoOpValidator.class) {
            try {
                return mapping.validator().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new UndeclaredThrowableException(e);
            }
        } else if (StringUtils.isNotBlank(attribute)) {
            if (values.length > 0) {
                return new TagAttributeValueValidator(tag, attribute, values);
            } else if (attribute.charAt(0) == '!') {
                String attributeName = attribute.substring(1);
                return new TagAttributeNotPresentValidator(tag, attributeName);
            } else {
                return new TagAttributePresentValidator(tag, attribute);
            }
        } else {
            return new TagValidator(tag);
        }
    }

    private List<String> getValidConstellationDescriptions() {
        List<String> descriptions = new LinkedList<>();
        for (Validator constellation : validConstellations) {
            descriptions.add(constellation.describe());
        }
        return descriptions;
    }

    public boolean canValidate() {
        return !validConstellations.isEmpty();
    }

    public void assertValidity(WebElement webElement) {
        if (validConstellations.isEmpty()) {
            return;
        }
        boolean valid = false;
        for (Validator constellation : validConstellations) {
            if (constellation.isValid(webElement)) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new WrongElementClassException(getInvalidityMessage(webElement));
        }
    }

    private String getInvalidityMessage(WebElement webElement) {
        return webElement + " is not a valid web element for class: " + type + "\n\tValid elements include: \n\t - "
            + StringUtils.join(validConstellationDescriptions, "\n\t - ");
    }

}
