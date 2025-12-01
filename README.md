# Bajaj Finserv Health - Qualifier 1 (Java)

A Spring Boot application that automatically generates a webhook, solves a SQL question based on registration number, and submits the solution on startup.

## Overview

This application:
1. Generates a webhook by calling the Bajaj Finserv Health API
2. Determines which SQL question to solve based on the last digit of the registration number
3. Solves the SQL question
4. Submits the final SQL query to the webhook URL with proper authorization

## Project Structure

```
src/main/java/com/bajaj/qualifier1/
├── Qualifier1Application.java          # Main Spring Boot application
├── config/
│   └── RestTemplateConfig.java        # RestTemplate bean configuration
├── model/
│   ├── WebhookRequest.java            # Request model for webhook generation
│   ├── WebhookResponse.java           # Response model from webhook API
│   └── SqlSubmissionRequest.java      # Request model for SQL submission
├── runner/
│   └── QualifierRunner.java           # CommandLineRunner - orchestrates the process
└── service/
    ├── WebhookService.java            # Service for API calls (webhook generation & submission)
    └── SqlService.java                # Service for SQL question solving
```

## Configuration

### Registration Details
The application uses the following credentials (configured in `QualifierRunner.java`):
- **Name**: Kajal Meshram
- **Registration Number**: 22BCE7772
- **Email**: kajalmeshram1203@gmail.com

### Question Selection Logic
- **Last digit ODD** (1, 3, 5, 7, 9) → Question 1
- **Last digit EVEN** (0, 2, 4, 6, 8) → Question 2

For `22BCE7772`, the last digit is **2** (EVEN), so **Question 2** will be solved.

## Important Notes

⚠️ **IMPORTANT**: The SQL queries in `SqlService.java` are placeholders. You must replace them with the actual SQL solutions:
- `solveQuestion1()` - Replace with actual SQL for Question 1
- `solveQuestion2()` - Replace with actual SQL for Question 2

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Or using the wrapper:
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

## Example Output Logs

When the application starts, you'll see logs similar to:

```
 BAJAJ FINSERV HEALTH QUALIFIER
Application Starting 

>>> STEP 1: Generating Webhook...
2024-01-01 10:00:00 - === Starting Webhook Generation ===
2024-01-01 10:00:00 - Request URL: https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA
2024-01-01 10:00:00 - Request Body: Name=Nishu Kumari, RegNo=REG12346, Email=kumarinishu407@gmail.com
2024-01-01 10:00:00 - Sending POST request to generate webhook...
2024-01-01 10:00:01 - === Webhook Generation Success ===
2024-01-01 10:00:01 - Webhook URL: https://bfhldevapigw.healthrx.co.in/hiring/webhook/abc123
2024-01-01 10:00:01 - Access Token received: Yes (length: 200)

>>> STEP 2: Determining SQL Question...
2024-01-01 10:00:01 - Registration Number: REG12346, Last Digit: 6, Question Selected: 2
2024-01-01 10:00:01 - Question 2 will be solved

>>> STEP 3: Solving SQL Question 2...
2024-01-01 10:00:01 - === Solving SQL Question 2 ===
2024-01-01 10:00:01 - SQL Query Generated: SELECT * FROM table_name WHERE condition = 'value';

>>> STEP 4: Submitting SQL Query...
2024-01-01 10:00:01 - === Starting SQL Query Submission ===
2024-01-01 10:00:01 - Webhook URL: https://bfhldevapigw.healthrx.co.in/hiring/webhook/abc123
2024-01-01 10:00:01 - SQL Query: SELECT * FROM table_name WHERE condition = 'value';
2024-01-01 10:00:01 - Sending POST request to submit SQL query...
2024-01-01 10:00:02 - === SQL Query Submission Response ===
2024-01-01 10:00:02 - Status Code: 200 OK
2024-01-01 10:00:02 - Response Body: {"status":"success","message":"Query submitted successfully"}
2024-01-01 10:00:02 - === SQL Query Submission Success ===

**FINAL RESULT: SUCCESS**
SQL query has been submitted successfully!


✅ SUCCESS: SQL query submitted successfully!
```

### Error Output Example

If an error occurs, you'll see:

```
❌ FAILURE: Failed to submit SQL query

2024-01-01 10:00:02 - === FATAL ERROR ===
2024-01-01 10:00:02 - Error during execution: Connection refused
```

## API Endpoints

### 1. Generate Webhook
- **URL**: `POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- **Request Body**:
```json
{
  "name": "Kajal Meshram",
  "regNo": "22BCE7772",
  "email": "kajalmeshram1203@gmail.com"
}
```
- **Response**:
```json
{
  "webhook": "https://bfhldevapigw.healthrx.co.in/hiring/webhook/abc123",
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 2. Submit SQL Query
- **URL**: `POST <webhook_url>` (from step 1)
- **Headers**:
  - `Authorization: <accessToken>`
  - `Content-Type: application/json`
- **Request Body**:
```json
{
  "finalQuery": "SELECT * FROM table_name WHERE condition = 'value';"
}
```

## Dependencies

- Spring Boot 3.4.12
- Spring Web (includes RestTemplate)
- Lombok
- SLF4J Logging

## Technologies Used

- Java 17
- Spring Boot
- Maven
- RestTemplate for HTTP calls
- SLF4J for logging

## Author

Kajal Meshram
Registration: 22BCE7772  
Email: kajalmeshram1203@gmail.com

