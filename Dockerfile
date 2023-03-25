FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/backend-doklad-0.0.1-SNAPSHOT.jar /app
COPY pom.xml /app

RUN apt-get update && apt-get install -y maven
RUN mvn package

EXPOSE 8080

CMD ["java", "-jar", "backend-doklad-0.0.1-SNAPSHOT.jar"]