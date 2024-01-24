# Java Exam Back-End

Java Exam Back-End is a Spring Boot application built with Maven, MySQL, and GraphQL. It provides a robust back-end solution for managing employees with CRUD (Create, Read, Update, Delete) operations and includes secure login functionality.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)

## Prerequisites

- Java 8 or higher
- Maven 3.2 or higher

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/cruzmarkus4321/java-exam-back-end.git
    ```

2. Open the project in your IDE

## Configuration

Create or update the `application.properties` file in the `src/main/resources` directory and set the following properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Usage

1. Run the application in your IDE

2. Open your web browser and navigate to [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql)
