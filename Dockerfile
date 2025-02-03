# Use an official Maven image for the build process
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a smaller JDK image to run the built application
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/school-admission-system.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
