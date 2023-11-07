#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
#COPY target/Spit.API-0.0.1-SNAPSHOT.jar /app/app.jar
#
#EXPOSE 8080
#
#CMD ["java", "-jar", "app.jar"]
# Use an image that has both Maven and JDK (adjust the version as needed)
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project definition and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Build the application with skipping tests
RUN mvn package install -DskipTests

# Use a lightweight image with JRE
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/Spit.API-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
