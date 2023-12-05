FROM eclipse-temurin:17.0.9_9-jre

#FROM bellsoft/liberica-openjdk-alpine-musl:17
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY input.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
