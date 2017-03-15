package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Function;

import info.novatec.testit.webtester.api.browser.Browser;


/**
 * Factory class for creating Chrome {@link Browser} instances.
 * Needs the {@code webdriver.chrome.driver} system property pointing to the driver proxy server executable.
 * <p>
 * <b>The following capabilities are set by default:</b>
 * <ul>
 * <li>Native Events are disabled</li>
 * <li>Unsigned certificates are accepted</li>
 * </ul>
 * <b>Additional information on using the {@link ChromeDriver}:</b>
 * <p>
 * https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver
 *
 * @see Browser
 * @see ChromeDriver
 * @since 1.2
 */
public class ChromeFactory extends BaseBrowserFactory<ChromeFactory> {

    public ChromeFactory() {
        super(new Function<DesiredCapabilities, WebDriver>() {
            @Override
            public WebDriver apply(DesiredCapabilities capabilities) {
                return new ChromeDriver(capabilities);
            }
        });
    }

}
