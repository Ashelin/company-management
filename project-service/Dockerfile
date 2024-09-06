FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/project-service-0.0.1-SNAPSHOT.jar project-service.jar
ENTRYPOINT ["java", "-jar", "/project-service.jar"]
EXPOSE 8083