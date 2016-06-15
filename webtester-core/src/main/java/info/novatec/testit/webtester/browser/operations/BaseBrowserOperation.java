package info.novatec.testit.webtester.browser.operations;

import org.openqa.selenium.WebDriver;

import info.novatec.testit.webtester.api.browser.Browser;
import info.novatec.testit.webtester.api.config.Configuration;


public class BaseBrowserOperation {

    private final Browser browser;

    protected BaseBrowserOperation(Browser browser) {
        this.browser = browser;
    }

    protected final Browser browser() {
        return browser;
    }

    protected final Configuration configuration() {
        return browser.getConfiguration();
    }

    protected final WebDriver webDriver() {
        return browser.getWebDriver();
    }

}
