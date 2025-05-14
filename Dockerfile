# Stage 1: Build the application
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (caching layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src /app/src
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Set environment variables for PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/yourdb
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=yourpassword
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]