[Home](../README.md)

# Page Objects

## The Page Object Pattern
The WebTester framework's architecture and design is based around the Page Object Pattern.
For more information about Page Object Pattern see:
- [Martin Fowler on Page Objects](http://martinfowler.com/bliki/PageObject.html)
- [Selenium Wiki Article](https://code.google.com/p/selenium/wiki/PageObjects)

## PageObject
The `PageObject` class is the parent for all [functional page objects](page-object-functional.md) as well
as the base class of all pages used to implement the Page Object Pattern.
It provides base methods every page or element on a page will need.

Every page object wraps a `WebElement` which maps to one element in the DOM tree of the displayed web page.
They are lazy loaded proxies to those `WebElement` instances.
This means that the element doesn't need to be present or visible at the time the `PageObject` is initialized.
The element will be resolved when the first interaction method is called.

**Note:** This behavior can be modified by changing the global caching property or overriding the behavior at declaration time.

Page objects are initialized using the `create(pageObjectClass)` method of a `Browser` instance.

Subclasses of `PageObject` can be single elements like a `Button` or an `Image`. They might also represent a "fragment" of
a page (a set of elements) as well as the whole page itself. Each page object declared within another page object limits
the search context for its elements (so called 'nested' page objects).

### Example
The following example represents an application with two pages which share an navigation menu. Both pages display
different tables. The navigation menu is static and contains two links. This demonstrates inheritance and composition
of page information.

```java
// abstract base class declaring the navigation menu
public abstract class BasePage extends PageObject {
 
    /* The navigation menu is identified by its ID
     * This automatically limits the search scope for the navigation menu's elements to 
     * everything contained inside the tag with the ID "navMenu" */
    @IdentifyUsing("navMenu")
    NavigationMenuWidget navigationMenu;
 
}
 
// first page containing a table with the ID "tableOfFirstPage"
public class FirstPage extends BasePage {
     
    @IdentifyUsing("tableOfFirstPage")
    Table table;
 
    ...
 
}
 
// second page containing a table with the ID "tableOfSecondPage"
public class SecondPage extends BasePage {
 
    @IdentifyUsing("tableOfSecondPage")
    Table table;
 
    ...
 
}
 
// the navigation menu as a widget (or page fragment)
public class NavigationMenuWidget extends PageObject {
 
    @IdentifyUsing("firstLink")
    Link firstLink;
 
    @IdentifyUsing("secondLink")
    Link secondLink;
 
}
```

## PageObjectList
A `PageObjectList` provides the same lazy loading feature as a `PageObject`. The `@IdentifyUsing` annotation works as well,
with the small difference that you can use "soft" identification properties. When you try to identify an element as
an `PageObject` and get multiple possible elements for your @IdentifyUsing description an exception will be thrown.
With a `PageObjectList` this is not a problem as long as all matches share the generic type of the list.
If no elements could be identified for the given criteria the list will be empty.

In addition the `PageObjectList` declares a filter method with which the content of the list can be filtered by matching it
against a specific condition.

### Example
```java
// will contain all images of the displayed page
@IdentifyUsing ( method = Method.XPATH, value ".//img" )
List<Image> images;
 
// will contain all divs who's ID starts with "msg:"
@IdentifyUsing ( method = Method.XPATH, value = ".//div[starts-with(@id, 'msg:')]" )
PageObjectList<PageObject> messages;
```

## @IdentifyUsing
This annotation must be used on fields of type `PageObject` (or subclasses there of) in order for it to be initialized
when the page object itself is initialized.
 
The annotation provides all necessary information to identify the corresponding element(s) in the DOM of the displayed page.

**Properties:**

- value - A String containing identification data for the method to use. Might be as simple as an HTML-ID if `Method.ID` is used.
- method - The method being used to identify the object in the DOM. This is an enumeration value of the Method class. If no method is provided the default `Method.ID` is used.
- elementname - Optional String containing a human readable name for the object. This is generally used for log output and events in cases where the identification information is hard to understand for someone reading a report.

**Constraints:**

- Annotated fields must be of type `PageObject`, `List<PageObject>` or `PageObjectList<PageObject>` (or corresponding subclasses).

### Example
```java
// using the default ID method and the value "username"
@IdentifyUsing ( "username" )
TextField username;
 
// using the default ID method and providing a name
@IdentifyUsing ( value = "uidFieldX776", elementname = "Username Field" )
TextField username;
 
// using the XPATH method to identify a specific table
@IdentifyUsing ( value = ".//*[@id='datatableForm:testResultsTable']/div/table", method = Method.XPATH )
Table testResultTable;
```

## @PostConstruct
This annotation can be added to methods of `PageObject` subclasses. Every annotated method will be invoked after the `PageObject` was initialized.
These methods are generally used to verify that the correct page is displayed after the `PageObject` was initialized.

**Constraints:**

- The invocation order of multiple methods is not deterministic.
- Annotated methods must not have arguments.

### Example
```java
public class LoginPage extends PageObject {
 
    @IdentifyUsing ( "username" )
    TextField username;
 
    @IdentifyUsing ( "password" )
    PasswordField password;
 
    @IdentifyUsing ( "login" )
    Button login;
 
    @PostConstruct
    void assertThatCorrectPageIsDisplayed () {
        Waits.waitUntil(username, is(visible()));
        Waits.waitUntil(password, is(visible()));
    }
 
    ...
 
}
```

## @Visible
This annotation can be added to fields already annotated with `@IdentifyUsing`. Every annotated field will be checked
for visibility after the `PageObject` was initialized and all `@PostConstruct` annotated methods were
invoked.

If the annotated field is a `List` or `PageObjectList` the value property of the annotation must be used to declare how many
elements of the list should be visible.

**Note:** The given number is not the number of page objects expected to be in the list! It is the number of expected _visible_ page objects in the list!

**Properties:**

- value - The number of expected visible elements in the list. Defaults to 0 and is ignored for non-lists.

**Constraints:**

- The order in which the fields are checked for visibility is not deterministic.

### Examples
```java
@Visible
@IdentifyUsing ( "username" )
TextField username;

@Visible
@IdentifyUsing ( "password" )
PasswordField password;

@IdentifyUsing ( "login" )
Button login;
}
```

```java
@Visible(5)
@IdentifyUsing ( method = Method.CLASS, value = "textField" )
List<TextField> textFields;
```

# Linked Documentation

- [Anatomy of Page Objects](page-object-anatomy.md)
- [Page Object Factory](page-object-factory.md)
