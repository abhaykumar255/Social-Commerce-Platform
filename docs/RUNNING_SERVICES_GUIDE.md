# Running Services Guide

This guide explains how to run services in different environments and configure Spring profiles.

---

## 📋 Quick Reference

| Environment | Profile | Command |
|-------------|---------|---------|
| Local Dev | `local` | `mvn spring-boot:run -Dspring-boot.run.profiles=local` |
| Docker Dev | `docker` | `SPRING_PROFILES_ACTIVE=docker mvn spring-boot:run` |
| Production | `prod` | Set via environment variable |

---

## 1. Understanding Spring Profiles

Spring profiles allow different configurations for different environments:

```
application.yml          → Default/shared config (always loaded)
application-local.yml    → Local development (your machine)
application-docker.yml   → Docker environment
application-prod.yml     → Production environment
```

**Loading Order**: `application.yml` loads first, then profile-specific file overrides values.

---

## 2. Setting SPRING_PROFILES_ACTIVE

### Method 1: Command Line Argument (Recommended for Dev)

```bash
# Using Maven
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Using Java JAR
java -jar target/user-service.jar --spring.profiles.active=local

# Multiple profiles
mvn spring-boot:run -Dspring-boot.run.profiles=local,debug
```

### Method 2: Environment Variable (Recommended for Docker/Prod)

```bash
# Linux/Mac - Terminal
export SPRING_PROFILES_ACTIVE=docker
mvn spring-boot:run

# Linux/Mac - Single command
SPRING_PROFILES_ACTIVE=docker mvn spring-boot:run

# Windows - Command Prompt
set SPRING_PROFILES_ACTIVE=docker
mvn spring-boot:run

# Windows - PowerShell
$env:SPRING_PROFILES_ACTIVE="docker"
mvn spring-boot:run
```

### Method 3: IDE Configuration

#### IntelliJ IDEA
1. Go to **Run** → **Edit Configurations**
2. Select your Spring Boot application
3. In **Active profiles** field, enter: `local`
4. Or in **Environment variables**, add: `SPRING_PROFILES_ACTIVE=local`

#### VS Code
Add to `.vscode/launch.json`:
```json
{
  "configurations": [
    {
      "type": "java",
      "name": "UserService-Local",
      "request": "launch",
      "mainClass": "com.social.user.UserServiceApplication",
      "env": {
        "SPRING_PROFILES_ACTIVE": "local"
      }
    }
  ]
}
```

### Method 4: Docker Compose

```yaml
services:
  commerce-users-service:
    environment:
      SPRING_PROFILES_ACTIVE: docker
      # Other env variables...
```

### Method 5: Dockerfile

```dockerfile
ENV SPRING_PROFILES_ACTIVE=docker
```

### Method 6: application.yml (Default Profile)

```yaml
spring:
  profiles:
    active: local  # Default if nothing else is set
```

> ⚠️ **Priority Order** (highest to lowest):
> 1. Command line argument (`--spring.profiles.active=`)
> 2. Environment variable (`SPRING_PROFILES_ACTIVE`)
> 3. `application.yml` default

---

## 3. Running Services - Step by Step

### 3.1 Local Development

**Prerequisites:**
- PostgreSQL running on localhost:5432
- Database created
- Redis running (if needed)

```bash
# Step 1: Create database
psql -U postgres -c "CREATE DATABASE commerce_users_db;"

# Step 2: Navigate to service
cd user-service

# Step 3: Run with local profile
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Alternative: Using environment variable
export SPRING_PROFILES_ACTIVE=local
mvn spring-boot:run
```

**Verify:**
```bash
curl http://localhost:9001/actuator/health
curl http://localhost:9001/actuator/env | grep "active"
```

### 3.2 Docker Development

**Prerequisites:**
- Docker containers running (postgres, redis, etc.)

```bash
# Step 1: Start infrastructure
cd docker
docker-compose -f docker-compose-dev.yml up -d

# Step 2: Run service with docker profile
cd ../user-service
mvn spring-boot:run -Dspring-boot.run.profiles=docker

# Or using environment variable
SPRING_PROFILES_ACTIVE=docker mvn spring-boot:run
```

### 3.3 Running Multiple Services

**Terminal 1 - Infrastructure:**
```bash
cd docker
docker-compose -f docker-compose-dev.yml up -d
```

**Terminal 2 - Service Discovery:**
```bash
cd service-discovery
mvn spring-boot:run
```

**Terminal 3 - Config Server (Optional):**
```bash
cd config-server
mvn spring-boot:run
```

**Terminal 4+ - Microservices:**
```bash
# Each in separate terminal
cd user-service && mvn spring-boot:run -Dspring-boot.run.profiles=local
cd product-service && mvn spring-boot:run -Dspring-boot.run.profiles=local
cd order-service && mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### 3.4 Running as JAR

```bash
# Build
cd user-service
mvn clean package -DskipTests

# Run
java -jar target/user-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=local

