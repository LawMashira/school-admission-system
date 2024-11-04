# Use the official OpenJDK 17 from Docker Hub
FROM openjdk:23
#Set working directory inside the container

COPY target/school-admission-system.jar /app
WORKDIR /app
EXPOSE 8080

CMD ["java", "-jar","school-admission-system.jar"]