# ---------------------------------
# 🔧 Build Stage: Compiling the App
# ---------------------------------

# Use a Maven image with OpenJDK 17 to compile the Java application
FROM maven:3.8.4-openjdk-17-slim AS build
CMD ["echo", "Build - Completed FROM"]

# Set the working directory inside the container
WORKDIR /app
CMD ["echo", "Build - Completed WORKDIR"]

# Copy only the pom.xml to leverage Docker cache for dependency resolution
COPY pom.xml .
CMD ["echo", "Build - Completed pom COPY"]

# Download project dependencies (without compiling the full source yet)
RUN mvn dependency:go-offline
CMD ["echo", "Build - Completed pom DEPENDENCIES"]

# Copy the source code after dependencies are cached
COPY src ./src
CMD ["echo", "Build - Completed src COPY"]

# Compile and package the application into a .jar file (skipping tests to speed up build)
RUN mvn clean package -DskipTests
CMD ["echo", "Build - Completed jar"]

# ----------------------------------------------
# 🚀 Runtime Stage: Minimal Image to Run the App
# ----------------------------------------------

#Use a lightweight OpenJDK 17 image for running the application
FROM openjdk:17-jdk-slim
CMD ["echo", "Run - Completed FROM"]

#Set the working directory inside the runtime container
WORKDIR /app
CMD ["echo", "Run - Completed WORKDIR"]

#Copy the built .jar file from the build stage into the runtime image
COPY --from=build /app/target/Whispers-API-0.0.1-SNAPSHOT.jar app.jar
CMD ["echo", "Run - Completed jar COPY"]

#(Optional) Document the port your app listens on (Spring Boot default)
EXPOSE 8080

#Define the default command to run when the container starts
CMD ["java", "-jar", "app.jar"]
CMD ["echo", "Run - Container started."]