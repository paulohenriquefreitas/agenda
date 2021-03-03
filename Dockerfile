FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} agenda-1.0.0.jar
ENTRYPOINT ["java","-jar","/agenda-1.0.0.jar"]