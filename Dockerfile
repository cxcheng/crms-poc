FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/crms-poc-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} crms-poc.jar
ENTRYPOINT ["java", "-jar", "/crms-poc.jar"]