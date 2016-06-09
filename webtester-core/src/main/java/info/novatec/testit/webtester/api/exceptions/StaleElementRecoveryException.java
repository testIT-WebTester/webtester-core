package info.novatec.testit.webtester.api.exceptions;

import info.novatec.testit.webtester.pageobjects.PageObject;


/**
 * This exception is thrown when a {@link PageObject} loses its reference and
 * the recovery mechanism failed to find it again.
 *
 * @since 0.9.0
 * @deprecated caching was removed in v1.2 - this exists in order to NOT break the API till v1.3
 */
@SuppressWarnings("serial")
@Deprecated
public class StaleElementRecoveryException extends WebTesterException {

    // TODO: delete for v1.3

    public StaleElementRecoveryException(Throwable e) {
        super("Recovery of stale element failed!", e);
    }

}
