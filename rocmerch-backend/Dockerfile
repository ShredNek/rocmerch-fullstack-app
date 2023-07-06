FROM openjdk:17
COPY ./target/rocmerch-backend-0.0.1-SNAPSHOT.jar /app/rocmerch-backend.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "rocmerch-backend.jar"]