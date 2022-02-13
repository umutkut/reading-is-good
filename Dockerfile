FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ${JSON_FILE} ./
ENTRYPOINT ["java","-jar","/app.jar"]