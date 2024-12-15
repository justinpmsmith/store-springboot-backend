# Use a base image with Java
FROM openjdk:17-jdk-alpine

ARG version

RUN mkdir -p /allianceSeeds/api/

COPY ./target/toolbox-${version}.jar /allianceSeeds/api/app.jar

WORKDIR /allianceSeeds/api/

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
