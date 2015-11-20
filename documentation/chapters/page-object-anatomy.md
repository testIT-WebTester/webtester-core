[Home](../README.md)

# Anatomy of a Page Object 
Page Objects generally provide four kinds of methods:

- Actions
- Navigations
- Workflows
- Information Getter

## Actions
Actions are methods which change the state of a page without leaving it. This could be the input of text in a text field or 
the selection of a value in a select menu.
   
Rules:
   
- Method name represents an action: "setUsername", "changeDataOfBith" etc.
- Method returns the same instance of the PageObject for fluent API support.
- Method does not change multiple states.

```java
public LoginPage setFirstName(String name){
    firstName.setText(name);
    return this;
}
 
public LoginPage selectBirthMonth(String month){
    birthMonth.selectByText(month);
    return this;
}
```

## Navigations
Navigations are methods which execute an action that leads to a page change. This could be the click of a link or the direct 
opening of an URL. The difference between navigations and actions is, that a navigation has to declare what page comes "next". 
This means that for a single navigation "action" there might me multiple methods. I.g. this is necessary to declare "bad case" 
paths through the application. E.g. if a login fails or a process could not be finished.
   
Rules:
   
- Method name represents an action: "clickLogin", "clickLoginExpectingError" etc.
- Method returns a new instance of the target page's PageObject for fluent API support.
- Method does not change multiple states.

```java
public MainPage clickLogin(){
    login.click();
    return create(MainPage.class);
}
 
public LoginPage clickLoginExpectingError(){
    login.click();
    return create(LoginPage.class);
}
```

## Workflows
Workflows combine different methods in order to allow for "fast" navigation over pages. I.g. they combine a set of actions 
with a navigation. This could e.g. be a single method to log into a system.
   
Rules:
   
- Method name represents a process: "login", "register" etc.
- Method's return type and value depends on the last command in the workflow: Is it an action or a navigation?
- Method does change multiple states.

```java
public MainPage login(String username, String password){
    return setUsername(username)
        .setPassword(password)
        .clickLogin();
}
 
public LoginPage loginExpectingError(String username, String password){
    return setUsername(username)
        .setPassword(password)
        .clickLoginExpectingError();
}
```

## Information Getter
Information getter are methods which retrieve information from a page. This could be the text of a displayed error 
message or the content of a certain text field.
  
Rules:
  
- Method name represents a request: "getErrorMessages", "getNumberOfDisplayedTableEntries" etc.
- Method's return type might be anything but a PageObject.
- Method does not change any states.

```java
public String getErrorMessage () {
    return errorMessage.getText();
}
 
public int getNumberOfSearchResults () {
    int counter = 0;
    // some logic
    return counter;
}
```

# Linked Documentation

- [Page Objects](page-object.md)
