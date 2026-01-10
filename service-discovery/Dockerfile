# Runtime Dockerfile for Service Discovery (Eureka)
# Build JAR first: mvn clean package -DskipTests -pl service-discovery -am
FROM eclipse-temurin:17-jre

WORKDIR /app

# Add non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy the pre-built jar
COPY target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]

