# Runtime Dockerfile for User Service
# Build JAR first: mvn clean package -DskipTests -pl user-service -am
FROM eclipse-temurin:17-jre

WORKDIR /app

# Add non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy the pre-built jar
COPY target/*.jar app.jar

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]

