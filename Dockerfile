#freeboard-service/Dockerfile
FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]
