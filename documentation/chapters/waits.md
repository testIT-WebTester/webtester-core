[Home](../README.md)

# The Waits Utility Class
The `Waits` utility class contains all kinds of methods which allow you / your tests to wait for certain conditions.
It is split between methods that allow 'general' wait operations and methods who's conditions are dependent on a `PageObject`.

**Wait a given amount of time:**

- `waitSeconds(long)`
- `waitMilliseconds(long)`
- `wait(long, TimeUnit)`

**Wait until a specific condition is met:**

- `waitMillisecondsUntil(long, Supplier<Boolean>)`
- `waitSecondsUntil(long, Supplier<Boolean>)`
- `waitUntil(long, TimeUnit, Supplier<Boolean>)`

**Wait until a PageObject fulfills a specific condition:**

- `waitUntil(PageObject, Predicate<PageObject>)`

## Examples
```java
// waits 5 seconds
Waits.waitSeconds(5);
 
// waits 150 milliseconds
Waits.waitMilliseconds(150);
 
// waits 1 hour
Waits.wait(1, TimeUnit.HOURS);
 
// waits until the hidden field is present on the DOM
Waits.waitUntil(hiddenField, is(present()));
 
// waits until the proceed button is visible on the page
Waits.waitUntil(proceedButton, is(visible()));
 
// waits until the loading icon is no longer visible on the page
Waits.waitUntil(loadingIcon, is(invisible()));
Waits.waitUntil(loadingIcon, is(not(visible())));
```

# Linked Documentation

- [Conditions](conditions.md)
