# Use the official OpenJDK 17 from Docker Hub
FROM openjdk:23
#Set working directory inside the container
WORKDIR /app
COPY target/school-admission-system.jar /app

EXPOSE 8080

CMD ["java", "-jar","school-admission-system.jar"]