FROM openjdk:17-alpine as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw clean package

FROM openjdk:17-alpine as run
COPY --from=build target/KameleoonTrialTask-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]