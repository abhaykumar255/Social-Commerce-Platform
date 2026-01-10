# Runtime Dockerfile for Social Service
# Build JAR first: mvn clean package -DskipTests -pl social-service -am
FROM eclipse-temurin:17-jre

WORKDIR /app

# Add non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy the pre-built jar
COPY target/*.jar app.jar

EXPOSE 9005

ENTRYPOINT ["java", "-jar", "app.jar"]

