FROM openjdk:11-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 443
ENTRYPOINT ["java","-jar","/app.jar"]