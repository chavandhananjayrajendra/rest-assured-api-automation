# REST Assured API Automation Framework

A Java-based API automation framework built using **REST Assured, TestNG, and Maven** to validate REST API endpoints through automated functional and response validation tests. The project integrates **GitHub Actions and Jenkins** to execute API tests as part of a CI/CD workflow.

## Project Overview

This project demonstrates how REST API testing can be automated using reusable request specifications, structured test classes, POJO-based request models, and automated build execution.

The framework currently uses [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a public fake REST API, to practice and demonstrate API automation concepts.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language and compilation target |
| REST Assured 5.5.0 | REST API automation and validation |
| TestNG 7.10.2 | Test execution and test organization |
| Maven | Dependency management and build automation |
| Jackson | JSON serialization and deserialization support |
| Git & GitHub | Version control and source code hosting |
| GitHub Actions | Cloud-based CI test execution |
| Jenkins | Local CI/CD pipeline execution |

## Features

- Automated testing of REST API endpoints.
- Validation of HTTP status codes and response fields.
- GET requests for retrieving individual and multiple resources.
- POST requests for creating resources.
- PUT requests for complete resource updates.
- PATCH requests for partial resource updates.
- DELETE requests for resource deletion.
- POJO-based request payload handling.
- Reusable request specifications and shared test setup.
- Externalized configuration using a properties file.
- Request and response logging for easier debugging.
- Maven-based test execution.
- CI pipeline integration with GitHub Actions and Jenkins.

*Note: The listed operations describe the project scope. Refer to the current test classes and CI results for the scenarios actually implemented and passing.*

## Project Structure

```text
api-automate/
├── .github/
│   └── workflows/
│       └── api-tests.yml
├── src/
│   └── test/
│       ├── java/
│       │   └── com/qa/automation/
│       │       ├── base/
│       │       │   └── BaseTest.java
│       │       ├── models/
│       │       │   └── Post.java
│       │       ├── specs/
│       │       │   └── RequestSpecFactory.java
│       │       ├── tests/
│       │       │   ├── GetPostTest.java
│       │       │   └── UpdatePostTest.java
│       │       └── utils/
│       │           └── ConfigReader.java
│       └── resources/
│           └── config.properties
├── Jenkinsfile
├── pom.xml
└── README.md
```

*The structure above reflects the main files; update the test-file list if your repository contains additional classes.*

## Prerequisites

Install the following before running the project:

- JDK 17 or later
- Apache Maven 3.9 or compatible
- Git
- IntelliJ IDEA or another Java IDE

Verify your Java and Maven installations:

```bash
java -version
mvn -version
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/chavandhanajayrajendra/rest-assured-api-automation.git
```

### 2. Navigate to the project directory

```bash
cd rest-assured-api-automation
```

### 3. Run the automated test suite

```bash
mvn clean test
```

Maven compiles the test code, resolves dependencies, and executes the tests configured through TestNG and Maven Surefire.

### 4. Review test results

After execution, review the generated reports in:

```text
target/surefire-reports/
```

Check the Maven console output and report files for the number of tests executed, passed, skipped, or failed.

## API Test Scenarios

The framework covers or is being extended to cover the following categories:

| HTTP Method | Example Endpoint | Validation Objective |
|---|---|---|
| GET | `/posts/1` | Validate status code and resource fields |
| GET | `/posts` | Validate collection response |
| GET | `/posts?userId=1` | Validate query-parameter filtering |
| GET | `/posts/999999` | Validate behavior for a nonexistent resource |
| POST | `/posts` | Validate resource creation response |
| PUT | `/posts/1` | Validate complete update response |
| PATCH | `/posts/1` | Validate partial update response |
| DELETE | `/posts/1` | Validate deletion response |

Actual response behavior is determined by the public API. JSONPlaceholder simulates write operations, so POST, PUT, PATCH, and DELETE requests do **not** permanently modify its underlying dataset.

## CI/CD Integration

### GitHub Actions

The workflow in `.github/workflows/api-tests.yml` runs the Maven test suite on pushes and pull requests targeting the `main` branch. It also supports manual execution through GitHub Actions.

### Jenkins

The root-level `Jenkinsfile` defines a pipeline that checks out the repository, verifies the Java and Maven installations, runs the API tests, and publishes available Surefire test reports.

## Configuration

The base URL is externalized in:

```text
src/test/resources/config.properties
```

Example:

```properties
base.url=https://jsonplaceholder.typicode.com
```

This helps keep environment-specific configuration separate from test logic.

## Future Enhancements

- Add data-driven testing using TestNG DataProviders.
- Add JSON Schema validation.
- Improve negative testing and boundary-value coverage.
- Introduce environment-specific configuration.
- Add Allure or Extent Reports for enhanced test reporting.
- Add API authentication and authorization test scenarios.
- Integrate GitLab CI/CD.
- Improve reusable utilities and framework documentation.

## Learning Outcomes

This project demonstrates practical experience with REST API automation, HTTP methods, response validation, reusable framework components, Maven test execution, Git version control, and CI/CD pipeline integration.



---

*This project is intended for learning and demonstrating API automation practices using a public test API.*
