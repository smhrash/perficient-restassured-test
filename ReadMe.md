#  Perficient RestAssured Test
## Overview
Perficient RestAssured Test is an automated API testing framework for Toyota's web application, built using
**RestAssured,
Cucumber,
TestNG, and
Maven**.
It enables API validation using behavior-driven development (BDD) and generates test reports.

## Technologies Used
- **Java 17**
- **RestAssured**
- **Cucumber**
- **TestNG**
- **Maven for dependency management**

## Project Structure
```
perficient-restassured-test/
│-- src/
│   ├── main/
│   │   ├── java/com/perficient/sarker/conveter/       # POJO Conveter
│   │   ├── java/com/perficient/sarker/dto/            # DTOs 
│   │   ├── resources/                          # Configuration files and test data
│   ├── test/
│   │   ├── java/com/perficient/sarker/constants/      # Path Param
│   │   ├── java/com/perficient/sarker/runners/        # Test Runners
│   │   ├── java/com/perficient/sarker/steps/          # Step definition
│   │   ├── resources/features/                 # Feature Files
│-- pom.xml                                     # Maven dependencies
│-- ReadDMe.md                                   # Project documentation
│-- reports/                                    # Test Reports
```

## Setup Instructions
### 1. Clone the repository
```bash
  git clone <repository-url>
cd toyota-gpt-ui-test
```

### 2. Install dependencies & generate test report
Ensure Maven is installed, then run:
```bash
  mvn clean install
```

### 3. Run the tests
To execute Cucumber tests, use:
```bash
  mvn test
```
or specify a **tag** to run a specific test suite:
```bash
  mvn test -Dcucumber.filter.tags="@regression"
```

### 4. View Reports
After execution, reports are available in:
```
target/surefire-reports/
target/cucumber-reports/
```

## Authentication Handling
1. Ensure you have a valid **authorization token**.
2. Use **environment variables** or update `ConfigReader.java` to manage credentials securely.

## Continuous Integration (CI)
You can integrate this framework with **Jenkins, GitHub Actions, or Azure DevOps** by running:
```bash
  mvn test
```
in your CI/CD pipeline configuration.

## Contribution Guidelines
1. Fork the repository.
2. Create a new branch for your feature/bugfix:
   ```bash
   git checkout -b feature-new-test
   ```
3. Commit your changes:
   ```bash
   git commit -m "Added new UI test for login"
   ```
4. Push and create a pull request.

## Contact
For issues or contributions, reach out via:
- Email: **sarkerm.rashid@perficient.com**
- GitHub Issues: **[Repository Issues](https://github.com/toyota-gpt/issues)**

---

### 📌 **Happy Testing! 🚀**
