[Home](../README.md)

# The Conditions Utility Class
The utility class `Conditions` provides several factory methods for creating GUAVA `Predicate` instances.
These can be (re-)used in the following sub-systems:

- Filtering a [`PageObjectList`](page-object.md)
- As a condition of `waitUntil(...)` operations from the [`Waits` API](waits.md)

```java
browser.findMany(".textfield").filter(Conditions.is(Conditions.visible()));
Waits.waitUntil(textField, Conditions.has(Conditions.text("foo")));
```

Since all of these make use of GUAVA's `Predicate` interface it is easy to create your own conditions if need be.

## Syntax

Conditions are designed to be readable. They take a lot of inspiration from Hamecrest's `Matcher` API.
It is generally recommended to use static imports when working with the `Conditions` class.

We provide two kinds of classes in the `info.novatec.testit.webtester.utils.conditions` package:

- Syntax operations like `Has`, `Is` and `Not` in order to make conditions more readable
- Property conditions like `TextEquals`, `Visible`, `Writeable` etc. which are executing the real checks

## List of current Conditions

**Syntax:**

- `Either`
- `Has`
- `Is`
- `Not`

**Checks:**

- `AttributeWithValue`
- `Disabled`
- `Editable`
- `Enabled`
- `FileName`
- `Interactable`
- `Invisible`
- `Label`
- `Present`
- `ReadOnly`
- `Selected`
- `SelectedIndex`
- `SelectedIndices`
- `SelectedText`
- `SelectedTexts`
- `SelectedValue`
- `SelectedValues`
- `SourcePath`
- `TextContains`
- `TextEquals`
- `Value`
- `Visible`
- `VisibleTextContains`
- `VisibleTextEquals`

## Examples
```java
// ## Waits API ##
Waits.waitUntil(textField, Conditions.has(Conditions.text("foo")));
waitUntil(textField, has(text("foo")));
 
// ## Ad-Hoc PageObject creation API
browser.findMany(".textfield").filter(is(visible));
```

# Linked Documentation

- [Ad-Hoc Finding of Page Objects](ad-hoc-find.md)
- [Waits API](waits.md)
