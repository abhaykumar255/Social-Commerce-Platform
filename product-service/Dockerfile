# Runtime Dockerfile for Product Service
# Build JAR first: mvn clean package -DskipTests -pl product-service -am
FROM eclipse-temurin:17-jre

WORKDIR /app

# Add non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

# Copy the pre-built jar
COPY target/*.jar app.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "app.jar"]

