# Use an official OpenJDK image as a build stage
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use a smaller JDK image to run the JAR
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/school-admission-system.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
