[Home](../README.md)

# The Mouse Utility Class
The `Mouse` utility class contains all kinds of methods which allow you to use or at least simulate the use
(depending on the `WebDriver` implementation) of mouse actions. These are the currently implemented mouse actions:

- `click(PageObject)`
- `doubleClick(PageObject)`
- `moveTo(PageObject)`
- `moveToEach(PageObject, PageObject...)`
- `moveToEach(Collection<PageObject>)`

## Mouse.click()
Executes a click on the given `PageObject` by first moving the mouse to the center of it.

## Mouse.doubleClick()
Executes a double click on the given `PageObject` by first moving the mouse to the center of it.

## Mouse.moveToEach()
Moves the mouse to each of the given `PageObject`s in turn. The page objects have to be visible in order to move
the mouse to it. This method can be used to navigate dynamically displayed menu structures because it waits for
each page object to be displayed before moving the mouse to it.

## Mouse.moveTo()
Moves the mouse to the given `PageObject`. The page object has to be visible in order to move the mouse to it.

## Examples
```java
// clicks a button
Mouse.click(button);
 
// double clicks an image
Mouse.doubleClick(image);
 
// moves the mouse to the link
Mouse.moveTo(link);
 
// moves the mouse to each link as they appear
Mouse.moveToEach(fileMenu, fileMenuNew, fileMenuNewPage);
```

# Linked Documentation

- [Page Objects](page-object.md)
