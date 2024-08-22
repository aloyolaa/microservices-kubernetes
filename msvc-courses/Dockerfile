FROM maven:3.9.7-amazoncorretto-17 AS dependencies

WORKDIR /app/msvc-courses

COPY ./pom.xml /app
COPY ./msvc-courses/.mvn /.mvn.
COPY ./msvc-courses/mvnw .
COPY ./msvc-courses/pom.xml .

RUN mvn dependency:go-offline -B

FROM maven:3.9.7-amazoncorretto-17 AS build

WORKDIR /app/msvc-courses

COPY --from=dependencies /root/.m2 /root/.m2
COPY ./pom.xml /app
COPY ./msvc-courses/src ./src
COPY ./msvc-courses/pom.xml .

RUN mvn clean package -DskipTests

FROM amazoncorretto:17 AS prod

WORKDIR /app

EXPOSE 8001

RUN mkdir ./logs

COPY --from=build /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/app/msvc-courses-0.0.1-SNAPSHOT.jar"]