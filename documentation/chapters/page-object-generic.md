[Home](../README.md)

# Generic Page Element
The `GenericElement` `PageObject` class is basically the `WebElement` of page objects.
It opens up all methods of `WebElement` which were not already implemented in `PageObject` (maybe with a different name).
 
It mainly intended for the [Ad-Hoc find API](ad-hoc-find.md) in order to minimize the number of calls needed to make
when rapidly prototyping or looking up deeply nested elements.

## Casting
`GenericElement` provides a method `as(Class)` which allows the 'cast' of the generic element to any other `PageObject` class.

```java
// find returns a GenericElement
Button b = browser.find(#button).asButton();
```

# Linked Documentation

- [Ad-Hoc find API](ad-hoc-find.md)
- [Page Objects](page-object.md)
