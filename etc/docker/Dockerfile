FROM openjdk:17-jdk-slim
COPY target/prices-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]