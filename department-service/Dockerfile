FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/department-service-0.0.1-SNAPSHOT.jar department-service.jar
ENTRYPOINT ["java", "-jar", "/department-service.jar"]
EXPOSE 8081