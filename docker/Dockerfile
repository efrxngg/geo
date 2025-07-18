FROM maven:3-openjdk-17-slim AS builder

# Build dependency offline to streamline build
RUN mkdir project
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -Dmaven.test.skip=true
# compute the created jar name and put it in a known location to copy to the next layer.
# If the user changes pom.xml to have a different version, or artifactId, this will find the jar
RUN grep version target/maven-archiver/pom.properties | cut -d '=' -f2 >.env-version
RUN grep artifactId target/maven-archiver/pom.properties | cut -d '=' -f2 >.env-id
RUN mv target/$(cat .env-id)-$(cat .env-version).jar target/export-run-artifact.jar

# Stage 2: Create the final image
FROM openjdk:17-slim

WORKDIR /app

# Create necessary directories
RUN mkdir /log /config

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/export-run-artifact.jar /app/export-run-artifact.jar

# Copy the configuration files
COPY config/ /config

# Set environment variables
ENV JAR=export-run-artifact.jar
ENV DEFAULT_OPTIONS="-Duser.timezone=America/Guayaquil -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom"

# Create the startup script
RUN echo '#!/bin/sh' > /app/start.sh && \
    echo 'exec java $DEFAULT_OPTIONS -jar /app/$JAR' >> /app/start.sh && \
    chmod +x /app/start.sh

# Define the entry point
ENTRYPOINT ["/app/start.sh"]


