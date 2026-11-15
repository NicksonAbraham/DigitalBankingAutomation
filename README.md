Digital Banking Automation Framework

Project Overview

Digital Banking Automation is a Selenium-based test automation framework developed using Java, TestNG and Maven.

The framework automates login functionality of a digital banking application and validates both positive and negative login scenarios.

Tech Stack

- Programming Language: Java
- Automation Tool: Selenium WebDriver
- Testing Framework: TestNG
- Build Tool: Maven
- Reporting: Extent Reports
- IDE: Eclipse
- Version Control: Git & GitHub

Project Structure

DigitalBankingAutomation
│
├── src/main/java
│   └── com.bank.qa.pages
│       └── LoginPage.java
│
├── src/test/java
│   └── com.bank.qa
│       ├── listeners
│       ├── reports
│       └── tests
│
├── src/test/resources
│   └── testng.xml
│
├── reports
├── screenshots
├── pom.xml
├── .gitignore
└── README.md

Test Scenarios

1. Valid Login Test
2. Invalid Login Test
3. Login with Multiple Test Data
4. Intentional Failure Test (used to validate failure reporting and screenshot capture)

Framework Features

- Page Object Model (POM)
- TestNG annotations
- Data-driven testing
- TestNG listeners
- Automatic screenshot capture on test failure
- Extent HTML reporting
- Maven test execution
- GitHub version control

How to Run the Project

Prerequisites

- Java JDK 17 or above
- Eclipse IDE
- Maven
- Google Chrome

Execution Steps

1. Clone the repository.
2. Import the project into Eclipse as an Existing Maven Project.
3. Update Maven dependencies.
4. Open "testng.xml".
5. Right-click and select Run As → TestNG Suite.

Alternatively, run:

mvn clean test

Reports and Screenshots

The framework generates execution reports and captures screenshots when tests fail.

Author

Nickson Abraham

GitHub: https://github.com/NicksonAbraham

---

This project is developed for learning and demonstrating Selenium Java test automation practices.