# Stage 1: Build the application
FROM maven:3-openjdk-17-slim AS builder
WORKDIR /app

# Copy the project definition file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-slim

WORKDIR /app

# Create necessary directories
RUN mkdir /log /config

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/geo-0.0.1-SNAPSHOT.jar /app/geo-0.0.1-SNAPSHOT.jar

# Set environment variables
ENV JAR=geo-0.0.1-SNAPSHOT.jar
ENV DEFAULT_OPTIONS="-Duser.timezone=America/Guayaquil -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom"

# Create the startup script
RUN echo '#!/bin/sh' > /app/start.sh && \
    echo 'exec java $DEFAULT_OPTIONS -jar /app/$JAR' >> /app/start.sh && \
    chmod +x /app/start.sh

# Define the entry point
ENTRYPOINT ["/app/start.sh"]
