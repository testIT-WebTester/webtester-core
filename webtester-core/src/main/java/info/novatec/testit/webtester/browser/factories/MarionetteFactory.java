package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Function;

import info.novatec.testit.webtester.api.browser.Browser;


/**
 * Factory class for creating Firefox {@link Browser} instances.
 * This will only work for Firefox browsers version 47 and above!
 * All older versions should be initialized using the {@link FirefoxFactory}.
 * <p>
 * <b>The following capabilities are set by default:</b>
 * <ul>
 * <li>Native Events are disabled</li>
 * <li>Unsigned certificates are accepted</li>
 * </ul>
 * <b>Additional information on using the {@link FirefoxDriver}:</b>
 * <p>
 * https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver
 *
 * @see Browser
 * @see FirefoxDriver
 * @see FirefoxFactory
 * @since 1.2
 */
public class MarionetteFactory extends BaseBrowserFactory<MarionetteFactory> {

    public MarionetteFactory() {
        super(new Function<DesiredCapabilities, WebDriver>() {
            @Override
            public WebDriver apply(DesiredCapabilities capabilities) {
                capabilities.setCapability("marionette", true);
                return new FirefoxDriver(capabilities);
            }
        });
    }

}
