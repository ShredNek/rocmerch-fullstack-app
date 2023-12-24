
FROM openjdk:17
WORKDIR /app
COPY ./target/rocmerch-backend-0.0.1-SNAPSHOT.jar /app/rocmerch-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rocmerch-backend.jar"]
