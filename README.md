# chargeSquare

`chargeSquare` is a Spring Boot application that provides RESTful APIs for managing charging stations. This project is containerized using Docker and deployed on a Kubernetes cluster managed via Helm.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Building the Application](#building-the-application)
- [Running the Application](#running-the-application)
- [Testing the Application](#testing-the-application)
- [CI/CD Pipelines](#cicd-pipelines)
- [Postman Collection](#postman-collection)

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- JDK 17
- Maven
- Docker
- Kubernetes (Minikube or any Kubernetes cluster)
- `kubectl`
- Helm
- Postman (for API testing)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/adilylz07/chargeSquare.git
    cd chargeSquare
    ```

## Building the Application

1. Use Maven to build the Spring Boot application without running the tests:
    ```sh
    mvn clean package -DskipTests
    ```

## Running the Application

### Running Locally

1. Run the application using Maven:
    ```sh
    mvn spring-boot:run
    ```

2. The application will start and be accessible at:
    ```
    http://localhost:8083/stations
    ```

### Running with Docker

1. Build the Docker image:
    ```sh
    docker build -t chargesquare:latest .
    ```

2. Tag and push the Docker image to Docker Hub:
    ```sh
    docker tag chargesquare:latest <your-dockerhub-username>/chargesquare:latest
    docker push <your-dockerhub-username>/chargesquare:latest
    ```

### Deploying on Kubernetes using Helm

1. Package the Helm chart (if needed):
    ```sh
    helm package ./chargeHelm
    ```

2. Deploy or upgrade the application using Helm:
    ```sh
    helm upgrade chargesquare ./chargeHelm --namespace chargesquare
    ```

3. Verify the deployment:
    ```sh
    kubectl get pods -n chargesquare
    kubectl get svc -n chargesquare
    ```

4. Forward the service port for local access:
    ```sh
    kubectl port-forward service/chargesquare 8083:8083 -n chargesquare
    ```

5. Access the API locally:
    ```
    http://localhost:8083/stations
    ```

## Testing the Application

1. Use Postman or `curl` to test the REST API. For example:
    ```sh
    curl -X GET http://localhost:8083/stations
    ```

2. You should receive a JSON response with the requested data.

## CI/CD Pipelines

The project uses GitHub Actions for Continuous Integration and Continuous Deployment (CI/CD). The pipeline includes the following stages:

### Lint
- Checks the code quality using Maven Checkstyle.
- Ensures that the code adheres to the project's coding standards.
- Command: `mvn checkstyle:check`.

### Test
- Runs unit tests to verify the correctness of the application.
- Command: `mvn test`.

### Build
- Builds the project and creates a JAR file using Maven.
- Command: `mvn clean package -DskipTests`.

### Deploy
- Deploys the application to a Kubernetes cluster using Helm.
- Uses `kubectl` and Helm to configure and deploy the application.
- Verifies the deployment by ensuring the pods and services are running.

For more details, refer to the `.github/workflows` directory in the repository.

## Postman Collection

A Postman collection is available for testing the API. Follow these steps to use it:

1. Download the Postman collection file [here](https://github.com/adilylz07/chargeSquare/blob/main/chargeSquare.postman_collection.json).
2. Open Postman and import the collection:
   - Click on `Import` in the top-left corner.
   - Select `File` and upload the downloaded `chargeSquare.postman_collection.json` file.
3. Use the imported collection to test the available API endpoints.

## Notes

- Ensure that Kafka and Zookeeper are running if your application depends on them.
- Update `application.yml` or environment variables to match your deployment configuration.
- Replace `<your-dockerhub-username>` with your actual Docker Hub username in the deployment YAML or Helm chart.

For further questions or contributions, feel free to create an issue or submit a pull request to the repository.

