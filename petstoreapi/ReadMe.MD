# Automation
# BDD Testing framework using Cucumber and Java
This file provides information about API automation testing of https://petstore.swagger.io/ everything about your pets section

# Introduction
Sample  framework for automating RESTful API testing. The framework uses the RestAssured library for making API calls POST/GET/PUT/Delete and performs verification of status code, pet status, pet id etc.

# Prerequisites
1. Eclipse
2. Java
3. Selenium Webdriver
4. Chromedriver
5. Maven

# Project Directory
src
  +main             
  +test
    +java             Testrunner, methods
    +resources
      +feature

# Setting up the project
Clone repository 
Install cucumber plugin from Eclipse marketplace and restart Eclipse
Refresh the project 
Right click on Project and Click Configure -> Convert to Cucumber project
Open feature file under - src/test/resources/petStoreApi.feature
Right click and select Run as Cucumber Feature
