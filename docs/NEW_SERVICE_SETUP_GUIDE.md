# New Service Setup Guide

This guide provides a checklist for creating and configuring a new microservice in the Social Commerce Platform.

---

## 📋 Quick Checklist

- [ ] Choose service name following naming convention
- [ ] Create database with proper naming
- [ ] Create `application.yml` with correct service name
- [ ] Create `application-local.yml` for local development
- [ ] Create `application-docker.yml` for Docker environment
- [ ] Create `bootstrap.yml` (optional, for Config Server)
- [ ] Add config file in `config-server/src/main/resources/config/`
- [ ] Register route in API Gateway
- [ ] Add fallback endpoint in API Gateway
- [ ] Update `docker-compose-dev.yml`
- [ ] Add Feign clients if inter-service communication needed

---

## 1. Naming Conventions

### Service Name
```
Pattern: commerce-{domain}-service
Examples:
  - commerce-users-service
  - commerce-products-service
  - commerce-orders-service
  - commerce-payments-service
  - commerce-notifications-service
  - commerce-social-service
```

### Database Name
```
Pattern: commerce_{domain}_db
Examples:
  - commerce_users_db
  - commerce_products_db
  - commerce_orders_db
```

### Port Allocation
| Service | Port |
|---------|------|
| Config Server | 8888 |
| Service Discovery | 8761 |
| API Gateway | 8080 |
| Users Service | 9001 |
| Products Service | 9002 |
| Orders Service | 9003 |
| Payments Service | 9004 |
| Social Service | 9005 |
| Notifications Service | 9006 |
| **Next Available** | **9007+** |

---

## 2. Configuration Files

### 2.1 `application.yml` (Main Config)

```yaml
server:
  port: ${SERVER_PORT:90XX}  # Replace XX with your port

spring:
  application:
    name: commerce-{domain}-service  # MUST match config file name
  config:
    import: "optional:configserver:${CONFIG_SERVER_URL:http://localhost:8888}"

  # For PostgreSQL
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/commerce_{domain}_db}
    username: ${SPRING_DATASOURCE_USERNAME:postgres}
    password: ${SPRING_DATASOURCE_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver

  # For MongoDB (if needed)
  # data:
  #   mongodb:
  #     uri: ${MONGODB_URI:mongodb://localhost:27017/commerce_{domain}_db}

  jpa:
    hibernate:
      ddl-auto: ${JPA_DDL_AUTO:update}
    show-sql: false

# Eureka Client
eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_SERVER_URL:http://localhost:8761/eureka/}
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${random.uuid}

# Actuator
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
```

### 2.2 `application-local.yml`

```yaml
# Local Development Profile
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/commerce_{domain}_db
    username: postgres
    password: postgres

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

logging:
  level:
    com.social: DEBUG
```

### 2.3 `application-docker.yml`

```yaml
# Docker Profile
spring:
  datasource:
    url: jdbc:postgresql://postgres-{domain}:5432/commerce_{domain}_db
    username: postgres
    password: postgres

eureka:
  client:
    service-url:
      defaultZone: http://commerce-service-discovery:8761/eureka/
```

### 2.4 `bootstrap.yml` (Optional - for Config Server priority)

```yaml
spring:
  application:
    name: commerce-{domain}-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: false
      enabled: false  # Set true to require Config Server
```

---

## 3. Config Server Setup

Create file: `config-server/src/main/resources/config/commerce-{domain}-service.yml`

```yaml
# Configuration for commerce-{domain}-service
server:
  port: ${SERVER_PORT:90XX}

spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/commerce_{domain}_db}
    username: ${SPRING_DATASOURCE_USERNAME:postgres}
    password: ${SPRING_DATASOURCE_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: ${JPA_DDL_AUTO:update}

# Add service-specific config here
```

> ⚠️ **IMPORTANT**: The filename MUST match `spring.application.name` exactly!

---

## 4. API Gateway Setup

### 4.1 Add Route in `GatewayConfig.java`

Location: `api-gateway/src/main/java/com/social/gateway/config/GatewayConfig.java`

```java
// {Domain} Service Routes
.route("commerce-{domain}-service", r -> r
        .path("/api/v1/{domain}/**")
        .filters(f -> f
                .stripPrefix(0)
                .addRequestHeader("X-Gateway", "API-Gateway")
                .circuitBreaker(config -> config
                        .setName("{domain}ServiceCircuitBreaker")
                        .setFallbackUri("forward:/fallback/{domain}-service")))
        .uri("lb://commerce-{domain}-service"))
```

### 4.2 Add Fallback in `FallbackController.java`

Location: `api-gateway/src/main/java/com/social/gateway/controller/FallbackController.java`

```java
@GetMapping("/{domain}-service")
public ResponseEntity<ApiResponse<String>> {domain}ServiceFallback() {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ApiResponse.error("{Domain} service is temporarily unavailable."));
}
```

---

## 5. Docker Configuration

### 5.1 Add Database in `docker-compose-dev.yml`

