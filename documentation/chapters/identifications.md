[Home](../README.md)

# The Identifications Utility Class
The utility class `Identifications` provides several factory methods for creating `Identification` instances.
These are used by WebTester as an abstraction over Selenium's `By` classes.
They are relevant to the following (sub-)systems:

- [Ad-Hoc](ad-hoc-find.md) finding of page objects
- `Method` enumeration for [`@IdentifyUsing`](page-object.md)
- Creating `PageObjectModels` for the [`PageObjectFactory`](page-object-factory.md)

```java
// Ad-Hoc finding of page objects
browser.findBy(Identifications.id("username"));
```

# Linked Documentation

- [Ad-Hoc Finding of Page Objects](ad-hoc-find.md)
- [Page Objects](page-object.md)
- [Page Object Factory](page-object-factory.md)
