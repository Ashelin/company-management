# Company Management System

## Overview

The Company Management System is a microservices-based application designed to manage companies, departments, teams, and projects. It provides a set of APIs to handle CRUD operations for companies, departments, teams, and projects, while also allowing retrieval of related entities across different services.

## Features

- **Company Management**: Manage company information and retrieve details about departments and teams associated with each company.
- **Department Management**: Handle departments, including their associated teams and projects.
- **Team Management**: Create and manage teams, and associate them with projects.
- **Project Management**: Manage projects and associate them with teams and managers.

## Architecture

The system is composed of several microservices:

1. **Company Service** (`company-service`): Manages company information and retrieves data from the Department and Team services.
2. **Department Service** (`department-service`): Manages departments and provides information about teams and projects.
3. **Team Service** (`team-service`): Manages teams and retrieves project information.
4. **Project Service** (`project-service`): Manages projects and provides details about managers.

Each service has its own database and communicates with other services using HTTP requests and WebClient.

## Technologies Used

- **Spring Boot**: Framework for building the microservices.
- **Spring Data JPA**: For data persistence and repository management.
- **WebClient**: For making HTTP requests between microservices.
- **Docker**: For containerization and running services in isolated environments.
- **PostgreSQL**: Database management system used in the services.

## Getting Started

### Prerequisites

- Java 21 or higher
- Docker and Docker Compose
- PostgreSQL

### Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/Ashelin/company-management.git
    cd company-management
    ```

2. Navigate to the root directory of the project and start the services using Docker Compose:

    ```bash
    docker-compose up --build
    ```

3. Access the services at the following URLs:

    - Company Service: `http://localhost:8080`
    - Department Service: `http://localhost:8081`
    - Team Service: `http://localhost:8082`
    - Project Service: `http://localhost:8083`

## API Endpoints

### Company Service API

- **Search Companies**
  - `GET /api/company/search`
  - **Response:**
    ```json
    [
      {
        "id": 1,
        "companyName": "WOWcompany co",
        "departments": [
          {
            "departmentId": 1,
            "departmentName": "IT Department",
            "teams": [
              {
                "teamId": 1,
                "teamName": "Development Team",
                "projects": [
                  {
                    "projectId": 1,
                    "projectName": "Epic project",
                    "manager": {
                      "managerName": "John Doe",
                      "email": "john.doe@example.com"
                    }
                  }
                ]
              }
            ]
          }
        ],
        "creationTimestamp": "2024-09-05T14:40:18.567562",
        "modificationTimestamp": "2024-09-05T14:40:18.567621"
      }
    ]
    ```

- **Create Company**
  - `POST /api/company`
  - **Request Body:**
    ```json
    {
      "companyName": "New Company"
    }
    ```
  - **Response:**
    ```http
    HTTP/1.1 201 Created
    ```

- **Update Company**
  - `PUT /api/company/{id}`
  - **Request Body:**
    ```json
    {
      "companyName": "Updated Company Name"
    }
    ```
  - **Response:**
    ```http
    HTTP/1.1 200 OK
    ```

- **Delete Company**
  - `DELETE /api/company/{id}`
  - **Response:**
    ```http
    HTTP/1.1 200 OK
    ```

### Department Service API

- **Get Departments by Company ID**
  - `GET /api/departments?companyId={companyId}`
  - **Response:**
    ```json
    [
      {
        "departmentId": 1,
        "departmentName": "IT Department",
        "teams": []
      }
    ]
    ```

- **Create Department**
  - `POST /api/departments`
  - **Request Body:**
    ```json
    {
      "departmentName": "New Department",
      "companyId": 1
    }
    ```
  - **Response:**
    ```http
    HTTP/1.1 201 Created
    ```

### Team Service API

- **Get Teams by Department ID**
  - `GET /api/teams?departmentId={departmentId}`
  - **Response:**
    ```json
    [
      {
        "teamId": 1,
        "teamName": "Development Team",
        "projects": []
      }
    ]
    ```

- **Create Team**
  - `POST /api/teams`
  - **Request Body:**
    ```json
    {
      "teamName": "New Team",
      "departmentId": 1
    }
    ```
  - **Response:**
    ```http
    HTTP/1.1 201 Created
    ```

### Project Service API

- **Get Projects by Team ID**
  - `GET /api/projects?teamId={teamId}`
  - **Response:**
    ```json
    [
      {
        "projectId": 1,
        "projectName": "Epic project",
        "manager": {
          "managerName": "John Doe",
          "email": "john.doe@example.com"
        }
      }
    ]
    ```

- **Create Project**
  - `POST /api/projects`
  - **Request Body:**
    ```json
    {
      "projectName": "New Project",
      "teamId": 1,
      "manager": {
        "managerName": "Jane Smith",
        "email": "jane.smith@example.com"
      }
    }
    ```
  - **Response:**
    ```http
    HTTP/1.1 201 Created
    ```

## Contact

For any questions or issues, please open an issue on the GitHub repository or contact the project maintainers.

Feel free to adjust any sections or details as needed based on your specific project requirements!
