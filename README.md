# uinames for Java

This is a library to generate random localized names consuming the API of [uinames](http://uinames.com/) as source.

## Installation

Download the [JAR file](https://github.com/juanda95/uinames-java/releases/download/1.0.0/uiname.jar) and its dependencies (see below) and import them to your project.

## Dependencies

At the time this library was written, this was the dependencies used to work with it.

* [Gson 2.3.1](http://search.maven.org/remotecontent?filepath=com/google/code/gson/gson/2.3.1/gson-2.3.1.jar)
* [OkHTTP 2.4.0](https://search.maven.org/remote_content?g=com.squareup.okhttp&a=okhttp&v=2.4.0)
* [OkIO 2.4.0](https://search.maven.org/remote_content?g=com.squareup.okio&a=okio&v=2.4.0)

## Using uinames-java

First of all import the library classes into your project:

```java
import name.davidsanchez.uiname.*;
```

Then create an instance of the UIName class:

```java
UIName uiName = new UIName();
```

This way, you will use the default parameters. You can modify parameters this way:

```java
UIName uiName = new UIName()
    .setAmount(2) // Amount of names you want to generate
    .setGender(Constants.Gender.FEMALE) // Gender of the names you want to generate
    .setCountry(Constants.Country.COLOMBIA); // Localization of the names you want to generate
```

Then, you can use the **generateNames()** method, it will ask you to implement the UINameResponse interface:

```java
uiName.generateNames(new UINameResponse() {
    @Override
    public void onNamesReceived(Person[] people) {
		// The parameter people have all the generated people names in their 
		// corresponding class.
        for (Person person : people) {
        	System.out.println("Name: " + p.getName() + 
                "\nSurname: " + p.getSurname() +
                "\nCountry: " + p.getCountry() +
                "\nGender: " + p.getGender());
        }
    }
});
```