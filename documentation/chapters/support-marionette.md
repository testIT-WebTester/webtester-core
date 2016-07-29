[Home](../README.md)

# Firefox Marionette Browser Factory
The module `webtester-support-marionette` provides a default `BrowserFactory` implementation called `MarionetteFactory`.
Marionette can drive Firefox Browser from version 47 onward but is only fully supported starting with version 48.

## Default Driver Configuration
In order to optimize testing the following properties are set when creating a `WebDriver` using the `MarionetteFactory`:

- "Native Events" are disabled -> Selenium does not simulate human typing.
- Untrusted certificates are always trusted.

## Additional Service Executable
The 'MarionetteDriver' needs an additional executable to communicate with a Firefox browser.
It can be downloaded [here](https://github.com/mozilla/geckodriver/releases).
Until further development you may have to rename the `geckodriver.exe` to `wires.exe` after unzipping.

The path to the executable must be declared as a system or environment property named: `webdriver.gecko.driver`

**For more details take a look at these links:**

- https://github.com/mozilla/geckodriver
- https://developer.mozilla.org/en-US/docs/Mozilla/QA/Marionette


# Linked Documentation

- [Browser](browser.md)
