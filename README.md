# Bank Customer Accounts Service

## Overview

The `bank-auth-service` is a critical component of the banking application responsible for handling authentication and authorization. Its primary functions include:

- **Token Validation:**
   - Validates incoming tokens to ensure that requests are from authenticated and authorized users. This process helps in securing access to other services and resources within the application.

- **Token Generation:**
   - Generates new authentication tokens for users. This function is used during login and token refresh operations to provide secure access to the system.

## Prerequisites

To run this service, you need the following:

- **Java 17**
- **Maven**
- **MySQL**
- **Kafka**

## Checking Test Coverage

1. **Build the Project:**
   Run the following command to build the project and generate test reports:
   ```sh
   mvn clean install
   ```
2. **View Test Coverage Report:**
   After building the project, navigate to the coverage report located at:
   ```sh
   /target/site/jacoco/index.html
   ```

## Running the Application

**Note:** Ensure to start the services in the following order:

1. **`bank-customer-service`**:
    - Start this service first. It is responsible for validating customer details. it's core component.

2. **`bank-auth-service`**:
    - Start this service second. It handles token validation and security.

3. **`bank-customer-accounts-service`**:
    - Finally, start this service. It manages customer accounts and sends notifications.


To run the `bank-customer-accounts-service`, follow these steps:

1.**Set Up MySQL Database:**
- Create a MySQL database. You can name it `bank`.

2. **Configure Database Access:**
    - Update your application configuration to match your MySQL setup. This typically involves setting the username and password in the `application.yml` file.

3. **(Optional) Configure Kafka:**
    - If you need to customize Kafka settings, update the configuration in `KafkaProducerConfig.java`. Adjust properties like `bootstrap.servers`, `key.serializer`, `value.serializer`, etc., according to your Kafka setup.

4. **Start the Application:**
    - Use Maven to run the application:
      ```sh
      mvn spring-boot:run
      ```

## Configuration Details

### Database Configuration

- **File to Update:** `application.yml`
- **Properties to Configure:**
  ```yml
  spring:
  datasource:
    url: jdbc:mysql://localhost:3306/bank
    username: your_database_username
    password: your_database_password
  ```
## Note on Accessing Resources

To access any resources in the system, you need to authenticate and obtain a token. Use the following API to generate a token:

### Token Generation API

- **Endpoint:** `http://localhost:9090/api/login`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "username": "admin",
    "password": "admin"
  }
  ```
  **Curl Command**
  ```curl 
  curl -X POST http://localhost:9090/api/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin"}'
  ```
  **Note 'admin' is a default user in the system.**