# Step 1: Build the application
# Use a Maven image with the correct JDK version to build the Java application
FROM maven:3.9.0-amazoncorretto-17 as build
WORKDIR /app

# Copy the project files into the Docker image
COPY src /app/src
COPY pom.xml /app

# Run Maven build
RUN mvn clean package -DskipTests

# Step 2: Create the runtime image
# Use a base image with Amazon Corretto JDK 17 to run the application
FROM amazoncorretto:17
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/target/*.jar /app/application.jar

# Expose the port the application uses
EXPOSE 8080

# Set the default command to run the Java application
CMD ["java", "-Xmx2048M", "-jar", "application.jar"]
