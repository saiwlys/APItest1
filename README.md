# Automated test for Zendesk API


* [Required Software](#required-software)
* [How to execute the tests](#how-to-execute-the-tests)
* [About the Project Structure](#about-the-project-structure)
* [Libraries](#libraries)

This project was created with test automation for a REST API using Rest-Assured.

Goals:
* Create a new Lead using public API (https://developers.getbase.com/) without any address
* Using API check if lead exists
* Update lead address

Expected result:
* Using API check if lead was updates with correct address



## Required software
* Java JDK 15+
* Maven installed

## How to execute the tests
To be able to run the test you must have an account on the Zendesk platform to sell:
https://www.zendesk.com.
Generate an access token in your Zendesk account settings, then enter it into the static variable "apiToken" in the BaseTest class.

### Running the test suites

The test suites can be run directly by your IDE or by command line.
If you run `mvn test` all the tests will execute because it's the regular Maven lifecycle to run all the tests.

## About the Project Structure

### src/main/java

#### models
Models of classes used during tests

### src/test/java
Tests checking the set goals

#### base
Base classes for tests

## Libraries
* [RestAssured 4.4.0](http://rest-assured.io/) library to test REST APIs
* [JUnit 4.11](https://junit.org) to support the test creation
* [Jackson 2.9.5](https://github.com/FasterXML/jackson) to support the test creation
* [JSON 20210307](https://github.com/stleary/JSON-java) to support the test creation

