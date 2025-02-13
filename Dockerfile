# Step 1: Use an official Java runtime as a parent image

#FROM openjdk:17-jdk-slim
FROM eclipse-temurin:21

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file from target folder (after build) into the container
COPY target/Car-Insurance-Application-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the application port (e.g., 8080)
EXPOSE 8080

# Step 5: Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
#car-insurance-application