# Use the Maven image with JDK 17 to build the project
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory and copy necessary files
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

# Run Maven to build the application, skipping tests
RUN mvn clean package -DskipTests

# Use OpenJDK 17 as the base image for running the application
FROM openjdk:17-ea-15-jdk-oracle

# Copy the JAR file from the build stage to the final image
COPY --from=build /app/target/chargeSquare-0.0.1-SNAPSHOT.jar /app/chargeSquare.jar

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/chargeSquare.jar"]
