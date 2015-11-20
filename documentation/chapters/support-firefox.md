[Home](../README.md)

# Firefox Browser Factory
The module `webtester-support-firefox` provides a default `BrowserFactory` implementation called `FirefoxFactory`.

## Default Driver Configuration
In order to optimize testing the following properties are set when creating a `WebDriver` using the `FirefoxFactory`:

- "Native Events" are disabled -> Selenium does not simulate human typing.
- Untrusted certificates are always trusted.

**For more details take a look at these links:**

- https://github.com/SeleniumHQ/selenium/wiki/FirefoxDriver

# Linked Documentation

- [Browser](browser.md)
