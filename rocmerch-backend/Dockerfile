# Use the official maven/Java 17 image to create a build artifact
FROM maven:3.8.4-openjdk-17 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use the Official OpenJDK image for a lean production stage of our multi-stage build.
FROM eclipse-temurin:17-jdk

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/rocmerch-backend-0.0.1-SNAPSHOT.jar /rocmerch-backend.jar

# Run the web service on container startup.
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/rocmerch-backend.jar"]
