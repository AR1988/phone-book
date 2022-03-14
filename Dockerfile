# syntax=docker/dockerfile:1
FROM maven as base
WORKDIR /app
COPY pom.xml mvnw ./
COPY src ./src

FROM node:17.4.0-alpine3.14 as base-ui
ENV PATH /app/node_modules/.bin:$PATH
WORKDIR /app
COPY --from=base app/src/main/webapp/ ./

FROM base-ui as build-ui
RUN npm ci --silent
RUN npm run build

FROM base as build-api
WORKDIR /app
COPY --from=build-ui /app/dist/webapp /app/src/main/resources/static
RUN mvn clean package -DskipTests spring-boot:repackage

FROM openjdk:11-jre-slim as prod
WORKDIR /app
EXPOSE 8080
COPY --from=build-api /app/target/phonebook-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]