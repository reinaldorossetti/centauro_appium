# Cucumber-Java Skeleton

[![Build Status](https://travis-ci.org/cucumber/cucumber-java-skeleton.svg?branch=master)](https://travis-ci.org/cucumber/cucumber-java-skeleton)

This is the simplest possible build script setup for Cucumber using Java.
There is nothing fancy like a webapp or browser testing. All this does is to show you how
to install and run Cucumber!

There is a single feature file with one scenario. The scenario has three steps, two of them pending. See if you can make them all pass!

## Rules
Keywords
Each line that isn’t a blank line has to start with a Gherkin keyword, followed by any text you like. The only exceptions are the feature and scenario descriptions.

#### The primary keywords are:

Feature  
Rule (as of Gherkin 6)  
Example (or Scenario)  
Given, When, Then, And, But (steps)  
Background  
Scenario Outline (or Scenario Template)  
Examples  

#### There are a few secondary keywords as well:  
""" (Doc Strings)  
| (Data Tables)  
@ (Tags)  
\# (Comments)  


#### Tag Expressions are boolean expressions of tags with the logical operators and, or and not.

Migrating from old style tags  
--tags @dev  //=> stays the same  
--tags ~@dev //=> becomes --tags 'not @dev'  
--tags @foo,@bar //=> becomes --tags '@foo or @bar'  
--tags @foo --tags @bar //=> becomes --tags '@foo and bar'  
--tags ~@foo --tags @bar,@zap //=> becomes --tags 'not @foo and (@bar or @zap)'  


## Get the code

Git:

    git clone https://github.com/cucumber/cucumber-java-skeleton.git
    cd cucumber-java-skeleton

Subversion:

    svn checkout https://github.com/cucumber/cucumber-java-skeleton/trunk cucumber-java-skeleton
    cd cucumber-java-skeleton

## Use Maven

MACOS - Run following command on Terminal
```
    $ brew update  
    $ brew install maven
```

Open a command window and run:

    mvn test

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

## Use Gradle

Open a command window and run:

    gradlew test --info

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

## Overriding options

The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what plugins to use etc.
When you use the JUnit runner, these options are generated from the `@CucumberOptions` annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the
`cucumber.options` system property. The general form is:

Using Maven:

    mvn -Dcucumber.options="--tags @cukesY and @cukesX" test

Using Gradle:

    ./gradlew -Dcucumber.options="--tags @cukesY and @cukesX" test --info

Let's look at some things you can do with `cucumber.options`. Try this:

    ./gradlew -Dcucumber.options="--help"

That should list all the available options.

*IMPORTANT*

When you override options with `-Dcucumber.options`, you will completely override whatever options are hard-coded in
your `@CucumberOptions` or in the script calling `cucumber.api.cli.Main`. There is one exception to this rule, and that
is the `--plugin` option. This will not _override_, but _add_ a plugin. The reason for this is to make it easier
for 3rd party tools (such as [Cucumber Pro](https://cucumber.pro/)) to automatically configure additional plugins by appending arguments to a `cucumber.properties`
file.

### Run a subset of Features or Scenarios

Specify a particular scenario by *line* (and use the pretty plugin, which prints the scenario back)

    -Dcucumber.options="classpath:skeleton/belly.feature:4 --plugin pretty"

This works because Maven puts `./src/test/resources` on your `classpath`.
You can also specify files to run by filesystem path:

    -Dcucumber.options="src/test/resources/skeleton/belly.feature:4 --plugin pretty"

You can also specify what to run by *tag*:

    -Dcucumber.options="--tags @cukesY and @cukesX --plugin pretty"

### Running only the scenarios that failed in the previous run

    -Dcucumber.options="@target/rerun.txt"

This works as long as you have the `rerun` formatter enabled.

### Specify a different formatter:

For example a JUnit formatter:

    ./gradlew -Dcucumber.options="--plugin junit:target/cucumber-junit-report.xml"


## Use Maven + Allure

run your tests
```
mvn clean test  
```
You can generate a report using one of the following command:
```
mvn allure:serve  
```
Report will be generated into temp folder. Web server with results will start.
```
mvn allure:report
```
Report will be generated tо directory: target/site/allure-maven/index.htm

## Project Structure
````text
Our project structure is now as follows:  
├── pom.xml             (containing Cucumber and JUnit dependencies)
└── src (java root path project)
    ├── main (project code)
    │   └── java        (marked as java sources root)
    │   └── resources   (marked as java resources root)
    └── test (project test)
        ├── java        (marked as test sources root)
        └── resources   (marked as test resources root)
                └── <project> (project name io.cucumber.skeleton)
                       └── belly.feature
                └── allure.properties
````

## References:  

https://github.com/cucumber/cucumber/tree/master/tag-expressions  
https://cucumber.io/docs/gherkin/reference/   
https://github.com/allure-framework/allure-maven   
https://cucumber.io/docs/installation/java/  
https://medium.com/@mlvandijk/getting-started-with-cucumber-in-java-a-10-minute-tutorial-586652d2c82  
