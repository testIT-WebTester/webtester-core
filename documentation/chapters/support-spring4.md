[Home](../README.md)

# Spring Configuration Adapter
The `webtester-support-spring4` module provides a `ConfigurationAdapter` implementation called `SpringEnvironmentConfigurationAdapter`.
This adapter can be used to resolve properties from the existing `Configuration` against a Spring `Environment`.
All keys which could successfully be resolved against the environment will then be overridden in the configuration.

## Example
```properties
# WebTester Configuration
foo = hello world!
bar = welcome world!
 
# Spring Environment
foo = hello spring world!
 
# Resulting Configuration
foo = hello spring world!
bar = welcome world!
```

# Factory Beans
In addition to the configuration adapter the module also provides `FactoryBean` implementations which can be used to
easily initialize different WebTester services as beans.

- `DefaultSpringConfigurationFactoryBean`
- `ConfigurationBuilderFactoryBean`
- `PrototypeConfigurationBuilderFactoryBean`

**DefaultSpringConfigurationFactoryBean**
Creates a `Configuration` instance which results from using the `DefaultConfigurationBuilder` in conjunction with the 
`SpringEnvironmentConfigurationAdapter`.

**ConfigurationBuilderFactoryBean**
Creates a singleton `ConfigurationBuilder` instance.
`ConfigurationAdapter` and `ConfigurationExporter` beans can be added via setters.

**PrototypeConfigurationBuilderFactoryBean**
Creates a prototyped `ConfigurationBuilder` instance.
`ConfigurationAdapter` and `ConfigurationExporter` beans can be added via setters.

# Linked Documentation

- [Configuration](configuration.md)