# With environment variable
SPRING_PROFILES_ACTIVE=local java -jar target/user-service-0.0.1-SNAPSHOT.jar
```

---

## 4. Testing Profile Configuration

### 4.1 Check Active Profile

```bash
# Via actuator endpoint
curl http://localhost:9001/actuator/env | jq '.activeProfiles'

# Or check logs at startup - look for:
# "The following profiles are active: local"
```

### 4.2 Check Loaded Configuration

```bash
# All environment properties
curl http://localhost:9001/actuator/env

# Specific property
curl http://localhost:9001/actuator/env/spring.datasource.url
```

### 4.3 Test Database Connection

```bash
# Health check shows DB status
curl http://localhost:9001/actuator/health | jq '.components.db'
```

---

## 5. Environment-Specific Configuration

### 5.1 What Goes Where

| Configuration | application.yml | application-local.yml | application-docker.yml |
|--------------|-----------------|----------------------|------------------------|
| Service name | ✅ | ❌ | ❌ |
| Server port | ✅ (with env var) | ❌ | ❌ |
| JPA settings | ✅ | ❌ | ❌ |
| Eureka URL | ❌ | ✅ localhost | ✅ container name |
| DB URL | ❌ | ✅ localhost | ✅ container name |
| Logging level | ✅ (default) | ✅ DEBUG | ✅ INFO |

### 5.2 Example Configurations

**application.yml** (shared):
```yaml
spring:
  application:
    name: commerce-users-service
  jpa:
    hibernate:
      ddl-auto: update
```

**application-local.yml**:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/commerce_users_db
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
logging:
  level:
    com.social: DEBUG
```

**application-docker.yml**:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://postgres-users:5432/commerce_users_db
eureka:
  client:
    service-url:
      defaultZone: http://commerce-service-discovery:8761/eureka/
logging:
  level:
    com.social: INFO
```

---

## 6. Common Scenarios

### 6.1 Override Single Property

```bash
# Override database URL
mvn spring-boot:run -Dspring-boot.run.profiles=local \
  -Dspring.datasource.url=jdbc:postgresql://192.168.1.100:5432/mydb
```

### 6.2 Run Without Eureka (Standalone)

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local \
  -Deureka.client.enabled=false
```

### 6.3 Run With Debug Logging

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local \
  -Dlogging.level.com.social=DEBUG
```

### 6.4 Run With Different Port

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local \
  -Dserver.port=9099
```

---

## 7. Troubleshooting

### Profile Not Loading

**Symptom:** Service uses wrong configuration
**Check:**
```bash
# Look for this line in startup logs
grep "The following profiles are active" logs/app.log
```

### Database Connection Failed

**Symptom:** `Connection refused` error
**Check:**
```bash
# Verify database is running
psql -U postgres -h localhost -p 5432 -c "SELECT 1"

# Check URL in your profile file matches
cat src/main/resources/application-local.yml | grep url
```

### Eureka Registration Failed

**Symptom:** Service not visible in Eureka dashboard
**Check:**
```bash
# Verify Eureka is running
curl http://localhost:8761/actuator/health

# Check Eureka URL in config
cat src/main/resources/application-local.yml | grep defaultZone
```

---

## 8. Quick Commands Cheat Sheet

```bash
# ===== LOCAL DEVELOPMENT =====
mvn spring-boot:run -Dspring-boot.run.profiles=local

# ===== DOCKER DEVELOPMENT =====
SPRING_PROFILES_ACTIVE=docker mvn spring-boot:run

# ===== CHECK ACTIVE PROFILE =====
curl -s localhost:9001/actuator/env | grep -A2 "activeProfiles"

# ===== CHECK HEALTH =====
curl localhost:9001/actuator/health

# ===== RUN AS JAR =====
java -jar target/*.jar --spring.profiles.active=local

# ===== RUN WITH MULTIPLE PROFILES =====
mvn spring-boot:run -Dspring-boot.run.profiles=local,debug

# ===== OVERRIDE PROPERTY =====
mvn spring-boot:run -Dspring-boot.run.profiles=local -Dserver.port=9099
```

---

## 9. IDE Quick Setup

### IntelliJ IDEA - Create Run Configurations

1. **Run** → **Edit Configurations** → **+** → **Spring Boot**
2. Create these configurations:

| Name | Main Class | Active Profiles |
|------|-----------|-----------------|
| UserService-Local | `com.social.user.UserServiceApplication` | `local` |
| UserService-Docker | `com.social.user.UserServiceApplication` | `docker` |
| ProductService-Local | `com.social.product.ProductServiceApplication` | `local` |

### VS Code - Create Launch Configurations

Add to `.vscode/launch.json`:
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Users-Local",
      "request": "launch",
      "mainClass": "com.social.user.UserServiceApplication",
      "projectName": "user-service",
      "env": {"SPRING_PROFILES_ACTIVE": "local"}
    },
    {
      "type": "java",
      "name": "Products-Local",
      "request": "launch", 
      "mainClass": "com.social.product.ProductServiceApplication",
      "projectName": "product-service",
      "env": {"SPRING_PROFILES_ACTIVE": "local"}
    }
  ]
}
```

---

*Last Updated: January 2026*

