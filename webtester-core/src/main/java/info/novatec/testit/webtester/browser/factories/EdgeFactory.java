package info.novatec.testit.webtester.browser.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Function;

import info.novatec.testit.webtester.api.browser.Browser;


/**
 * Factory class for creating Microsoft Edge {@link Browser} instances.
 * Needs the {@code webdriver.edge.driver} system property pointing to the driver proxy server executable.
 * <p>
 * <b>The following capabilities are set by default:</b>
 * <ul>
 * <li>Native Events are disabled</li>
 * <li>Unsigned certificates are accepted</li>
 * </ul>
 *
 * @see Browser
 * @see EdgeDriver
 * @since 1.2
 */
public class EdgeFactory extends BaseBrowserFactory<EdgeFactory> {

    public EdgeFactory() {
        super(new Function<DesiredCapabilities, WebDriver>() {
            @Override
            public WebDriver apply(DesiredCapabilities capabilities) {
                return new EdgeDriver(capabilities);
            }
        });
    }

    public Browser createBrowser() {
        EdgeDriverService service = EdgeDriverService.createDefaultService();
        DesiredCapabilities capabilities = getDefaultCapabilities();
        return createBrowser(new EdgeDriver(service, capabilities));
    }

}