```yaml
# PostgreSQL for {Domain} Service
postgres-{domain}:
  image: postgres:15-alpine
  container_name: postgres-{domain}-dev
  environment:
    POSTGRES_DB: commerce_{domain}_db
    POSTGRES_USER: postgres
    POSTGRES_PASSWORD: postgres
  ports:
    - "54XX:5432"  # Use unique port
  volumes:
    - postgres_{domain}_data:/var/lib/postgresql/data
  networks:
    - social-commerce-network
  healthcheck:
    test: ["CMD-SHELL", "pg_isready -U postgres -d commerce_{domain}_db"]
    interval: 10s
    timeout: 5s
    retries: 5
```

### 5.2 Add Volume

```yaml
volumes:
  # ... existing volumes
  postgres_{domain}_data:
```

### 5.3 Add Service (Optional)

```yaml
commerce-{domain}-service:
  build:
    context: ../{domain}-service
    dockerfile: Dockerfile
  container_name: commerce-{domain}-service-dev
  environment:
    SPRING_PROFILES_ACTIVE: docker
    SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-{domain}:5432/commerce_{domain}_db
    SPRING_DATASOURCE_USERNAME: postgres
    SPRING_DATASOURCE_PASSWORD: postgres
    EUREKA_SERVER_URL: http://commerce-service-discovery:8761/eureka/
  ports:
    - "90XX:90XX"
  depends_on:
    postgres-{domain}:
      condition: service_healthy
  networks:
    - social-commerce-network
```

---

## 6. Inter-Service Communication (Feign Clients)

If your service needs to call other services, create Feign clients:

```java
@FeignClient(name = "commerce-{target}-service", path = "/api/v1/{target}")
public interface {Target}Client {
    
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<{Target}DTO>> get{Target}ById(@PathVariable Long id);
}
```

> ⚠️ **IMPORTANT**: The `name` must match the target service's `spring.application.name`

---

## 7. Database Setup

### Local PostgreSQL
```sql
-- Connect to PostgreSQL
psql -U postgres

-- Create database
CREATE DATABASE commerce_{domain}_db;

-- Verify
\l

-- Connect to the database
\c commerce_{domain}_db
```

### Local MongoDB
```javascript
// Connect to MongoDB
mongosh

// Create database (created automatically on first insert)
use commerce_{domain}_db

// Create a collection
db.createCollection("sample")
```

---

## 8. Environment Variables Reference

| Variable | Description | Default |
|----------|-------------|---------|
| `SERVER_PORT` | Service port | 90XX |
| `SPRING_DATASOURCE_URL` | Database URL | jdbc:postgresql://localhost:5432/... |
| `SPRING_DATASOURCE_USERNAME` | DB username | postgres |
| `SPRING_DATASOURCE_PASSWORD` | DB password | postgres |
| `EUREKA_SERVER_URL` | Eureka server | http://localhost:8761/eureka/ |
| `CONFIG_SERVER_URL` | Config server | http://localhost:8888 |
| `REDIS_HOST` | Redis host | localhost |
| `REDIS_PORT` | Redis port | 6379 |
| `KAFKA_BOOTSTRAP_SERVERS` | Kafka servers | localhost:9092 |
| `MONGODB_URI` | MongoDB URI | mongodb://localhost:27017/... |

---

## 9. Running the Service

### Local Development
```bash
# Create database first
psql -U postgres -c "CREATE DATABASE commerce_{domain}_db;"

# Run with local profile
cd {domain}-service
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Docker Development
```bash
# Start infrastructure
cd docker
docker-compose -f docker-compose-dev.yml up -d postgres-{domain}

# Run service
cd ../{domain}-service
mvn spring-boot:run -Dspring-boot.run.profiles=docker
```

---

## 10. Verification Checklist

After setup, verify:

1. **Service Registration**: Check Eureka dashboard at `http://localhost:8761`
2. **Health Check**: `curl http://localhost:90XX/actuator/health`
3. **API Gateway**: `curl http://localhost:8080/api/v1/{domain}/...`
4. **Config Server**: `curl http://localhost:8888/commerce-{domain}-service/default`

---

## 📁 File Structure Summary

```
{domain}-service/
├── src/main/
│   ├── java/com/social/{domain}/
│   │   ├── {Domain}ServiceApplication.java
│   │   ├── config/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   ├── dto/
│   │   └── client/          # Feign clients (if needed)
│   └── resources/
│       ├── application.yml
│       ├── application-local.yml
│       ├── application-docker.yml
│       └── bootstrap.yml    # Optional
├── pom.xml
└── Dockerfile
```

---

## ⚠️ Common Mistakes to Avoid

1. **Mismatched service name**: `spring.application.name` must match config filename
2. **Wrong Eureka URL in Docker**: Use `commerce-service-discovery` not `localhost`
3. **Missing database**: Always create DB before starting service
4. **Port conflicts**: Check port allocation table before choosing
5. **Feign client name**: Must match target service's `spring.application.name`
6. **Missing fallback**: Add Gateway fallback or circuit breaker fails silently

---

*Last Updated: January 2026*

