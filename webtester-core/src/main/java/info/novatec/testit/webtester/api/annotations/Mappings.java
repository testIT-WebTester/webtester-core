package info.novatec.testit.webtester.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This meta annotation can be used to annotate a {@link info.novatec.testit.webtester.pageobjects.PageObject} with multiple
 * {@link Mapping} annotations.
 *
 * @see Mapping
 * @since 1.2.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mappings {

    /**
     * @return a number of grouped {@link Mapping} annotations
     * @since 1.2.0
     */
    Mapping[] value();

}
