FROM maven:3.9.7-amazoncorretto-17 AS dependencies

WORKDIR /app/msvc-users

COPY ./pom.xml /app
COPY ./msvc-users/.mvn /.mvn.
COPY ./msvc-users/mvnw .
COPY ./msvc-users/pom.xml .

RUN mvn dependency:go-offline -B

FROM maven:3.9.7-amazoncorretto-17 AS build

WORKDIR /app/msvc-users

COPY --from=dependencies /root/.m2 /root/.m2
COPY ./pom.xml /app
COPY ./msvc-users/src ./src
COPY ./msvc-users/pom.xml .

RUN mvn clean package -DskipTests

FROM amazoncorretto:17 AS prod

WORKDIR /app

EXPOSE 8001

RUN mkdir ./logs

COPY --from=build /app/msvc-users/target/msvc-users-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "/app/msvc-users-0.0.1-SNAPSHOT.jar"]