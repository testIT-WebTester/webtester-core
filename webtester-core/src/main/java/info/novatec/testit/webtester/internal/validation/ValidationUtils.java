package info.novatec.testit.webtester.internal.validation;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import info.novatec.testit.webtester.api.annotations.Internal;


@Internal
public final class ValidationUtils {

    private ValidationUtils() {
        // utility class constructor
    }

    public static Set<String> toNormalizedSet(String... values) {
        Set<String> set = new HashSet<>();
        for (String value : values) {
            set.add(normalize(value));
        }
        return set;
    }

    public static String normalize(String value) {
        return StringUtils.defaultString(value).toLowerCase();
    }

}
