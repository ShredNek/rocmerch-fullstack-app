
FROM openjdk:17
COPY ./target/rocmerch-backend-0.0.1-SNAPSHOT.jar /app/rocmerch-backend.jar
WORKDIR /app
EXPOSE 8080/tcp  # Expose tcp port 8080 for the application to run
ENTRYPOINT ["java", "-jar", "rocmerch-backend.jar"]
