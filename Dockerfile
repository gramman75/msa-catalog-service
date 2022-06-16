FROM openjdk:11.0-jdk-slim-buster

VOLUME /temp

COPY target/catalog-service-1.0.jar catalog-service.jar

ENTRYPOINT ["java","-jar","catalog-service.jar"]