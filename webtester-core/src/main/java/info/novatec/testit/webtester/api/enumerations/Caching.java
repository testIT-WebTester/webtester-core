package info.novatec.testit.webtester.api.enumerations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.novatec.testit.webtester.api.config.Configuration;
import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * Enumeration of modes that can be used to configure a {@link PageObject page
 * object's} caching behavior.
 *
 * @since 0.9.9
 * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
 */
@Deprecated
public enum Caching {

    // TODO: delete for v1.3

    /**
     * Use caching behavior as set in the {@link Configuration}.
     *
     * @since 0.9.9
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    DEFAULT,

    /**
     * Explicitly activate caching for the {@link PageObject}, ignoring whatever
     * is set in the {@link Configuration}.
     *
     * @since 0.9.9
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    ON,

    /**
     * Explicitly deactivate caching for the {@link PageObject}, ignoring
     * whatever is set in the {@link Configuration}.
     *
     * @since 0.9.9
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    OFF;

    private static final Logger LOGGER = LoggerFactory.getLogger(Caching.class);

    /**
     * Calculates whether or not caching should be activated by checking the
     * given {@link Configuration} as well as the given {@link Caching} setting.
     *
     * @param configuration the configuration to use in order to get the global
     * caching setting
     * @param caching a caching instance to consider in the decision
     * @return true if caching should be active otherwise false
     * @since 0.9.9
     * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
     */
    @Deprecated
    public static boolean shouldCache(Configuration configuration, Caching caching) {
        LOGGER.warn("deprecated method 'shouldCache(Configuration configuration, Caching caching)' used...");
        return false;
    }

}
