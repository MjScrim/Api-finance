FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
ENV JAR_NAME=scrimcrm-api-0.0.1-SNAPSHOT.jar
COPY --from=build /app/target/$JAR_NAME $JAR_NAME
EXPOSE 8080
CMD java -jar $JAR_NAME