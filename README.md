# Credit Card Validator API

The Credit Card Validator API is a service that allows you to validate credit card numbers and perform various checks on credit card information.

## Table of Contents

- [Introduction](#introduction)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Endpoints](#endpoints)

## Introduction

The Credit Card Validator API provides a simple and reliable way to validate credit card numbers and perform essential checks on cardholder data. It is designed to assist developers in building secure and robust payment processing systems.

## Getting Started

To get started with the Credit Card Validator API, follow the steps below:

### Prerequisites

Before using the API, ensure you have the following prerequisites:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Boot](https://spring.io/projects/spring-boot)

### Installation

To deploy the Credit Card Validator API as a WAR (Web Application Archive) to a Servlet container, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/credit-card-validator-api.git
   
2. Build the project using Maven:
   ```bash
   cd credit-card-validator-api
    mvn clean install


### Endpoints

The Credit Card Validator API provides the following endpoints for credit card validation:

#### Validate Credit Card

- **Endpoint:** `/validate`
- **HTTP Method:** POST
- **Description:** Validate a credit card number and receive validation results.
- **Request Body:**

  ```json
  { 
    "cardNumber": "1234567890",
    "cvc": "123",
    "month": 12,
    "year": 2023,
    "cardHolderName": "John Doe",
    "issuer": "visa"
}
- **Response 200:**
```json
{
    "status": "200 OK",
    "message": "Success",
    "data": {
        "cardNumber": "1234567890",
        "cvc": "123",
        "month": "12",
        "year": "2023",
        "cardHolderName": "John Doe",
        "issuer": "visa"
    },
    "code": 200
}
