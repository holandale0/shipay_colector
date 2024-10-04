FROM eclipse-temurin:17.0.10_7-jre-alpine AS run

ARG JAR_FILE=*.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Xmx1024m","-jar","/app.jar"]