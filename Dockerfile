# BUILD
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# RUN
FROM eclipse-temurin:21-jre-alpine

RUN mkdir -p /var/log/horadoremedio

RUN adduser -D -u 1001 horadoremedio
RUN chown -R horadoremedio:horadoremedio /var/log/horadoremedio
RUN chmod 700 /var/log/horadoremedio

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

USER horadoremedio

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]