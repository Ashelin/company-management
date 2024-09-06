FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/company-service-0.0.1-SNAPSHOT.jar company-service.jar
ENTRYPOINT ["java", "-jar", "/company-service.jar"]
EXPOSE 8080