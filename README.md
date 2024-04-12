# The Score Automation Assessment

## Introduction

This is Java Appium automation framework

## Requirements

- Java ver. 11 , you could
  use [AS Java SDK v11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
- [Maven](http://maven.apache.org)
- Appium 2.0
- Android Emulator (Android version 9)

## Execution

You can run the basic Testng suite (located in ``src/main/resources/xmlSuites/Regresssion.xml``) executing
the following command:

``` bash
mvn clean test
```
## Html Report

You can generate an Allure Html report using the command:

``` bash
mvn io.qameta.allure:allure-maven:serve
```