# DailyFinance-Automation

## In this project, where I automated [Daily Finance](https://dailyfinance.roadtocareer.net/) by performing regression testing using selenium-webdriver and TestNG as testing framework.

The following modules are automated
- Login
    - Admin login with valid credentials
    - Admin login with invalid credentials
    - User login
    - Logout
- Registration
   - User Registration with all fields
   - User registration with required fields
   - User registration without any required fields
- Edit Profile
- Add cost for items
- Search Item

## Technology
- Tool: Selenium
- IDE: Intellij
- Language: Java
- Testing Framework: TestNG
- Build tool: Gradle

## Pre-requisite
- Install jdk 11, gradle, allure
- clone this project and unzip it
- Open the project folder in Intellij
- Click on the terminal and run the automation script

## Run the automation script by following the command:

`gradle clean test`

## After automation write following commands on the terminal to view the allure report:

`allure generate allure-results --clean -output`
`allure serve allure-results`

## Allure Report
![allure01](https://github.com/user-attachments/assets/37b750a0-9042-4e69-9b36-e8f77da948c0)

![allure02](https://github.com/user-attachments/assets/a4a5b1d2-52a4-4465-8286-6b57719919be)

