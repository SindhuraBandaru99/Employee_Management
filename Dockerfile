# Use a suitable base image with Java support
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/spring-0.0.1-SNAPSHOT.jar /app

# Expose the port on which the Spring Boot application runs
EXPOSE 8080

# Define the command to run the Spring Boot application
CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]
