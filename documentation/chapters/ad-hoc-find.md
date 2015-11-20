[Home](../README.md)

# Ad-Hoc finding of Page Objects
The `Browser` and `PageObject` classes provide entry points to the "ad-hoc finding" API.
This allows you to apply a more script style approach to your tests.

**Doing so might be useful in the following circumstances:**

- You don't have an unique identification property for initializing a `PageObject` field and need to take a programmatic approach 
- You want to rapidly prototype your tests scenarios
- You want to execute some bordercases upon elements and don't like to declare those as fields in your page objects
- Getting to a specific page object is not possible by using `@IdentifyUsing`, `@FindBy` or `@FindBys`

## Examples
```java
// find an element by its ID 'fooId' as a GenericElement 
GenericElement element = getBrowser()
    .find("#fooId");

// find many elements by their shared CSS class 'foo' as a list of GenericElement
List<GenericElement> elements = getBrowser()
    .findMany(".foo");
    
// find a TextField by it's ID - Identification first
TextField textField = getBrowser()
    .findBy(id("textField"))
    .as(TextField.class);
    
// find a TextField by it's ID - class first
TextField textField = getBrowser()
    .find(TextField.class)
    .by(id("textField"));

// find all all elements with the CSS class 'foo' within an element with ID 'group' as TextFields
List<TextField> textFields = getBrowser()
    .find("#group")
    .findBy(css(".foo"))
    .asMany(TextField.class);
```

# Linked Documentation

- [Conditions](conditions.md)
- [Identifications](identifications.md)
- [Generic Elements](page-object-generic.md)
