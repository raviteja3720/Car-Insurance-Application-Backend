Car Insurance API

Overview

Car Insurance Management System built with Spring Boot, providing RESTful APIs for managing policies, vehicles, drivers, and insured details. Supports policy creation, activation/cancellation, vehicle and driver management, and auto-generated IDs. Uses Spring Data JPA for database interactions and Swagger UI for API documentation.

Features

Vehicle Management: Add, update, retrieve, and delete vehicle details.

Policy Management: Create, update, activate, and cancel insurance policies.

Driver Management: Manage drivers associated with policies.

Sequence Number Generation: Auto-generate policy numbers, vehicle IDs, and driver IDs.

API Documentation: Swagger UI for easy API exploration and testing.

Tech Stack

Backend: Java, Spring Boot, Spring Data JPA

Database: MySQL/PostgreSQL (Configurable)

API Documentation: Swagger/OpenAPI

Installation & Setup

Prerequisites

Java 17+

Maven 3+

MySQL/PostgreSQL database

Steps to Run

Clone the repository:

git clone https://github.com/raviteja3720/Car-Insurance-Application-Backend.git

cd Car-Insurance-Application-Backend

Configure the database in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/car_insurance_db
spring.datasource.username=root
spring.datasource.password=yourpassword

Build and run the application:

mvn clean install
mvn spring-boot:run

Access the API documentation at:

http://localhost:8080/swagger-ui/index.html


REST API Endpoints

Policy Controller

  POST /Api/Policies/updateInsuredDetails
  
  POST /Api/Policies/CreatePolicy
  
  GET /Api/Policies/getPolicyDetailsByPolicyNumber/{policyNumber}
  
  GET /Api/Policies/getCoverageDetails/{PolicyNumber}
  
  GET /Api/Policies/getAllPolicies
  
  GET /Api/Policies/cancelPolicy/{PolicyNumber}
  
  GET /Api/Policies/activatePolicy/{PolicyNumber}

Sequence Number Controller

  GET /Api/Policies/generateVehicleId
  
  GET /Api/Policies/generatePolicyNumber
  
  GET /Api/Policies/generateDriverId

Driver Controller

  POST /Api/Driver/updateDriverByPolicyNumberAndDriverId
  
  POST /Api/Driver/addDriverByPolicyNumber
  
  GET /Api/Driver/getDriverDetailsByPolicyNumberAndDriverId
  
  GET /Api/Driver/getAllDriverDetailsByPolicyNumber
  
  DELETE /Api/Driver/DeleteDriverByPolicyNumberAndDriverId

Vehicle Controller
  POST /Api/Vehicle/updateVehicleByPolicyNumberAndVehicleId
  
  POST /Api/Vehicle/addVehicleByPolicyNumber
  
  GET /Api/Vehicle/getVehicleDetailsByPolicyNumberAndVehicleId
  
  GET /Api/Vehicle/getAllVehicleDetailsByPolicyNumber
  
  DELETE /Api/Vehicle/DeleteVehicleByPolicyNumberAndVehicleId
