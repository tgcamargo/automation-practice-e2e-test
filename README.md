# Automation Practice E2E Test

The purpose of this project is to test the application with url http://automationpractice.com/index.php following the test cases described in TESTCASES.md:

## Getting Started

### Prerequisites

* Java IDE
* Maven

Open the pom.xml file and import the settings for your environment.

Configure the system property variables in the pom.xml in the following section:
```
<systemPropertyVariables>
    <url>http://automationpractice.com</url>
    <OS>linux</OS> <!--possible values: linux | macos | windows-->
    <browser>chrome headless</browser> <!--possible values: chrome | firefox | ie | safari | chrome headless -->
    <defaultUser.id>hf_challenge_123456@hf123456.com</defaultUser.id>
    <defaultUser.pwd>12345678</defaultUser.pwd>
    <defaultUser.fullName>Joe Black</defaultUser.fullName>>
</systemPropertyVariables>
```

### Running the tests

The tests can be run using JUnit runner on your IDE or can be run by maven.
If the tests are run in maven they'll run in parallel mode.

Maven command:
```
//First run:
mvn install

//Following runs:
mvn test
```

### Checking the Execution

After the end of the tests, there be generated 2 files in the /target:
* testLog.txt - Log of all executions.
* testReport.html - HTML execution report showing details of each test and execution. If there is an error in a test, the print screen generated is shown in the report as well as the error itself.

## Built With

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The java IDE used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Selenium WebDriver](https://selenium.dev/) - Used to handle with the web drivers and navigate
* [JUnit](https://junit.org/) - Used to do the validations and handle with the tests executions
* [Extent Reporting Framework](https://extentreports.com/) - Used to generate html report
* [Java Faker](https://github.com/DiUS/java-faker) - Used to generate random data for tests
* [Commons IO](https://commons.apache.org/proper/commons-io/) - Used to generate print screens

## Author

**Thiago Camargo** 