[Home](../README.md)

# AJAX
JavaScript heavy applications pose special problems when testing through the UI. They tend to lose their `WebElement` 
references a lot. Resulting in occurrences of `StaleElementReferenceException`. WebTester as multiple ways of dealing with
these problems.

The first being an automatic retry mechanism: Every action of a `PageObject` executed by WebTester will automatically be 
retried in case a `StaleElementReferenceException` occurred. This should provide base stability in simple JavaScript 
applications. Depending on your application there are a number of additional steps you can take to stabilize your tests in
these difficult environments.
 
## Manually Invalidating the PageObject's Cache
One approach to handling asynchronous changes to your page objects is to invalidate those you know can change,
before interacting with them. This can be done by using the `Invalidator` utility class. It clears the `WebElement` cache 
of `PageObject`, a `List<PageObject>` as well as `PageObjectList` instances.

- Invalidating a `PageObject` instance will result in the recursive invalidation of all of the page object's fields.
- Invalidating a `List<PageObject>` instance will result in the invalidation of each page object of the list. 
- Invalidating a `PageObjectList` instance will result in the invalidation of the list it self.

> Since this approach adds certain overhead to your test code it is only recommended for applications with low JavaScript 
/ AJAX use.

## Deactivating the PageObject's Cache
If you known which elements of a page change frequently you can deactivate the cache for those elements by setting the 
`caching` property of their `@IdentifyUsing` annotation to `OFF`. This will force the object to look up it's corresponding
`WebElement` every time a method is called. This will add a certain overhead to your test execution time but will 
guarantee that the elements are always usable.

> This approach is recommended for applications with a medium amount of JavaScript / AJAX.

## Global Deactivation of the PageObject Cache
In cases where the application under test is solely based on AJAX it is possible to deactivate the `PageObject`'s 
`WebElement` caching by default. This can be done by setting the `pageobjects.caching` property to `false`. It is still 
possible to activate caching for select page elements by setting the `caching` property of their `@IdentifyUsing` 
annotation to `ON`.

> This approach will create the most overhead in execution time but will also stabilize your tests the most.

# Linked Documentation

- [Configuration](configuration.md)
- [Page Objects](page-object.md)
