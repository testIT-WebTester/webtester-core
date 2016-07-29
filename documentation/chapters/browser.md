[Home](../README.md)

# Browser

## Interfaces

### Browser
A `Browser` provides a streamlined and context centric API for the interaction with a web browser.

### BrowserBuilder
A `BrowserBuilder` provides the framework for initializing `Browser` instances and setting custom service implementations.
Customizable services are:

- [`Configuration`](configuration.md)
- [`PageObjectFactory`](page-object-factory.md)

### BrowserFactory
A `BrowserFactory` creates an abstraction over the `Browser` initialisation based on project-global settings.
They are intended to allow easy browser initialization and encapsulation of the underlying configuration / initialization
processes.

**Provided default BrowserFactory implementations:**

- [`ChromeFactory`](support-chrome.md)
- [`FirefoxFactory`](support-firefox.md)
- [`MarionetteFactory`](support-marionette.md)
- [`InternetExplorerFactory`](support-ie.md)

Each of them comes in their own support module. The Firefox's factory implementation is trimmed for optimal performance.

### ProxyConfiguration
In order to configure a proxy you can either configure it manually when initializing the `WebDriver` or
you can implement a `ProxyConfiguration` and provide it to the `BrowserFactory` before creating a `Browser` instance.

```java
ProxyConfiguration pc = createProxyConfiguration();
Browser browser = new FirefoxFactory().withProxyConfiguration(pc).createBrowser();
```

## The WebDriver Browser
The `WebDriverBrowser` class implements `Browser` and is used to wrap a Selenium `WebDriver`.
Instances can be created by using the `WebDriverBrowser's` factory methods:

1. `WebDriverBrowser.forWebDriver(webDriver).build();`
2. `WebDriverBrowser.buildForWebDriver(webDriver);`

Both of these are equal. The first method can be used to customize same aspects of the browser before it is build.

## Examples
**Initialization of a new WebDriverBrowser instance:**
```java
WebDriver webDriver = createWebDriver();
Browser browser = WebDriverBrowser.buildForWebDriver(webDriver);
Browser browser = WebDriverBrowser.forWebDriver(webDriver).build();
Browser browser = new WebDriverBrowserBuilder(webDriver).build();
```
**Initialization of a new WebDriverBrowser instance with custom service implementations:**
```java
Configuration config = createConfiguration();
PageObjectFactory factory = createFactory();

Browser browser = WebDriverBrowser.forWebDriver(webDriver)
                        .withConfiguration(config)
                        .withFactory(factory)
                        .build();

Browser browser = new WebDriverBrowserBuilder(webDriver)
                        .withConfiguration(config)
                        .withFactory(factory)
                        .build();
```

# Linked Documentation

- [Configuration](configuration.md)
- [Page Object Factory](page-object-factory.md)
- [Chrome Support](support-chrome.md)
- [Firefox Support](support-firefox.md)
- [Internet Explorer Support](support-ie.md)
