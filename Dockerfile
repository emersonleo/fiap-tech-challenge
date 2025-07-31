# BUILD
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# RUN
FROM eclipse-temurin:21-jre-alpine

RUN mkdir -p /var/log/chefonline

RUN adduser -D -u 1001 chefonline
RUN chown -R chefonline:chefonline /var/log/chefonline
RUN chmod 700 /var/log/chefonline

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

USER chefonline

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]