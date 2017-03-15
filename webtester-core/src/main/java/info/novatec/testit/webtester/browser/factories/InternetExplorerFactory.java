package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Function;

import info.novatec.testit.webtester.api.browser.Browser;


/**
 * Factory class for creating Internet Explorer {@link Browser} instances.
 * Needs the {@code webdriver.ie.driver} system property pointing to the driver proxy server executable.
 * <p>
 * <b>The following capabilities are set by default:</b>
 * <ul>
 * <li>Native Events are disabled</li>
 * <li>Unsigned certificates are accepted</li>
 * </ul>
 * <b>Additional information on using the {@link InternetExplorerDriver}:</b>
 * <p>
 * https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver
 *
 * @see Browser
 * @see InternetExplorerDriver
 * @since 1.2
 */
public class InternetExplorerFactory extends BaseBrowserFactory<InternetExplorerFactory> {

    public InternetExplorerFactory() {
        super(new Function<DesiredCapabilities, WebDriver>() {
            @Override
            public WebDriver apply(DesiredCapabilities capabilities) {
                return new InternetExplorerDriver(capabilities);
            }
        });
    }

    public Browser createBrowser(int port) {
        InternetExplorerDriverService service = InternetExplorerDriverService.createDefaultService();
        DesiredCapabilities capabilities = getDefaultCapabilities();
        return createBrowser(new InternetExplorerDriver(service, capabilities, port));
    }

}
