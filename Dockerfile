FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]