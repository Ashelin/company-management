FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY target/team-service-0.0.1-SNAPSHOT.jar team-service.jar
ENTRYPOINT ["java", "-jar", "/team-service.jar"]
EXPOSE 8082
