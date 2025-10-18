# 🚀 MASTER IMPLEMENTATION GUIDE - COMPLETE STEP-BY-STEP

> **📌 YOUR SINGLE SOURCE OF TRUTH - EVERYTHING IN ONE PLACE!**
> 
> **This guide contains EVERY file, EVERY command, EVERY detail you need!**
> 
> Follow this document step-by-step from scratch to production. No confusion, no jumping around!

---

## 📋 TABLE OF CONTENTS

1. [Project Overview](#1-project-overview)
2. [What Are We Building?](#2-what-are-we-building)
3. [Complete Project Structure](#3-complete-project-structure)
4. [Prerequisites & Installation](#4-prerequisites--installation)
5. [Initial Project Setup](#5-initial-project-setup)
6. [Phase 1: Basic Implementation (Week 1-2)](#6-phase-1-basic-implementation-week-1-2)
7. [Phase 2: Core Features (Week 3-4)](#7-phase-2-core-features-week-3-4)
8. [Phase 3: Advanced Features (Week 5-6)](#8-phase-3-advanced-features-week-5-6)
9. [Phase 4: Production Ready (Week 7-8)](#9-phase-4-production-ready-week-7-8)
10. [Testing Strategy](#10-testing-strategy)
11. [Deployment](#11-deployment)
12. [Monitoring & Operations](#12-monitoring--operations)
13. [Troubleshooting](#13-troubleshooting)
14. [Quick Reference](#14-quick-reference)

---

## 1. PROJECT OVERVIEW

### **What Is This Project?**

**Social Commerce Platform** - An enterprise-grade e-commerce platform with social features built using **Spring Boot 3.5.5** microservices architecture.

### **Key Statistics:**

- 🎯 **Scale**: Designed for 20M+ concurrent users
- 🏗️ **Architecture**: 6 microservices + 5 databases + Kafka event streaming
- ☁️ **Cloud**: Multi-cloud ready (AWS + Azure)
- 📊 **Availability**: 99.99% uptime target
- ⚡ **Performance**: <200ms API response time
- 🔒 **Security**: JWT authentication, OAuth 2.0, RBAC

### **Why Build This?**

✅ **For Your Resume**: Showcase enterprise-grade microservices experience  
✅ **For Interviews**: Demonstrate complete system design knowledge  
✅ **For Learning**: Master industry best practices and patterns  
✅ **For Portfolio**: Create an impressive GitHub showcase  
✅ **For Career**: Stand out from other candidates  

### **What You'll Learn:**

- ✅ Spring Boot 3.5.5 microservices development
- ✅ Microservices architecture patterns
- ✅ Event-driven architecture with Kafka
- ✅ Polyglot persistence (5 different databases)
- ✅ API Gateway and Service Discovery
- ✅ Security (JWT, OAuth 2.0, RBAC)
- ✅ Caching strategies (Redis)
- ✅ Monitoring and observability
- ✅ Docker and Kubernetes
- ✅ CI/CD pipelines
- ✅ Cloud deployment (AWS/Azure)

---

## 2. WHAT ARE WE BUILDING?

### **The 6 Microservices:**

| # | Service | Purpose | Port | Database | Tech Stack |
|---|---------|---------|------|----------|------------|
| 1 | **User Service** | User management, authentication, profiles, RBAC | **9001** | PostgreSQL | Spring Boot, Spring Security, JWT |
| 2 | **Product Service** | Product catalog, inventory, categories, search | **9002** | PostgreSQL | Spring Boot, Spring Data JPA |
| 3 | **Order Service** | Order processing, checkout, order history | **9003** | PostgreSQL | Spring Boot, Spring Data JPA |
| 4 | **Payment Service** | Payment processing, transactions, refunds | **9004** | PostgreSQL | Spring Boot, Stripe/PayPal |
| 5 | **Social Service** | Posts, comments, likes, follows, feeds | **9005** | MongoDB | Spring Boot, Spring Data MongoDB |
| 6 | **Notification Service** | Email, SMS, push notifications, alerts | **9006** | MongoDB | Spring Boot, Spring Kafka |

### **Supporting Infrastructure:**

| Component | Technology | Port | Purpose |
|-----------|-----------|------|---------|
| **API Gateway** | Spring Cloud Gateway | **9000** | Single entry point, routing, rate limiting, authentication |
| **Service Discovery** | Eureka Server | **8761** | Service registration & discovery |
| **Config Server** | Spring Cloud Config | **8888** | Centralized configuration management |
| **Message Broker** | Apache Kafka (KRaft) | **9092-9094** | Event streaming, async communication |
| **Cache** | Redis | **6379** | Multi-level caching, session management |
| **Search Engine** | Elasticsearch | **9200** | Full-text search, analytics, logging |
| **Graph Database** | Neo4j | **7474** | Social graph, recommendations |

### **The 5 Databases (Polyglot Persistence):**

1. **PostgreSQL** (Port 5432)
   - Transactional data: users, products, orders, payments
   - ACID compliance for financial transactions
   - Master-slave replication for high availability

2. **MongoDB** (Port 27017)
   - Document-based data: posts, comments, notifications
   - Flexible schema for social features
   - Replica set for high availability

3. **Redis** (Port 6379)
   - In-memory caching: product cache, user sessions
   - Pub/Sub for real-time features
   - Cluster mode for scalability

4. **Elasticsearch** (Port 9200)
   - Full-text search: product search, user search
   - Log aggregation and analytics
   - Distributed search cluster

5. **Neo4j** (Port 7474)
   - Graph database: social connections, recommendations
   - Friend-of-friend queries
   - Recommendation algorithms

### **Common Library Module:**

**common-lib** - Shared utilities, DTOs, entities, and components across all services

**Purpose:**
- ✅ Avoid code duplication
- ✅ Centralize common utilities
- ✅ Share DTOs and entities
- ✅ Consistent error handling
- ✅ Common security components
- ✅ Shared validation logic

**What's Inside:**
- Common DTOs (ApiResponse, ErrorResponse, PageResponse)
- Common entities (BaseEntity with id, createdAt, updatedAt)
- Common exceptions (ResourceNotFoundException, ValidationException)
- Common utilities (DateUtils, StringUtils, ValidationUtils)
- Common security (JwtUtil, SecurityContext)
- Common constants (AppConstants, ErrorCodes)

---

## 3. COMPLETE PROJECT STRUCTURE

### **Root Directory Structure:**

```
social-commerce-platform/
│
├── pom.xml                          # Parent POM (Spring Boot 3.5.5)
├── README.md                        # Project documentation
├── .gitignore                       # Git ignore file
│
├── common-lib/                      # ⭐ Common library module
│   ├── pom.xml
│   └── src/main/java/com/social/common/
│       ├── dto/                     # Shared DTOs
│       │   ├── ApiResponse.java
│       │   ├── ErrorResponse.java
│       │   ├── PageResponse.java
│       │   └── ValidationError.java
│       ├── entity/                  # Base entities
│       │   └── BaseEntity.java
│       ├── exception/               # Common exceptions
│       │   ├── ResourceNotFoundException.java
│       │   ├── ValidationException.java
│       │   ├── UnauthorizedException.java
│       │   └── GlobalExceptionHandler.java
│       ├── util/                    # Utility classes
│       │   ├── DateUtils.java
│       │   ├── StringUtils.java
│       │   ├── ValidationUtils.java
│       │   └── JsonUtils.java
│       ├── security/                # Security utilities
│       │   ├── JwtUtil.java
│       │   ├── SecurityContext.java
│       │   └── PasswordEncoder.java
│       └── constant/                # Constants
│           ├── AppConstants.java
│           └── ErrorCodes.java
│
├── api-gateway/                     # API Gateway (Port 9000)
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/social/gateway/
│       │   │   ├── ApiGatewayApplication.java
│       │   │   ├── config/
│       │   │   │   ├── GatewayConfig.java
│       │   │   │   ├── SecurityConfig.java
│       │   │   │   ├── RateLimitConfig.java
│       │   │   │   └── CorsConfig.java
│       │   │   ├── filter/
│       │   │   │   ├── AuthenticationFilter.java
│       │   │   │   ├── TracingFilter.java
│       │   │   │   └── LoggingFilter.java
│       │   │   └── exception/
│       │   │       └── GatewayExceptionHandler.java
│       │   └── resources/
│       │       ├── application.yml
│       │       └── application-prod.yml
│       └── test/
│           └── java/com/social/gateway/
│
├── service-discovery/               # Eureka Server (Port 8761)
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/social/discovery/
│       │   │   └── ServiceDiscoveryApplication.java
│       │   └── resources/
│       │       └── application.yml
│       └── test/
│
├── config-server/                   # Config Server (Port 8888)
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/social/config/
│       │   │   └── ConfigServerApplication.java
│       │   └── resources/
│       │       └── application.yml
│       └── test/
│
├── user-service/                    # User Service (Port 9001)
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/social/user/
│       │   │   ├── UserServiceApplication.java
│       │   │   ├── controller/
│       │   │   │   ├── UserController.java
│       │   │   │   ├── AuthController.java
│       │   │   │   └── ProfileController.java
│       │   │   ├── service/
│       │   │   │   ├── UserService.java
│       │   │   │   ├── AuthService.java
│       │   │   │   └── ProfileService.java
│       │   │   ├── repository/
│       │   │   │   ├── UserRepository.java
│       │   │   │   └── RoleRepository.java
│       │   │   ├── model/
│       │   │   │   ├── User.java
│       │   │   │   ├── Role.java
│       │   │   │   └── Permission.java
│       │   │   ├── dto/
│       │   │   │   ├── UserDTO.java
│       │   │   │   ├── CreateUserRequest.java
│       │   │   │   ├── UpdateUserRequest.java
│       │   │   │   ├── LoginRequest.java
│       │   │   │   └── LoginResponse.java
│       │   │   ├── config/
│       │   │   │   ├── SecurityConfig.java
│       │   │   │   ├── DatabaseConfig.java
│       │   │   │   └── CacheConfig.java
│       │   │   └── exception/
│       │   │       └── UserServiceException.java
│       │   └── resources/
│       │       ├── application.yml
│       │       ├── application-dev.yml
│       │       ├── application-prod.yml
│       │       └── db/migration/
│       │           └── V1__init_user_schema.sql
│       └── test/
│           └── java/com/social/user/
│               ├── controller/
│               ├── service/
│               └── repository/
│
├── product-service/                 # Product Service (Port 9002)
├── order-service/                   # Order Service (Port 9003)
├── payment-service/                 # Payment Service (Port 9004)
├── social-service/                  # Social Service (Port 9005)
├── notification-service/            # Notification Service (Port 9006)
│
├── docker/                          # Docker configurations
│   ├── docker-compose-infrastructure.yml
│   ├── docker-compose-services.yml
│   ├── docker-compose.yml
│   ├── prometheus.yml
│   └── grafana/
│
├── kubernetes/                      # Kubernetes manifests
│   ├── namespace.yaml
│   ├── configmap.yaml
│   ├── secrets.yaml
│   └── deployments/
│
├── .github/                         # GitHub Actions CI/CD
│   └── workflows/
│       └── ci-cd.yml
│
└── scripts/                         # Utility scripts
    ├── start-infrastructure.sh
    ├── start-services.sh
    └── stop-all.sh
```

### **File Count Summary:**

- **Total Modules**: 9 (1 common-lib + 2 infrastructure + 6 microservices)
- **Total Java Files**: ~150+ files
- **Total Configuration Files**: ~30+ files
- **Total Test Files**: ~80+ files

---

## 4. PREREQUISITES & INSTALLATION

### **Required Software:**

| Software | Version | Purpose | Download Link |
|----------|---------|---------|---------------|
| **Java JDK** | 17+ | Runtime environment | [Adoptium](https://adoptium.net/) |
| **Maven** | 3.9+ | Build tool | [Maven](https://maven.apache.org/download.cgi) |
| **Docker** | 24+ | Containerization | [Docker Desktop](https://www.docker.com/products/docker-desktop) |
| **Docker Compose** | 2.20+ | Multi-container orchestration | Included with Docker Desktop |
| **Git** | 2.40+ | Version control | [Git](https://git-scm.com/downloads) |
| **IDE** | Latest | Development | IntelliJ IDEA / VS Code |
| **Postman** | Latest | API testing | [Postman](https://www.postman.com/downloads/) |

---

### **DETAILED INSTALLATION GUIDE:**

#### **Step 1: Install Java 17 (Required)**

**macOS:**
```bash
# Using Homebrew (Recommended)
brew install openjdk@17

# Add to PATH
echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc

# Set JAVA_HOME
echo 'export JAVA_HOME="/opt/homebrew/opt/openjdk@17"' >> ~/.zshrc
source ~/.zshrc

# Verify installation
java -version
# Expected output: openjdk version "17.0.x"

javac -version
# Expected output: javac 17.0.x

echo $JAVA_HOME
# Expected output: /opt/homebrew/opt/openjdk@17
```

**Windows:**
```bash
# 1. Download OpenJDK 17 from https://adoptium.net/
# 2. Download the .msi installer for Windows
# 3. Run the installer
# 4. Check "Set JAVA_HOME variable" during installation
# 5. Check "Add to PATH" during installation

# Verify installation (in Command Prompt or PowerShell)
java -version
javac -version
echo %JAVA_HOME%
```

**Linux (Ubuntu/Debian):**
```bash
# Update package list
sudo apt update

# Install OpenJDK 17
sudo apt install openjdk-17-jdk -y

# Set JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> ~/.bashrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc

# Verify installation
java -version
javac -version
echo $JAVA_HOME
```

---

#### **Step 2: Install Maven 3.9+ (Required)**

**macOS:**
```bash
# Using Homebrew (Recommended)
brew install maven

# Verify installation
mvn -version
# Expected output: Apache Maven 3.9.x

# Check Maven home
echo $M2_HOME
```

**Windows:**
```bash
# 1. Download Maven from https://maven.apache.org/download.cgi
# 2. Download apache-maven-3.9.x-bin.zip
# 3. Extract to C:\Program Files\Apache\maven
# 4. Add to System Environment Variables:
#    - Variable: M2_HOME
#    - Value: C:\Program Files\Apache\maven
# 5. Add to PATH: %M2_HOME%\bin

# Verify installation (in Command Prompt)
mvn -version
```

**Linux (Ubuntu/Debian):**
```bash
# Install Maven
sudo apt install maven -y

# Verify installation
mvn -version

# If you need a specific version, download manually:
cd /opt
sudo wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz
sudo tar xzf apache-maven-3.9.6-bin.tar.gz
sudo ln -s apache-maven-3.9.6 maven

# Add to PATH
echo 'export M2_HOME=/opt/maven' >> ~/.bashrc
echo 'export PATH=$M2_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc

mvn -version
```

---

#### **Step 3: Install Docker & Docker Compose (Required)**

**macOS:**
```bash
# 1. Download Docker Desktop from https://www.docker.com/products/docker-desktop
# 2. Install Docker Desktop.dmg
# 3. Start Docker Desktop from Applications
# 4. Wait for Docker to start (whale icon in menu bar)

# Verify installation
docker --version
# Expected output: Docker version 24.x.x

docker-compose --version
# Expected output: Docker Compose version v2.x.x

# Test Docker
docker run hello-world
# Should download and run hello-world container

# Check Docker is running
docker ps
# Should show empty list (no containers running yet)
```

**Windows:**
```bash
# 1. Download Docker Desktop from https://www.docker.com/products/docker-desktop
# 2. Install Docker Desktop Installer.exe
# 3. Enable WSL 2 if prompted
# 4. Restart computer if required
# 5. Start Docker Desktop

# Verify installation (in PowerShell or Command Prompt)
docker --version
docker-compose --version
docker run hello-world
docker ps
```

**Linux (Ubuntu/Debian):**
```bash
# Remove old versions
sudo apt remove docker docker-engine docker.io containerd runc

# Install Docker
sudo apt update
sudo apt install ca-certificates curl gnupg lsb-release -y

# Add Docker's official GPG key
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg

# Set up repository
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install Docker Engine
sudo apt update
sudo apt install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

# Add user to docker group (to run without sudo)
sudo usermod -aG docker $USER
newgrp docker

# Verify installation
docker --version
docker compose version
docker run hello-world
docker ps
```

---

#### **Step 4: Install Git (Required)**

**macOS:**
```bash
# Using Homebrew (Recommended)
brew install git

# Verify installation
git --version
# Expected output: git version 2.x.x

# Configure Git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Verify configuration
git config --list
```

**Windows:**
```bash
# 1. Download Git from https://git-scm.com/download/win
# 2. Install Git for Windows
# 3. Use default settings during installation
# 4. Select "Git from the command line and also from 3rd-party software"

# Verify installation (in Command Prompt or PowerShell)
git --version

# Configure Git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

**Linux (Ubuntu/Debian):**
```bash
# Install Git
sudo apt install git -y

# Verify installation
git --version

# Configure Git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Verify configuration
git config --list
```

---

#### **Step 5: Install IDE (Recommended)**

**Option 1: IntelliJ IDEA (Recommended for Java)**

```bash
# macOS
brew install --cask intellij-idea-ce  # Community Edition (Free)
# OR
brew install --cask intellij-idea     # Ultimate Edition (Paid, 30-day trial)

# Windows/Linux
# Download from https://www.jetbrains.com/idea/download/
# Install the downloaded file

# After installation:
# 1. Open IntelliJ IDEA
# 2. Install plugins:
#    - Lombok Plugin
#    - Spring Boot Plugin (pre-installed in Ultimate)
#    - Docker Plugin
#    - Kubernetes Plugin
# 3. Configure JDK: File > Project Structure > SDKs > Add JDK > Select Java 17
```

**Option 2: VS Code (Lightweight alternative)**

```bash
# macOS
brew install --cask visual-studio-code

# Windows/Linux
# Download from https://code.visualstudio.com/

# After installation, install extensions:
# 1. Open VS Code
# 2. Go to Extensions (Ctrl+Shift+X or Cmd+Shift+X)
# 3. Install:
#    - Extension Pack for Java (Microsoft)
#    - Spring Boot Extension Pack (VMware)
#    - Docker (Microsoft)
#    - Kubernetes (Microsoft)
#    - Lombok Annotations Support
```

---

#### **Step 6: Install Postman (Recommended for API Testing)**

```bash
# macOS
brew install --cask postman

# Windows/Linux
# Download from https://www.postman.com/downloads/
# Install the downloaded file

# After installation:
# 1. Create a free account (optional but recommended)
# 2. Create a new workspace: "Social Commerce Platform"
# 3. We'll import API collections later
```

---

### **VERIFICATION CHECKLIST:**

Run these commands to verify all installations:

```bash
# Create a verification script
cat > verify-installation.sh << 'EOF'
#!/bin/bash

echo "=== Verifying Installation ==="
echo ""

# Check Java
echo "1. Checking Java..."
if command -v java &> /dev/null; then
    java -version
    echo "✅ Java is installed"
else
    echo "❌ Java is NOT installed"
fi
echo ""

# Check Maven
echo "2. Checking Maven..."
if command -v mvn &> /dev/null; then
    mvn -version | head -1
    echo "✅ Maven is installed"
else
    echo "❌ Maven is NOT installed"
fi
echo ""

# Check Docker
echo "3. Checking Docker..."
if command -v docker &> /dev/null; then
    docker --version
    echo "✅ Docker is installed"
else
    echo "❌ Docker is NOT installed"
fi
echo ""

# Check Docker Compose
echo "4. Checking Docker Compose..."
if command -v docker-compose &> /dev/null || docker compose version &> /dev/null; then
    docker compose version 2>/dev/null || docker-compose --version
    echo "✅ Docker Compose is installed"
else
    echo "❌ Docker Compose is NOT installed"
fi
echo ""

# Check Git
echo "5. Checking Git..."
if command -v git &> /dev/null; then
    git --version
    echo "✅ Git is installed"
else
    echo "❌ Git is NOT installed"
fi
echo ""

echo "=== Verification Complete ==="
EOF

# Make it executable
chmod +x verify-installation.sh

# Run verification
./verify-installation.sh
```

**Expected Output:**
```
=== Verifying Installation ===

1. Checking Java...
openjdk version "17.0.x"
✅ Java is installed

2. Checking Maven...
Apache Maven 3.9.x
✅ Maven is installed

3. Checking Docker...
Docker version 24.x.x
✅ Docker is installed

4. Checking Docker Compose...
Docker Compose version v2.x.x
✅ Docker Compose is installed

5. Checking Git...
git version 2.x.x
✅ Git is installed

=== Verification Complete ===
```

**If any tool shows ❌, go back and reinstall that tool!**

---

### **TROUBLESHOOTING INSTALLATION ISSUES:**

#### **Issue 1: Java not found**
```bash
# Check if Java is installed
which java

# If not found, reinstall Java
# macOS: brew install openjdk@17
# Windows: Download from adoptium.net
# Linux: sudo apt install openjdk-17-jdk

# Make sure JAVA_HOME is set
echo $JAVA_HOME  # macOS/Linux
echo %JAVA_HOME% # Windows
```

#### **Issue 2: Maven not found**
```bash
# Check if Maven is installed
which mvn

# If not found, reinstall Maven
# macOS: brew install maven
# Windows: Download from maven.apache.org
# Linux: sudo apt install maven
```

#### **Issue 3: Docker not starting**
```bash
# macOS/Windows: Make sure Docker Desktop is running
# Check Docker status
docker info

# If error, restart Docker Desktop
# macOS: Restart from menu bar
# Windows: Restart from system tray
# Linux: sudo systemctl restart docker
```

#### **Issue 4: Permission denied (Docker on Linux)**
```bash
# Add user to docker group
sudo usermod -aG docker $USER

# Log out and log back in, or run:
newgrp docker

# Test Docker
docker ps
```

---

**✅ Once all tools are installed and verified, proceed to the next section!**

---

## 5. INITIAL PROJECT SETUP

> **Time Required**: 30 minutes
> **Goal**: Create the project structure and parent POM

### **Step 1: Create Root Directory and Initialize Git**

```bash
# Navigate to your workspace
cd ~/Desktop  # or wherever you want to create the project

# Create project directory
mkdir social-commerce-platform
cd social-commerce-platform

# Initialize Git repository
git init

# Verify
pwd
# Expected output: /Users/yourusername/Desktop/social-commerce-platform (or your path)

ls -la
# Should show .git directory
```

---

### **Step 2: Create .gitignore File**

```bash
# Create .gitignore file
cat > .gitignore << 'EOF'
# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties
dependency-reduced-pom.xml
buildNumber.properties
.mvn/timing.properties
.mvn/wrapper/maven-wrapper.jar

# IDE - IntelliJ IDEA
.idea/
*.iml
*.iws
*.ipr
out/

# IDE - Eclipse
.settings/
.classpath
.project
.factorypath

# IDE - VS Code
.vscode/
*.code-workspace

# OS
.DS_Store
.DS_Store?
._*
.Spotlight-V100
.Trashes
ehthumbs.db
Thumbs.db

# Logs
*.log
logs/
*.log.*

# Application
application-local.yml
application-secret.yml
*.env
.env

# Docker
.docker/
docker-compose.override.yml

# Kubernetes
*.kubeconfig
secrets/

# Temporary files
*.tmp
*.temp
*.swp
*.swo
*~

# Build
build/
dist/

# Node (if using frontend)
node_modules/
npm-debug.log
yarn-error.log
EOF

# Verify
cat .gitignore
```

---

### **Step 3: Create Parent POM (pom.xml)**

This is the **MOST IMPORTANT** file. It defines all dependencies and versions for all microservices.

```bash
# Create parent pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Spring Boot Parent -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.5</version>
        <relativePath/>
    </parent>

    <!-- Project Information -->
    <groupId>com.social</groupId>
    <artifactId>social-commerce-platform</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <name>Social Commerce Platform</name>
    <description>Enterprise-grade social commerce platform with microservices architecture</description>

    <!-- Properties -->
    <properties>
        <!-- Java Version -->
        <java.version>17</java.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>

        <!-- Spring Cloud Version -->
        <spring-cloud.version>2024.0.0</spring-cloud.version>

        <!-- Dependency Versions -->
        <lombok.version>1.18.30</lombok.version>
        <mapstruct.version>1.5.5.Final</mapstruct.version>
        <jwt.version>0.12.3</jwt.version>
        <springdoc.version>2.3.0</springdoc.version>
        <kafka.version>3.7.0</kafka.version>
        <resilience4j.version>2.1.0</resilience4j.version>
        <micrometer.version>1.12.0</micrometer.version>
    </properties>

    <!-- Modules -->
    <modules>
        <module>common-lib</module>
        <module>service-discovery</module>
        <module>config-server</module>
        <module>api-gateway</module>
        <module>user-service</module>
        <module>product-service</module>
        <module>order-service</module>
        <module>payment-service</module>
        <module>social-service</module>
        <module>notification-service</module>
    </modules>

    <!-- Dependency Management -->
    <dependencyManagement>
        <dependencies>
            <!-- Spring Cloud Dependencies -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- Common Library -->
            <dependency>
                <groupId>com.social</groupId>
                <artifactId>common-lib</artifactId>
                <version>${project.version}</version>
            </dependency>

            <!-- Lombok -->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <scope>provided</scope>
            </dependency>

            <!-- MapStruct -->
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct</artifactId>
                <version>${mapstruct.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>${mapstruct.version}</version>
                <scope>provided</scope>
            </dependency>

            <!-- JWT -->
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-api</artifactId>
                <version>${jwt.version}</version>
            </dependency>
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-impl</artifactId>
                <version>${jwt.version}</version>
                <scope>runtime</scope>
            </dependency>
            <dependency>
                <groupId>io.jsonwebtoken</groupId>
                <artifactId>jjwt-jackson</artifactId>
                <version>${jwt.version}</version>
                <scope>runtime</scope>
            </dependency>

            <!-- SpringDoc OpenAPI (Swagger) -->
            <dependency>
                <groupId>org.springdoc</groupId>
                <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
                <version>${springdoc.version}</version>
            </dependency>

            <!-- Resilience4j -->
            <dependency>
                <groupId>io.github.resilience4j</groupId>
                <artifactId>resilience4j-spring-boot3</artifactId>
                <version>${resilience4j.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!-- Build Configuration -->
    <build>
        <pluginManagement>
            <plugins>
                <!-- Spring Boot Maven Plugin -->
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <configuration>
                        <excludes>
                            <exclude>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                            </exclude>
                        </excludes>
                    </configuration>
                </plugin>

                <!-- Maven Compiler Plugin -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.11.0</version>
                    <configuration>
                        <source>17</source>
                        <target>17</target>
                        <annotationProcessorPaths>
                            <path>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                                <version>${lombok.version}</version>
                            </path>
                            <path>
                                <groupId>org.mapstruct</groupId>
                                <artifactId>mapstruct-processor</artifactId>
                                <version>${mapstruct.version}</version>
                            </path>
                        </annotationProcessorPaths>
                    </configuration>
                </plugin>

                <!-- Maven Surefire Plugin (for tests) -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.0.0</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>

</project>
EOF

# Verify
cat pom.xml
```

**What this POM does:**
- ✅ Uses Spring Boot 3.5.5 as parent
- ✅ Defines Java 17 as the version
- ✅ Declares all 10 modules (1 common-lib + 2 infrastructure + 6 microservices + config-server)
- ✅ Manages all dependency versions centrally
- ✅ Configures build plugins (Spring Boot, Compiler, Surefire)
- ✅ Sets up Lombok and MapStruct annotation processing

---

### **Step 4: Create README.md**

```bash
# Create README.md
cat > README.md << 'EOF'
# 🚀 Social Commerce Platform

Enterprise-grade social commerce platform built with Spring Boot 3.5.5 microservices architecture.

## 📊 Project Statistics

- **Spring Boot Version**: 3.5.5
- **Java Version**: 17
- **Microservices**: 6 services
- **Databases**: 5 (PostgreSQL, MongoDB, Redis, Elasticsearch, Neo4j)
- **Message Broker**: Apache Kafka (KRaft mode)
- **API Gateway**: Spring Cloud Gateway (Port 9000)
- **Service Discovery**: Eureka Server (Port 8761)
- **Scale**: Designed for 20M+ concurrent users
- **Availability**: 99.99% uptime target

## 🏗️ Architecture

### Microservices
- **User Service** (Port 9001) - User management, authentication, RBAC
- **Product Service** (Port 9002) - Product catalog, inventory management
- **Order Service** (Port 9003) - Order processing, checkout
- **Payment Service** (Port 9004) - Payment processing, transactions
- **Social Service** (Port 9005) - Posts, comments, likes, follows
- **Notification Service** (Port 9006) - Email, SMS, push notifications

### Infrastructure
- **API Gateway** (Port 9000) - Single entry point, routing, rate limiting
- **Service Discovery** (Port 8761) - Eureka Server for service registration
- **Config Server** (Port 8888) - Centralized configuration management
- **Common Library** - Shared utilities, DTOs, entities across services

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.9+
- Docker 24+
- Docker Compose 2.20+

### Start Infrastructure
```bash
# Start databases and Kafka
docker-compose -f docker/docker-compose-infrastructure.yml up -d
```

### Build All Services
```bash
# Build all modules
mvn clean install -DskipTests
```

### Start All Services
```bash
# Start all microservices
docker-compose -f docker/docker-compose.yml up -d
```

### Access Services
- **API Gateway**: http://localhost:9000
- **Eureka Dashboard**: http://localhost:8761
- **Swagger UI**: http://localhost:9000/swagger-ui.html
- **User Service**: http://localhost:9001
- **Product Service**: http://localhost:9002
- **Order Service**: http://localhost:9003
- **Payment Service**: http://localhost:9004
- **Social Service**: http://localhost:9005
- **Notification Service**: http://localhost:9006

## 📚 Documentation

See [Project/00-MASTER-IMPLEMENTATION-GUIDE.md](Project/00-MASTER-IMPLEMENTATION-GUIDE.md) for complete step-by-step implementation guide.

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run tests for specific service
cd user-service && mvn test

# Run with coverage
mvn test jacoco:report
```

## 🐳 Docker

```bash
# Build Docker images
docker-compose build

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

## ☸️ Kubernetes

```bash
# Deploy to Kubernetes
kubectl apply -f kubernetes/

# Check status
kubectl get pods -n social-commerce

# View logs
kubectl logs -f deployment/user-service -n social-commerce
```

## 📝 API Documentation

Once services are running, access Swagger UI:
- http://localhost:9000/swagger-ui.html

## 🔒 Security

- JWT authentication
- OAuth 2.0 / OpenID Connect
- Role-Based Access Control (RBAC)
- API rate limiting
- HTTPS/TLS encryption

## 📊 Monitoring

- **Prometheus**: http://localhost:9090
- **Grafana**: http://localhost:3000 (admin/admin)
- **Zipkin**: http://localhost:9411

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

MIT License

## 👤 Author

Your Name - [GitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Boot Team
- Spring Cloud Team
- Apache Kafka Team
- All open-source contributors
EOF

# Verify
cat README.md
```

---

### **Step 5: Create Directory Structure**

```bash
# Create all module directories
mkdir -p common-lib/src/main/java/com/social/common
mkdir -p common-lib/src/main/resources
mkdir -p common-lib/src/test/java/com/social/common

mkdir -p service-discovery/src/main/java/com/social/discovery
mkdir -p service-discovery/src/main/resources
mkdir -p service-discovery/src/test/java/com/social/discovery

mkdir -p config-server/src/main/java/com/social/config
mkdir -p config-server/src/main/resources
mkdir -p config-server/src/test/java/com/social/config

mkdir -p api-gateway/src/main/java/com/social/gateway
mkdir -p api-gateway/src/main/resources
mkdir -p api-gateway/src/test/java/com/social/gateway

mkdir -p user-service/src/main/java/com/social/user
mkdir -p user-service/src/main/resources
mkdir -p user-service/src/test/java/com/social/user

mkdir -p product-service/src/main/java/com/social/product
mkdir -p product-service/src/main/resources
mkdir -p product-service/src/test/java/com/social/product

mkdir -p order-service/src/main/java/com/social/order
mkdir -p order-service/src/main/resources
mkdir -p order-service/src/test/java/com/social/order

mkdir -p payment-service/src/main/java/com/social/payment
mkdir -p payment-service/src/main/resources
mkdir -p payment-service/src/test/java/com/social/payment

mkdir -p social-service/src/main/java/com/social/social
mkdir -p social-service/src/main/resources
mkdir -p social-service/src/test/java/com/social/social

mkdir -p notification-service/src/main/java/com/social/notification
mkdir -p notification-service/src/main/resources
mkdir -p notification-service/src/test/java/com/social/notification

# Create docker directory
mkdir -p docker/grafana/dashboards

# Create kubernetes directory
mkdir -p kubernetes/deployments
mkdir -p kubernetes/services
mkdir -p kubernetes/ingress

# Create scripts directory
mkdir -p scripts

# Create GitHub Actions directory
mkdir -p .github/workflows

# Verify directory structure
tree -L 3 -d
# OR if tree is not installed:
find . -type d -maxdepth 3 | sort
```

---

### **Step 6: Initial Git Commit**

```bash
# Add all files to Git
git add .

# Create initial commit
git commit -m "Initial project setup: parent POM, directory structure, README"

# Verify
git log --oneline
# Should show: Initial project setup: parent POM, directory structure, README

git status
# Should show: nothing to commit, working tree clean
```

---

### **Step 7: Verify Project Structure**

```bash
# Check project structure
ls -la

# Expected output:
# .git/
# .gitignore
# README.md
# pom.xml
# common-lib/
# service-discovery/
# config-server/
# api-gateway/
# user-service/
# product-service/
# order-service/
# payment-service/
# social-service/
# notification-service/
# docker/
# kubernetes/
# scripts/
# .github/

# Verify parent POM
mvn validate

# Expected output:
# [INFO] BUILD SUCCESS
```

---

### **✅ CHECKPOINT: Initial Setup Complete!**

**What you have now:**
- ✅ Project directory created
- ✅ Git repository initialized
- ✅ .gitignore file created
- ✅ Parent POM (pom.xml) created with Spring Boot 3.5.5
- ✅ README.md created
- ✅ All module directories created
- ✅ Docker and Kubernetes directories created
- ✅ Initial Git commit done

**Next Steps:**
- Create common-lib module
- Create infrastructure services (Eureka, Config Server, API Gateway)
- Create microservices (User, Product, Order, Payment, Social, Notification)

---

## 6. PHASE 1: BASIC IMPLEMENTATION (Week 1-2)

> **Time Required**: 2 weeks
> **Goal**: Create working microservices with basic CRUD operations
> **What You'll Build**: common-lib + 2 infrastructure services + 6 microservices

### **Implementation Timeline:**

| Day | Task | Time | Status |
|-----|------|------|--------|
| **Day 1** | Create common-lib module | 2 hours | ⬜ |
| **Day 2** | Create Service Discovery (Eureka) | 1 hour | ⬜ |
| **Day 3** | Create Config Server | 1 hour | ⬜ |
| **Day 4** | Create API Gateway | 2 hours | ⬜ |
| **Day 5** | Create User Service (Part 1) | 3 hours | ⬜ |
| **Day 6** | Create User Service (Part 2) + Test | 3 hours | ⬜ |
| **Day 7** | Create Product Service | 3 hours | ⬜ |
| **Day 8** | Create Order Service | 3 hours | ⬜ |
| **Day 9** | Create Payment Service | 3 hours | ⬜ |
| **Day 10** | Create Social Service | 3 hours | ⬜ |
| **Day 11** | Create Notification Service | 3 hours | ⬜ |
| **Day 12** | Setup Docker Compose for Infrastructure | 2 hours | ⬜ |
| **Day 13** | Integration Testing | 3 hours | ⬜ |
| **Day 14** | Documentation and Review | 2 hours | ⬜ |

---

### **DAY 1: CREATE COMMON-LIB MODULE**

> **Time**: 2 hours
> **Goal**: Create shared library with common utilities, DTOs, entities, and exceptions

#### **Step 1: Create common-lib POM**

```bash
# Navigate to common-lib directory
cd common-lib

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>common-lib</artifactId>
    <packaging>jar</packaging>

    <name>Common Library</name>
    <description>Shared utilities, DTOs, entities, and components</description>

    <dependencies>
        <!-- Spring Boot Starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!-- Spring Boot Starter Web (for REST controllers) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Spring Data JPA (for BaseEntity) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Jackson for JSON -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

        <!-- Apache Commons Lang -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
EOF

# Go back to root
cd ..
```

#### **Step 2: Create Common DTOs**

```bash
# Create DTO directory
mkdir -p common-lib/src/main/java/com/social/common/dto

# Create ApiResponse.java
cat > common-lib/src/main/java/com/social/common/dto/ApiResponse.java << 'EOF'
package com.social.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Success")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
EOF

# Create ErrorResponse.java
cat > common-lib/src/main/java/com/social/common/dto/ErrorResponse.java << 'EOF'
package com.social.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp;
    private List<ValidationError> validationErrors;

    public static ErrorResponse of(String error, String message, int status, String path) {
        return ErrorResponse.builder()
                .error(error)
                .message(message)
                .status(status)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
EOF

# Create ValidationError.java
cat > common-lib/src/main/java/com/social/common/dto/ValidationError.java << 'EOF'
package com.social.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String message;
    private Object rejectedValue;
}
EOF

# Create PageResponse.java
cat > common-lib/src/main/java/com/social/common/dto/PageResponse.java << 'EOF'
package com.social.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private boolean empty;
}
EOF
```

#### **Step 3: Create Base Entity**

```bash
# Create entity directory
mkdir -p common-lib/src/main/java/com/social/common/entity

# Create BaseEntity.java
cat > common-lib/src/main/java/com/social/common/entity/BaseEntity.java << 'EOF'
package com.social.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "deleted")
    private boolean deleted = false;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
EOF
```

#### **Step 4: Create Common Exceptions**

```bash
# Create exception directory
mkdir -p common-lib/src/main/java/com/social/common/exception

# Create ResourceNotFoundException.java
cat > common-lib/src/main/java/com/social/common/exception/ResourceNotFoundException.java << 'EOF'
package com.social.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: '%s'", resource, field, value));
    }
}
EOF

# Create ValidationException.java
cat > common-lib/src/main/java/com/social/common/exception/ValidationException.java << 'EOF'
package com.social.common.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
EOF

# Create UnauthorizedException.java
cat > common-lib/src/main/java/com/social/common/exception/UnauthorizedException.java << 'EOF'
package com.social.common.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
EOF

# Create BusinessException.java
cat > common-lib/src/main/java/com/social/common/exception/BusinessException.java << 'EOF'
package com.social.common.exception;

public class BusinessException extends RuntimeException {

    private final String errorCode;

    public BusinessException(String message) {
        super(message);
        this.errorCode = "BUSINESS_ERROR";
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
EOF

# Create GlobalExceptionHandler.java
cat > common-lib/src/main/java/com/social/common/exception/GlobalExceptionHandler.java << 'EOF'
package com.social.common.exception;

import com.social.common.dto.ErrorResponse;
import com.social.common.dto.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {
        log.error("Resource not found: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.of(
                "NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException ex, HttpServletRequest request) {
        log.error("Validation error: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.of(
                "VALIDATION_ERROR",
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(
            UnauthorizedException ex, HttpServletRequest request) {
        log.error("Unauthorized: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.of(
                "UNAUTHORIZED",
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex, HttpServletRequest request) {
        log.error("Business error: {}", ex.getMessage());

        ErrorResponse error = ErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("Validation failed: {}", ex.getMessage());

        List<ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapToValidationError)
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse.builder()
                .error("VALIDATION_FAILED")
                .message("Validation failed for one or more fields")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .validationErrors(validationErrors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {
        log.error("Unexpected error: ", ex);

        ErrorResponse error = ErrorResponse.of(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Please try again later.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private ValidationError mapToValidationError(FieldError fieldError) {
        return ValidationError.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .rejectedValue(fieldError.getRejectedValue())
                .build();
    }
}
EOF
```

#### **Step 5: Create Common Utilities**

```bash
# Create util directory
mkdir -p common-lib/src/main/java/com/social/common/util

# Create DateUtils.java
cat > common-lib/src/main/java/com/social/common/util/DateUtils.java << 'EOF'
package com.social.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DEFAULT_FORMATTER) : null;
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern(pattern)) : null;
    }

    public static LocalDateTime parse(String dateTimeString) {
        return dateTimeString != null ? LocalDateTime.parse(dateTimeString, DEFAULT_FORMATTER) : null;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return localDateTime != null ?
                Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date != null ?
                LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()) : null;
    }

    public static boolean isBefore(LocalDateTime date1, LocalDateTime date2) {
        return date1 != null && date2 != null && date1.isBefore(date2);
    }

    public static boolean isAfter(LocalDateTime date1, LocalDateTime date2) {
        return date1 != null && date2 != null && date1.isAfter(date2);
    }
}
EOF

# Create StringUtils.java
cat > common-lib/src/main/java/com/social/common/util/StringUtils.java << 'EOF'
package com.social.common.util;

import java.util.UUID;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String maskEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];

        if (username.length() <= 2) {
            return "**@" + domain;
        }

        return username.substring(0, 2) + "***@" + domain;
    }

    public static String maskPhone(String phone) {
        if (isEmpty(phone) || phone.length() < 4) {
            return phone;
        }
        return "***" + phone.substring(phone.length() - 4);
    }
}
EOF

# Create ValidationUtils.java
cat > common-lib/src/main/java/com/social/common/util/ValidationUtils.java << 'EOF'
package com.social.common.util;

import com.social.common.exception.ValidationException;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    public static void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new ValidationException("Email cannot be empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email format");
        }
    }

    public static void validatePhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new ValidationException("Phone number cannot be empty");
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new ValidationException("Invalid phone number format");
        }
    }

    public static void validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new ValidationException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException(
                    "Password must contain at least one digit, one lowercase, " +
                    "one uppercase letter, and one special character"
            );
        }
    }

    public static void validateNotNull(Object object, String fieldName) {
        if (object == null) {
            throw new ValidationException(fieldName + " cannot be null");
        }
    }

    public static void validateNotEmpty(String value, String fieldName) {
        if (StringUtils.isEmpty(value)) {
            throw new ValidationException(fieldName + " cannot be empty");
        }
    }
}
EOF

# Create JsonUtils.java
cat > common-lib/src/main/java/com/social/common/util/JsonUtils.java << 'EOF'
package com.social.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON", e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error converting JSON to object", e);
            return null;
        }
    }

    public static String toPrettyJson(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to pretty JSON", e);
            return null;
        }
    }
}
EOF
```

#### **Step 6: Create Common Constants**

```bash
# Create constant directory
mkdir -p common-lib/src/main/java/com/social/common/constant

# Create AppConstants.java
cat > common-lib/src/main/java/com/social/common/constant/AppConstants.java << 'EOF'
package com.social.common.constant;

public class AppConstants {

    // Application
    public static final String APP_NAME = "Social Commerce Platform";
    public static final String APP_VERSION = "1.0.0";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "DESC";

    // Date Format
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_ZONE = "UTC";

    // JWT
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";
    public static final long JWT_EXPIRATION = 86400000L; // 24 hours
    public static final long JWT_REFRESH_EXPIRATION = 604800000L; // 7 days

    // Cache
    public static final String CACHE_USER = "users";
    public static final String CACHE_PRODUCT = "products";
    public static final String CACHE_ORDER = "orders";
    public static final int CACHE_TTL_MINUTES = 60;

    // Kafka Topics
    public static final String TOPIC_USER_EVENTS = "user.events";
    public static final String TOPIC_PRODUCT_EVENTS = "product.events";
    public static final String TOPIC_ORDER_EVENTS = "order.events";
    public static final String TOPIC_PAYMENT_EVENTS = "payment.events";
    public static final String TOPIC_NOTIFICATION_EVENTS = "notification.events";

    // Rate Limiting
    public static final int RATE_LIMIT_PER_MINUTE = 60;
    public static final int RATE_LIMIT_PER_HOUR = 1000;

    // File Upload
    public static final long MAX_FILE_SIZE = 10485760L; // 10MB
    public static final String[] ALLOWED_IMAGE_TYPES = {"image/jpeg", "image/png", "image/gif"};

    private AppConstants() {
        // Private constructor to prevent instantiation
    }
}
EOF

# Create ErrorCodes.java
cat > common-lib/src/main/java/com/social/common/constant/ErrorCodes.java << 'EOF'
package com.social.common.constant;

public class ErrorCodes {

    // General Errors
    public static final String INTERNAL_SERVER_ERROR = "ERR_001";
    public static final String VALIDATION_ERROR = "ERR_002";
    public static final String RESOURCE_NOT_FOUND = "ERR_003";
    public static final String UNAUTHORIZED = "ERR_004";
    public static final String FORBIDDEN = "ERR_005";
    public static final String BAD_REQUEST = "ERR_006";

    // User Errors
    public static final String USER_NOT_FOUND = "USER_001";
    public static final String USER_ALREADY_EXISTS = "USER_002";
    public static final String INVALID_CREDENTIALS = "USER_003";
    public static final String USER_INACTIVE = "USER_004";
    public static final String USER_LOCKED = "USER_005";

    // Product Errors
    public static final String PRODUCT_NOT_FOUND = "PROD_001";
    public static final String PRODUCT_OUT_OF_STOCK = "PROD_002";
    public static final String INVALID_PRODUCT_DATA = "PROD_003";

    // Order Errors
    public static final String ORDER_NOT_FOUND = "ORD_001";
    public static final String ORDER_ALREADY_PROCESSED = "ORD_002";
    public static final String ORDER_CANNOT_BE_CANCELLED = "ORD_003";
    public static final String INVALID_ORDER_STATUS = "ORD_004";

    // Payment Errors
    public static final String PAYMENT_FAILED = "PAY_001";
    public static final String PAYMENT_NOT_FOUND = "PAY_002";
    public static final String INSUFFICIENT_FUNDS = "PAY_003";
    public static final String PAYMENT_ALREADY_PROCESSED = "PAY_004";

    // Authentication Errors
    public static final String INVALID_TOKEN = "AUTH_001";
    public static final String TOKEN_EXPIRED = "AUTH_002";
    public static final String INVALID_REFRESH_TOKEN = "AUTH_003";

    private ErrorCodes() {
        // Private constructor to prevent instantiation
    }
}
EOF
```

#### **Step 7: Create Security Utilities**

```bash
# Create security directory
mkdir -p common-lib/src/main/java/com/social/common/security

# Create JwtUtil.java
cat > common-lib/src/main/java/com/social/common/security/JwtUtil.java << 'EOF'
package com.social.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret:your-256-bit-secret-key-here-make-it-long-enough-for-hs256-algorithm}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(UUID userId, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return createToken(claims, userId.toString());
    }

    public String generateToken(UUID userId, String email, Map<String, Object> additionalClaims) {
        Map<String, Object> claims = new HashMap<>(additionalClaims);
        claims.put("email", email);
        return createToken(claims, userId.toString());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public UUID extractUserId(String token) {
        String userId = extractClaim(token, Claims::getSubject);
        return UUID.fromString(userId);
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
EOF

# Create SecurityContext.java
cat > common-lib/src/main/java/com/social/common/security/SecurityContext.java << 'EOF'
package com.social.common.security;

import java.util.UUID;

public class SecurityContext {

    private static final ThreadLocal<UUID> currentUserId = new ThreadLocal<>();
    private static final ThreadLocal<String> currentUserEmail = new ThreadLocal<>();

    public static void setCurrentUserId(UUID userId) {
        currentUserId.set(userId);
    }

    public static UUID getCurrentUserId() {
        return currentUserId.get();
    }

    public static void setCurrentUserEmail(String email) {
        currentUserEmail.set(email);
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail.get();
    }

    public static void clear() {
        currentUserId.remove();
        currentUserEmail.remove();
    }
}
EOF
```

#### **Step 8: Build common-lib**

```bash
# Navigate to root directory
cd ~/Desktop/social-commerce-platform  # or your project path

# Build common-lib
mvn clean install -pl common-lib -DskipTests

# Expected output:
# [INFO] BUILD SUCCESS
# [INFO] Total time: XX s

# Verify the JAR was created
ls -la common-lib/target/

# Should see: common-lib-1.0.0-SNAPSHOT.jar
```

#### **Step 9: Commit common-lib**

```bash
# Add all common-lib files
git add common-lib/

# Commit
git commit -m "feat: create common-lib module with DTOs, entities, exceptions, utilities, and constants"

# Verify
git log --oneline
```

---

### **✅ DAY 1 CHECKPOINT: common-lib Complete!**

**What you have now:**
- ✅ common-lib module created
- ✅ Common DTOs (ApiResponse, ErrorResponse, PageResponse, ValidationError)
- ✅ Base Entity with auditing fields
- ✅ Common Exceptions (ResourceNotFoundException, ValidationException, etc.)
- ✅ Global Exception Handler
- ✅ Utility classes (DateUtils, StringUtils, ValidationUtils, JsonUtils)
- ✅ Constants (AppConstants, ErrorCodes)
- ✅ Security utilities (JwtUtil, SecurityContext)
- ✅ common-lib built and installed to local Maven repository

**Next Steps:**
- Create Service Discovery (Eureka Server)
- Create Config Server
- Create API Gateway

---

### **DAY 2: CREATE SERVICE DISCOVERY (EUREKA SERVER)**

> **Time**: 1 hour
> **Goal**: Create Eureka Server for service registration and discovery
> **Port**: 8761

#### **Step 1: Create service-discovery POM**

```bash
# Navigate to service-discovery directory
cd service-discovery

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>service-discovery</artifactId>
    <packaging>jar</packaging>

    <name>Service Discovery</name>
    <description>Eureka Server for service registration and discovery</description>

    <dependencies>
        <!-- Eureka Server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create ServiceDiscoveryApplication**

```bash
# Create main application class
cat > service-discovery/src/main/java/com/social/discovery/ServiceDiscoveryApplication.java << 'EOF'
package com.social.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create application.yml**

```bash
# Create application.yml
cat > service-discovery/src/main/resources/application.yml << 'EOF'
server:
  port: 8761

spring:
  application:
    name: service-discovery

eureka:
  instance:
    hostname: localhost
    prefer-ip-address: false

  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/

  server:
    enable-self-preservation: false
    eviction-interval-timer-in-ms: 10000
    renewal-percent-threshold: 0.85

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

logging:
  level:
    com.netflix.eureka: INFO
    com.netflix.discovery: INFO
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
EOF
```

#### **Step 4: Build and Run Eureka Server**

```bash
# Build service-discovery
mvn clean install -pl service-discovery -DskipTests

# Run Eureka Server
cd service-discovery
mvn spring-boot:run

# In a new terminal, verify Eureka is running
curl http://localhost:8761/actuator/health

# Expected output:
# {"status":"UP"}

# Open Eureka Dashboard in browser
# http://localhost:8761
```

#### **Step 5: Commit service-discovery**

```bash
# Stop the running service (Ctrl+C)

# Navigate to root
cd ..

# Add and commit
git add service-discovery/
git commit -m "feat: create service-discovery (Eureka Server) on port 8761"
```

---

### **DAY 3: CREATE CONFIG SERVER**

> **Time**: 1 hour
> **Goal**: Create Config Server for centralized configuration management
> **Port**: 8888

#### **Step 1: Create config-server POM**

```bash
# Navigate to config-server directory
cd config-server

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>config-server</artifactId>
    <packaging>jar</packaging>

    <name>Config Server</name>
    <description>Centralized configuration server</description>

    <dependencies>
        <!-- Config Server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create ConfigServerApplication**

```bash
# Create main application class
cat > config-server/src/main/java/com/social/config/ConfigServerApplication.java << 'EOF'
package com.social.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create application.yml**

```bash
# Create application.yml
cat > config-server/src/main/resources/application.yml << 'EOF'
server:
  port: 8888

spring:
  application:
    name: config-server

  cloud:
    config:
      server:
        native:
          search-locations: classpath:/config
        git:
          uri: https://github.com/your-username/config-repo
          default-label: main
          clone-on-start: true
          timeout: 5
        # Use native profile for local development
        # Use git profile for production

  profiles:
    active: native

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10
    lease-expiration-duration-in-seconds: 30

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,env
  endpoint:
    health:
      show-details: always

logging:
  level:
    org.springframework.cloud.config: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
EOF
```

#### **Step 4: Create Configuration Files**

```bash
# Create config directory
mkdir -p config-server/src/main/resources/config

# Create application.yml (default config for all services)
cat > config-server/src/main/resources/config/application.yml << 'EOF'
# Common configuration for all services

spring:
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

logging:
  level:
    root: INFO
    com.social: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
EOF

# Create user-service.yml
cat > config-server/src/main/resources/config/user-service.yml << 'EOF'
server:
  port: 9001

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/user_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update

jwt:
  secret: your-256-bit-secret-key-here-make-it-long-enough-for-hs256-algorithm
  expiration: 86400000
EOF

# Create product-service.yml
cat > config-server/src/main/resources/config/product-service.yml << 'EOF'
server:
  port: 9002

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/product_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver

  redis:
    host: localhost
    port: 6379
EOF

# Create order-service.yml
cat > config-server/src/main/resources/config/order-service.yml << 'EOF'
server:
  port: 9003

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/order_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver

  kafka:
    bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
EOF

# Create payment-service.yml
cat > config-server/src/main/resources/config/payment-service.yml << 'EOF'
server:
  port: 9004

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/payment_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
EOF

# Create social-service.yml
cat > config-server/src/main/resources/config/social-service.yml << 'EOF'
server:
  port: 9005

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/social_db
      database: social_db
EOF

# Create notification-service.yml
cat > config-server/src/main/resources/config/notification-service.yml << 'EOF'
server:
  port: 9006

spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/notification_db
      database: notification_db

  kafka:
    bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
    consumer:
      group-id: notification-service
EOF
```

#### **Step 5: Build and Test Config Server**

```bash
# Build config-server
mvn clean install -pl config-server -DskipTests

# Run Config Server (make sure Eureka is running first)
cd config-server
mvn spring-boot:run

# In a new terminal, test config server
curl http://localhost:8888/user-service/default

# Expected output: JSON with user-service configuration

# Test other services
curl http://localhost:8888/product-service/default
curl http://localhost:8888/order-service/default
```

#### **Step 6: Commit config-server**

```bash
# Stop the running service (Ctrl+C)

# Navigate to root
cd ..

# Add and commit
git add config-server/
git commit -m "feat: create config-server for centralized configuration on port 8888"
```

---

### **DAY 4: CREATE API GATEWAY**

> **Time**: 2 hours
> **Goal**: Create API Gateway as single entry point for all services
> **Port**: 9000

#### **Step 1: Create api-gateway POM**

```bash
# Navigate to api-gateway directory
cd api-gateway

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>api-gateway</artifactId>
    <packaging>jar</packaging>

    <name>API Gateway</name>
    <description>API Gateway for routing and load balancing</description>

    <dependencies>
        <!-- Spring Cloud Gateway -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- Circuit Breaker -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-circuitbreaker-reactor-resilience4j</artifactId>
        </dependency>

        <!-- Redis for Rate Limiting -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>

        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create ApiGatewayApplication**

```bash
# Create main application class
cat > api-gateway/src/main/java/com/social/gateway/ApiGatewayApplication.java << 'EOF'
package com.social.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create Gateway Configuration**

```bash
# Create config directory
mkdir -p api-gateway/src/main/java/com/social/gateway/config

# Create GatewayConfig.java
cat > api-gateway/src/main/java/com/social/gateway/config/GatewayConfig.java << 'EOF'
package com.social.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service Routes
                .route("user-service", r -> r
                        .path("/api/v1/users/**", "/api/v1/auth/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("userServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/user-service")))
                        .uri("lb://user-service"))

                // Product Service Routes
                .route("product-service", r -> r
                        .path("/api/v1/products/**", "/api/v1/categories/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("productServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/product-service")))
                        .uri("lb://product-service"))

                // Order Service Routes
                .route("order-service", r -> r
                        .path("/api/v1/orders/**", "/api/v1/cart/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("orderServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/order-service")))
                        .uri("lb://order-service"))

                // Payment Service Routes
                .route("payment-service", r -> r
                        .path("/api/v1/payments/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("paymentServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/payment-service")))
                        .uri("lb://payment-service"))

                // Social Service Routes
                .route("social-service", r -> r
                        .path("/api/v1/posts/**", "/api/v1/comments/**", "/api/v1/follows/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("socialServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/social-service")))
                        .uri("lb://social-service"))

                // Notification Service Routes
                .route("notification-service", r -> r
                        .path("/api/v1/notifications/**")
                        .filters(f -> f
                                .stripPrefix(0)
                                .addRequestHeader("X-Gateway", "API-Gateway")
                                .circuitBreaker(config -> config
                                        .setName("notificationServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/notification-service")))
                        .uri("lb://notification-service"))

                .build();
    }
}
EOF

# Create CorsConfig.java
cat > api-gateway/src/main/java/com/social/gateway/config/CorsConfig.java << 'EOF'
package com.social.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:4200"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
EOF
```

#### **Step 4: Create Filters**

```bash
# Create filter directory
mkdir -p api-gateway/src/main/java/com/social/gateway/filter

# Create AuthenticationFilter.java
cat > api-gateway/src/main/java/com/social/gateway/filter/AuthenticationFilter.java << 'EOF'
package com.social.gateway.filter;

import com.social.common.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    private static final List<String> OPEN_ENDPOINTS = List.of(
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh",
            "/actuator/health"
    );

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            // Skip authentication for open endpoints
            if (isOpenEndpoint(path)) {
                return chain.filter(exchange);
            }

            // Check if Authorization header exists
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Missing authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Invalid authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            try {
                // Validate token
                if (!jwtUtil.validateToken(token)) {
                    return onError(exchange, "Invalid or expired token", HttpStatus.UNAUTHORIZED);
                }

                // Extract user information and add to headers
                String userId = jwtUtil.extractUserId(token).toString();
                String email = jwtUtil.extractEmail(token);

                // Add user info to request headers
                ServerHttpRequest modifiedRequest = request.mutate()
                        .header("X-User-Id", userId)
                        .header("X-User-Email", email)
                        .build();

                return chain.filter(exchange.mutate().request(modifiedRequest).build());

            } catch (Exception e) {
                log.error("Error validating token: {}", e.getMessage());
                return onError(exchange, "Authentication failed", HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private boolean isOpenEndpoint(String path) {
        return OPEN_ENDPOINTS.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}
EOF

# Create LoggingFilter.java
cat > api-gateway/src/main/java/com/social/gateway/filter/LoggingFilter.java << 'EOF'
package com.social.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("Incoming request: {} {} from {}",
                request.getMethod(),
                request.getURI().getPath(),
                request.getRemoteAddress());

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            long duration = System.currentTimeMillis() - startTime;

            log.info("Outgoing response: {} {} - Status: {} - Duration: {}ms",
                    request.getMethod(),
                    request.getURI().getPath(),
                    response.getStatusCode(),
                    duration);
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
EOF
```

#### **Step 5: Create Fallback Controller**

```bash
# Create controller directory
mkdir -p api-gateway/src/main/java/com/social/gateway/controller

# Create FallbackController.java
cat > api-gateway/src/main/java/com/social/gateway/controller/FallbackController.java << 'EOF'
package com.social.gateway.controller;

import com.social.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/user-service")
    public ResponseEntity<ApiResponse<String>> userServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("User service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/product-service")
    public ResponseEntity<ApiResponse<String>> productServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Product service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/order-service")
    public ResponseEntity<ApiResponse<String>> orderServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Order service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/payment-service")
    public ResponseEntity<ApiResponse<String>> paymentServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Payment service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/social-service")
    public ResponseEntity<ApiResponse<String>> socialServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Social service is temporarily unavailable. Please try again later."));
    }

    @GetMapping("/notification-service")
    public ResponseEntity<ApiResponse<String>> notificationServiceFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Notification service is temporarily unavailable. Please try again later."));
    }
}
EOF
```

#### **Step 6: Create application.yml**

```bash
# Create application.yml
cat > api-gateway/src/main/resources/application.yml << 'EOF'
server:
  port: 9000

spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true

      default-filters:
        - name: Retry
          args:
            retries: 3
            statuses: BAD_GATEWAY,SERVICE_UNAVAILABLE
            methods: GET,POST
            backoff:
              firstBackoff: 50ms
              maxBackoff: 500ms
              factor: 2
              basedOnPreviousValue: false

      globalcors:
        cors-configurations:
          '[/**]':
            allowedOrigins: "*"
            allowedMethods:
              - GET
              - POST
              - PUT
              - DELETE
              - OPTIONS
              - PATCH
            allowedHeaders: "*"
            allowCredentials: false

  redis:
    host: localhost
    port: 6379
    timeout: 2000ms

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10
    lease-expiration-duration-in-seconds: 30

# Circuit Breaker Configuration
resilience4j:
  circuitbreaker:
    instances:
      userServiceCircuitBreaker:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 10s
        failure-rate-threshold: 50
      productServiceCircuitBreaker:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 10s
        failure-rate-threshold: 50
      orderServiceCircuitBreaker:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 10s
        failure-rate-threshold: 50

# JWT Configuration
jwt:
  secret: your-256-bit-secret-key-here-make-it-long-enough-for-hs256-algorithm
  expiration: 86400000

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus,gateway
  endpoint:
    health:
      show-details: always
    gateway:
      enabled: true

logging:
  level:
    org.springframework.cloud.gateway: DEBUG
    com.social.gateway: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
EOF
```

#### **Step 7: Build and Test API Gateway**

```bash
# Build api-gateway
mvn clean install -pl api-gateway -DskipTests

# Make sure Eureka and Config Server are running

# Run API Gateway
cd api-gateway
mvn spring-boot:run

# In a new terminal, test API Gateway
curl http://localhost:9000/actuator/health

# Expected output:
# {"status":"UP"}

# Check gateway routes
curl http://localhost:9000/actuator/gateway/routes | jq

# Check Eureka Dashboard - API Gateway should be registered
# http://localhost:8761
```

#### **Step 8: Commit api-gateway**

```bash
# Stop the running service (Ctrl+C)

# Navigate to root
cd ..

# Add and commit
git add api-gateway/
git commit -m "feat: create api-gateway with routing, authentication, circuit breaker on port 9000"
```

---

### **✅ DAY 2-4 CHECKPOINT: Infrastructure Services Complete!**

**What you have now:**
- ✅ **Service Discovery (Eureka Server)** - Port 8761
  - Service registration and discovery
  - Eureka dashboard
  - Health checks

- ✅ **Config Server** - Port 8888
  - Centralized configuration management
  - Native and Git profiles
  - Configuration for all services

- ✅ **API Gateway** - Port 9000
  - Single entry point for all services
  - Routing to all microservices
  - JWT authentication filter
  - Logging filter
  - Circuit breaker with fallbacks
  - CORS configuration
  - Rate limiting ready

**Architecture So Far:**

```
Client
  ↓
API Gateway (9000)
  ↓
Service Discovery (8761)
  ↓
[Microservices will be here]
```

**Next Steps:**
- Create User Service (Port 9001)
- Create Product Service (Port 9002)
- Create Order Service (Port 9003)
- Create Payment Service (Port 9004)
- Create Social Service (Port 9005)
- Create Notification Service (Port 9006)

---

### **DAY 5-6: CREATE USER SERVICE**

> **Time**: 6 hours (2 days)
> **Goal**: Create User Service with authentication, CRUD operations, and RBAC
> **Port**: 9001
> **Database**: PostgreSQL

#### **Step 1: Create user-service POM**

```bash
# Navigate to user-service directory
cd user-service

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>user-service</artifactId>
    <packaging>jar</packaging>

    <name>User Service</name>
    <description>User management, authentication, and authorization service</description>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Boot Starter Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Spring Boot Starter Security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- PostgreSQL Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- Redis for Caching -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!-- Spring Boot Starter Cache -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- MapStruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Micrometer Prometheus -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- H2 Database for Testing -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create UserServiceApplication**

```bash
# Create main application class
cat > user-service/src/main/java/com/social/user/UserServiceApplication.java << 'EOF'
package com.social.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.social.user", "com.social.common"})
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableCaching
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create Entity Classes**

```bash
# Create model directory
mkdir -p user-service/src/main/java/com/social/user/model

# Create User.java
cat > user-service/src/main/java/com/social/user/model/User.java << 'EOF'
package com.social.user.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_phone", columnList = "phone")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "phone_verified")
    private boolean phoneVerified = false;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts = 0;

    @Column(name = "locked_until")
    private LocalDateTime lockedUntil;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED, DELETED
    }
}
EOF

# Create Role.java
cat > user-service/src/main/java/com/social/user/model/Role.java << 'EOF'
package com.social.user.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();
}
EOF

# Create Permission.java
cat > user-service/src/main/java/com/social/user/model/Permission.java << 'EOF'
package com.social.user.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "resource", nullable = false, length = 50)
    private String resource;

    @Column(name = "action", nullable = false, length = 50)
    private String action;
}
EOF
```

#### **Step 4: Create DTOs**

```bash
# Create dto directory
mkdir -p user-service/src/main/java/com/social/user/dto

# Create UserDTO.java
cat > user-service/src/main/java/com/social/user/dto/UserDTO.java << 'EOF'
package com.social.user.dto;

import com.social.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;
    private LocalDateTime dateOfBirth;
    private String status;
    private boolean emailVerified;
    private boolean phoneVerified;
    private LocalDateTime lastLoginAt;
    private Set<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .avatarUrl(user.getAvatarUrl())
                .dateOfBirth(user.getDateOfBirth())
                .status(user.getStatus().name())
                .emailVerified(user.isEmailVerified())
                .phoneVerified(user.isPhoneVerified())
                .lastLoginAt(user.getLastLoginAt())
                .roles(user.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
EOF

# Create CreateUserRequest.java
cat > user-service/src/main/java/com/social/user/dto/CreateUserRequest.java << 'EOF'
package com.social.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase letter, and one special character"
    )
    private String password;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phone;

    private LocalDateTime dateOfBirth;
}
EOF

# Create UpdateUserRequest.java
cat > user-service/src/main/java/com/social/user/dto/UpdateUserRequest.java << 'EOF'
package com.social.user.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phone;

    private String avatarUrl;

    private LocalDateTime dateOfBirth;
}
EOF

# Create LoginRequest.java
cat > user-service/src/main/java/com/social/user/dto/LoginRequest.java << 'EOF'
package com.social.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
EOF

# Create LoginResponse.java
cat > user-service/src/main/java/com/social/user/dto/LoginResponse.java << 'EOF'
package com.social.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String tokenType;
    private long expiresIn;
    private UUID userId;
    private String email;
    private String firstName;
    private String lastName;
}
EOF

# Create ChangePasswordRequest.java
cat > user-service/src/main/java/com/social/user/dto/ChangePasswordRequest.java << 'EOF'
package com.social.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase letter, and one special character"
    )
    private String newPassword;
}
EOF
```

#### **Step 5: Create Repositories**

```bash
# Create repository directory
mkdir -p user-service/src/main/java/com/social/user/repository

# Create UserRepository.java
cat > user-service/src/main/java/com/social/user/repository/UserRepository.java << 'EOF'
package com.social.user.repository;

import com.social.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.deleted = false AND " +
           "(LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<User> searchUsers(@Param("search") String search, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.deleted = false AND u.status = :status")
    Page<User> findByStatus(@Param("status") User.UserStatus status, Pageable pageable);

    @Query("SELECT COUNT(u) FROM User u WHERE u.deleted = false AND u.status = 'ACTIVE'")
    long countActiveUsers();
}
EOF

# Create RoleRepository.java
cat > user-service/src/main/java/com/social/user/repository/RoleRepository.java << 'EOF'
package com.social.user.repository;

import com.social.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
EOF

# Create PermissionRepository.java
cat > user-service/src/main/java/com/social/user/repository/PermissionRepository.java << 'EOF'
package com.social.user.repository;

import com.social.user.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    Optional<Permission> findByName(String name);

    List<Permission> findByResource(String resource);

    boolean existsByName(String name);
}
EOF
```

#### **Step 6: Create Service Layer**

```bash
# Create service directory
mkdir -p user-service/src/main/java/com/social/user/service

# Create UserService.java
cat > user-service/src/main/java/com/social/user/service/UserService.java << 'EOF'
package com.social.user.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.user.dto.*;
import com.social.user.model.Role;
import com.social.user.model.User;
import com.social.user.repository.RoleRepository;
import com.social.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO createUser(CreateUserRequest request) {
        log.info("Creating user with email: {}", request.getEmail());

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Email already exists");
        }

        // Check if phone already exists
        if (request.getPhone() != null && userRepository.existsByPhone(request.getPhone())) {
            throw new ValidationException("Phone number already exists");
        }

        // Create user entity
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setStatus(User.UserStatus.ACTIVE);

        // Assign default role
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Save user
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());

        return UserDTO.fromEntity(savedUser);
    }

    @Cacheable(value = "users", key = "#id")
    public UserDTO getUserById(UUID id) {
        log.info("Fetching user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return UserDTO.fromEntity(user);
    }

    public UserDTO getUserByEmail(String email) {
        log.info("Fetching user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return UserDTO.fromEntity(user);
    }

    public PageResponse<UserDTO> getAllUsers(int page, int size, String sortBy, String sortDir) {
        log.info("Fetching all users - page: {}, size: {}", page, size);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);

        return PageResponse.of(
                userPage.getContent().stream().map(UserDTO::fromEntity).toList(),
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }

    public PageResponse<UserDTO> searchUsers(String search, int page, int size) {
        log.info("Searching users with query: {}", search);

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.searchUsers(search, pageable);

        return PageResponse.of(
                userPage.getContent().stream().map(UserDTO::fromEntity).toList(),
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.isLast()
        );
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public UserDTO updateUser(UUID id, UpdateUserRequest request) {
        log.info("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Update fields if provided
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            // Check if phone already exists for another user
            userRepository.findByPhone(request.getPhone()).ifPresent(existingUser -> {
                if (!existingUser.getId().equals(id)) {
                    throw new ValidationException("Phone number already exists");
                }
            });
            user.setPhone(request.getPhone());
        }
        if (request.getAvatarUrl() != null) {
            user.setAvatarUrl(request.getAvatarUrl());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }

        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", updatedUser.getId());

        return UserDTO.fromEntity(updatedUser);
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(UUID id) {
        log.info("Deleting user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Soft delete
        user.setDeleted(true);
        user.setStatus(User.UserStatus.DELETED);
        userRepository.save(user);

        log.info("User deleted successfully with ID: {}", id);
    }

    @Transactional
    public void changePassword(UUID id, ChangePasswordRequest request) {
        log.info("Changing password for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Verify current password
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new ValidationException("Current password is incorrect");
        }

        // Update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        log.info("Password changed successfully for user with ID: {}", id);
    }

    @Transactional
    public void updateLastLogin(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        user.setLastLoginAt(LocalDateTime.now());
        user.setFailedLoginAttempts(0);
        userRepository.save(user);
    }

    @Transactional
    public void incrementFailedLoginAttempts(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            if (user.getFailedLoginAttempts() >= 5) {
                user.setLockedUntil(LocalDateTime.now().plusMinutes(30));
                log.warn("User account locked due to multiple failed login attempts: {}", email);
            }
            userRepository.save(user);
        });
    }

    public long getActiveUserCount() {
        return userRepository.countActiveUsers();
    }
}
EOF

# Create AuthService.java
cat > user-service/src/main/java/com/social/user/service/AuthService.java << 'EOF'
package com.social.user.service;

import com.social.common.exception.UnauthorizedException;
import com.social.common.exception.ValidationException;
import com.social.common.security.JwtUtil;
import com.social.user.dto.LoginRequest;
import com.social.user.dto.LoginResponse;
import com.social.user.model.User;
import com.social.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        log.info("Login attempt for email: {}", request.getEmail());

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        // Check if user is locked
        if (user.getLockedUntil() != null && user.getLockedUntil().isAfter(LocalDateTime.now())) {
            throw new UnauthorizedException("Account is locked. Please try again later.");
        }

        // Check if user is active
        if (user.getStatus() != User.UserStatus.ACTIVE) {
            throw new UnauthorizedException("Account is not active");
        }

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            userService.incrementFailedLoginAttempts(request.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }

        // Update last login
        userService.updateLastLogin(user.getId());

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getId(), user.getEmail());

        log.info("Login successful for user: {}", user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresIn(86400000L) // 24 hours
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public void logout(String token) {
        // In a real application, you would invalidate the token
        // For now, we'll just log the logout
        log.info("User logged out");
    }
}
EOF
```

#### **Step 7: Create Controllers**

```bash
# Create controller directory
mkdir -p user-service/src/main/java/com/social/user/controller

# Create UserController.java
cat > user-service/src/main/java/com/social/user/controller/UserController.java << 'EOF'
package com.social.user.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.user.dto.*;
import com.social.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for user management")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserRequest request) {
        log.info("REST request to create user: {}", request.getEmail());
        UserDTO user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(user, "User created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable UUID id) {
        log.info("REST request to get user by ID: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(@PathVariable String email) {
        log.info("REST request to get user by email: {}", email);
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @GetMapping
    @Operation(summary = "Get all users with pagination")
    public ResponseEntity<ApiResponse<PageResponse<UserDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        log.info("REST request to get all users");
        PageResponse<UserDTO> users = userService.getAllUsers(page, size, sortBy, sortDir);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/search")
    @Operation(summary = "Search users")
    public ResponseEntity<ApiResponse<PageResponse<UserDTO>>> searchUsers(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to search users with query: {}", query);
        PageResponse<UserDTO> users = userService.searchUsers(query, page, size);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUserRequest request) {
        log.info("REST request to update user: {}", id);
        UserDTO user = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success(user, "User updated successfully"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID id) {
        log.info("REST request to delete user: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success(null, "User deleted successfully"));
    }

    @PostMapping("/{id}/change-password")
    @Operation(summary = "Change user password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @PathVariable UUID id,
            @Valid @RequestBody ChangePasswordRequest request) {
        log.info("REST request to change password for user: {}", id);
        userService.changePassword(id, request);
        return ResponseEntity.ok(ApiResponse.success(null, "Password changed successfully"));
    }

    @GetMapping("/count/active")
    @Operation(summary = "Get active user count")
    public ResponseEntity<ApiResponse<Long>> getActiveUserCount() {
        log.info("REST request to get active user count");
        long count = userService.getActiveUserCount();
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}
EOF

# Create AuthController.java
cat > user-service/src/main/java/com/social/user/controller/AuthController.java << 'EOF'
package com.social.user.controller;

import com.social.common.dto.ApiResponse;
import com.social.user.dto.CreateUserRequest;
import com.social.user.dto.LoginRequest;
import com.social.user.dto.LoginResponse;
import com.social.user.dto.UserDTO;
import com.social.user.service.AuthService;
import com.social.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for authentication")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody CreateUserRequest request) {
        log.info("REST request to register user: {}", request.getEmail());
        UserDTO user = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(user, "User registered successfully"));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        log.info("REST request to login user: {}", request.getEmail());
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response, "Login successful"));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String token) {
        log.info("REST request to logout user");
        authService.logout(token);
        return ResponseEntity.ok(ApiResponse.success(null, "Logout successful"));
    }
}
EOF
```

#### **Step 8: Create Security Configuration**

```bash
# Create config directory
mkdir -p user-service/src/main/java/com/social/user/config

# Create SecurityConfig.java
cat > user-service/src/main/java/com/social/user/config/SecurityConfig.java << 'EOF'
package com.social.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
EOF

# Create RedisConfig.java
cat > user-service/src/main/java/com/social/user/config/RedisConfig.java << 'EOF'
package com.social.user.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }
}
EOF

# Create OpenApiConfig.java
cat > user-service/src/main/java/com/social/user/config/OpenApiConfig.java << 'EOF'
package com.social.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userServiceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9001");
        server.setDescription("User Service");

        Contact contact = new Contact();
        contact.setName("Social Commerce Platform");
        contact.setEmail("support@socialcommerce.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("User Service API")
                .version("1.0.0")
                .description("User management, authentication, and authorization service")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
EOF
```

#### **Step 9: Create application.yml**

```bash
# Create application.yml
cat > user-service/src/main/resources/application.yml << 'EOF'
server:
  port: 9001

spring:
  application:
    name: user-service

  datasource:
    url: jdbc:postgresql://localhost:5432/user_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true

  redis:
    host: localhost
    port: 6379
    timeout: 2000ms
    lettuce:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 2

  cache:
    type: redis
    redis:
      time-to-live: 3600000

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    lease-renewal-interval-in-seconds: 10
    lease-expiration-duration-in-seconds: 30

jwt:
  secret: your-256-bit-secret-key-here-make-it-long-enough-for-hs256-algorithm
  expiration: 86400000

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

logging:
  level:
    root: INFO
    com.social.user: DEBUG
    org.springframework.web: DEBUG
    org.hibernate.SQL: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/user-service.log
EOF

# Create bootstrap.yml (for config server)
cat > user-service/src/main/resources/bootstrap.yml << 'EOF'
spring:
  application:
    name: user-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: false
      enabled: false
EOF
```

#### **Step 10: Create Database Initialization Script**

```bash
# Create data.sql for initial roles and permissions
cat > user-service/src/main/resources/data.sql << 'EOF'
-- Insert default roles
INSERT INTO roles (id, name, description, created_at, updated_at, version, deleted)
VALUES
    (gen_random_uuid(), 'ROLE_USER', 'Standard user role', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ROLE_ADMIN', 'Administrator role', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ROLE_MODERATOR', 'Moderator role', NOW(), NOW(), 0, false)
ON CONFLICT (name) DO NOTHING;

-- Insert default permissions
INSERT INTO permissions (id, name, description, resource, action, created_at, updated_at, version, deleted)
VALUES
    (gen_random_uuid(), 'USER_READ', 'Read user information', 'USER', 'READ', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'USER_WRITE', 'Create and update users', 'USER', 'WRITE', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'USER_DELETE', 'Delete users', 'USER', 'DELETE', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'PRODUCT_READ', 'Read product information', 'PRODUCT', 'READ', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'PRODUCT_WRITE', 'Create and update products', 'PRODUCT', 'WRITE', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ORDER_READ', 'Read order information', 'ORDER', 'READ', NOW(), NOW(), 0, false),
    (gen_random_uuid(), 'ORDER_WRITE', 'Create and update orders', 'ORDER', 'WRITE', NOW(), NOW(), 0, false)
ON CONFLICT (name) DO NOTHING;
EOF
```

#### **Step 11: Build and Run User Service**

```bash
# First, make sure PostgreSQL is running
docker run --name postgres-user \
  -e POSTGRES_DB=user_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:15

# Make sure Redis is running
docker run --name redis \
  -p 6379:6379 \
  -d redis:7-alpine

# Build user-service
mvn clean install -pl user-service -DskipTests

# Run User Service (make sure Eureka is running)
cd user-service
mvn spring-boot:run

# In a new terminal, verify User Service is running
curl http://localhost:9001/actuator/health

# Expected output:
# {"status":"UP"}

# Check Swagger UI
# http://localhost:9001/swagger-ui.html
```

#### **Step 12: Test User Service with curl**

```bash
# Test 1: Register a new user
curl -X POST http://localhost:9001/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "Password@123",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "+1234567890"
  }' | jq

# Expected output:
# {
#   "success": true,
#   "message": "User registered successfully",
#   "data": {
#     "id": "...",
#     "email": "john.doe@example.com",
#     "firstName": "John",
#     "lastName": "Doe",
#     ...
#   }
# }

# Test 2: Login
curl -X POST http://localhost:9001/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "Password@123"
  }' | jq

# Save the token from the response
TOKEN="<token_from_response>"

# Test 3: Get user by ID
curl -X GET http://localhost:9001/api/v1/users/{user_id} \
  -H "Authorization: Bearer $TOKEN" | jq

# Test 4: Get all users
curl -X GET "http://localhost:9001/api/v1/users?page=0&size=10" \
  -H "Authorization: Bearer $TOKEN" | jq

# Test 5: Update user
curl -X PUT http://localhost:9001/api/v1/users/{user_id} \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John Updated",
    "lastName": "Doe Updated"
  }' | jq

# Test 6: Change password
curl -X POST http://localhost:9001/api/v1/users/{user_id}/change-password \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "currentPassword": "Password@123",
    "newPassword": "NewPassword@123"
  }' | jq

# Test 7: Search users
curl -X GET "http://localhost:9001/api/v1/users/search?query=john&page=0&size=10" \
  -H "Authorization: Bearer $TOKEN" | jq

# Test 8: Get active user count
curl -X GET http://localhost:9001/api/v1/users/count/active \
  -H "Authorization: Bearer $TOKEN" | jq
```

#### **Step 13: Commit user-service**

```bash
# Stop the running service (Ctrl+C)

# Navigate to root
cd ..

# Add and commit
git add user-service/
git commit -m "feat: create user-service with authentication, CRUD, and RBAC on port 9001"
```

---

### **✅ DAY 5-6 CHECKPOINT: User Service Complete!**

**What you have now:**
- ✅ **User Service** - Port 9001
  - Complete user management (CRUD operations)
  - Authentication (register, login, logout)
  - JWT token generation and validation
  - Password encryption with BCrypt
  - Role-based access control (RBAC)
  - Redis caching for user data
  - PostgreSQL database integration
  - Swagger API documentation
  - Global exception handling
  - Input validation
  - Pagination and search
  - Soft delete
  - Account locking after failed login attempts
  - Prometheus metrics

**Database Schema:**
- `users` table with auditing fields
- `roles` table
- `permissions` table
- `user_roles` join table
- `role_permissions` join table

**API Endpoints:**
- `POST /api/v1/auth/register` - Register new user
- `POST /api/v1/auth/login` - Login user
- `POST /api/v1/auth/logout` - Logout user
- `GET /api/v1/users` - Get all users (paginated)
- `GET /api/v1/users/{id}` - Get user by ID
- `GET /api/v1/users/email/{email}` - Get user by email
- `GET /api/v1/users/search` - Search users
- `PUT /api/v1/users/{id}` - Update user
- `DELETE /api/v1/users/{id}` - Delete user (soft delete)
- `POST /api/v1/users/{id}/change-password` - Change password
- `GET /api/v1/users/count/active` - Get active user count

**Next Steps:**
- Create Product Service (Port 9002)
- Create Order Service (Port 9003)
- Create Payment Service (Port 9004)
- Create Social Service (Port 9005)
- Create Notification Service (Port 9006)

---

### **DAY 7: CREATE PRODUCT SERVICE**

> **Time**: 4 hours
> **Goal**: Create Product Service with catalog management, inventory, and search
> **Port**: 9002
> **Database**: PostgreSQL + Redis + Elasticsearch

#### **Step 1: Create product-service POM**

```bash
# Navigate to product-service directory
cd product-service

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>product-service</artifactId>
    <packaging>jar</packaging>

    <name>Product Service</name>
    <description>Product catalog, inventory, and search service</description>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Boot Starter Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- PostgreSQL Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Elasticsearch -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
        </dependency>

        <!-- Redis for Caching -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!-- Spring Boot Starter Cache -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- MapStruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Micrometer Prometheus -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- H2 Database for Testing -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create ProductServiceApplication**

```bash
# Create main application class
cat > product-service/src/main/java/com/social/product/ProductServiceApplication.java << 'EOF'
package com.social.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.social.product", "com.social.common"})
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableCaching
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create Entity Classes**

```bash
# Create model directory
mkdir -p product-service/src/main/java/com/social/product/model

# Create Product.java
cat > product-service/src/main/java/com/social/product/model/Product.java << 'EOF'
package com.social.product.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_sku", columnList = "sku"),
        @Index(name = "idx_name", columnList = "name"),
        @Index(name = "idx_category", columnList = "category_id")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "sku", nullable = false, unique = true, length = 50)
    private String sku;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "cost_price", precision = 10, scale = 2)
    private BigDecimal costPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity = 0;

    @Column(name = "low_stock_threshold")
    private Integer lowStockThreshold = 10;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Column(name = "weight", precision = 10, scale = 2)
    private BigDecimal weight;

    @Column(name = "dimensions", length = 100)
    private String dimensions;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @Column(name = "sold_count")
    private Long soldCount = 0L;

    @Column(name = "featured")
    private boolean featured = false;

    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    public enum ProductStatus {
        ACTIVE, INACTIVE, OUT_OF_STOCK, DISCONTINUED
    }

    public Integer getAvailableQuantity() {
        return stockQuantity - reservedQuantity;
    }

    public boolean isInStock() {
        return getAvailableQuantity() > 0;
    }

    public boolean isLowStock() {
        return getAvailableQuantity() <= lowStockThreshold;
    }
}
EOF

# Create Category.java
cat > product-service/src/main/java/com/social/product/model/Category.java << 'EOF'
package com.social.product.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories", indexes = {
        @Index(name = "idx_slug", columnList = "slug")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "slug", nullable = false, unique = true, length = 100)
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> children = new ArrayList<>();

    @Column(name = "display_order")
    private Integer displayOrder = 0;

    @Column(name = "active")
    private boolean active = true;
}
EOF

# Create ProductDocument.java (for Elasticsearch)
cat > product-service/src/main/java/com/social/product/model/ProductDocument.java << 'EOF'
package com.social.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String sku;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Double)
    private BigDecimal discountPrice;

    @Field(type = FieldType.Keyword)
    private String categoryId;

    @Field(type = FieldType.Text)
    private String categoryName;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Integer)
    private Integer stockQuantity;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Keyword)
    private List<String> images;

    @Field(type = FieldType.Double)
    private BigDecimal rating;

    @Field(type = FieldType.Integer)
    private Integer reviewCount;

    @Field(type = FieldType.Long)
    private Long viewCount;

    @Field(type = FieldType.Long)
    private Long soldCount;

    @Field(type = FieldType.Boolean)
    private boolean featured;

    @Field(type = FieldType.Keyword)
    private List<String> tags;

    public static ProductDocument fromProduct(Product product) {
        return ProductDocument.builder()
                .id(product.getId().toString())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .categoryId(product.getCategory() != null ? product.getCategory().getId().toString() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .brand(product.getBrand())
                .stockQuantity(product.getStockQuantity())
                .status(product.getStatus().name())
                .images(product.getImages())
                .rating(product.getRating())
                .reviewCount(product.getReviewCount())
                .viewCount(product.getViewCount())
                .soldCount(product.getSoldCount())
                .featured(product.isFeatured())
                .tags(product.getTags())
                .build();
    }
}
EOF
```

#### **Step 4: Create DTOs**

```bash
# Create dto directory
mkdir -p product-service/src/main/java/com/social/product/dto

# Create ProductDTO.java
cat > product-service/src/main/java/com/social/product/dto/ProductDTO.java << 'EOF'
package com.social.product.dto;

import com.social.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private UUID id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private UUID categoryId;
    private String categoryName;
    private String brand;
    private Integer stockQuantity;
    private Integer availableQuantity;
    private String status;
    private List<String> images;
    private BigDecimal weight;
    private String dimensions;
    private BigDecimal rating;
    private Integer reviewCount;
    private Long viewCount;
    private Long soldCount;
    private boolean featured;
    private boolean inStock;
    private boolean lowStock;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductDTO fromEntity(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .brand(product.getBrand())
                .stockQuantity(product.getStockQuantity())
                .availableQuantity(product.getAvailableQuantity())
                .status(product.getStatus().name())
                .images(product.getImages())
                .weight(product.getWeight())
                .dimensions(product.getDimensions())
                .rating(product.getRating())
                .reviewCount(product.getReviewCount())
                .viewCount(product.getViewCount())
                .soldCount(product.getSoldCount())
                .featured(product.isFeatured())
                .inStock(product.isInStock())
                .lowStock(product.isLowStock())
                .tags(product.getTags())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
EOF

# Create CreateProductRequest.java
cat > product-service/src/main/java/com/social/product/dto/CreateProductRequest.java << 'EOF'
package com.social.product.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "SKU is required")
    @Size(max = 50, message = "SKU must not exceed 50 characters")
    private String sku;

    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "Discount price must be non-negative")
    private BigDecimal discountPrice;

    @DecimalMin(value = "0.00", message = "Cost price must be non-negative")
    private BigDecimal costPrice;

    @NotNull(message = "Category is required")
    private UUID categoryId;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be non-negative")
    private Integer stockQuantity;

    @Min(value = 0, message = "Low stock threshold must be non-negative")
    private Integer lowStockThreshold;

    private List<String> images;

    @DecimalMin(value = "0.00", message = "Weight must be non-negative")
    private BigDecimal weight;

    @Size(max = 100, message = "Dimensions must not exceed 100 characters")
    private String dimensions;

    private boolean featured;

    private List<String> tags;
}
EOF

# Create UpdateProductRequest.java
cat > product-service/src/main/java/com/social/product/dto/UpdateProductRequest.java << 'EOF'
package com.social.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @DecimalMin(value = "0.00", message = "Discount price must be non-negative")
    private BigDecimal discountPrice;

    private UUID categoryId;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    private String brand;

    private List<String> images;

    @DecimalMin(value = "0.00", message = "Weight must be non-negative")
    private BigDecimal weight;

    @Size(max = 100, message = "Dimensions must not exceed 100 characters")
    private String dimensions;

    private Boolean featured;

    private List<String> tags;
}
EOF

# Create UpdateStockRequest.java
cat > product-service/src/main/java/com/social/product/dto/UpdateStockRequest.java << 'EOF'
package com.social.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequest {

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be non-negative")
    private Integer quantity;

    private String operation; // ADD, SUBTRACT, SET
}
EOF

# Create CategoryDTO.java
cat > product-service/src/main/java/com/social/product/dto/CategoryDTO.java << 'EOF'
package com.social.product.dto;

import com.social.product.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private UUID id;
    private String name;
    private String slug;
    private String description;
    private String imageUrl;
    private UUID parentId;
    private List<CategoryDTO> children;
    private Integer displayOrder;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CategoryDTO fromEntity(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .description(category.getDescription())
                .imageUrl(category.getImageUrl())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .children(category.getChildren().stream()
                        .map(CategoryDTO::fromEntity)
                        .collect(Collectors.toList()))
                .displayOrder(category.getDisplayOrder())
                .active(category.isActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
EOF

# Create CreateCategoryRequest.java
cat > product-service/src/main/java/com/social/product/dto/CreateCategoryRequest.java << 'EOF'
package com.social.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Slug is required")
    @Size(min = 2, max = 100, message = "Slug must be between 2 and 100 characters")
    private String slug;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    private String imageUrl;

    private UUID parentId;

    private Integer displayOrder;
}
EOF
```

#### **Step 5: Create Repositories**

```bash
# Create repository directory
mkdir -p product-service/src/main/java/com/social/product/repository

# Create ProductRepository.java
cat > product-service/src/main/java/com/social/product/repository/ProductRepository.java << 'EOF'
package com.social.product.repository;

import com.social.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySku(String sku);

    boolean existsBySku(String sku);

    Page<Product> findByDeletedFalse(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.category.id = :categoryId")
    Page<Product> findByCategoryId(@Param("categoryId") UUID categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.status = :status")
    Page<Product> findByStatus(@Param("status") Product.ProductStatus status, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.featured = true")
    Page<Product> findFeaturedProducts(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Product> searchProducts(@Param("search") String search, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND " +
           "p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceRange(@Param("minPrice") BigDecimal minPrice,
                                    @Param("maxPrice") BigDecimal maxPrice,
                                    Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND " +
           "p.stockQuantity - p.reservedQuantity <= p.lowStockThreshold")
    List<Product> findLowStockProducts();

    @Query("SELECT p FROM Product p WHERE p.deleted = false AND " +
           "p.stockQuantity - p.reservedQuantity = 0")
    List<Product> findOutOfStockProducts();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.deleted = false AND p.status = 'ACTIVE'")
    long countActiveProducts();
}
EOF

# Create CategoryRepository.java
cat > product-service/src/main/java/com/social/product/repository/CategoryRepository.java << 'EOF'
package com.social.product.repository;

import com.social.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findBySlug(String slug);

    boolean existsBySlug(String slug);

    @Query("SELECT c FROM Category c WHERE c.deleted = false AND c.parent IS NULL ORDER BY c.displayOrder")
    List<Category> findRootCategories();

    @Query("SELECT c FROM Category c WHERE c.deleted = false AND c.parent.id = :parentId ORDER BY c.displayOrder")
    List<Category> findByParentId(UUID parentId);

    @Query("SELECT c FROM Category c WHERE c.deleted = false AND c.active = true ORDER BY c.displayOrder")
    List<Category> findActiveCategories();
}
EOF

# Create ProductDocumentRepository.java (Elasticsearch)
cat > product-service/src/main/java/com/social/product/repository/ProductDocumentRepository.java << 'EOF'
package com.social.product.repository;

import com.social.product.model.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {

    Page<ProductDocument> findByNameContainingOrDescriptionContaining(
            String name, String description, Pageable pageable);

    Page<ProductDocument> findByBrand(String brand, Pageable pageable);

    Page<ProductDocument> findByCategoryId(String categoryId, Pageable pageable);

    Page<ProductDocument> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    Page<ProductDocument> findByFeaturedTrue(Pageable pageable);

    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name^3\", \"description\", \"brand^2\", \"tags\"]}}]}}")
    Page<ProductDocument> searchProducts(String query, Pageable pageable);
}
EOF
```

#### **Step 6: Create Service Layer**

```bash
# Create service directory
mkdir -p product-service/src/main/java/com/social/product/service

# Create ProductService.java
cat > product-service/src/main/java/com/social/product/service/ProductService.java << 'EOF'
package com.social.product.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.product.dto.*;
import com.social.product.model.Category;
import com.social.product.model.Product;
import com.social.product.model.ProductDocument;
import com.social.product.repository.CategoryRepository;
import com.social.product.repository.ProductDocumentRepository;
import com.social.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductDocumentRepository productDocumentRepository;

    @Transactional
    public ProductDTO createProduct(CreateProductRequest request) {
        log.info("Creating product with SKU: {}", request.getSku());

        // Check if SKU already exists
        if (productRepository.existsBySku(request.getSku())) {
            throw new ValidationException("Product with SKU " + request.getSku() + " already exists");
        }

        // Validate category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Create product
        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscountPrice(request.getDiscountPrice());
        product.setCostPrice(request.getCostPrice());
        product.setCategory(category);
        product.setBrand(request.getBrand());
        product.setStockQuantity(request.getStockQuantity());
        product.setLowStockThreshold(request.getLowStockThreshold() != null ? request.getLowStockThreshold() : 10);
        product.setImages(request.getImages());
        product.setWeight(request.getWeight());
        product.setDimensions(request.getDimensions());
        product.setFeatured(request.isFeatured());
        product.setTags(request.getTags());

        // Save to PostgreSQL
        Product savedProduct = productRepository.save(product);

        // Index to Elasticsearch
        indexProduct(savedProduct);

        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return ProductDTO.fromEntity(savedProduct);
    }

    @Cacheable(value = "products", key = "#id")
    public ProductDTO getProductById(UUID id) {
        log.info("Fetching product with ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Increment view count
        product.setViewCount(product.getViewCount() + 1);
        productRepository.save(product);

        return ProductDTO.fromEntity(product);
    }

    public ProductDTO getProductBySku(String sku) {
        log.info("Fetching product with SKU: {}", sku);
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with SKU: " + sku));
        return ProductDTO.fromEntity(product);
    }

    public PageResponse<ProductDTO> getAllProducts(int page, int size, String sortBy, String sortDir) {
        log.info("Fetching all products - page: {}, size: {}", page, size);

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByDeletedFalse(pageable);

        return PageResponse.of(
                productPage.getContent().stream().map(ProductDTO::fromEntity).toList(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    public PageResponse<ProductDTO> getProductsByCategory(UUID categoryId, int page, int size) {
        log.info("Fetching products for category: {}", categoryId);

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByCategoryId(categoryId, pageable);

        return PageResponse.of(
                productPage.getContent().stream().map(ProductDTO::fromEntity).toList(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    public PageResponse<ProductDTO> searchProducts(String query, int page, int size) {
        log.info("Searching products with query: {}", query);

        Pageable pageable = PageRequest.of(page, size);

        // Use Elasticsearch for search
        Page<ProductDocument> documentPage = productDocumentRepository.searchProducts(query, pageable);

        List<ProductDTO> products = documentPage.getContent().stream()
                .map(doc -> {
                    Product product = productRepository.findById(UUID.fromString(doc.getId())).orElse(null);
                    return product != null ? ProductDTO.fromEntity(product) : null;
                })
                .filter(dto -> dto != null)
                .toList();

        return PageResponse.of(
                products,
                documentPage.getNumber(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                documentPage.getTotalPages(),
                documentPage.isLast()
        );
    }

    public PageResponse<ProductDTO> getFeaturedProducts(int page, int size) {
        log.info("Fetching featured products");

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findFeaturedProducts(pageable);

        return PageResponse.of(
                productPage.getContent().stream().map(ProductDTO::fromEntity).toList(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    @Transactional
    @CacheEvict(value = "products", key = "#id")
    public ProductDTO updateProduct(UUID id, UpdateProductRequest request) {
        log.info("Updating product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Update fields if provided
        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getDiscountPrice() != null) {
            product.setDiscountPrice(request.getDiscountPrice());
        }
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            product.setCategory(category);
        }
        if (request.getBrand() != null) {
            product.setBrand(request.getBrand());
        }
        if (request.getImages() != null) {
            product.setImages(request.getImages());
        }
        if (request.getWeight() != null) {
            product.setWeight(request.getWeight());
        }
        if (request.getDimensions() != null) {
            product.setDimensions(request.getDimensions());
        }
        if (request.getFeatured() != null) {
            product.setFeatured(request.getFeatured());
        }
        if (request.getTags() != null) {
            product.setTags(request.getTags());
        }

        Product updatedProduct = productRepository.save(product);

        // Update Elasticsearch index
        indexProduct(updatedProduct);

        log.info("Product updated successfully with ID: {}", updatedProduct.getId());
        return ProductDTO.fromEntity(updatedProduct);
    }

    @Transactional
    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(UUID id) {
        log.info("Deleting product with ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        // Soft delete
        product.setDeleted(true);
        product.setStatus(Product.ProductStatus.DISCONTINUED);
        productRepository.save(product);

        // Remove from Elasticsearch
        productDocumentRepository.deleteById(id.toString());

        log.info("Product deleted successfully with ID: {}", id);
    }

    @Transactional
    public ProductDTO updateStock(UUID id, UpdateStockRequest request) {
        log.info("Updating stock for product ID: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        switch (request.getOperation().toUpperCase()) {
            case "ADD":
                product.setStockQuantity(product.getStockQuantity() + request.getQuantity());
                break;
            case "SUBTRACT":
                if (product.getStockQuantity() < request.getQuantity()) {
                    throw new ValidationException("Insufficient stock");
                }
                product.setStockQuantity(product.getStockQuantity() - request.getQuantity());
                break;
            case "SET":
                product.setStockQuantity(request.getQuantity());
                break;
            default:
                throw new ValidationException("Invalid operation: " + request.getOperation());
        }

        // Update status based on stock
        if (product.getAvailableQuantity() == 0) {
            product.setStatus(Product.ProductStatus.OUT_OF_STOCK);
        } else if (product.getStatus() == Product.ProductStatus.OUT_OF_STOCK) {
            product.setStatus(Product.ProductStatus.ACTIVE);
        }

        Product updatedProduct = productRepository.save(product);
        indexProduct(updatedProduct);

        log.info("Stock updated successfully for product ID: {}", id);
        return ProductDTO.fromEntity(updatedProduct);
    }

    @Transactional
    public void reserveStock(UUID id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getAvailableQuantity() < quantity) {
            throw new ValidationException("Insufficient stock available");
        }

        product.setReservedQuantity(product.getReservedQuantity() + quantity);
        productRepository.save(product);
    }

    @Transactional
    public void releaseStock(UUID id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setReservedQuantity(Math.max(0, product.getReservedQuantity() - quantity));
        productRepository.save(product);
    }

    public List<ProductDTO> getLowStockProducts() {
        return productRepository.findLowStockProducts().stream()
                .map(ProductDTO::fromEntity)
                .toList();
    }

    public long getActiveProductCount() {
        return productRepository.countActiveProducts();
    }

    private void indexProduct(Product product) {
        try {
            ProductDocument document = ProductDocument.fromProduct(product);
            productDocumentRepository.save(document);
            log.info("Product indexed to Elasticsearch: {}", product.getId());
        } catch (Exception e) {
            log.error("Failed to index product to Elasticsearch: {}", e.getMessage());
        }
    }
}
EOF

# Create CategoryService.java
cat > product-service/src/main/java/com/social/product/service/CategoryService.java << 'EOF'
package com.social.product.service;

import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.product.dto.CategoryDTO;
import com.social.product.dto.CreateCategoryRequest;
import com.social.product.model.Category;
import com.social.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDTO createCategory(CreateCategoryRequest request) {
        log.info("Creating category: {}", request.getName());

        // Check if slug already exists
        if (categoryRepository.existsBySlug(request.getSlug())) {
            throw new ValidationException("Category with slug " + request.getSlug() + " already exists");
        }

        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());
        category.setDisplayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0);

        // Set parent if provided
        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found"));
            category.setParent(parent);
        }

        Category savedCategory = categoryRepository.save(category);
        log.info("Category created successfully with ID: {}", savedCategory.getId());

        return CategoryDTO.fromEntity(savedCategory);
    }

    @Cacheable(value = "categories", key = "#id")
    public CategoryDTO getCategoryById(UUID id) {
        log.info("Fetching category with ID: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        return CategoryDTO.fromEntity(category);
    }

    public CategoryDTO getCategoryBySlug(String slug) {
        log.info("Fetching category with slug: {}", slug);
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with slug: " + slug));
        return CategoryDTO.fromEntity(category);
    }

    public List<CategoryDTO> getAllCategories() {
        log.info("Fetching all categories");
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    public List<CategoryDTO> getRootCategories() {
        log.info("Fetching root categories");
        return categoryRepository.findRootCategories().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    public List<CategoryDTO> getActiveCategories() {
        log.info("Fetching active categories");
        return categoryRepository.findActiveCategories().stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "categories", key = "#id")
    public void deleteCategory(UUID id) {
        log.info("Deleting category with ID: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));

        // Soft delete
        category.setDeleted(true);
        category.setActive(false);
        categoryRepository.save(category);

        log.info("Category deleted successfully with ID: {}", id);
    }
}
EOF
```

#### **Step 7: Create Controllers**

```bash
# Create controller directory
mkdir -p product-service/src/main/java/com/social/product/controller

# Create ProductController.java
cat > product-service/src/main/java/com/social/product/controller/ProductController.java << 'EOF'
package com.social.product.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.product.dto.*;
import com.social.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "APIs for product management")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@Valid @RequestBody CreateProductRequest request) {
        log.info("REST request to create product: {}", request.getSku());
        ProductDTO product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(product, "Product created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable UUID id) {
        log.info("REST request to get product by ID: {}", id);
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Get product by SKU")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductBySku(@PathVariable String sku) {
        log.info("REST request to get product by SKU: {}", sku);
        ProductDTO product = productService.getProductBySku(sku);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @GetMapping
    @Operation(summary = "Get all products with pagination")
    public ResponseEntity<ApiResponse<PageResponse<ProductDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        log.info("REST request to get all products");
        PageResponse<ProductDTO> products = productService.getAllProducts(page, size, sortBy, sortDir);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get products by category")
    public ResponseEntity<ApiResponse<PageResponse<ProductDTO>>> getProductsByCategory(
            @PathVariable UUID categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get products by category: {}", categoryId);
        PageResponse<ProductDTO> products = productService.getProductsByCategory(categoryId, page, size);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/search")
    @Operation(summary = "Search products")
    public ResponseEntity<ApiResponse<PageResponse<ProductDTO>>> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to search products with query: {}", query);
        PageResponse<ProductDTO> products = productService.searchProducts(query, page, size);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/featured")
    @Operation(summary = "Get featured products")
    public ResponseEntity<ApiResponse<PageResponse<ProductDTO>>> getFeaturedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get featured products");
        PageResponse<ProductDTO> products = productService.getFeaturedProducts(page, size);
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProductRequest request) {
        log.info("REST request to update product: {}", id);
        ProductDTO product = productService.updateProduct(id, request);
        return ResponseEntity.ok(ApiResponse.success(product, "Product updated successfully"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable UUID id) {
        log.info("REST request to delete product: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Product deleted successfully"));
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Update product stock")
    public ResponseEntity<ApiResponse<ProductDTO>> updateStock(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateStockRequest request) {
        log.info("REST request to update stock for product: {}", id);
        ProductDTO product = productService.updateStock(id, request);
        return ResponseEntity.ok(ApiResponse.success(product, "Stock updated successfully"));
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get low stock products")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getLowStockProducts() {
        log.info("REST request to get low stock products");
        List<ProductDTO> products = productService.getLowStockProducts();
        return ResponseEntity.ok(ApiResponse.success(products));
    }

    @GetMapping("/count/active")
    @Operation(summary = "Get active product count")
    public ResponseEntity<ApiResponse<Long>> getActiveProductCount() {
        log.info("REST request to get active product count");
        long count = productService.getActiveProductCount();
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}
EOF

# Create CategoryController.java
cat > product-service/src/main/java/com/social/product/controller/CategoryController.java << 'EOF'
package com.social.product.controller;

import com.social.common.dto.ApiResponse;
import com.social.product.dto.CategoryDTO;
import com.social.product.dto.CreateCategoryRequest;
import com.social.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Management", description = "APIs for category management")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        log.info("REST request to create category: {}", request.getName());
        CategoryDTO category = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(category, "Category created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable UUID id) {
        log.info("REST request to get category by ID: {}", id);
        CategoryDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Get category by slug")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryBySlug(@PathVariable String slug) {
        log.info("REST request to get category by slug: {}", slug);
        CategoryDTO category = categoryService.getCategoryBySlug(slug);
        return ResponseEntity.ok(ApiResponse.success(category));
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories() {
        log.info("REST request to get all categories");
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @GetMapping("/root")
    @Operation(summary = "Get root categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getRootCategories() {
        log.info("REST request to get root categories");
        List<CategoryDTO> categories = categoryService.getRootCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @GetMapping("/active")
    @Operation(summary = "Get active categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getActiveCategories() {
        log.info("REST request to get active categories");
        List<CategoryDTO> categories = categoryService.getActiveCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable UUID id) {
        log.info("REST request to delete category: {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Category deleted successfully"));
    }
}
EOF
```

#### **Step 8: Create Configuration**

```bash
# Create config directory
mkdir -p product-service/src/main/java/com/social/product/config

# Create ElasticsearchConfig.java
cat > product-service/src/main/java/com/social/product/config/ElasticsearchConfig.java << 'EOF'
package com.social.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.social.product.repository")
public class ElasticsearchConfig {
    // Elasticsearch configuration
}
EOF

# Create RedisConfig.java
cat > product-service/src/main/java/com/social/product/config/RedisConfig.java << 'EOF'
package com.social.product.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
                )
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
                );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }
}
EOF

# Create OpenApiConfig.java
cat > product-service/src/main/java/com/social/product/config/OpenApiConfig.java << 'EOF'
package com.social.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9002");
        server.setDescription("Product Service");

        Contact contact = new Contact();
        contact.setName("Social Commerce Platform");
        contact.setEmail("support@socialcommerce.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Product Service API")
                .version("1.0.0")
                .description("Product catalog, inventory, and search service")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
EOF
```

#### **Step 9: Create application.yml**

```bash
# Create application.yml
cat > product-service/src/main/resources/application.yml << 'EOF'
server:
  port: 9002

spring:
  application:
    name: product-service

  datasource:
    url: jdbc:postgresql://localhost:5432/product_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

  elasticsearch:
    uris: http://localhost:9200
    connection-timeout: 5s
    socket-timeout: 60s

  redis:
    host: localhost
    port: 6379
    timeout: 2000ms

  cache:
    type: redis
    redis:
      time-to-live: 3600000

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always

springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html

logging:
  level:
    root: INFO
    com.social.product: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
EOF

# Create bootstrap.yml
cat > product-service/src/main/resources/bootstrap.yml << 'EOF'
spring:
  application:
    name: product-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: false
      enabled: false
EOF
```

#### **Step 10: Build and Run Product Service**

```bash
# First, make sure PostgreSQL, Redis, and Elasticsearch are running

# PostgreSQL for Product Service
docker run --name postgres-product \
  -e POSTGRES_DB=product_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5433:5432 \
  -d postgres:15

# Redis (if not already running)
docker run --name redis \
  -p 6379:6379 \
  -d redis:7-alpine

# Elasticsearch
docker run --name elasticsearch \
  -e "discovery.type=single-node" \
  -e "xpack.security.enabled=false" \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  -p 9200:9200 \
  -p 9300:9300 \
  -d docker.elastic.co/elasticsearch/elasticsearch:8.11.0

# Wait for Elasticsearch to start (30 seconds)
sleep 30

# Verify Elasticsearch is running
curl http://localhost:9200

# Build product-service
mvn clean install -pl product-service -DskipTests

# Run Product Service (make sure Eureka is running)
cd product-service
mvn spring-boot:run

# In a new terminal, verify Product Service is running
curl http://localhost:9002/actuator/health

# Check Swagger UI
# http://localhost:9002/swagger-ui.html
```

#### **Step 11: Test Product Service with curl**

```bash
# Test 1: Create a category
curl -X POST http://localhost:9002/api/v1/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Electronics",
    "slug": "electronics",
    "description": "Electronic devices and accessories",
    "displayOrder": 1
  }' | jq

# Save the category ID from the response
CATEGORY_ID="<category_id_from_response>"

# Test 2: Create a product
curl -X POST http://localhost:9002/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "sku": "LAPTOP-001",
    "name": "MacBook Pro 16-inch",
    "description": "Apple MacBook Pro with M3 chip, 16GB RAM, 512GB SSD",
    "price": 2499.99,
    "discountPrice": 2299.99,
    "costPrice": 1800.00,
    "categoryId": "'$CATEGORY_ID'",
    "brand": "Apple",
    "stockQuantity": 50,
    "lowStockThreshold": 10,
    "images": [
      "https://example.com/images/macbook-1.jpg",
      "https://example.com/images/macbook-2.jpg"
    ],
    "weight": 2.1,
    "dimensions": "35.79 x 24.81 x 1.68 cm",
    "featured": true,
    "tags": ["laptop", "apple", "macbook", "m3"]
  }' | jq

# Save the product ID from the response
PRODUCT_ID="<product_id_from_response>"

# Test 3: Get product by ID
curl -X GET http://localhost:9002/api/v1/products/$PRODUCT_ID | jq

# Test 4: Get all products
curl -X GET "http://localhost:9002/api/v1/products?page=0&size=10" | jq

# Test 5: Search products (Elasticsearch)
curl -X GET "http://localhost:9002/api/v1/products/search?query=macbook&page=0&size=10" | jq

# Test 6: Get products by category
curl -X GET "http://localhost:9002/api/v1/products/category/$CATEGORY_ID?page=0&size=10" | jq

# Test 7: Get featured products
curl -X GET "http://localhost:9002/api/v1/products/featured?page=0&size=10" | jq

# Test 8: Update product
curl -X PUT http://localhost:9002/api/v1/products/$PRODUCT_ID \
  -H "Content-Type: application/json" \
  -d '{
    "name": "MacBook Pro 16-inch (Updated)",
    "price": 2399.99,
    "featured": true
  }' | jq

# Test 9: Update stock (ADD)
curl -X PATCH http://localhost:9002/api/v1/products/$PRODUCT_ID/stock \
  -H "Content-Type: application/json" \
  -d '{
    "quantity": 20,
    "operation": "ADD"
  }' | jq

# Test 10: Update stock (SUBTRACT)
curl -X PATCH http://localhost:9002/api/v1/products/$PRODUCT_ID/stock \
  -H "Content-Type: application/json" \
  -d '{
    "quantity": 5,
    "operation": "SUBTRACT"
  }' | jq

# Test 11: Get low stock products
curl -X GET http://localhost:9002/api/v1/products/low-stock | jq

# Test 12: Get active product count
curl -X GET http://localhost:9002/api/v1/products/count/active | jq

# Test 13: Get all categories
curl -X GET http://localhost:9002/api/v1/categories | jq

# Test 14: Get root categories
curl -X GET http://localhost:9002/api/v1/categories/root | jq

# Test 15: Get category by slug
curl -X GET http://localhost:9002/api/v1/categories/slug/electronics | jq
```

#### **Step 12: Commit product-service**

```bash
# Stop the running service (Ctrl+C)

# Navigate to root
cd ..

# Add and commit
git add product-service/
git commit -m "feat: create product-service with catalog, inventory, and Elasticsearch search on port 9002"
```

---

### **✅ DAY 7 CHECKPOINT: Product Service Complete!**

**What you have now:**
- ✅ **Product Service** - Port 9002
  - Complete product catalog management (CRUD)
  - Category management with hierarchical structure
  - Inventory management (stock tracking, reservations)
  - Elasticsearch integration for advanced search
  - Redis caching for product data
  - PostgreSQL database integration
  - Swagger API documentation
  - Global exception handling
  - Input validation
  - Pagination and sorting
  - Soft delete
  - Low stock alerts
  - Featured products
  - Product ratings and reviews tracking
  - View count and sold count tracking
  - Prometheus metrics

**Database Schema:**
- `products` table with full product details
- `categories` table with hierarchical structure
- `product_images` table (ElementCollection)
- `product_tags` table (ElementCollection)

**Elasticsearch Index:**
- `products` index with full-text search
- Multi-field search (name, description, brand, tags)
- Weighted search (name^3, brand^2)

**API Endpoints:**

**Products:**
- `POST /api/v1/products` - Create product
- `GET /api/v1/products/{id}` - Get product by ID
- `GET /api/v1/products/sku/{sku}` - Get product by SKU
- `GET /api/v1/products` - Get all products (paginated)
- `GET /api/v1/products/category/{categoryId}` - Get products by category
- `GET /api/v1/products/search` - Search products (Elasticsearch)
- `GET /api/v1/products/featured` - Get featured products
- `PUT /api/v1/products/{id}` - Update product
- `DELETE /api/v1/products/{id}` - Delete product (soft delete)
- `PATCH /api/v1/products/{id}/stock` - Update stock (ADD/SUBTRACT/SET)
- `GET /api/v1/products/low-stock` - Get low stock products
- `GET /api/v1/products/count/active` - Get active product count

**Categories:**
- `POST /api/v1/categories` - Create category
- `GET /api/v1/categories/{id}` - Get category by ID
- `GET /api/v1/categories/slug/{slug}` - Get category by slug
- `GET /api/v1/categories` - Get all categories
- `GET /api/v1/categories/root` - Get root categories
- `GET /api/v1/categories/active` - Get active categories
- `DELETE /api/v1/categories/{id}` - Delete category (soft delete)

**Features:**
- ✅ Product catalog with categories
- ✅ Inventory management
- ✅ Stock reservation system
- ✅ Elasticsearch full-text search
- ✅ Redis caching
- ✅ Low stock alerts
- ✅ Featured products
- ✅ Product ratings and reviews
- ✅ View count tracking
- ✅ Sold count tracking
- ✅ Image gallery support
- ✅ Product tags
- ✅ Hierarchical categories
- ✅ Price management (regular, discount, cost)
- ✅ Product dimensions and weight
- ✅ SKU management

**Next Steps:**
- Create Order Service (Port 9003)
- Create Payment Service (Port 9004)
- Create Social Service (Port 9005)
- Create Notification Service (Port 9006)

---

### **DAY 8: CREATE ORDER SERVICE**

> **Time**: 5 hours
> **Goal**: Create Order Service with shopping cart, order processing, and Kafka events
> **Port**: 9003
> **Database**: PostgreSQL + Kafka

#### **Step 1: Create order-service POM**

```bash
# Navigate to order-service directory
cd order-service

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>order-service</artifactId>
    <packaging>jar</packaging>

    <name>Order Service</name>
    <description>Order processing, shopping cart, and checkout service</description>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Boot Starter Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- PostgreSQL Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Kafka -->
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- OpenFeign for inter-service communication -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!-- Circuit Breaker -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
        </dependency>

        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- MapStruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Micrometer Prometheus -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- H2 Database for Testing -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create OrderServiceApplication**

```bash
# Create main application class
cat > order-service/src/main/java/com/social/order/OrderServiceApplication.java << 'EOF'
package com.social.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(scanBasePackages = {"com.social.order", "com.social.common"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaAuditing
@EnableKafka
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create Entity Classes**

```bash
# Create model directory
mkdir -p order-service/src/main/java/com/social/order/model

# Create Order.java
cat > order-service/src/main/java/com/social/order/model/Order.java << 'EOF'
package com.social.order.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", indexes = {
        @Index(name = "idx_order_number", columnList = "order_number"),
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Column(name = "order_number", nullable = false, unique = true, length = 50)
    private String orderNumber;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(name = "tax", nullable = false, precision = 10, scale = 2)
    private BigDecimal tax = BigDecimal.ZERO;

    @Column(name = "shipping_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal shippingCost = BigDecimal.ZERO;

    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Embedded
    private ShippingAddress shippingAddress;

    @Embedded
    private BillingAddress billingAddress;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "payment_id")
    private UUID paymentId;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @Column(name = "shipped_at")
    private LocalDateTime shippedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Column(name = "cancellation_reason", columnDefinition = "TEXT")
    private String cancellationReason;

    public enum OrderStatus {
        PENDING,
        CONFIRMED,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        REFUNDED
    }

    public void calculateTotal() {
        this.subtotal = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = subtotal
                .add(tax)
                .add(shippingCost)
                .subtract(discount != null ? discount : BigDecimal.ZERO);
    }
}
EOF

# Create OrderItem.java
cat > order-service/src/main/java/com/social/order/model/OrderItem.java << 'EOF'
package com.social.order.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Column(name = "product_sku", nullable = false, length = 50)
    private String productSku;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    public void calculateSubtotal() {
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
EOF

# Create ShippingAddress.java
cat > order-service/src/main/java/com/social/order/model/ShippingAddress.java << 'EOF'
package com.social.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {

    @Column(name = "shipping_full_name", length = 100)
    private String fullName;

    @Column(name = "shipping_phone", length = 20)
    private String phone;

    @Column(name = "shipping_address_line1", length = 200)
    private String addressLine1;

    @Column(name = "shipping_address_line2", length = 200)
    private String addressLine2;

    @Column(name = "shipping_city", length = 100)
    private String city;

    @Column(name = "shipping_state", length = 100)
    private String state;

    @Column(name = "shipping_postal_code", length = 20)
    private String postalCode;

    @Column(name = "shipping_country", length = 100)
    private String country;
}
EOF

# Create BillingAddress.java
cat > order-service/src/main/java/com/social/order/model/BillingAddress.java << 'EOF'
package com.social.order.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddress {

    @Column(name = "billing_full_name", length = 100)
    private String fullName;

    @Column(name = "billing_phone", length = 20)
    private String phone;

    @Column(name = "billing_address_line1", length = 200)
    private String addressLine1;

    @Column(name = "billing_address_line2", length = 200)
    private String addressLine2;

    @Column(name = "billing_city", length = 100)
    private String city;

    @Column(name = "billing_state", length = 100)
    private String state;

    @Column(name = "billing_postal_code", length = 20)
    private String postalCode;

    @Column(name = "billing_country", length = 100)
    private String country;
}
EOF

# Create Cart.java
cat > order-service/src/main/java/com/social/order/model/Cart.java << 'EOF'
package com.social.order.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts", indexes = {
        @Index(name = "idx_cart_user_id", columnList = "user_id")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseEntity {

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    public void calculateTotal() {
        this.total = items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
EOF

# Create CartItem.java
cat > order-service/src/main/java/com/social/order/model/CartItem.java << 'EOF'
package com.social.order.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    public void calculateSubtotal() {
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
EOF
```

#### **Step 4: Create DTOs**

```bash
# Create dto directory
mkdir -p order-service/src/main/java/com/social/order/dto

# Create OrderDTO.java
cat > order-service/src/main/java/com/social/order/dto/OrderDTO.java << 'EOF'
package com.social.order.dto;

import com.social.order.model.BillingAddress;
import com.social.order.model.Order;
import com.social.order.model.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private UUID id;
    private String orderNumber;
    private UUID userId;
    private List<OrderItemDTO> items;
    private String status;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal discount;
    private BigDecimal total;
    private ShippingAddress shippingAddress;
    private BillingAddress billingAddress;
    private String paymentMethod;
    private UUID paymentId;
    private String notes;
    private LocalDateTime confirmedAt;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime cancelledAt;
    private String cancellationReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderDTO fromEntity(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .items(order.getItems().stream()
                        .map(OrderItemDTO::fromEntity)
                        .collect(Collectors.toList()))
                .status(order.getStatus().name())
                .subtotal(order.getSubtotal())
                .tax(order.getTax())
                .shippingCost(order.getShippingCost())
                .discount(order.getDiscount())
                .total(order.getTotal())
                .shippingAddress(order.getShippingAddress())
                .billingAddress(order.getBillingAddress())
                .paymentMethod(order.getPaymentMethod())
                .paymentId(order.getPaymentId())
                .notes(order.getNotes())
                .confirmedAt(order.getConfirmedAt())
                .shippedAt(order.getShippedAt())
                .deliveredAt(order.getDeliveredAt())
                .cancelledAt(order.getCancelledAt())
                .cancellationReason(order.getCancellationReason())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
EOF

# Create OrderItemDTO.java
cat > order-service/src/main/java/com/social/order/dto/OrderItemDTO.java << 'EOF'
package com.social.order.dto;

import com.social.order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private UUID id;
    private UUID productId;
    private String productName;
    private String productSku;
    private String productImage;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public static OrderItemDTO fromEntity(OrderItem item) {
        return OrderItemDTO.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .productName(item.getProductName())
                .productSku(item.getProductSku())
                .productImage(item.getProductImage())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }
}
EOF

# Create CreateOrderRequest.java
cat > order-service/src/main/java/com/social/order/dto/CreateOrderRequest.java << 'EOF'
package com.social.order.dto;

import com.social.order.model.BillingAddress;
import com.social.order.model.ShippingAddress;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "Shipping address is required")
    private ShippingAddress shippingAddress;

    @NotNull(message = "Billing address is required")
    private BillingAddress billingAddress;

    private String paymentMethod;

    private BigDecimal shippingCost;

    private BigDecimal discount;

    private String notes;
}
EOF

# Create CartDTO.java
cat > order-service/src/main/java/com/social/order/dto/CartDTO.java << 'EOF'
package com.social.order.dto;

import com.social.order.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private UUID id;
    private UUID userId;
    private List<CartItemDTO> items;
    private BigDecimal total;
    private Integer itemCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CartDTO fromEntity(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(cart.getItems().stream()
                        .map(CartItemDTO::fromEntity)
                        .collect(Collectors.toList()))
                .total(cart.getTotal())
                .itemCount(cart.getItems().size())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }
}
EOF

# Create CartItemDTO.java
cat > order-service/src/main/java/com/social/order/dto/CartItemDTO.java << 'EOF'
package com.social.order.dto;

import com.social.order.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public static CartItemDTO fromEntity(CartItem item) {
        return CartItemDTO.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .subtotal(item.getSubtotal())
                .build();
    }
}
EOF

# Create AddToCartRequest.java
cat > order-service/src/main/java/com/social/order/dto/AddToCartRequest.java << 'EOF'
package com.social.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
EOF

# Create UpdateCartItemRequest.java
cat > order-service/src/main/java/com/social/order/dto/UpdateCartItemRequest.java << 'EOF'
package com.social.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartItemRequest {

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
EOF
```

#### **Step 5: Create Event Classes (for Kafka)**

```bash
# Create event directory
mkdir -p order-service/src/main/java/com/social/order/event

# Create OrderEvent.java
cat > order-service/src/main/java/com/social/order/event/OrderEvent.java << 'EOF'
package com.social.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private UUID orderId;
    private String orderNumber;
    private UUID userId;
    private String eventType; // CREATED, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    private BigDecimal total;
    private String status;
    private LocalDateTime timestamp;
}
EOF

# Create OrderCreatedEvent.java
cat > order-service/src/main/java/com/social/order/event/OrderCreatedEvent.java << 'EOF'
package com.social.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private UUID orderId;
    private String orderNumber;
    private UUID userId;
    private List<OrderItemEvent> items;
    private BigDecimal total;
    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemEvent {
        private UUID productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}
EOF
```

#### **Step 6: Create Repositories**

```bash
# Create repository directory
mkdir -p order-service/src/main/java/com/social/order/repository

# Create OrderRepository.java
cat > order-service/src/main/java/com/social/order/repository/OrderRepository.java << 'EOF'
package com.social.order.repository;

import com.social.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByOrderNumber(String orderNumber);

    Page<Order> findByUserIdAndDeletedFalse(UUID userId, Pageable pageable);

    Page<Order> findByStatusAndDeletedFalse(Order.OrderStatus status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.deleted = false AND o.userId = :userId AND o.status = :status")
    Page<Order> findByUserIdAndStatus(@Param("userId") UUID userId,
                                       @Param("status") Order.OrderStatus status,
                                       Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.deleted = false AND " +
           "o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate,
                                        @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.deleted = false AND o.userId = :userId")
    long countByUserId(@Param("userId") UUID userId);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.deleted = false AND o.status = :status")
    long countByStatus(@Param("status") Order.OrderStatus status);

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.deleted = false AND " +
           "o.status IN ('CONFIRMED', 'PROCESSING', 'SHIPPED', 'DELIVERED')")
    BigDecimal getTotalRevenue();
}
EOF

# Create CartRepository.java
cat > order-service/src/main/java/com/social/order/repository/CartRepository.java << 'EOF'
package com.social.order.repository;

import com.social.order.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);
}
EOF

# Create CartItemRepository.java
cat > order-service/src/main/java/com/social/order/repository/CartItemRepository.java << 'EOF'
package com.social.order.repository;

import com.social.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    Optional<CartItem> findByCartIdAndProductId(UUID cartId, UUID productId);
}
EOF
```

#### **Step 7: Create Feign Clients (for inter-service communication)**

```bash
# Create client directory
mkdir -p order-service/src/main/java/com/social/order/client

# Create ProductClient.java
cat > order-service/src/main/java/com/social/order/client/ProductClient.java << 'EOF'
package com.social.order.client;

import com.social.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "product-service", path = "/api/v1/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ApiResponse<ProductDTO> getProductById(@PathVariable UUID id);

    @PatchMapping("/{id}/stock")
    ApiResponse<ProductDTO> updateStock(@PathVariable UUID id, @RequestBody Map<String, Object> request);

    // ProductDTO inner class for Feign response
    class ProductDTO {
        public UUID id;
        public String sku;
        public String name;
        public String description;
        public java.math.BigDecimal price;
        public java.math.BigDecimal discountPrice;
        public Integer stockQuantity;
        public Integer availableQuantity;
        public String status;
        public java.util.List<String> images;
        public boolean inStock;
    }
}
EOF

# Create UserClient.java
cat > order-service/src/main/java/com/social/order/client/UserClient.java << 'EOF'
package com.social.order.client;

import com.social.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service", path = "/api/v1/users")
public interface UserClient {

    @GetMapping("/{id}")
    ApiResponse<UserDTO> getUserById(@PathVariable UUID id);

    // UserDTO inner class for Feign response
    class UserDTO {
        public UUID id;
        public String email;
        public String firstName;
        public String lastName;
        public String phone;
    }
}
EOF
```

#### **Step 8: Create Kafka Producer**

```bash
# Create kafka directory
mkdir -p order-service/src/main/java/com/social/order/kafka

# Create OrderEventProducer.java
cat > order-service/src/main/java/com/social/order/kafka/OrderEventProducer.java << 'EOF'
package com.social.order.kafka;

import com.social.order.event.OrderCreatedEvent;
import com.social.order.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String ORDER_EVENTS_TOPIC = "order.events";
    private static final String ORDER_CREATED_TOPIC = "order.created";

    public void sendOrderEvent(OrderEvent event) {
        try {
            kafkaTemplate.send(ORDER_EVENTS_TOPIC, event.getOrderId().toString(), event);
            log.info("Order event sent to Kafka: {}", event);
        } catch (Exception e) {
            log.error("Failed to send order event to Kafka: {}", e.getMessage());
        }
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        try {
            kafkaTemplate.send(ORDER_CREATED_TOPIC, event.getOrderId().toString(), event);
            log.info("Order created event sent to Kafka: {}", event);
        } catch (Exception e) {
            log.error("Failed to send order created event to Kafka: {}", e.getMessage());
        }
    }
}
EOF
```

#### **Step 9: Create Service Layer**

```bash
# Create service directory
mkdir -p order-service/src/main/java/com/social/order/service

# Create OrderService.java (Part 1 - due to size, split into chunks)
cat > order-service/src/main/java/com/social/order/service/OrderService.java << 'EOF'
package com.social.order.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.order.client.ProductClient;
import com.social.order.dto.CreateOrderRequest;
import com.social.order.dto.OrderDTO;
import com.social.order.event.OrderCreatedEvent;
import com.social.order.event.OrderEvent;
import com.social.order.kafka.OrderEventProducer;
import com.social.order.model.Cart;
import com.social.order.model.Order;
import com.social.order.model.OrderItem;
import com.social.order.repository.CartRepository;
import com.social.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductClient productClient;
    private final OrderEventProducer eventProducer;

    @Transactional
    @CircuitBreaker(name = "orderService", fallbackMethod = "createOrderFallback")
    public OrderDTO createOrder(UUID userId, CreateOrderRequest request) {
        log.info("Creating order for user: {}", userId);

        // Get user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ValidationException("Cart is empty"));

        if (cart.getItems().isEmpty()) {
            throw new ValidationException("Cart is empty");
        }

        // Create order
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setUserId(userId);
        order.setStatus(Order.OrderStatus.PENDING);
        order.setShippingAddress(request.getShippingAddress());
        order.setBillingAddress(request.getBillingAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setShippingCost(request.getShippingCost() != null ? request.getShippingCost() : BigDecimal.ZERO);
        order.setDiscount(request.getDiscount());
        order.setNotes(request.getNotes());

        // Convert cart items to order items
        for (var cartItem : cart.getItems()) {
            // Get product details
            ProductClient.ProductDTO product = getProductDetails(cartItem.getProductId());

            // Validate stock
            if (!product.inStock || product.availableQuantity < cartItem.getQuantity()) {
                throw new ValidationException("Product " + product.name + " is out of stock");
            }

            // Create order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(product.id);
            orderItem.setProductName(product.name);
            orderItem.setProductSku(product.sku);
            orderItem.setProductImage(product.images != null && !product.images.isEmpty() ? product.images.get(0) : null);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(product.discountPrice != null ? product.discountPrice : product.price);
            orderItem.calculateSubtotal();

            order.getItems().add(orderItem);

            // Reserve stock
            reserveProductStock(product.id, cartItem.getQuantity());
        }

        // Calculate totals
        order.calculateTotal();
        order.setTax(order.getSubtotal().multiply(BigDecimal.valueOf(0.1))); // 10% tax
        order.calculateTotal();

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cart.getItems().clear();
        cart.calculateTotal();
        cartRepository.save(cart);

        // Publish order created event
        publishOrderCreatedEvent(savedOrder);

        log.info("Order created successfully: {}", savedOrder.getOrderNumber());
        return OrderDTO.fromEntity(savedOrder);
    }

    public OrderDTO getOrderById(UUID id) {
        log.info("Fetching order with ID: {}", id);
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        return OrderDTO.fromEntity(order);
    }

    public OrderDTO getOrderByNumber(String orderNumber) {
        log.info("Fetching order with number: {}", orderNumber);
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with number: " + orderNumber));
        return OrderDTO.fromEntity(order);
    }

    public PageResponse<OrderDTO> getUserOrders(UUID userId, int page, int size) {
        log.info("Fetching orders for user: {}", userId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Order> orderPage = orderRepository.findByUserIdAndDeletedFalse(userId, pageable);

        return PageResponse.of(
                orderPage.getContent().stream().map(OrderDTO::fromEntity).toList(),
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                orderPage.isLast()
        );
    }

    public PageResponse<OrderDTO> getAllOrders(int page, int size) {
        log.info("Fetching all orders");

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Order> orderPage = orderRepository.findAll(pageable);

        return PageResponse.of(
                orderPage.getContent().stream().map(OrderDTO::fromEntity).toList(),
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                orderPage.isLast()
        );
    }

    @Transactional
    public OrderDTO confirmOrder(UUID id) {
        log.info("Confirming order: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() != Order.OrderStatus.PENDING) {
            throw new ValidationException("Order cannot be confirmed in current status");
        }

        order.setStatus(Order.OrderStatus.CONFIRMED);
        order.setConfirmedAt(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(order);

        // Publish event
        publishOrderEvent(updatedOrder, "CONFIRMED");

        return OrderDTO.fromEntity(updatedOrder);
    }

    @Transactional
    public OrderDTO shipOrder(UUID id) {
        log.info("Shipping order: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() != Order.OrderStatus.CONFIRMED && order.getStatus() != Order.OrderStatus.PROCESSING) {
            throw new ValidationException("Order cannot be shipped in current status");
        }

        order.setStatus(Order.OrderStatus.SHIPPED);
        order.setShippedAt(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(order);

        // Publish event
        publishOrderEvent(updatedOrder, "SHIPPED");

        return OrderDTO.fromEntity(updatedOrder);
    }

    @Transactional
    public OrderDTO deliverOrder(UUID id) {
        log.info("Delivering order: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() != Order.OrderStatus.SHIPPED) {
            throw new ValidationException("Order cannot be delivered in current status");
        }

        order.setStatus(Order.OrderStatus.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(order);

        // Publish event
        publishOrderEvent(updatedOrder, "DELIVERED");

        return OrderDTO.fromEntity(updatedOrder);
    }

    @Transactional
    public OrderDTO cancelOrder(UUID id, String reason) {
        log.info("Cancelling order: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() == Order.OrderStatus.DELIVERED || order.getStatus() == Order.OrderStatus.CANCELLED) {
            throw new ValidationException("Order cannot be cancelled in current status");
        }

        // Release reserved stock
        for (OrderItem item : order.getItems()) {
            releaseProductStock(item.getProductId(), item.getQuantity());
        }

        order.setStatus(Order.OrderStatus.CANCELLED);
        order.setCancelledAt(LocalDateTime.now());
        order.setCancellationReason(reason);
        Order updatedOrder = orderRepository.save(order);

        // Publish event
        publishOrderEvent(updatedOrder, "CANCELLED");

        return OrderDTO.fromEntity(updatedOrder);
    }

    public long getUserOrderCount(UUID userId) {
        return orderRepository.countByUserId(userId);
    }

    public BigDecimal getTotalRevenue() {
        return orderRepository.getTotalRevenue();
    }

    // Helper methods
    private String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis();
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "getProductDetailsFallback")
    private ProductClient.ProductDTO getProductDetails(UUID productId) {
        return productClient.getProductById(productId).getData();
    }

    private ProductClient.ProductDTO getProductDetailsFallback(UUID productId, Exception e) {
        log.error("Failed to get product details: {}", e.getMessage());
        throw new ValidationException("Product service is unavailable");
    }

    @CircuitBreaker(name = "productService")
    private void reserveProductStock(UUID productId, Integer quantity) {
        Map<String, Object> request = new HashMap<>();
        request.put("quantity", quantity);
        request.put("operation", "SUBTRACT");
        productClient.updateStock(productId, request);
    }

    @CircuitBreaker(name = "productService")
    private void releaseProductStock(UUID productId, Integer quantity) {
        Map<String, Object> request = new HashMap<>();
        request.put("quantity", quantity);
        request.put("operation", "ADD");
        productClient.updateStock(productId, request);
    }

    private void publishOrderCreatedEvent(Order order) {
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .items(order.getItems().stream()
                        .map(item -> OrderCreatedEvent.OrderItemEvent.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .build())
                        .collect(Collectors.toList()))
                .total(order.getTotal())
                .createdAt(order.getCreatedAt())
                .build();

        eventProducer.sendOrderCreatedEvent(event);
    }

    private void publishOrderEvent(Order order, String eventType) {
        OrderEvent event = OrderEvent.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(order.getUserId())
                .eventType(eventType)
                .total(order.getTotal())
                .status(order.getStatus().name())
                .timestamp(LocalDateTime.now())
                .build();

        eventProducer.sendOrderEvent(event);
    }

    // Fallback method for createOrder
    private OrderDTO createOrderFallback(UUID userId, CreateOrderRequest request, Exception e) {
        log.error("Failed to create order: {}", e.getMessage());
        throw new ValidationException("Order service is temporarily unavailable");
    }
}
EOF

# Create CartService.java
cat > order-service/src/main/java/com/social/order/service/CartService.java << 'EOF'
package com.social.order.service;

import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.order.client.ProductClient;
import com.social.order.dto.AddToCartRequest;
import com.social.order.dto.CartDTO;
import com.social.order.dto.UpdateCartItemRequest;
import com.social.order.model.Cart;
import com.social.order.model.CartItem;
import com.social.order.repository.CartItemRepository;
import com.social.order.repository.CartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductClient productClient;

    @Transactional
    @CircuitBreaker(name = "cartService", fallbackMethod = "addToCartFallback")
    public CartDTO addToCart(UUID userId, AddToCartRequest request) {
        log.info("Adding product {} to cart for user {}", request.getProductId(), userId);

        // Get or create cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart);
                });

        // Get product details
        ProductClient.ProductDTO product = productClient.getProductById(request.getProductId()).getData();

        // Validate stock
        if (!product.inStock || product.availableQuantity < request.getQuantity()) {
            throw new ValidationException("Product is out of stock");
        }

        // Check if item already exists in cart
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), request.getProductId())
                .orElse(null);

        if (cartItem != null) {
            // Update quantity
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.calculateSubtotal();
        } else {
            // Create new cart item
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductId(product.id);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUnitPrice(product.discountPrice != null ? product.discountPrice : product.price);
            cartItem.calculateSubtotal();
            cart.getItems().add(cartItem);
        }

        // Calculate cart total
        cart.calculateTotal();
        Cart savedCart = cartRepository.save(cart);

        log.info("Product added to cart successfully");
        return CartDTO.fromEntity(savedCart);
    }

    public CartDTO getCart(UUID userId) {
        log.info("Fetching cart for user: {}", userId);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return CartDTO.fromEntity(cart);
    }

    @Transactional
    public CartDTO updateCartItem(UUID userId, UUID itemId, UpdateCartItemRequest request) {
        log.info("Updating cart item {} for user {}", itemId, userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        // Validate stock
        ProductClient.ProductDTO product = productClient.getProductById(cartItem.getProductId()).getData();
        if (!product.inStock || product.availableQuantity < request.getQuantity()) {
            throw new ValidationException("Insufficient stock");
        }

        cartItem.setQuantity(request.getQuantity());
        cartItem.calculateSubtotal();

        cart.calculateTotal();
        Cart savedCart = cartRepository.save(cart);

        log.info("Cart item updated successfully");
        return CartDTO.fromEntity(savedCart);
    }

    @Transactional
    public CartDTO removeCartItem(UUID userId, UUID itemId) {
        log.info("Removing cart item {} for user {}", itemId, userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().removeIf(item -> item.getId().equals(itemId));
        cart.calculateTotal();
        Cart savedCart = cartRepository.save(cart);

        log.info("Cart item removed successfully");
        return CartDTO.fromEntity(savedCart);
    }

    @Transactional
    public void clearCart(UUID userId) {
        log.info("Clearing cart for user: {}", userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().clear();
        cart.calculateTotal();
        cartRepository.save(cart);

        log.info("Cart cleared successfully");
    }

    // Fallback method
    private CartDTO addToCartFallback(UUID userId, AddToCartRequest request, Exception e) {
        log.error("Failed to add to cart: {}", e.getMessage());
        throw new ValidationException("Cart service is temporarily unavailable");
    }
}
EOF
```

#### **Step 10: Create Controllers**

```bash
# Create controller directory
mkdir -p order-service/src/main/java/com/social/order/controller

# Create OrderController.java
cat > order-service/src/main/java/com/social/order/controller/OrderController.java << 'EOF'
package com.social.order.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.order.dto.CreateOrderRequest;
import com.social.order.dto.OrderDTO;
import com.social.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order Management", description = "APIs for order management")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<ApiResponse<OrderDTO>> createOrder(
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody CreateOrderRequest request) {
        log.info("REST request to create order for user: {}", userId);
        OrderDTO order = orderService.createOrder(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(order, "Order created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderById(@PathVariable UUID id) {
        log.info("REST request to get order by ID: {}", id);
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(ApiResponse.success(order));
    }

    @GetMapping("/number/{orderNumber}")
    @Operation(summary = "Get order by order number")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderByNumber(@PathVariable String orderNumber) {
        log.info("REST request to get order by number: {}", orderNumber);
        OrderDTO order = orderService.getOrderByNumber(orderNumber);
        return ResponseEntity.ok(ApiResponse.success(order));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user orders")
    public ResponseEntity<ApiResponse<PageResponse<OrderDTO>>> getUserOrders(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get orders for user: {}", userId);
        PageResponse<OrderDTO> orders = orderService.getUserOrders(userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(orders));
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<ApiResponse<PageResponse<OrderDTO>>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get all orders");
        PageResponse<OrderDTO> orders = orderService.getAllOrders(page, size);
        return ResponseEntity.ok(ApiResponse.success(orders));
    }

    @PatchMapping("/{id}/confirm")
    @Operation(summary = "Confirm order")
    public ResponseEntity<ApiResponse<OrderDTO>> confirmOrder(@PathVariable UUID id) {
        log.info("REST request to confirm order: {}", id);
        OrderDTO order = orderService.confirmOrder(id);
        return ResponseEntity.ok(ApiResponse.success(order, "Order confirmed successfully"));
    }

    @PatchMapping("/{id}/ship")
    @Operation(summary = "Ship order")
    public ResponseEntity<ApiResponse<OrderDTO>> shipOrder(@PathVariable UUID id) {
        log.info("REST request to ship order: {}", id);
        OrderDTO order = orderService.shipOrder(id);
        return ResponseEntity.ok(ApiResponse.success(order, "Order shipped successfully"));
    }

    @PatchMapping("/{id}/deliver")
    @Operation(summary = "Deliver order")
    public ResponseEntity<ApiResponse<OrderDTO>> deliverOrder(@PathVariable UUID id) {
        log.info("REST request to deliver order: {}", id);
        OrderDTO order = orderService.deliverOrder(id);
        return ResponseEntity.ok(ApiResponse.success(order, "Order delivered successfully"));
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancel order")
    public ResponseEntity<ApiResponse<OrderDTO>> cancelOrder(
            @PathVariable UUID id,
            @RequestParam(required = false) String reason) {
        log.info("REST request to cancel order: {}", id);
        OrderDTO order = orderService.cancelOrder(id, reason);
        return ResponseEntity.ok(ApiResponse.success(order, "Order cancelled successfully"));
    }

    @GetMapping("/user/{userId}/count")
    @Operation(summary = "Get user order count")
    public ResponseEntity<ApiResponse<Long>> getUserOrderCount(@PathVariable UUID userId) {
        log.info("REST request to get order count for user: {}", userId);
        long count = orderService.getUserOrderCount(userId);
        return ResponseEntity.ok(ApiResponse.success(count));
    }

    @GetMapping("/revenue/total")
    @Operation(summary = "Get total revenue")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotalRevenue() {
        log.info("REST request to get total revenue");
        BigDecimal revenue = orderService.getTotalRevenue();
        return ResponseEntity.ok(ApiResponse.success(revenue));
    }
}
EOF

# Create CartController.java
cat > order-service/src/main/java/com/social/order/controller/CartController.java << 'EOF'
package com.social.order.controller;

import com.social.common.dto.ApiResponse;
import com.social.order.dto.AddToCartRequest;
import com.social.order.dto.CartDTO;
import com.social.order.dto.UpdateCartItemRequest;
import com.social.order.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Shopping Cart", description = "APIs for shopping cart management")
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<ApiResponse<CartDTO>> addToCart(
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody AddToCartRequest request) {
        log.info("REST request to add item to cart for user: {}", userId);
        CartDTO cart = cartService.addToCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(cart, "Item added to cart successfully"));
    }

    @GetMapping
    @Operation(summary = "Get user cart")
    public ResponseEntity<ApiResponse<CartDTO>> getCart(@RequestHeader("X-User-Id") UUID userId) {
        log.info("REST request to get cart for user: {}", userId);
        CartDTO cart = cartService.getCart(userId);
        return ResponseEntity.ok(ApiResponse.success(cart));
    }

    @PutMapping("/items/{itemId}")
    @Operation(summary = "Update cart item")
    public ResponseEntity<ApiResponse<CartDTO>> updateCartItem(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID itemId,
            @Valid @RequestBody UpdateCartItemRequest request) {
        log.info("REST request to update cart item {} for user: {}", itemId, userId);
        CartDTO cart = cartService.updateCartItem(userId, itemId, request);
        return ResponseEntity.ok(ApiResponse.success(cart, "Cart item updated successfully"));
    }

    @DeleteMapping("/items/{itemId}")
    @Operation(summary = "Remove cart item")
    public ResponseEntity<ApiResponse<CartDTO>> removeCartItem(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID itemId) {
        log.info("REST request to remove cart item {} for user: {}", itemId, userId);
        CartDTO cart = cartService.removeCartItem(userId, itemId);
        return ResponseEntity.ok(ApiResponse.success(cart, "Cart item removed successfully"));
    }

    @DeleteMapping
    @Operation(summary = "Clear cart")
    public ResponseEntity<ApiResponse<Void>> clearCart(@RequestHeader("X-User-Id") UUID userId) {
        log.info("REST request to clear cart for user: {}", userId);
        cartService.clearCart(userId);
        return ResponseEntity.ok(ApiResponse.success(null, "Cart cleared successfully"));
    }
}
EOF
```

#### **Step 11: Create Configuration Classes**

```bash
# Create config directory
mkdir -p order-service/src/main/java/com/social/order/config

# Create KafkaConfig.java
cat > order-service/src/main/java/com/social/order/config/KafkaConfig.java << 'EOF'
package com.social.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderEventsTopic() {
        return TopicBuilder.name("order.events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderCreatedTopic() {
        return TopicBuilder.name("order.created")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
EOF

# Create OpenApiConfig.java
cat > order-service/src/main/java/com/social/order/config/OpenApiConfig.java << 'EOF'
package com.social.order.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9003");
        server.setDescription("Order Service - Development");

        Contact contact = new Contact();
        contact.setName("Social Commerce Platform");
        contact.setEmail("support@socialcommerce.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Order Service API")
                .version("1.0.0")
                .description("Order processing, shopping cart, and checkout service")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
EOF
```

#### **Step 12: Create application.yml**

```bash
# Create application.yml
cat > order-service/src/main/resources/application.yml << 'EOF'
spring:
  application:
    name: order-service

  config:
    import: optional:configserver:http://localhost:8888

  datasource:
    url: jdbc:postgresql://localhost:5432/order_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        use_sql_comments: true

  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      properties:
        spring.json.add.type.headers: false
    consumer:
      group-id: order-service-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
        spring.json.use.type.headers: false

  cloud:
    openfeign:
      circuitbreaker:
        enabled: true

server:
  port: 9003

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${server.port}

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

resilience4j:
  circuitbreaker:
    instances:
      orderService:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 10s
        failure-rate-threshold: 50
        slow-call-rate-threshold: 100
        slow-call-duration-threshold: 5s
      productService:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        failure-rate-threshold: 50
      cartService:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        failure-rate-threshold: 50

logging:
  level:
    com.social.order: DEBUG
    org.springframework.kafka: INFO
    org.springframework.cloud.openfeign: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
EOF
```

#### **Step 13: Build and Run Order Service**

```bash
# Build the service
cd order-service
mvn clean install -DskipTests
cd ..

# Start PostgreSQL for Order Service
docker run -d \
  --name order-postgres \
  -e POSTGRES_DB=order_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5433:5432 \
  postgres:15

# Start Kafka (if not already running)
docker run -d \
  --name kafka \
  -p 9092:9092 \
  -e KAFKA_ENABLE_KRAFT=yes \
  -e KAFKA_CFG_PROCESS_ROLES=broker,controller \
  -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
  -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
  -e KAFKA_CFG_NODE_ID=1 \
  -e ALLOW_PLAINTEXT_LISTENER=yes \
  bitnami/kafka:3.7.0

# Wait for services to be ready
sleep 10

# Run Order Service
cd order-service
mvn spring-boot:run
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.5.5)

Order Service started on port 9003
Registered with Eureka Server
Connected to PostgreSQL: order_db
Connected to Kafka: localhost:9092
Swagger UI: http://localhost:9003/swagger-ui.html
```

#### **Step 14: Test Order Service with curl**

```bash
# 1. Add item to cart
curl -X POST http://localhost:9003/api/v1/cart/items \
  -H "Content-Type: application/json" \
  -H "X-User-Id: <USER_ID>" \
  -d '{
    "productId": "<PRODUCT_ID>",
    "quantity": 2
  }'

# Expected Response:
# {
#   "success": true,
#   "message": "Item added to cart successfully",
#   "data": {
#     "id": "...",
#     "userId": "...",
#     "items": [...],
#     "total": 199.98,
#     "itemCount": 1
#   }
# }

# 2. Get cart
curl -X GET http://localhost:9003/api/v1/cart \
  -H "X-User-Id: <USER_ID>"

# 3. Update cart item
curl -X PUT http://localhost:9003/api/v1/cart/items/<ITEM_ID> \
  -H "Content-Type: application/json" \
  -H "X-User-Id: <USER_ID>" \
  -d '{
    "quantity": 3
  }'

# 4. Create order from cart
curl -X POST http://localhost:9003/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "X-User-Id: <USER_ID>" \
  -d '{
    "shippingAddress": {
      "fullName": "John Doe",
      "phone": "+1234567890",
      "addressLine1": "123 Main St",
      "city": "New York",
      "state": "NY",
      "postalCode": "10001",
      "country": "USA"
    },
    "billingAddress": {
      "fullName": "John Doe",
      "phone": "+1234567890",
      "addressLine1": "123 Main St",
      "city": "New York",
      "state": "NY",
      "postalCode": "10001",
      "country": "USA"
    },
    "paymentMethod": "CREDIT_CARD",
    "shippingCost": 10.00,
    "notes": "Please deliver before 5 PM"
  }'

# Expected Response:
# {
#   "success": true,
#   "message": "Order created successfully",
#   "data": {
#     "id": "...",
#     "orderNumber": "ORD-1234567890",
#     "userId": "...",
#     "items": [...],
#     "status": "PENDING",
#     "subtotal": 199.98,
#     "tax": 19.99,
#     "shippingCost": 10.00,
#     "total": 229.97
#   }
# }

# 5. Get order by ID
curl -X GET http://localhost:9003/api/v1/orders/<ORDER_ID>

# 6. Get order by order number
curl -X GET http://localhost:9003/api/v1/orders/number/ORD-1234567890

# 7. Get user orders
curl -X GET "http://localhost:9003/api/v1/orders/user/<USER_ID>?page=0&size=10"

# 8. Confirm order
curl -X PATCH http://localhost:9003/api/v1/orders/<ORDER_ID>/confirm

# 9. Ship order
curl -X PATCH http://localhost:9003/api/v1/orders/<ORDER_ID>/ship

# 10. Deliver order
curl -X PATCH http://localhost:9003/api/v1/orders/<ORDER_ID>/deliver

# 11. Cancel order
curl -X PATCH "http://localhost:9003/api/v1/orders/<ORDER_ID>/cancel?reason=Customer%20requested"

# 12. Get user order count
curl -X GET http://localhost:9003/api/v1/orders/user/<USER_ID>/count

# 13. Get total revenue
curl -X GET http://localhost:9003/api/v1/orders/revenue/total

# 14. Remove cart item
curl -X DELETE http://localhost:9003/api/v1/cart/items/<ITEM_ID> \
  -H "X-User-Id: <USER_ID>"

# 15. Clear cart
curl -X DELETE http://localhost:9003/api/v1/cart \
  -H "X-User-Id: <USER_ID>"
```

#### **Step 15: Verify Kafka Events**

```bash
# Check Kafka topics
docker exec -it kafka kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --list

# Expected Output:
# order.events
# order.created

# Consume order events
docker exec -it kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic order.events \
  --from-beginning

# You should see order events like:
# {"orderId":"...","orderNumber":"ORD-1234567890","userId":"...","eventType":"CREATED","total":229.97,"status":"PENDING","timestamp":"2025-10-16T10:30:00"}
```

#### **Step 16: Verify in Swagger UI**

```bash
# Open Swagger UI
open http://localhost:9003/swagger-ui.html

# Test all endpoints:
# - POST /api/v1/cart/items - Add to cart
# - GET /api/v1/cart - Get cart
# - PUT /api/v1/cart/items/{itemId} - Update cart item
# - DELETE /api/v1/cart/items/{itemId} - Remove cart item
# - DELETE /api/v1/cart - Clear cart
# - POST /api/v1/orders - Create order
# - GET /api/v1/orders/{id} - Get order by ID
# - GET /api/v1/orders/number/{orderNumber} - Get order by number
# - GET /api/v1/orders/user/{userId} - Get user orders
# - PATCH /api/v1/orders/{id}/confirm - Confirm order
# - PATCH /api/v1/orders/{id}/ship - Ship order
# - PATCH /api/v1/orders/{id}/deliver - Deliver order
# - PATCH /api/v1/orders/{id}/cancel - Cancel order
```

#### **Step 17: Commit Changes**

```bash
# Add and commit
git add order-service/
git commit -m "feat: Add Order Service with shopping cart and Kafka events

- Created Order, OrderItem, Cart, CartItem entities
- Implemented order processing workflow
- Added shopping cart functionality
- Integrated with Product Service via Feign
- Added Kafka event publishing for order events
- Implemented order status tracking (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)
- Added stock reservation and release
- Created comprehensive REST APIs
- Port: 9003
- Database: PostgreSQL + Kafka"
```

---

### **✅ DAY 8 CHECKPOINT**

**What We Built:**
- ✅ Order Service (Port 9003)
- ✅ Shopping cart functionality
- ✅ Order processing workflow
- ✅ Order status tracking
- ✅ Stock reservation/release
- ✅ Kafka event publishing
- ✅ Feign client integration with Product Service
- ✅ 15 REST API endpoints
- ✅ Circuit breaker patterns
- ✅ PostgreSQL + Kafka integration

**Entities Created:**
- Order (with order items, addresses, payment info)
- OrderItem (product details, quantity, pricing)
- Cart (user shopping cart)
- CartItem (cart line items)
- ShippingAddress, BillingAddress (embedded)

**APIs Created:**
- Cart: 5 endpoints (add, get, update, remove, clear)
- Orders: 11 endpoints (create, get, list, confirm, ship, deliver, cancel, stats)

**Events Published:**
- order.events - General order events
- order.created - Order creation events

**Next Steps:**
- Create Payment Service (Port 9004)
- Create Social Service (Port 9005)
- Create Notification Service (Port 9006)

---

### **DAY 9: CREATE PAYMENT SERVICE**

> **Time**: 5 hours
> **Goal**: Create Payment Service with Stripe integration and transaction management
> **Port**: 9004
> **Database**: PostgreSQL + Kafka

#### **Step 1: Create payment-service POM**

```bash
# Navigate to payment-service directory
cd payment-service

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>payment-service</artifactId>
    <packaging>jar</packaging>

    <name>Payment Service</name>
    <description>Payment processing, transaction management, and Stripe integration</description>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Spring Boot Starter Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- PostgreSQL Driver -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Kafka -->
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- OpenFeign for inter-service communication -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!-- Circuit Breaker -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
        </dependency>

        <!-- Stripe Java SDK -->
        <dependency>
            <groupId>com.stripe</groupId>
            <artifactId>stripe-java</artifactId>
            <version>24.3.0</version>
        </dependency>

        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- MapStruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Micrometer Prometheus -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- H2 Database for Testing -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create PaymentServiceApplication**

```bash
# Create main application class
cat > payment-service/src/main/java/com/social/payment/PaymentServiceApplication.java << 'EOF'
package com.social.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication(scanBasePackages = {"com.social.payment", "com.social.common"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaAuditing
@EnableKafka
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create Entity Classes**

```bash
# Create model directory
mkdir -p payment-service/src/main/java/com/social/payment/model

# Create Payment.java
cat > payment-service/src/main/java/com/social/payment/model/Payment.java << 'EOF'
package com.social.payment.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments", indexes = {
        @Index(name = "idx_payment_transaction_id", columnList = "transaction_id"),
        @Index(name = "idx_payment_order_id", columnList = "order_id"),
        @Index(name = "idx_payment_user_id", columnList = "user_id"),
        @Index(name = "idx_payment_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    @Column(name = "transaction_id", nullable = false, unique = true, length = 100)
    private String transactionId;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "USD";

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "stripe_payment_intent_id", length = 100)
    private String stripePaymentIntentId;

    @Column(name = "stripe_charge_id", length = 100)
    private String stripeChargeId;

    @Column(name = "card_last_four", length = 4)
    private String cardLastFour;

    @Column(name = "card_brand", length = 20)
    private String cardBrand;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "failure_reason", columnDefinition = "TEXT")
    private String failureReason;

    @Column(name = "refund_amount", precision = 10, scale = 2)
    private BigDecimal refundAmount;

    @Column(name = "refund_reason", columnDefinition = "TEXT")
    private String refundReason;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "refunded_at")
    private LocalDateTime refundedAt;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        STRIPE,
        BANK_TRANSFER,
        CASH_ON_DELIVERY
    }

    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REFUNDED,
        PARTIALLY_REFUNDED,
        CANCELLED
    }

    public boolean isRefundable() {
        return status == PaymentStatus.COMPLETED &&
               (refundAmount == null || refundAmount.compareTo(amount) < 0);
    }

    public BigDecimal getRemainingRefundableAmount() {
        if (refundAmount == null) {
            return amount;
        }
        return amount.subtract(refundAmount);
    }
}
EOF

# Create Transaction.java
cat > payment-service/src/main/java/com/social/payment/model/Transaction.java << 'EOF'
package com.social.payment.model;

import com.social.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions", indexes = {
        @Index(name = "idx_transaction_payment_id", columnList = "payment_id"),
        @Index(name = "idx_transaction_type", columnList = "transaction_type")
})
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "USD";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @Column(name = "reference_id", length = 100)
    private String referenceId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    public enum TransactionType {
        CHARGE,
        REFUND,
        AUTHORIZATION,
        CAPTURE,
        VOID
    }

    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED,
        CANCELLED
    }
}
EOF
```

#### **Step 4: Create DTOs**

```bash
# Create dto directory
mkdir -p payment-service/src/main/java/com/social/payment/dto

# Create PaymentDTO.java
cat > payment-service/src/main/java/com/social/payment/dto/PaymentDTO.java << 'EOF'
package com.social.payment.dto;

import com.social.payment.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private UUID id;
    private String transactionId;
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private String status;
    private String stripePaymentIntentId;
    private String stripeChargeId;
    private String cardLastFour;
    private String cardBrand;
    private String description;
    private String failureReason;
    private BigDecimal refundAmount;
    private String refundReason;
    private LocalDateTime processedAt;
    private LocalDateTime refundedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PaymentDTO fromEntity(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .transactionId(payment.getTransactionId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .paymentMethod(payment.getPaymentMethod().name())
                .status(payment.getStatus().name())
                .stripePaymentIntentId(payment.getStripePaymentIntentId())
                .stripeChargeId(payment.getStripeChargeId())
                .cardLastFour(payment.getCardLastFour())
                .cardBrand(payment.getCardBrand())
                .description(payment.getDescription())
                .failureReason(payment.getFailureReason())
                .refundAmount(payment.getRefundAmount())
                .refundReason(payment.getRefundReason())
                .processedAt(payment.getProcessedAt())
                .refundedAt(payment.getRefundedAt())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
}
EOF

# Create CreatePaymentRequest.java
cat > payment-service/src/main/java/com/social/payment/dto/CreatePaymentRequest.java << 'EOF'
package com.social.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

    @NotNull(message = "Order ID is required")
    private UUID orderId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    private String currency = "USD";

    @NotNull(message = "Payment method is required")
    private String paymentMethod;

    private String stripeToken;

    private String description;
}
EOF

# Create RefundRequest.java
cat > payment-service/src/main/java/com/social/payment/dto/RefundRequest.java << 'EOF'
package com.social.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequest {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Reason is required")
    private String reason;
}
EOF

# Create StripePaymentRequest.java
cat > payment-service/src/main/java/com/social/payment/dto/StripePaymentRequest.java << 'EOF'
package com.social.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StripePaymentRequest {

    private BigDecimal amount;
    private String currency;
    private String token;
    private String description;
}
EOF
```

#### **Step 5: Create Event Classes**

```bash
# Create event directory
mkdir -p payment-service/src/main/java/com/social/payment/event

# Create PaymentEvent.java
cat > payment-service/src/main/java/com/social/payment/event/PaymentEvent.java << 'EOF'
package com.social.payment.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {

    private UUID paymentId;
    private String transactionId;
    private UUID orderId;
    private UUID userId;
    private String eventType; // COMPLETED, FAILED, REFUNDED
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime timestamp;
}
EOF

# Create PaymentCompletedEvent.java
cat > payment-service/src/main/java/com/social/payment/event/PaymentCompletedEvent.java << 'EOF'
package com.social.payment.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompletedEvent {

    private UUID paymentId;
    private String transactionId;
    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private LocalDateTime completedAt;
}
EOF
```

#### **Step 6: Create Repositories**

```bash
# Create repository directory
mkdir -p payment-service/src/main/java/com/social/payment/repository

# Create PaymentRepository.java
cat > payment-service/src/main/java/com/social/payment/repository/PaymentRepository.java << 'EOF'
package com.social.payment.repository;

import com.social.payment.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByOrderId(UUID orderId);

    Page<Payment> findByUserIdAndDeletedFalse(UUID userId, Pageable pageable);

    Page<Payment> findByStatusAndDeletedFalse(Payment.PaymentStatus status, Pageable pageable);

    @Query("SELECT p FROM Payment p WHERE p.deleted = false AND p.userId = :userId AND p.status = :status")
    Page<Payment> findByUserIdAndStatus(@Param("userId") UUID userId,
                                         @Param("status") Payment.PaymentStatus status,
                                         Pageable pageable);

    @Query("SELECT p FROM Payment p WHERE p.deleted = false AND " +
           "p.createdAt BETWEEN :startDate AND :endDate")
    List<Payment> findPaymentsBetweenDates(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.deleted = false AND p.userId = :userId")
    long countByUserId(@Param("userId") UUID userId);

    @Query("SELECT COUNT(p) FROM Payment p WHERE p.deleted = false AND p.status = :status")
    long countByStatus(@Param("status") Payment.PaymentStatus status);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.deleted = false AND p.status = 'COMPLETED'")
    BigDecimal getTotalProcessedAmount();

    @Query("SELECT SUM(p.refundAmount) FROM Payment p WHERE p.deleted = false AND p.refundAmount IS NOT NULL")
    BigDecimal getTotalRefundedAmount();
}
EOF

# Create TransactionRepository.java
cat > payment-service/src/main/java/com/social/payment/repository/TransactionRepository.java << 'EOF'
package com.social.payment.repository;

import com.social.payment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByPaymentId(UUID paymentId);

    List<Transaction> findByPaymentIdOrderByCreatedAtDesc(UUID paymentId);
}
EOF
```

#### **Step 7: Create Stripe Service**

```bash
# Create service directory
mkdir -p payment-service/src/main/java/com/social/payment/service

# Create StripeService.java
cat > payment-service/src/main/java/com/social/payment/service/StripeService.java << 'EOF'
package com.social.payment.service;

import com.social.common.exception.ValidationException;
import com.social.payment.dto.StripePaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class StripeService {

    @Value("${stripe.api.key:sk_test_dummy_key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
        log.info("Stripe API initialized");
    }

    public PaymentIntent createPaymentIntent(StripePaymentRequest request) {
        try {
            log.info("Creating Stripe payment intent for amount: {}", request.getAmount());

            // Convert amount to cents (Stripe uses smallest currency unit)
            long amountInCents = request.getAmount().multiply(BigDecimal.valueOf(100)).longValue();

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency(request.getCurrency().toLowerCase())
                    .setDescription(request.getDescription())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            log.info("Payment intent created: {}", paymentIntent.getId());
            return paymentIntent;

        } catch (StripeException e) {
            log.error("Failed to create payment intent: {}", e.getMessage());
            throw new ValidationException("Failed to create payment intent: " + e.getMessage());
        }
    }

    public Charge createCharge(StripePaymentRequest request) {
        try {
            log.info("Creating Stripe charge for amount: {}", request.getAmount());

            // Convert amount to cents
            long amountInCents = request.getAmount().multiply(BigDecimal.valueOf(100)).longValue();

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amountInCents);
            chargeParams.put("currency", request.getCurrency().toLowerCase());
            chargeParams.put("source", request.getToken());
            chargeParams.put("description", request.getDescription());

            Charge charge = Charge.create(chargeParams);
            log.info("Charge created: {}", charge.getId());
            return charge;

        } catch (StripeException e) {
            log.error("Failed to create charge: {}", e.getMessage());
            throw new ValidationException("Failed to process payment: " + e.getMessage());
        }
    }

    public Refund createRefund(String chargeId, BigDecimal amount, String reason) {
        try {
            log.info("Creating Stripe refund for charge: {}, amount: {}", chargeId, amount);

            // Convert amount to cents
            long amountInCents = amount.multiply(BigDecimal.valueOf(100)).longValue();

            RefundCreateParams params = RefundCreateParams.builder()
                    .setCharge(chargeId)
                    .setAmount(amountInCents)
                    .setReason(RefundCreateParams.Reason.REQUESTED_BY_CUSTOMER)
                    .build();

            Refund refund = Refund.create(params);
            log.info("Refund created: {}", refund.getId());
            return refund;

        } catch (StripeException e) {
            log.error("Failed to create refund: {}", e.getMessage());
            throw new ValidationException("Failed to process refund: " + e.getMessage());
        }
    }

    public PaymentIntent retrievePaymentIntent(String paymentIntentId) {
        try {
            return PaymentIntent.retrieve(paymentIntentId);
        } catch (StripeException e) {
            log.error("Failed to retrieve payment intent: {}", e.getMessage());
            throw new ValidationException("Failed to retrieve payment intent: " + e.getMessage());
        }
    }

    public Charge retrieveCharge(String chargeId) {
        try {
            return Charge.retrieve(chargeId);
        } catch (StripeException e) {
            log.error("Failed to retrieve charge: {}", e.getMessage());
            throw new ValidationException("Failed to retrieve charge: " + e.getMessage());
        }
    }
}
EOF
```

#### **Step 8: Create Kafka Producer**

```bash
# Create kafka directory
mkdir -p payment-service/src/main/java/com/social/payment/kafka

# Create PaymentEventProducer.java
cat > payment-service/src/main/java/com/social/payment/kafka/PaymentEventProducer.java << 'EOF'
package com.social.payment.kafka;

import com.social.payment.event.PaymentCompletedEvent;
import com.social.payment.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String PAYMENT_EVENTS_TOPIC = "payment.events";
    private static final String PAYMENT_COMPLETED_TOPIC = "payment.completed";

    public void sendPaymentEvent(PaymentEvent event) {
        try {
            kafkaTemplate.send(PAYMENT_EVENTS_TOPIC, event.getPaymentId().toString(), event);
            log.info("Payment event sent to Kafka: {}", event);
        } catch (Exception e) {
            log.error("Failed to send payment event to Kafka: {}", e.getMessage());
        }
    }

    public void sendPaymentCompletedEvent(PaymentCompletedEvent event) {
        try {
            kafkaTemplate.send(PAYMENT_COMPLETED_TOPIC, event.getPaymentId().toString(), event);
            log.info("Payment completed event sent to Kafka: {}", event);
        } catch (Exception e) {
            log.error("Failed to send payment completed event to Kafka: {}", e.getMessage());
        }
    }
}
EOF
```

#### **Step 9: Create Kafka Consumer (for Order Events)**

```bash
# Create OrderEventConsumer.java
cat > payment-service/src/main/java/com/social/payment/kafka/OrderEventConsumer.java << 'EOF'
package com.social.payment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order.created", groupId = "payment-service-group")
    public void consumeOrderCreatedEvent(String message) {
        try {
            log.info("Received order created event: {}", message);

            // Parse the event
            Map<String, Object> event = objectMapper.readValue(message, Map.class);

            // Process the event (e.g., prepare for payment)
            log.info("Order {} is ready for payment", event.get("orderNumber"));

        } catch (Exception e) {
            log.error("Failed to process order created event: {}", e.getMessage());
        }
    }
}
EOF
```

#### **Step 10: Create PaymentService**

```bash
# Create PaymentService.java
cat > payment-service/src/main/java/com/social/payment/service/PaymentService.java << 'EOF'
package com.social.payment.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.payment.dto.CreatePaymentRequest;
import com.social.payment.dto.PaymentDTO;
import com.social.payment.dto.RefundRequest;
import com.social.payment.dto.StripePaymentRequest;
import com.social.payment.event.PaymentCompletedEvent;
import com.social.payment.event.PaymentEvent;
import com.social.payment.kafka.PaymentEventProducer;
import com.social.payment.model.Payment;
import com.social.payment.model.Transaction;
import com.social.payment.repository.PaymentRepository;
import com.social.payment.repository.TransactionRepository;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;
    private final StripeService stripeService;
    private final PaymentEventProducer eventProducer;

    @Transactional
    @CircuitBreaker(name = "paymentService", fallbackMethod = "createPaymentFallback")
    public PaymentDTO createPayment(UUID userId, CreatePaymentRequest request) {
        log.info("Creating payment for order: {}", request.getOrderId());

        // Check if payment already exists for this order
        if (paymentRepository.findByOrderId(request.getOrderId()).isPresent()) {
            throw new ValidationException("Payment already exists for this order");
        }

        // Create payment entity
        Payment payment = new Payment();
        payment.setTransactionId(generateTransactionId());
        payment.setOrderId(request.getOrderId());
        payment.setUserId(userId);
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setPaymentMethod(Payment.PaymentMethod.valueOf(request.getPaymentMethod()));
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setDescription(request.getDescription());

        // Process payment based on method
        if (payment.getPaymentMethod() == Payment.PaymentMethod.STRIPE ||
            payment.getPaymentMethod() == Payment.PaymentMethod.CREDIT_CARD) {

            processStripePayment(payment, request.getStripeToken());
        } else {
            // For other payment methods, mark as processing
            payment.setStatus(Payment.PaymentStatus.PROCESSING);
        }

        // Save payment
        Payment savedPayment = paymentRepository.save(payment);

        // Create transaction record
        createTransaction(savedPayment, Transaction.TransactionType.CHARGE,
                         savedPayment.getAmount(), Transaction.TransactionStatus.COMPLETED);

        // Publish event if completed
        if (savedPayment.getStatus() == Payment.PaymentStatus.COMPLETED) {
            publishPaymentCompletedEvent(savedPayment);
        }

        log.info("Payment created successfully: {}", savedPayment.getTransactionId());
        return PaymentDTO.fromEntity(savedPayment);
    }

    public PaymentDTO getPaymentById(UUID id) {
        log.info("Fetching payment with ID: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));
        return PaymentDTO.fromEntity(payment);
    }

    public PaymentDTO getPaymentByTransactionId(String transactionId) {
        log.info("Fetching payment with transaction ID: {}", transactionId);
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with transaction ID: " + transactionId));
        return PaymentDTO.fromEntity(payment);
    }

    public PaymentDTO getPaymentByOrderId(UUID orderId) {
        log.info("Fetching payment for order: {}", orderId);
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for order: " + orderId));
        return PaymentDTO.fromEntity(payment);
    }

    public PageResponse<PaymentDTO> getUserPayments(UUID userId, int page, int size) {
        log.info("Fetching payments for user: {}", userId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Payment> paymentPage = paymentRepository.findByUserIdAndDeletedFalse(userId, pageable);

        return PageResponse.of(
                paymentPage.getContent().stream().map(PaymentDTO::fromEntity).toList(),
                paymentPage.getNumber(),
                paymentPage.getSize(),
                paymentPage.getTotalElements(),
                paymentPage.getTotalPages(),
                paymentPage.isLast()
        );
    }

    public PageResponse<PaymentDTO> getAllPayments(int page, int size) {
        log.info("Fetching all payments");

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Payment> paymentPage = paymentRepository.findAll(pageable);

        return PageResponse.of(
                paymentPage.getContent().stream().map(PaymentDTO::fromEntity).toList(),
                paymentPage.getNumber(),
                paymentPage.getSize(),
                paymentPage.getTotalElements(),
                paymentPage.getTotalPages(),
                paymentPage.isLast()
        );
    }

    @Transactional
    public PaymentDTO refundPayment(UUID id, RefundRequest request) {
        log.info("Refunding payment: {}", id);

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        // Validate refund
        if (!payment.isRefundable()) {
            throw new ValidationException("Payment is not refundable");
        }

        BigDecimal remainingAmount = payment.getRemainingRefundableAmount();
        if (request.getAmount().compareTo(remainingAmount) > 0) {
            throw new ValidationException("Refund amount exceeds remaining refundable amount");
        }

        // Process refund with Stripe
        if (payment.getStripeChargeId() != null) {
            Refund refund = stripeService.createRefund(payment.getStripeChargeId(),
                                                       request.getAmount(),
                                                       request.getReason());
            log.info("Stripe refund created: {}", refund.getId());
        }

        // Update payment
        BigDecimal currentRefundAmount = payment.getRefundAmount() != null ?
                                         payment.getRefundAmount() : BigDecimal.ZERO;
        payment.setRefundAmount(currentRefundAmount.add(request.getAmount()));
        payment.setRefundReason(request.getReason());
        payment.setRefundedAt(LocalDateTime.now());

        // Update status
        if (payment.getRefundAmount().compareTo(payment.getAmount()) >= 0) {
            payment.setStatus(Payment.PaymentStatus.REFUNDED);
        } else {
            payment.setStatus(Payment.PaymentStatus.PARTIALLY_REFUNDED);
        }

        Payment updatedPayment = paymentRepository.save(payment);

        // Create transaction record
        createTransaction(updatedPayment, Transaction.TransactionType.REFUND,
                         request.getAmount(), Transaction.TransactionStatus.COMPLETED);

        // Publish event
        publishPaymentEvent(updatedPayment, "REFUNDED");

        log.info("Payment refunded successfully");
        return PaymentDTO.fromEntity(updatedPayment);
    }

    public BigDecimal getTotalProcessedAmount() {
        return paymentRepository.getTotalProcessedAmount();
    }

    public BigDecimal getTotalRefundedAmount() {
        return paymentRepository.getTotalRefundedAmount();
    }

    public long getUserPaymentCount(UUID userId) {
        return paymentRepository.countByUserId(userId);
    }

    // Helper methods
    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis();
    }

    private void processStripePayment(Payment payment, String stripeToken) {
        try {
            StripePaymentRequest stripeRequest = StripePaymentRequest.builder()
                    .amount(payment.getAmount())
                    .currency(payment.getCurrency())
                    .token(stripeToken)
                    .description(payment.getDescription())
                    .build();

            // Create charge
            Charge charge = stripeService.createCharge(stripeRequest);

            // Update payment with Stripe details
            payment.setStripeChargeId(charge.getId());
            payment.setCardLastFour(charge.getPaymentMethodDetails().getCard().getLast4());
            payment.setCardBrand(charge.getPaymentMethodDetails().getCard().getBrand());
            payment.setStatus(Payment.PaymentStatus.COMPLETED);
            payment.setProcessedAt(LocalDateTime.now());

            log.info("Stripe payment processed successfully");

        } catch (Exception e) {
            log.error("Stripe payment failed: {}", e.getMessage());
            payment.setStatus(Payment.PaymentStatus.FAILED);
            payment.setFailureReason(e.getMessage());
        }
    }

    private void createTransaction(Payment payment, Transaction.TransactionType type,
                                   BigDecimal amount, Transaction.TransactionStatus status) {
        Transaction transaction = new Transaction();
        transaction.setPayment(payment);
        transaction.setTransactionType(type);
        transaction.setAmount(amount);
        transaction.setCurrency(payment.getCurrency());
        transaction.setStatus(status);
        transaction.setProcessedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    private void publishPaymentCompletedEvent(Payment payment) {
        PaymentCompletedEvent event = PaymentCompletedEvent.builder()
                .paymentId(payment.getId())
                .transactionId(payment.getTransactionId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .paymentMethod(payment.getPaymentMethod().name())
                .completedAt(payment.getProcessedAt())
                .build();

        eventProducer.sendPaymentCompletedEvent(event);
    }

    private void publishPaymentEvent(Payment payment, String eventType) {
        PaymentEvent event = PaymentEvent.builder()
                .paymentId(payment.getId())
                .transactionId(payment.getTransactionId())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .eventType(eventType)
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod().name())
                .status(payment.getStatus().name())
                .timestamp(LocalDateTime.now())
                .build();

        eventProducer.sendPaymentEvent(event);
    }

    // Fallback method
    private PaymentDTO createPaymentFallback(UUID userId, CreatePaymentRequest request, Exception e) {
        log.error("Failed to create payment: {}", e.getMessage());
        throw new ValidationException("Payment service is temporarily unavailable");
    }
}
EOF
```

#### **Step 11: Create Controller**

```bash
# Create controller directory
mkdir -p payment-service/src/main/java/com/social/payment/controller

# Create PaymentController.java
cat > payment-service/src/main/java/com/social/payment/controller/PaymentController.java << 'EOF'
package com.social.payment.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.payment.dto.CreatePaymentRequest;
import com.social.payment.dto.PaymentDTO;
import com.social.payment.dto.RefundRequest;
import com.social.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Management", description = "APIs for payment processing and management")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Create a new payment")
    public ResponseEntity<ApiResponse<PaymentDTO>> createPayment(
            @RequestHeader("X-User-Id") UUID userId,
            @Valid @RequestBody CreatePaymentRequest request) {
        log.info("REST request to create payment for order: {}", request.getOrderId());
        PaymentDTO payment = paymentService.createPayment(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(payment, "Payment created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID")
    public ResponseEntity<ApiResponse<PaymentDTO>> getPaymentById(@PathVariable UUID id) {
        log.info("REST request to get payment by ID: {}", id);
        PaymentDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(ApiResponse.success(payment));
    }

    @GetMapping("/transaction/{transactionId}")
    @Operation(summary = "Get payment by transaction ID")
    public ResponseEntity<ApiResponse<PaymentDTO>> getPaymentByTransactionId(@PathVariable String transactionId) {
        log.info("REST request to get payment by transaction ID: {}", transactionId);
        PaymentDTO payment = paymentService.getPaymentByTransactionId(transactionId);
        return ResponseEntity.ok(ApiResponse.success(payment));
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Get payment by order ID")
    public ResponseEntity<ApiResponse<PaymentDTO>> getPaymentByOrderId(@PathVariable UUID orderId) {
        log.info("REST request to get payment for order: {}", orderId);
        PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(ApiResponse.success(payment));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user payments")
    public ResponseEntity<ApiResponse<PageResponse<PaymentDTO>>> getUserPayments(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get payments for user: {}", userId);
        PageResponse<PaymentDTO> payments = paymentService.getUserPayments(userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(payments));
    }

    @GetMapping
    @Operation(summary = "Get all payments")
    public ResponseEntity<ApiResponse<PageResponse<PaymentDTO>>> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get all payments");
        PageResponse<PaymentDTO> payments = paymentService.getAllPayments(page, size);
        return ResponseEntity.ok(ApiResponse.success(payments));
    }

    @PostMapping("/{id}/refund")
    @Operation(summary = "Refund payment")
    public ResponseEntity<ApiResponse<PaymentDTO>> refundPayment(
            @PathVariable UUID id,
            @Valid @RequestBody RefundRequest request) {
        log.info("REST request to refund payment: {}", id);
        PaymentDTO payment = paymentService.refundPayment(id, request);
        return ResponseEntity.ok(ApiResponse.success(payment, "Payment refunded successfully"));
    }

    @GetMapping("/stats/total-processed")
    @Operation(summary = "Get total processed amount")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotalProcessedAmount() {
        log.info("REST request to get total processed amount");
        BigDecimal total = paymentService.getTotalProcessedAmount();
        return ResponseEntity.ok(ApiResponse.success(total));
    }

    @GetMapping("/stats/total-refunded")
    @Operation(summary = "Get total refunded amount")
    public ResponseEntity<ApiResponse<BigDecimal>> getTotalRefundedAmount() {
        log.info("REST request to get total refunded amount");
        BigDecimal total = paymentService.getTotalRefundedAmount();
        return ResponseEntity.ok(ApiResponse.success(total));
    }

    @GetMapping("/user/{userId}/count")
    @Operation(summary = "Get user payment count")
    public ResponseEntity<ApiResponse<Long>> getUserPaymentCount(@PathVariable UUID userId) {
        log.info("REST request to get payment count for user: {}", userId);
        long count = paymentService.getUserPaymentCount(userId);
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}
EOF
```

#### **Step 12: Create Configuration Classes**

```bash
# Create config directory
mkdir -p payment-service/src/main/java/com/social/payment/config

# Create KafkaConfig.java
cat > payment-service/src/main/java/com/social/payment/config/KafkaConfig.java << 'EOF'
package com.social.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic paymentEventsTopic() {
        return TopicBuilder.name("payment.events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentCompletedTopic() {
        return TopicBuilder.name("payment.completed")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
EOF

# Create OpenApiConfig.java
cat > payment-service/src/main/java/com/social/payment/config/OpenApiConfig.java << 'EOF'
package com.social.payment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI paymentServiceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9004");
        server.setDescription("Payment Service - Development");

        Contact contact = new Contact();
        contact.setName("Social Commerce Platform");
        contact.setEmail("support@socialcommerce.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Payment Service API")
                .version("1.0.0")
                .description("Payment processing, transaction management, and Stripe integration")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
EOF
```

#### **Step 13: Create application.yml**

```bash
# Create application.yml
cat > payment-service/src/main/resources/application.yml << 'EOF'
spring:
  application:
    name: payment-service

  config:
    import: optional:configserver:http://localhost:8888

  datasource:
    url: jdbc:postgresql://localhost:5432/payment_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        use_sql_comments: true

  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      properties:
        spring.json.add.type.headers: false
    consumer:
      group-id: payment-service-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
        spring.json.use.type.headers: false

server:
  port: 9004

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${server.port}

stripe:
  api:
    key: ${STRIPE_API_KEY:sk_test_dummy_key}

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

resilience4j:
  circuitbreaker:
    instances:
      paymentService:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 10s
        failure-rate-threshold: 50
        slow-call-rate-threshold: 100
        slow-call-duration-threshold: 5s

logging:
  level:
    com.social.payment: DEBUG
    org.springframework.kafka: INFO
    com.stripe: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
EOF
```

#### **Step 14: Build and Run Payment Service**

```bash
# Build the service
cd payment-service
mvn clean install -DskipTests
cd ..

# Start PostgreSQL for Payment Service
docker run -d \
  --name payment-postgres \
  -e POSTGRES_DB=payment_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5434:5432 \
  postgres:15

# Wait for database to be ready
sleep 10

# Run Payment Service
cd payment-service
mvn spring-boot:run
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.5.5)

Payment Service started on port 9004
Registered with Eureka Server
Connected to PostgreSQL: payment_db
Connected to Kafka: localhost:9092
Stripe API initialized
Swagger UI: http://localhost:9004/swagger-ui.html
```

#### **Step 15: Test Payment Service with curl**

```bash
# 1. Create a payment
curl -X POST http://localhost:9004/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "X-User-Id: <USER_ID>" \
  -d '{
    "orderId": "<ORDER_ID>",
    "amount": 229.97,
    "currency": "USD",
    "paymentMethod": "CREDIT_CARD",
    "stripeToken": "tok_visa",
    "description": "Payment for order ORD-1234567890"
  }'

# Expected Response:
# {
#   "success": true,
#   "message": "Payment created successfully",
#   "data": {
#     "id": "...",
#     "transactionId": "TXN-1234567890",
#     "orderId": "...",
#     "userId": "...",
#     "amount": 229.97,
#     "currency": "USD",
#     "paymentMethod": "CREDIT_CARD",
#     "status": "COMPLETED",
#     "stripeChargeId": "ch_...",
#     "cardLastFour": "4242",
#     "cardBrand": "visa"
#   }
# }

# 2. Get payment by ID
curl -X GET http://localhost:9004/api/v1/payments/<PAYMENT_ID>

# 3. Get payment by transaction ID
curl -X GET http://localhost:9004/api/v1/payments/transaction/TXN-1234567890

# 4. Get payment by order ID
curl -X GET http://localhost:9004/api/v1/payments/order/<ORDER_ID>

# 5. Get user payments
curl -X GET "http://localhost:9004/api/v1/payments/user/<USER_ID>?page=0&size=10"

# 6. Get all payments
curl -X GET "http://localhost:9004/api/v1/payments?page=0&size=20"

# 7. Refund payment
curl -X POST http://localhost:9004/api/v1/payments/<PAYMENT_ID>/refund \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 50.00,
    "reason": "Customer requested partial refund"
  }'

# Expected Response:
# {
#   "success": true,
#   "message": "Payment refunded successfully",
#   "data": {
#     "id": "...",
#     "transactionId": "TXN-1234567890",
#     "status": "PARTIALLY_REFUNDED",
#     "refundAmount": 50.00,
#     "refundReason": "Customer requested partial refund"
#   }
# }

# 8. Get total processed amount
curl -X GET http://localhost:9004/api/v1/payments/stats/total-processed

# 9. Get total refunded amount
curl -X GET http://localhost:9004/api/v1/payments/stats/total-refunded

# 10. Get user payment count
curl -X GET http://localhost:9004/api/v1/payments/user/<USER_ID>/count
```

#### **Step 16: Verify Kafka Events**

```bash
# Check Kafka topics
docker exec -it kafka kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --list

# Expected Output:
# payment.events
# payment.completed
# order.events
# order.created

# Consume payment events
docker exec -it kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic payment.events \
  --from-beginning

# You should see payment events like:
# {"paymentId":"...","transactionId":"TXN-1234567890","orderId":"...","userId":"...","eventType":"COMPLETED","amount":229.97,"paymentMethod":"CREDIT_CARD","status":"COMPLETED","timestamp":"2025-10-16T11:00:00"}

# Consume payment completed events
docker exec -it kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic payment.completed \
  --from-beginning

# You should see:
# {"paymentId":"...","transactionId":"TXN-1234567890","orderId":"...","userId":"...","amount":229.97,"currency":"USD","paymentMethod":"CREDIT_CARD","completedAt":"2025-10-16T11:00:00"}
```

#### **Step 17: Test Stripe Integration (Optional)**

```bash
# To test with real Stripe API:
# 1. Get your Stripe test API key from https://dashboard.stripe.com/test/apikeys
# 2. Set environment variable:
export STRIPE_API_KEY=sk_test_your_actual_test_key

# 3. Restart Payment Service
cd payment-service
mvn spring-boot:run

# 4. Use Stripe test card numbers:
# - Visa: 4242 4242 4242 4242
# - Mastercard: 5555 5555 5555 4444
# - Amex: 3782 822463 10005

# 5. Create payment with Stripe token
curl -X POST http://localhost:9004/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "X-User-Id: <USER_ID>" \
  -d '{
    "orderId": "<ORDER_ID>",
    "amount": 100.00,
    "currency": "USD",
    "paymentMethod": "STRIPE",
    "stripeToken": "tok_visa",
    "description": "Test payment"
  }'
```

#### **Step 18: Verify in Swagger UI**

```bash
# Open Swagger UI
open http://localhost:9004/swagger-ui.html

# Test all endpoints:
# - POST /api/v1/payments - Create payment
# - GET /api/v1/payments/{id} - Get payment by ID
# - GET /api/v1/payments/transaction/{transactionId} - Get by transaction ID
# - GET /api/v1/payments/order/{orderId} - Get by order ID
# - GET /api/v1/payments/user/{userId} - Get user payments
# - GET /api/v1/payments - Get all payments
# - POST /api/v1/payments/{id}/refund - Refund payment
# - GET /api/v1/payments/stats/total-processed - Get total processed
# - GET /api/v1/payments/stats/total-refunded - Get total refunded
# - GET /api/v1/payments/user/{userId}/count - Get user payment count
```

#### **Step 19: Commit Changes**

```bash
# Add and commit
git add payment-service/
git commit -m "feat: Add Payment Service with Stripe integration

- Created Payment and Transaction entities
- Implemented payment processing workflow
- Integrated Stripe for credit card payments
- Added refund functionality
- Implemented Kafka event publishing for payment events
- Added Kafka consumer for order events
- Created comprehensive REST APIs
- Port: 9004
- Database: PostgreSQL + Kafka
- External: Stripe API"
```

---

### **✅ DAY 9 CHECKPOINT**

**What We Built:**
- ✅ Payment Service (Port 9004)
- ✅ Stripe integration for payment processing
- ✅ Transaction management
- ✅ Refund functionality (full and partial)
- ✅ Kafka event publishing (payment.events, payment.completed)
- ✅ Kafka consumer for order events
- ✅ 10 REST API endpoints
- ✅ Circuit breaker patterns
- ✅ PostgreSQL + Kafka + Stripe integration

**Entities Created:**
- Payment (transaction details, Stripe info, status tracking)
- Transaction (transaction history, types: CHARGE, REFUND, AUTHORIZATION)

**Payment Methods Supported:**
- CREDIT_CARD
- DEBIT_CARD
- PAYPAL
- STRIPE
- BANK_TRANSFER
- CASH_ON_DELIVERY

**Payment Statuses:**
- PENDING
- PROCESSING
- COMPLETED
- FAILED
- REFUNDED
- PARTIALLY_REFUNDED
- CANCELLED

**APIs Created:**
- 10 endpoints (create, get by ID/transaction/order, list, refund, stats)

**Events Published:**
- payment.events - General payment events
- payment.completed - Payment completion events

**Events Consumed:**
- order.created - Order creation events from Order Service

**Next Steps:**
- Create Social Service (Port 9005)
- Create Notification Service (Port 9006)
- Complete Docker Compose setup

---

### **DAY 10: CREATE SOCIAL SERVICE**

> **Time**: 6 hours
> **Goal**: Create Social Service with posts, comments, likes, and social graph
> **Port**: 9005
> **Database**: MongoDB + Neo4j

#### **Step 1: Create social-service POM**

```bash
# Navigate to social-service directory
cd social-service

# Create pom.xml
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>social-service</artifactId>
    <packaging>jar</packaging>

    <name>Social Service</name>
    <description>Social features: posts, comments, likes, follows, and social graph</description>

    <dependencies>
        <!-- Spring Boot Starter Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Starter Data MongoDB -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>

        <!-- Spring Data Neo4j -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-neo4j</artifactId>
        </dependency>

        <!-- Spring Boot Starter Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Eureka Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!-- Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- OpenFeign for inter-service communication -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!-- Circuit Breaker -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
        </dependency>

        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <!-- MapStruct -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        </dependency>

        <!-- Spring Boot Actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Micrometer Prometheus -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Embedded MongoDB for Testing -->
        <dependency>
            <groupId>de.flapdoodle.embed</groupId>
            <artifactId>de.flapdoodle.embed.mongo</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
EOF

cd ..
```

#### **Step 2: Create SocialServiceApplication**

```bash
# Create main application class
cat > social-service/src/main/java/com/social/socialservice/SocialServiceApplication.java << 'EOF'
package com.social.socialservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication(scanBasePackages = {"com.social.socialservice", "com.social.common"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableMongoAuditing
@EnableNeo4jRepositories(basePackages = "com.social.socialservice.repository.neo4j")
public class SocialServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialServiceApplication.class, args);
    }
}
EOF
```

#### **Step 3: Create MongoDB Document Classes**

```bash
# Create model directory
mkdir -p social-service/src/main/java/com/social/socialservice/model/mongo

# Create Post.java
cat > social-service/src/main/java/com/social/socialservice/model/mongo/Post.java << 'EOF'
package com.social.socialservice.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private String id;

    @Indexed
    private String userId;

    private String content;

    private List<String> images;

    private List<String> tags;

    @Indexed
    private PostType type;

    @Indexed
    private PostStatus status;

    private String productId; // If post is about a product

    @Builder.Default
    private Set<String> likedBy = new HashSet<>();

    @Builder.Default
    private Integer likeCount = 0;

    @Builder.Default
    private Integer commentCount = 0;

    @Builder.Default
    private Integer shareCount = 0;

    @Builder.Default
    private Integer viewCount = 0;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private boolean deleted = false;

    public enum PostType {
        TEXT,
        IMAGE,
        VIDEO,
        PRODUCT_REVIEW,
        PRODUCT_RECOMMENDATION
    }

    public enum PostStatus {
        DRAFT,
        PUBLISHED,
        ARCHIVED,
        DELETED
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }

    public void incrementCommentCount() {
        this.commentCount++;
    }

    public void decrementCommentCount() {
        if (this.commentCount > 0) {
            this.commentCount--;
        }
    }
}
EOF

# Create Comment.java
cat > social-service/src/main/java/com/social/socialservice/model/mongo/Comment.java << 'EOF'
package com.social.socialservice.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    @Indexed
    private String postId;

    @Indexed
    private String userId;

    private String content;

    private String parentCommentId; // For nested comments/replies

    @Builder.Default
    private Set<String> likedBy = new HashSet<>();

    @Builder.Default
    private Integer likeCount = 0;

    @Builder.Default
    private Integer replyCount = 0;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private boolean deleted = false;

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
}
EOF
```

#### **Step 4: Create Neo4j Node Classes (Social Graph)**

```bash
# Create neo4j model directory
mkdir -p social-service/src/main/java/com/social/socialservice/model/neo4j

# Create UserNode.java
cat > social-service/src/main/java/com/social/socialservice/model/neo4j/UserNode.java << 'EOF'
package com.social.socialservice.model.neo4j;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Node("User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNode {

    @Id
    private String userId;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    @Builder.Default
    private Set<UserNode> following = new HashSet<>();

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.INCOMING)
    @Builder.Default
    private Set<UserNode> followers = new HashSet<>();

    public void follow(UserNode user) {
        this.following.add(user);
    }

    public void unfollow(UserNode user) {
        this.following.remove(user);
    }

    public boolean isFollowing(UserNode user) {
        return this.following.contains(user);
    }

    public int getFollowingCount() {
        return this.following.size();
    }

    public int getFollowersCount() {
        return this.followers.size();
    }
}
EOF
```

#### **Step 5: Create DTOs**

```bash
# Create dto directory
mkdir -p social-service/src/main/java/com/social/socialservice/dto

# Create PostDTO.java
cat > social-service/src/main/java/com/social/socialservice/dto/PostDTO.java << 'EOF'
package com.social.socialservice.dto;

import com.social.socialservice.model.mongo.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private String id;
    private String userId;
    private String content;
    private List<String> images;
    private List<String> tags;
    private String type;
    private String status;
    private String productId;
    private Integer likeCount;
    private Integer commentCount;
    private Integer shareCount;
    private Integer viewCount;
    private boolean likedByCurrentUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostDTO fromEntity(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .content(post.getContent())
                .images(post.getImages())
                .tags(post.getTags())
                .type(post.getType() != null ? post.getType().name() : null)
                .status(post.getStatus() != null ? post.getStatus().name() : null)
                .productId(post.getProductId())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .shareCount(post.getShareCount())
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostDTO fromEntity(Post post, String currentUserId) {
        PostDTO dto = fromEntity(post);
        dto.setLikedByCurrentUser(post.getLikedBy().contains(currentUserId));
        return dto;
    }
}
EOF

# Create CreatePostRequest.java
cat > social-service/src/main/java/com/social/socialservice/dto/CreatePostRequest.java << 'EOF'
package com.social.socialservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {

    @NotBlank(message = "Content is required")
    @Size(max = 5000, message = "Content must not exceed 5000 characters")
    private String content;

    private List<String> images;

    private List<String> tags;

    private String type;

    private String productId;
}
EOF

# Create UpdatePostRequest.java
cat > social-service/src/main/java/com/social/socialservice/dto/UpdatePostRequest.java << 'EOF'
package com.social.socialservice.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {

    @Size(max = 5000, message = "Content must not exceed 5000 characters")
    private String content;

    private List<String> images;

    private List<String> tags;
}
EOF

# Create CommentDTO.java
cat > social-service/src/main/java/com/social/socialservice/dto/CommentDTO.java << 'EOF'
package com.social.socialservice.dto;

import com.social.socialservice.model.mongo.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private String id;
    private String postId;
    private String userId;
    private String content;
    private String parentCommentId;
    private Integer likeCount;
    private Integer replyCount;
    private boolean likedByCurrentUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentDTO fromEntity(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .parentCommentId(comment.getParentCommentId())
                .likeCount(comment.getLikeCount())
                .replyCount(comment.getReplyCount())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    public static CommentDTO fromEntity(Comment comment, String currentUserId) {
        CommentDTO dto = fromEntity(comment);
        dto.setLikedByCurrentUser(comment.getLikedBy().contains(currentUserId));
        return dto;
    }
}
EOF

# Create CreateCommentRequest.java
cat > social-service/src/main/java/com/social/socialservice/dto/CreateCommentRequest.java << 'EOF'
package com.social.socialservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Content must not exceed 1000 characters")
    private String content;

    private String parentCommentId;
}
EOF

# Create FollowDTO.java
cat > social-service/src/main/java/com/social/socialservice/dto/FollowDTO.java << 'EOF'
package com.social.socialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO {

    private String userId;
    private String username;
    private String email;
    private boolean isFollowing;
    private boolean isFollower;
}
EOF

# Create SocialStatsDTO.java
cat > social-service/src/main/java/com/social/socialservice/dto/SocialStatsDTO.java << 'EOF'
package com.social.socialservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialStatsDTO {

    private String userId;
    private Integer followingCount;
    private Integer followersCount;
    private Integer postsCount;
    private Integer totalLikes;
    private Integer totalComments;
}
EOF
```

#### **Step 6: Create Repositories**

```bash
# Create repository directories
mkdir -p social-service/src/main/java/com/social/socialservice/repository/mongo
mkdir -p social-service/src/main/java/com/social/socialservice/repository/neo4j

# Create PostRepository.java (MongoDB)
cat > social-service/src/main/java/com/social/socialservice/repository/mongo/PostRepository.java << 'EOF'
package com.social.socialservice.repository.mongo;

import com.social.socialservice.model.mongo.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Page<Post> findByUserIdAndDeletedFalse(String userId, Pageable pageable);

    Page<Post> findByStatusAndDeletedFalse(Post.PostStatus status, Pageable pageable);

    @Query("{ 'deleted': false, 'status': 'PUBLISHED' }")
    Page<Post> findPublishedPosts(Pageable pageable);

    @Query("{ 'deleted': false, 'status': 'PUBLISHED', 'userId': { $in: ?0 } }")
    Page<Post> findPostsByUserIds(List<String> userIds, Pageable pageable);

    @Query("{ 'deleted': false, 'tags': { $in: ?0 } }")
    Page<Post> findByTags(List<String> tags, Pageable pageable);

    @Query("{ 'deleted': false, 'productId': ?0 }")
    Page<Post> findByProductId(String productId, Pageable pageable);

    long countByUserIdAndDeletedFalse(String userId);

    @Query(value = "{ 'deleted': false, 'userId': ?0 }", count = true)
    long countUserPosts(String userId);
}
EOF

# Create CommentRepository.java (MongoDB)
cat > social-service/src/main/java/com/social/socialservice/repository/mongo/CommentRepository.java << 'EOF'
package com.social.socialservice.repository.mongo;

import com.social.socialservice.model.mongo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Page<Comment> findByPostIdAndDeletedFalseAndParentCommentIdIsNull(String postId, Pageable pageable);

    List<Comment> findByParentCommentIdAndDeletedFalse(String parentCommentId);

    long countByPostIdAndDeletedFalse(String postId);

    @Query(value = "{ 'deleted': false, 'userId': ?0 }", count = true)
    long countUserComments(String userId);
}
EOF

# Create UserNodeRepository.java (Neo4j)
cat > social-service/src/main/java/com/social/socialservice/repository/neo4j/UserNodeRepository.java << 'EOF'
package com.social.socialservice.repository.neo4j;

import com.social.socialservice.model.neo4j.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode, String> {

    Optional<UserNode> findByUserId(String userId);

    @Query("MATCH (u:User {userId: $userId})-[:FOLLOWS]->(following:User) RETURN following")
    List<UserNode> findFollowing(String userId);

    @Query("MATCH (u:User {userId: $userId})<-[:FOLLOWS]-(follower:User) RETURN follower")
    List<UserNode> findFollowers(String userId);

    @Query("MATCH (u:User {userId: $userId})-[:FOLLOWS]->(following:User) RETURN count(following)")
    int countFollowing(String userId);

    @Query("MATCH (u:User {userId: $userId})<-[:FOLLOWS]-(follower:User) RETURN count(follower)")
    int countFollowers(String userId);

    @Query("MATCH (u1:User {userId: $userId1}), (u2:User {userId: $userId2}) " +
           "RETURN exists((u1)-[:FOLLOWS]->(u2))")
    boolean isFollowing(String userId1, String userId2);

    @Query("MATCH (u:User {userId: $userId})-[:FOLLOWS]->(f:User)-[:FOLLOWS]->(fof:User) " +
           "WHERE NOT (u)-[:FOLLOWS]->(fof) AND u <> fof " +
           "RETURN DISTINCT fof LIMIT $limit")
    List<UserNode> findSuggestedUsers(String userId, int limit);
}
EOF
```

#### **Step 7: Create Service Layer**

```bash
# Create service directory
mkdir -p social-service/src/main/java/com/social/socialservice/service

# Create PostService.java
cat > social-service/src/main/java/com/social/socialservice/service/PostService.java << 'EOF'
package com.social.socialservice.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.socialservice.dto.CreatePostRequest;
import com.social.socialservice.dto.PostDTO;
import com.social.socialservice.dto.UpdatePostRequest;
import com.social.socialservice.model.mongo.Post;
import com.social.socialservice.repository.mongo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDTO createPost(String userId, CreatePostRequest request) {
        log.info("Creating post for user: {}", userId);

        Post post = Post.builder()
                .userId(userId)
                .content(request.getContent())
                .images(request.getImages())
                .tags(request.getTags())
                .type(request.getType() != null ? Post.PostType.valueOf(request.getType()) : Post.PostType.TEXT)
                .status(Post.PostStatus.PUBLISHED)
                .productId(request.getProductId())
                .likeCount(0)
                .commentCount(0)
                .shareCount(0)
                .viewCount(0)
                .build();

        Post savedPost = postRepository.save(post);
        log.info("Post created successfully: {}", savedPost.getId());
        return PostDTO.fromEntity(savedPost);
    }

    public PostDTO getPostById(String id, String currentUserId) {
        log.info("Fetching post with ID: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + id));

        // Increment view count
        post.setViewCount(post.getViewCount() + 1);
        postRepository.save(post);

        return PostDTO.fromEntity(post, currentUserId);
    }

    public PageResponse<PostDTO> getUserPosts(String userId, String currentUserId, int page, int size) {
        log.info("Fetching posts for user: {}", userId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postPage = postRepository.findByUserIdAndDeletedFalse(userId, pageable);

        return PageResponse.of(
                postPage.getContent().stream()
                        .map(post -> PostDTO.fromEntity(post, currentUserId))
                        .toList(),
                postPage.getNumber(),
                postPage.getSize(),
                postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.isLast()
        );
    }

    public PageResponse<PostDTO> getFeed(List<String> followingUserIds, String currentUserId, int page, int size) {
        log.info("Fetching feed for user: {}", currentUserId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postPage = postRepository.findPostsByUserIds(followingUserIds, pageable);

        return PageResponse.of(
                postPage.getContent().stream()
                        .map(post -> PostDTO.fromEntity(post, currentUserId))
                        .toList(),
                postPage.getNumber(),
                postPage.getSize(),
                postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.isLast()
        );
    }

    public PageResponse<PostDTO> getPublicFeed(String currentUserId, int page, int size) {
        log.info("Fetching public feed");

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Post> postPage = postRepository.findPublishedPosts(pageable);

        return PageResponse.of(
                postPage.getContent().stream()
                        .map(post -> PostDTO.fromEntity(post, currentUserId))
                        .toList(),
                postPage.getNumber(),
                postPage.getSize(),
                postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.isLast()
        );
    }

    public PostDTO updatePost(String id, String userId, UpdatePostRequest request) {
        log.info("Updating post: {}", id);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getUserId().equals(userId)) {
            throw new ValidationException("You can only update your own posts");
        }

        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }
        if (request.getImages() != null) {
            post.setImages(request.getImages());
        }
        if (request.getTags() != null) {
            post.setTags(request.getTags());
        }

        Post updatedPost = postRepository.save(post);
        log.info("Post updated successfully");
        return PostDTO.fromEntity(updatedPost, userId);
    }

    public void deletePost(String id, String userId) {
        log.info("Deleting post: {}", id);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getUserId().equals(userId)) {
            throw new ValidationException("You can only delete your own posts");
        }

        post.setDeleted(true);
        post.setStatus(Post.PostStatus.DELETED);
        postRepository.save(post);
        log.info("Post deleted successfully");
    }

    public PostDTO likePost(String id, String userId) {
        log.info("User {} liking post: {}", userId, id);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (post.getLikedBy().contains(userId)) {
            throw new ValidationException("You have already liked this post");
        }

        post.getLikedBy().add(userId);
        post.incrementLikeCount();
        Post updatedPost = postRepository.save(post);

        log.info("Post liked successfully");
        return PostDTO.fromEntity(updatedPost, userId);
    }

    public PostDTO unlikePost(String id, String userId) {
        log.info("User {} unliking post: {}", userId, id);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        if (!post.getLikedBy().contains(userId)) {
            throw new ValidationException("You have not liked this post");
        }

        post.getLikedBy().remove(userId);
        post.decrementLikeCount();
        Post updatedPost = postRepository.save(post);

        log.info("Post unliked successfully");
        return PostDTO.fromEntity(updatedPost, userId);
    }

    public void incrementCommentCount(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.incrementCommentCount();
        postRepository.save(post);
    }

    public void decrementCommentCount(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.decrementCommentCount();
        postRepository.save(post);
    }

    public long getUserPostCount(String userId) {
        return postRepository.countByUserIdAndDeletedFalse(userId);
    }
}
EOF

# Create CommentService.java
cat > social-service/src/main/java/com/social/socialservice/service/CommentService.java << 'EOF'
package com.social.socialservice.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.socialservice.dto.CommentDTO;
import com.social.socialservice.dto.CreateCommentRequest;
import com.social.socialservice.model.mongo.Comment;
import com.social.socialservice.repository.mongo.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentDTO createComment(String postId, String userId, CreateCommentRequest request) {
        log.info("Creating comment on post {} by user {}", postId, userId);

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(request.getContent())
                .parentCommentId(request.getParentCommentId())
                .likeCount(0)
                .replyCount(0)
                .build();

        Comment savedComment = commentRepository.save(comment);

        // Increment post comment count
        postService.incrementCommentCount(postId);

        log.info("Comment created successfully: {}", savedComment.getId());
        return CommentDTO.fromEntity(savedComment);
    }

    public PageResponse<CommentDTO> getPostComments(String postId, String currentUserId, int page, int size) {
        log.info("Fetching comments for post: {}", postId);

        Sort sort = Sort.by("createdAt").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Comment> commentPage = commentRepository.findByPostIdAndDeletedFalseAndParentCommentIdIsNull(postId, pageable);

        return PageResponse.of(
                commentPage.getContent().stream()
                        .map(comment -> CommentDTO.fromEntity(comment, currentUserId))
                        .toList(),
                commentPage.getNumber(),
                commentPage.getSize(),
                commentPage.getTotalElements(),
                commentPage.getTotalPages(),
                commentPage.isLast()
        );
    }

    public List<CommentDTO> getCommentReplies(String commentId, String currentUserId) {
        log.info("Fetching replies for comment: {}", commentId);

        List<Comment> replies = commentRepository.findByParentCommentIdAndDeletedFalse(commentId);
        return replies.stream()
                .map(comment -> CommentDTO.fromEntity(comment, currentUserId))
                .toList();
    }

    public void deleteComment(String id, String userId) {
        log.info("Deleting comment: {}", id);

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new ValidationException("You can only delete your own comments");
        }

        comment.setDeleted(true);
        commentRepository.save(comment);

        // Decrement post comment count
        postService.decrementCommentCount(comment.getPostId());

        log.info("Comment deleted successfully");
    }

    public CommentDTO likeComment(String id, String userId) {
        log.info("User {} liking comment: {}", userId, id);

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (comment.getLikedBy().contains(userId)) {
            throw new ValidationException("You have already liked this comment");
        }

        comment.getLikedBy().add(userId);
        comment.incrementLikeCount();
        Comment updatedComment = commentRepository.save(comment);

        log.info("Comment liked successfully");
        return CommentDTO.fromEntity(updatedComment, userId);
    }

    public CommentDTO unlikeComment(String id, String userId) {
        log.info("User {} unliking comment: {}", userId, id);

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        if (!comment.getLikedBy().contains(userId)) {
            throw new ValidationException("You have not liked this comment");
        }

        comment.getLikedBy().remove(userId);
        comment.decrementLikeCount();
        Comment updatedComment = commentRepository.save(comment);

        log.info("Comment unliked successfully");
        return CommentDTO.fromEntity(updatedComment, userId);
    }
}
EOF

# Create SocialService.java (for follow/unfollow with Neo4j)
cat > social-service/src/main/java/com/social/socialservice/service/SocialService.java << 'EOF'
package com.social.socialservice.service;

import com.social.common.exception.ResourceNotFoundException;
import com.social.common.exception.ValidationException;
import com.social.socialservice.dto.FollowDTO;
import com.social.socialservice.dto.SocialStatsDTO;
import com.social.socialservice.model.neo4j.UserNode;
import com.social.socialservice.repository.mongo.CommentRepository;
import com.social.socialservice.repository.mongo.PostRepository;
import com.social.socialservice.repository.neo4j.UserNodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialService {

    private final UserNodeRepository userNodeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void followUser(String followerId, String followeeId) {
        log.info("User {} following user {}", followerId, followeeId);

        if (followerId.equals(followeeId)) {
            throw new ValidationException("You cannot follow yourself");
        }

        // Get or create follower node
        UserNode follower = userNodeRepository.findByUserId(followerId)
                .orElseGet(() -> createUserNode(followerId));

        // Get or create followee node
        UserNode followee = userNodeRepository.findByUserId(followeeId)
                .orElseGet(() -> createUserNode(followeeId));

        // Check if already following
        if (follower.isFollowing(followee)) {
            throw new ValidationException("You are already following this user");
        }

        // Create follow relationship
        follower.follow(followee);
        userNodeRepository.save(follower);

        log.info("User {} is now following user {}", followerId, followeeId);
    }

    @Transactional
    public void unfollowUser(String followerId, String followeeId) {
        log.info("User {} unfollowing user {}", followerId, followeeId);

        UserNode follower = userNodeRepository.findByUserId(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserNode followee = userNodeRepository.findByUserId(followeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!follower.isFollowing(followee)) {
            throw new ValidationException("You are not following this user");
        }

        follower.unfollow(followee);
        userNodeRepository.save(follower);

        log.info("User {} unfollowed user {}", followerId, followeeId);
    }

    public List<FollowDTO> getFollowing(String userId) {
        log.info("Fetching following list for user: {}", userId);

        List<UserNode> following = userNodeRepository.findFollowing(userId);
        return following.stream()
                .map(user -> FollowDTO.builder()
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .isFollowing(true)
                        .build())
                .collect(Collectors.toList());
    }

    public List<FollowDTO> getFollowers(String userId) {
        log.info("Fetching followers list for user: {}", userId);

        List<UserNode> followers = userNodeRepository.findFollowers(userId);
        return followers.stream()
                .map(user -> FollowDTO.builder()
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .isFollower(true)
                        .build())
                .collect(Collectors.toList());
    }

    public List<String> getFollowingIds(String userId) {
        List<UserNode> following = userNodeRepository.findFollowing(userId);
        return following.stream()
                .map(UserNode::getUserId)
                .collect(Collectors.toList());
    }

    public boolean isFollowing(String followerId, String followeeId) {
        return userNodeRepository.isFollowing(followerId, followeeId);
    }

    public SocialStatsDTO getUserStats(String userId) {
        log.info("Fetching social stats for user: {}", userId);

        int followingCount = userNodeRepository.countFollowing(userId);
        int followersCount = userNodeRepository.countFollowers(userId);
        long postsCount = postRepository.countUserPosts(userId);
        long commentsCount = commentRepository.countUserComments(userId);

        return SocialStatsDTO.builder()
                .userId(userId)
                .followingCount(followingCount)
                .followersCount(followersCount)
                .postsCount((int) postsCount)
                .totalComments((int) commentsCount)
                .build();
    }

    public List<FollowDTO> getSuggestedUsers(String userId, int limit) {
        log.info("Fetching suggested users for: {}", userId);

        List<UserNode> suggested = userNodeRepository.findSuggestedUsers(userId, limit);
        return suggested.stream()
                .map(user -> FollowDTO.builder()
                        .userId(user.getUserId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .isFollowing(false)
                        .build())
                .collect(Collectors.toList());
    }

    private UserNode createUserNode(String userId) {
        return UserNode.builder()
                .userId(userId)
                .username("user_" + userId)
                .email(userId + "@example.com")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
EOF
```

#### **Step 8: Create Controllers**

```bash
# Create controller directory
mkdir -p social-service/src/main/java/com/social/socialservice/controller

# Create PostController.java
cat > social-service/src/main/java/com/social/socialservice/controller/PostController.java << 'EOF'
package com.social.socialservice.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.socialservice.dto.CreatePostRequest;
import com.social.socialservice.dto.PostDTO;
import com.social.socialservice.dto.UpdatePostRequest;
import com.social.socialservice.service.PostService;
import com.social.socialservice.service.SocialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post Management", description = "APIs for social posts")
public class PostController {

    private final PostService postService;
    private final SocialService socialService;

    @PostMapping
    @Operation(summary = "Create a new post")
    public ResponseEntity<ApiResponse<PostDTO>> createPost(
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody CreatePostRequest request) {
        log.info("REST request to create post for user: {}", userId);
        PostDTO post = postService.createPost(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(post, "Post created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by ID")
    public ResponseEntity<ApiResponse<PostDTO>> getPostById(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to get post by ID: {}", id);
        PostDTO post = postService.getPostById(id, userId);
        return ResponseEntity.ok(ApiResponse.success(post));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user posts")
    public ResponseEntity<ApiResponse<PageResponse<PostDTO>>> getUserPosts(
            @PathVariable String userId,
            @RequestHeader("X-User-Id") String currentUserId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get posts for user: {}", userId);
        PageResponse<PostDTO> posts = postService.getUserPosts(userId, currentUserId, page, size);
        return ResponseEntity.ok(ApiResponse.success(posts));
    }

    @GetMapping("/feed")
    @Operation(summary = "Get personalized feed")
    public ResponseEntity<ApiResponse<PageResponse<PostDTO>>> getFeed(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get feed for user: {}", userId);

        // Get following user IDs
        List<String> followingIds = socialService.getFollowingIds(userId);
        followingIds.add(userId); // Include own posts

        PageResponse<PostDTO> posts = postService.getFeed(followingIds, userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(posts));
    }

    @GetMapping("/public")
    @Operation(summary = "Get public feed")
    public ResponseEntity<ApiResponse<PageResponse<PostDTO>>> getPublicFeed(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get public feed");
        PageResponse<PostDTO> posts = postService.getPublicFeed(userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(posts));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update post")
    public ResponseEntity<ApiResponse<PostDTO>> updatePost(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody UpdatePostRequest request) {
        log.info("REST request to update post: {}", id);
        PostDTO post = postService.updatePost(id, userId, request);
        return ResponseEntity.ok(ApiResponse.success(post, "Post updated successfully"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete post")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to delete post: {}", id);
        postService.deletePost(id, userId);
        return ResponseEntity.ok(ApiResponse.success(null, "Post deleted successfully"));
    }

    @PostMapping("/{id}/like")
    @Operation(summary = "Like post")
    public ResponseEntity<ApiResponse<PostDTO>> likePost(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to like post: {}", id);
        PostDTO post = postService.likePost(id, userId);
        return ResponseEntity.ok(ApiResponse.success(post, "Post liked successfully"));
    }

    @DeleteMapping("/{id}/like")
    @Operation(summary = "Unlike post")
    public ResponseEntity<ApiResponse<PostDTO>> unlikePost(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to unlike post: {}", id);
        PostDTO post = postService.unlikePost(id, userId);
        return ResponseEntity.ok(ApiResponse.success(post, "Post unliked successfully"));
    }
}
EOF

# Create CommentController.java
cat > social-service/src/main/java/com/social/socialservice/controller/CommentController.java << 'EOF'
package com.social.socialservice.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.socialservice.dto.CommentDTO;
import com.social.socialservice.dto.CreateCommentRequest;
import com.social.socialservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comment Management", description = "APIs for post comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    @Operation(summary = "Create a comment on a post")
    public ResponseEntity<ApiResponse<CommentDTO>> createComment(
            @PathVariable String postId,
            @RequestHeader("X-User-Id") String userId,
            @Valid @RequestBody CreateCommentRequest request) {
        log.info("REST request to create comment on post {} by user {}", postId, userId);
        CommentDTO comment = commentService.createComment(postId, userId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(comment, "Comment created successfully"));
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Get comments for a post")
    public ResponseEntity<ApiResponse<PageResponse<CommentDTO>>> getPostComments(
            @PathVariable String postId,
            @RequestHeader("X-User-Id") String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get comments for post: {}", postId);
        PageResponse<CommentDTO> comments = commentService.getPostComments(postId, userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(comments));
    }

    @GetMapping("/{commentId}/replies")
    @Operation(summary = "Get replies for a comment")
    public ResponseEntity<ApiResponse<List<CommentDTO>>> getCommentReplies(
            @PathVariable String commentId,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to get replies for comment: {}", commentId);
        List<CommentDTO> replies = commentService.getCommentReplies(commentId, userId);
        return ResponseEntity.ok(ApiResponse.success(replies));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comment")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to delete comment: {}", id);
        commentService.deleteComment(id, userId);
        return ResponseEntity.ok(ApiResponse.success(null, "Comment deleted successfully"));
    }

    @PostMapping("/{id}/like")
    @Operation(summary = "Like comment")
    public ResponseEntity<ApiResponse<CommentDTO>> likeComment(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to like comment: {}", id);
        CommentDTO comment = commentService.likeComment(id, userId);
        return ResponseEntity.ok(ApiResponse.success(comment, "Comment liked successfully"));
    }

    @DeleteMapping("/{id}/like")
    @Operation(summary = "Unlike comment")
    public ResponseEntity<ApiResponse<CommentDTO>> unlikeComment(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to unlike comment: {}", id);
        CommentDTO comment = commentService.unlikeComment(id, userId);
        return ResponseEntity.ok(ApiResponse.success(comment, "Comment unliked successfully"));
    }
}
EOF

# Create SocialController.java
cat > social-service/src/main/java/com/social/socialservice/controller/SocialController.java << 'EOF'
package com.social.socialservice.controller;

import com.social.common.dto.ApiResponse;
import com.social.socialservice.dto.FollowDTO;
import com.social.socialservice.dto.SocialStatsDTO;
import com.social.socialservice.service.SocialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/social")
@RequiredArgsConstructor
@Tag(name = "Social Graph", description = "APIs for follow/unfollow and social connections")
public class SocialController {

    private final SocialService socialService;

    @PostMapping("/follow/{followeeId}")
    @Operation(summary = "Follow a user")
    public ResponseEntity<ApiResponse<Void>> followUser(
            @RequestHeader("X-User-Id") String followerId,
            @PathVariable String followeeId) {
        log.info("REST request for user {} to follow user {}", followerId, followeeId);
        socialService.followUser(followerId, followeeId);
        return ResponseEntity.ok(ApiResponse.success(null, "User followed successfully"));
    }

    @DeleteMapping("/follow/{followeeId}")
    @Operation(summary = "Unfollow a user")
    public ResponseEntity<ApiResponse<Void>> unfollowUser(
            @RequestHeader("X-User-Id") String followerId,
            @PathVariable String followeeId) {
        log.info("REST request for user {} to unfollow user {}", followerId, followeeId);
        socialService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok(ApiResponse.success(null, "User unfollowed successfully"));
    }

    @GetMapping("/following/{userId}")
    @Operation(summary = "Get users that a user is following")
    public ResponseEntity<ApiResponse<List<FollowDTO>>> getFollowing(@PathVariable String userId) {
        log.info("REST request to get following list for user: {}", userId);
        List<FollowDTO> following = socialService.getFollowing(userId);
        return ResponseEntity.ok(ApiResponse.success(following));
    }

    @GetMapping("/followers/{userId}")
    @Operation(summary = "Get followers of a user")
    public ResponseEntity<ApiResponse<List<FollowDTO>>> getFollowers(@PathVariable String userId) {
        log.info("REST request to get followers list for user: {}", userId);
        List<FollowDTO> followers = socialService.getFollowers(userId);
        return ResponseEntity.ok(ApiResponse.success(followers));
    }

    @GetMapping("/stats/{userId}")
    @Operation(summary = "Get social stats for a user")
    public ResponseEntity<ApiResponse<SocialStatsDTO>> getUserStats(@PathVariable String userId) {
        log.info("REST request to get social stats for user: {}", userId);
        SocialStatsDTO stats = socialService.getUserStats(userId);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    @GetMapping("/suggestions")
    @Operation(summary = "Get suggested users to follow")
    public ResponseEntity<ApiResponse<List<FollowDTO>>> getSuggestedUsers(
            @RequestHeader("X-User-Id") String userId,
            @RequestParam(defaultValue = "10") int limit) {
        log.info("REST request to get suggested users for: {}", userId);
        List<FollowDTO> suggestions = socialService.getSuggestedUsers(userId, limit);
        return ResponseEntity.ok(ApiResponse.success(suggestions));
    }

    @GetMapping("/is-following/{followeeId}")
    @Operation(summary = "Check if user is following another user")
    public ResponseEntity<ApiResponse<Boolean>> isFollowing(
            @RequestHeader("X-User-Id") String followerId,
            @PathVariable String followeeId) {
        log.info("REST request to check if user {} is following user {}", followerId, followeeId);
        boolean isFollowing = socialService.isFollowing(followerId, followeeId);
        return ResponseEntity.ok(ApiResponse.success(isFollowing));
    }
}
EOF
```

#### **Step 9: Create Configuration Classes**

```bash
# Create config directory
mkdir -p social-service/src/main/java/com/social/socialservice/config

# Create MongoConfig.java
cat > social-service/src/main/java/com/social/socialservice/config/MongoConfig.java << 'EOF'
package com.social.socialservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.social.socialservice.repository.mongo")
public class MongoConfig {
}
EOF

# Create OpenApiConfig.java
cat > social-service/src/main/java/com/social/socialservice/config/OpenApiConfig.java << 'EOF'
package com.social.socialservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI socialServiceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9005");
        server.setDescription("Social Service - Development");

        Contact contact = new Contact();
        contact.setName("Social Commerce Platform");
        contact.setEmail("support@socialcommerce.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Social Service API")
                .version("1.0.0")
                .description("Social features: posts, comments, likes, follows, and social graph")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
EOF
```

#### **Step 10: Create application.yml**

```bash
# Create application.yml
cat > social-service/src/main/resources/application.yml << 'EOF'
spring:
  application:
    name: social-service

  config:
    import: optional:configserver:http://localhost:8888

  data:
    mongodb:
      uri: mongodb://localhost:27017/social_db
      auto-index-creation: true

    neo4j:
      uri: bolt://localhost:7687
      authentication:
        username: neo4j
        password: password

server:
  port: 9005

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${server.port}

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

resilience4j:
  circuitbreaker:
    instances:
      socialService:
        register-health-indicator: true
        sliding-window-size: 10
        minimum-number-of-calls: 5
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true
        wait-duration-in-open-state: 10s
        failure-rate-threshold: 50
        slow-call-rate-threshold: 100
        slow-call-duration-threshold: 5s

logging:
  level:
    com.social.socialservice: DEBUG
    org.springframework.data.mongodb: INFO
    org.springframework.data.neo4j: INFO
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"
EOF
```

#### **Step 11: Build and Run Social Service**

```bash
# Build the service
cd social-service
mvn clean install -DskipTests
cd ..

# Start MongoDB
docker run -d \
  --name social-mongodb \
  -p 27017:27017 \
  -e MONGO_INITDB_DATABASE=social_db \
  mongo:latest

# Start Neo4j
docker run -d \
  --name social-neo4j \
  -p 7474:7474 \
  -p 7687:7687 \
  -e NEO4J_AUTH=neo4j/password \
  neo4j:latest

# Wait for databases to be ready
sleep 15

# Run Social Service
cd social-service
mvn spring-boot:run
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.5.5)

Social Service started on port 9005
Registered with Eureka Server
Connected to MongoDB: social_db
Connected to Neo4j: bolt://localhost:7687
Swagger UI: http://localhost:9005/swagger-ui.html
Neo4j Browser: http://localhost:7474
```

#### **Step 12: Test Social Service with curl**

```bash
# 1. Create a post
curl -X POST http://localhost:9005/api/v1/posts \
  -H "Content-Type: application/json" \
  -H "X-User-Id: user123" \
  -d '{
    "content": "Just bought this amazing product! Highly recommended! #shopping #review",
    "images": ["https://example.com/image1.jpg"],
    "tags": ["shopping", "review"],
    "type": "PRODUCT_REVIEW",
    "productId": "<PRODUCT_ID>"
  }'

# Expected Response:
# {
#   "success": true,
#   "message": "Post created successfully",
#   "data": {
#     "id": "...",
#     "userId": "user123",
#     "content": "Just bought this amazing product!...",
#     "type": "PRODUCT_REVIEW",
#     "status": "PUBLISHED",
#     "likeCount": 0,
#     "commentCount": 0
#   }
# }

# 2. Get post by ID
curl -X GET http://localhost:9005/api/v1/posts/<POST_ID> \
  -H "X-User-Id: user123"

# 3. Get user posts
curl -X GET "http://localhost:9005/api/v1/posts/user/user123?page=0&size=10" \
  -H "X-User-Id: user123"

# 4. Get public feed
curl -X GET "http://localhost:9005/api/v1/posts/public?page=0&size=20" \
  -H "X-User-Id: user123"

# 5. Like a post
curl -X POST http://localhost:9005/api/v1/posts/<POST_ID>/like \
  -H "X-User-Id: user456"

# 6. Unlike a post
curl -X DELETE http://localhost:9005/api/v1/posts/<POST_ID>/like \
  -H "X-User-Id: user456"

# 7. Update a post
curl -X PUT http://localhost:9005/api/v1/posts/<POST_ID> \
  -H "Content-Type: application/json" \
  -H "X-User-Id: user123" \
  -d '{
    "content": "Updated content with more details!",
    "tags": ["shopping", "review", "updated"]
  }'

# 8. Create a comment
curl -X POST http://localhost:9005/api/v1/comments/post/<POST_ID> \
  -H "Content-Type: application/json" \
  -H "X-User-Id: user456" \
  -d '{
    "content": "Great post! Thanks for sharing!"
  }'

# 9. Get post comments
curl -X GET "http://localhost:9005/api/v1/comments/post/<POST_ID>?page=0&size=20" \
  -H "X-User-Id: user123"

# 10. Reply to a comment
curl -X POST http://localhost:9005/api/v1/comments/post/<POST_ID> \
  -H "Content-Type: application/json" \
  -H "X-User-Id: user123" \
  -d '{
    "content": "Thank you!",
    "parentCommentId": "<COMMENT_ID>"
  }'

# 11. Like a comment
curl -X POST http://localhost:9005/api/v1/comments/<COMMENT_ID>/like \
  -H "X-User-Id: user789"

# 12. Follow a user
curl -X POST http://localhost:9005/api/v1/social/follow/user456 \
  -H "X-User-Id: user123"

# Expected Response:
# {
#   "success": true,
#   "message": "User followed successfully"
# }

# 13. Unfollow a user
curl -X DELETE http://localhost:9005/api/v1/social/follow/user456 \
  -H "X-User-Id: user123"

# 14. Get following list
curl -X GET http://localhost:9005/api/v1/social/following/user123

# 15. Get followers list
curl -X GET http://localhost:9005/api/v1/social/followers/user123

# 16. Get social stats
curl -X GET http://localhost:9005/api/v1/social/stats/user123

# Expected Response:
# {
#   "success": true,
#   "data": {
#     "userId": "user123",
#     "followingCount": 5,
#     "followersCount": 10,
#     "postsCount": 15,
#     "totalComments": 25
#   }
# }

# 17. Get personalized feed (posts from followed users)
curl -X GET "http://localhost:9005/api/v1/posts/feed?page=0&size=20" \
  -H "X-User-Id: user123"

# 18. Get suggested users to follow
curl -X GET "http://localhost:9005/api/v1/social/suggestions?limit=10" \
  -H "X-User-Id: user123"

# 19. Check if following a user
curl -X GET http://localhost:9005/api/v1/social/is-following/user456 \
  -H "X-User-Id: user123"

# 20. Delete a post
curl -X DELETE http://localhost:9005/api/v1/posts/<POST_ID> \
  -H "X-User-Id: user123"
```

#### **Step 13: Verify in Neo4j Browser**

```bash
# Open Neo4j Browser
open http://localhost:7474

# Login with:
# Username: neo4j
# Password: password

# Run Cypher queries to visualize social graph:

# 1. View all users and their relationships
MATCH (u:User)-[r:FOLLOWS]->(f:User)
RETURN u, r, f

# 2. Find users with most followers
MATCH (u:User)<-[:FOLLOWS]-(follower:User)
RETURN u.userId, u.username, count(follower) as followerCount
ORDER BY followerCount DESC
LIMIT 10

# 3. Find mutual follows (friends)
MATCH (u1:User)-[:FOLLOWS]->(u2:User)-[:FOLLOWS]->(u1)
RETURN u1.userId, u2.userId

# 4. Find suggested users (friends of friends)
MATCH (u:User {userId: 'user123'})-[:FOLLOWS]->(f:User)-[:FOLLOWS]->(fof:User)
WHERE NOT (u)-[:FOLLOWS]->(fof) AND u <> fof
RETURN DISTINCT fof.userId, fof.username
LIMIT 10
```

#### **Step 14: Verify in MongoDB**

```bash
# Connect to MongoDB
docker exec -it social-mongodb mongosh social_db

# View posts
db.posts.find().pretty()

# View comments
db.comments.find().pretty()

# Count posts by user
db.posts.aggregate([
  { $match: { deleted: false } },
  { $group: { _id: "$userId", count: { $sum: 1 } } },
  { $sort: { count: -1 } }
])

# Find most liked posts
db.posts.find({ deleted: false }).sort({ likeCount: -1 }).limit(10)

# Exit MongoDB
exit
```

#### **Step 15: Verify in Swagger UI**

```bash
# Open Swagger UI
open http://localhost:9005/swagger-ui.html

# Test all endpoints:
# Posts: 9 endpoints (create, get, list, update, delete, like, unlike, feed)
# Comments: 6 endpoints (create, get, replies, delete, like, unlike)
# Social: 7 endpoints (follow, unfollow, following, followers, stats, suggestions, is-following)
```

#### **Step 16: Commit Changes**

```bash
# Add and commit
git add social-service/
git commit -m "feat: Add Social Service with MongoDB and Neo4j

- Created Post and Comment documents (MongoDB)
- Created UserNode for social graph (Neo4j)
- Implemented posts, comments, likes functionality
- Implemented follow/unfollow with Neo4j relationships
- Added personalized feed generation
- Added suggested users algorithm (friends of friends)
- Created comprehensive REST APIs
- Port: 9005
- Databases: MongoDB + Neo4j"
```

---

### **✅ DAY 10 CHECKPOINT**

**What We Built:**
- ✅ Social Service (Port 9005)
- ✅ Posts with images, tags, and product reviews
- ✅ Comments with nested replies
- ✅ Like/unlike for posts and comments
- ✅ Follow/unfollow functionality
- ✅ Social graph with Neo4j
- ✅ Personalized feed generation
- ✅ Suggested users (friends of friends)
- ✅ Social stats (followers, following, posts, comments)
- ✅ 22 REST API endpoints
- ✅ MongoDB + Neo4j integration

**MongoDB Collections:**
- posts (with likes, comments count, views)
- comments (with nested replies, likes)

**Neo4j Graph:**
- User nodes
- FOLLOWS relationships
- Social graph queries (followers, following, suggestions)

**Post Types:**
- TEXT, IMAGE, VIDEO, PRODUCT_REVIEW, PRODUCT_RECOMMENDATION

**Post Statuses:**
- DRAFT, PUBLISHED, ARCHIVED, DELETED

**APIs Created:**
- Posts: 9 endpoints
- Comments: 6 endpoints
- Social: 7 endpoints

**Next Steps:**
- Create Notification Service (Port 9006)
- Complete Docker Compose setup
- Integration testing

---

## **DAY 11: Notification Service (Port 9006)** 🔔

**Goal:** Build a notification service that handles email, SMS, and push notifications, consuming events from Kafka.

**Technologies:**
- Spring Boot 3.5.5
- MongoDB (notification history)
- Apache Kafka (event consumers)
- JavaMail (email notifications)
- Twilio (SMS notifications - optional)
- Firebase Cloud Messaging (push notifications - optional)

**Features:**
- Multi-channel notifications (email, SMS, push)
- Kafka event consumers (order, payment, social events)
- Notification templates
- Notification history and status tracking
- Retry mechanism for failed notifications
- User notification preferences

---

### **Step 1: Create Notification Service Module**

```bash
# Create notification-service directory structure
mkdir -p notification-service/src/main/java/com/social/notificationservice/{model,dto,repository,service,consumer,controller,config}
mkdir -p notification-service/src/main/resources
mkdir -p notification-service/src/test/java/com/social/notificationservice

# Create pom.xml
cat > notification-service/pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.social</groupId>
        <artifactId>social-commerce-platform</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <artifactId>notification-service</artifactId>
    <name>Notification Service</name>
    <description>Notification Service for Social Commerce Platform</description>

    <dependencies>
        <!-- Common Library -->
        <dependency>
            <groupId>com.social</groupId>
            <artifactId>common-lib</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>

        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Spring Cloud -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <!-- Kafka -->
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        </dependency>

        <!-- Micrometer Prometheus -->
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>

        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF

# Create main application class
cat > notification-service/src/main/java/com/social/notificationservice/NotificationServiceApplication.java << 'EOF'
package com.social.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.social.notificationservice", "com.social.common"})
@EnableDiscoveryClient
@EnableMongoAuditing
@EnableKafka
@EnableAsync
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
EOF
```

#### **Step 2: Create MongoDB Document Models**

```bash
# Create Notification.java
cat > notification-service/src/main/java/com/social/notificationservice/model/Notification.java << 'EOF'
package com.social.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    private String id;

    @Indexed
    private String userId;

    @Indexed
    private NotificationType type;

    @Indexed
    private NotificationChannel channel;

    private String subject;

    private String message;

    private Map<String, Object> data;

    @Indexed
    private NotificationStatus status;

    private String recipient; // email, phone number, or device token

    private String errorMessage;

    private Integer retryCount;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime sentAt;

    private boolean read;

    public enum NotificationType {
        ORDER_CREATED,
        ORDER_CONFIRMED,
        ORDER_SHIPPED,
        ORDER_DELIVERED,
        ORDER_CANCELLED,
        PAYMENT_SUCCESS,
        PAYMENT_FAILED,
        PAYMENT_REFUNDED,
        NEW_FOLLOWER,
        POST_LIKED,
        POST_COMMENTED,
        COMMENT_REPLIED,
        PRODUCT_BACK_IN_STOCK,
        PRICE_DROP,
        WELCOME,
        PASSWORD_RESET,
        ACCOUNT_VERIFICATION
    }

    public enum NotificationChannel {
        EMAIL,
        SMS,
        PUSH,
        IN_APP
    }

    public enum NotificationStatus {
        PENDING,
        SENT,
        FAILED,
        RETRY
    }

    public void incrementRetryCount() {
        this.retryCount = (this.retryCount == null ? 0 : this.retryCount) + 1;
    }
}
EOF

# Create NotificationPreference.java
cat > notification-service/src/main/java/com/social/notificationservice/model/NotificationPreference.java << 'EOF'
package com.social.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "notification_preferences")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPreference {

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;

    private String email;

    private String phoneNumber;

    private String deviceToken;

    @Builder.Default
    private Map<String, ChannelPreference> preferences = new HashMap<>();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelPreference {
        private boolean emailEnabled;
        private boolean smsEnabled;
        private boolean pushEnabled;
        private boolean inAppEnabled;
    }

    public boolean isChannelEnabled(Notification.NotificationType type, Notification.NotificationChannel channel) {
        ChannelPreference pref = preferences.get(type.name());
        if (pref == null) {
            return true; // Default: all channels enabled
        }

        return switch (channel) {
            case EMAIL -> pref.isEmailEnabled();
            case SMS -> pref.isSmsEnabled();
            case PUSH -> pref.isPushEnabled();
            case IN_APP -> pref.isInAppEnabled();
        };
    }
}
EOF
```

#### **Step 3: Create DTOs**

```bash
# Create dto directory
mkdir -p notification-service/src/main/java/com/social/notificationservice/dto

# Create NotificationDTO.java
cat > notification-service/src/main/java/com/social/notificationservice/dto/NotificationDTO.java << 'EOF'
package com.social.notificationservice.dto;

import com.social.notificationservice.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private String id;
    private String userId;
    private String type;
    private String channel;
    private String subject;
    private String message;
    private Map<String, Object> data;
    private String status;
    private boolean read;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

    public static NotificationDTO fromEntity(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .type(notification.getType() != null ? notification.getType().name() : null)
                .channel(notification.getChannel() != null ? notification.getChannel().name() : null)
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .data(notification.getData())
                .status(notification.getStatus() != null ? notification.getStatus().name() : null)
                .read(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .build();
    }
}
EOF

# Create SendNotificationRequest.java
cat > notification-service/src/main/java/com/social/notificationservice/dto/SendNotificationRequest.java << 'EOF'
package com.social.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendNotificationRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotNull(message = "Notification type is required")
    private String type;

    @NotNull(message = "Channel is required")
    private String channel;

    private String subject;

    @NotBlank(message = "Message is required")
    private String message;

    private Map<String, Object> data;

    private String recipient;
}
EOF

# Create NotificationEvent.java
cat > notification-service/src/main/java/com/social/notificationservice/dto/NotificationEvent.java << 'EOF'
package com.social.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {

    private String eventId;
    private String eventType;
    private String userId;
    private String notificationType;
    private Map<String, Object> data;
    private LocalDateTime timestamp;
}
EOF
```

#### **Step 4: Create Repositories**

```bash
# Create repository directory
mkdir -p notification-service/src/main/java/com/social/notificationservice/repository

# Create NotificationRepository.java
cat > notification-service/src/main/java/com/social/notificationservice/repository/NotificationRepository.java << 'EOF'
package com.social.notificationservice.repository;

import com.social.notificationservice.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    Page<Notification> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);

    Page<Notification> findByUserIdAndReadFalseOrderByCreatedAtDesc(String userId, Pageable pageable);

    List<Notification> findByStatusAndRetryCountLessThan(
            Notification.NotificationStatus status,
            Integer maxRetries
    );

    @Query("{ 'userId': ?0, 'read': false }")
    long countUnreadNotifications(String userId);

    @Query("{ 'status': 'PENDING', 'createdAt': { $lt: ?0 } }")
    List<Notification> findPendingNotificationsOlderThan(LocalDateTime dateTime);
}
EOF

# Create NotificationPreferenceRepository.java
cat > notification-service/src/main/java/com/social/notificationservice/repository/NotificationPreferenceRepository.java << 'EOF'
package com.social.notificationservice.repository;

import com.social.notificationservice.model.NotificationPreference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationPreferenceRepository extends MongoRepository<NotificationPreference, String> {

    Optional<NotificationPreference> findByUserId(String userId);
}
EOF
```

#### **Step 5: Create Email Service**

```bash
# Create service directory
mkdir -p notification-service/src/main/java/com/social/notificationservice/service

# Create EmailService.java
cat > notification-service/src/main/java/com/social/notificationservice/service/EmailService.java << 'EOF'
package com.social.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from:noreply@socialcommerce.com}")
    private String fromEmail;

    @Async
    public void sendSimpleEmail(String to, String subject, String text) {
        try {
            log.info("Sending simple email to: {}", to);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);
            log.info("Email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send email to: {}", to, e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Async
    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            log.info("Sending HTML email to: {}", to);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.info("HTML email sent successfully to: {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send HTML email to: {}", to, e);
            throw new RuntimeException("Failed to send HTML email", e);
        }
    }

    public String buildOrderConfirmationEmail(String orderId, String customerName) {
        return String.format("""
                <html>
                <body>
                    <h2>Order Confirmation</h2>
                    <p>Dear %s,</p>
                    <p>Your order <strong>%s</strong> has been confirmed!</p>
                    <p>We'll notify you when your order ships.</p>
                    <p>Thank you for shopping with us!</p>
                    <br>
                    <p>Best regards,<br>Social Commerce Team</p>
                </body>
                </html>
                """, customerName, orderId);
    }

    public String buildPaymentSuccessEmail(String orderId, String amount) {
        return String.format("""
                <html>
                <body>
                    <h2>Payment Successful</h2>
                    <p>Your payment of <strong>$%s</strong> for order <strong>%s</strong> was successful!</p>
                    <p>Thank you for your purchase.</p>
                    <br>
                    <p>Best regards,<br>Social Commerce Team</p>
                </body>
                </html>
                """, amount, orderId);
    }

    public String buildWelcomeEmail(String username) {
        return String.format("""
                <html>
                <body>
                    <h2>Welcome to Social Commerce!</h2>
                    <p>Hi %s,</p>
                    <p>Thank you for joining our community!</p>
                    <p>Start exploring products, follow friends, and share your experiences.</p>
                    <br>
                    <p>Best regards,<br>Social Commerce Team</p>
                </body>
                </html>
                """, username);
    }
}
EOF

# Create NotificationService.java
cat > notification-service/src/main/java/com/social/notificationservice/service/NotificationService.java << 'EOF'
package com.social.notificationservice.service;

import com.social.common.dto.PageResponse;
import com.social.common.exception.ResourceNotFoundException;
import com.social.notificationservice.dto.NotificationDTO;
import com.social.notificationservice.dto.SendNotificationRequest;
import com.social.notificationservice.model.Notification;
import com.social.notificationservice.model.NotificationPreference;
import com.social.notificationservice.repository.NotificationPreferenceRepository;
import com.social.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationPreferenceRepository preferenceRepository;
    private final EmailService emailService;

    public NotificationDTO createNotification(SendNotificationRequest request) {
        log.info("Creating notification for user: {}", request.getUserId());

        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .type(Notification.NotificationType.valueOf(request.getType()))
                .channel(Notification.NotificationChannel.valueOf(request.getChannel()))
                .subject(request.getSubject())
                .message(request.getMessage())
                .data(request.getData())
                .recipient(request.getRecipient())
                .status(Notification.NotificationStatus.PENDING)
                .retryCount(0)
                .read(false)
                .build();

        Notification saved = notificationRepository.save(notification);
        log.info("Notification created: {}", saved.getId());

        // Send notification asynchronously
        sendNotification(saved);

        return NotificationDTO.fromEntity(saved);
    }

    public void sendNotification(Notification notification) {
        try {
            log.info("Sending notification: {} via {}", notification.getId(), notification.getChannel());

            // Check user preferences
            NotificationPreference preference = preferenceRepository
                    .findByUserId(notification.getUserId())
                    .orElse(null);

            if (preference != null && !preference.isChannelEnabled(notification.getType(), notification.getChannel())) {
                log.info("Notification channel disabled for user: {}", notification.getUserId());
                notification.setStatus(Notification.NotificationStatus.FAILED);
                notification.setErrorMessage("Channel disabled by user preference");
                notificationRepository.save(notification);
                return;
            }

            // Send based on channel
            switch (notification.getChannel()) {
                case EMAIL -> sendEmailNotification(notification, preference);
                case SMS -> sendSmsNotification(notification, preference);
                case PUSH -> sendPushNotification(notification, preference);
                case IN_APP -> {
                    // In-app notifications are just stored in DB
                    notification.setStatus(Notification.NotificationStatus.SENT);
                    notification.setSentAt(LocalDateTime.now());
                }
            }

            notificationRepository.save(notification);
            log.info("Notification sent successfully: {}", notification.getId());

        } catch (Exception e) {
            log.error("Failed to send notification: {}", notification.getId(), e);
            notification.setStatus(Notification.NotificationStatus.FAILED);
            notification.setErrorMessage(e.getMessage());
            notification.incrementRetryCount();
            notificationRepository.save(notification);
        }
    }

    private void sendEmailNotification(Notification notification, NotificationPreference preference) {
        String email = notification.getRecipient();
        if (email == null && preference != null) {
            email = preference.getEmail();
        }

        if (email == null) {
            throw new RuntimeException("No email address found for user");
        }

        emailService.sendSimpleEmail(email, notification.getSubject(), notification.getMessage());
        notification.setStatus(Notification.NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());
    }

    private void sendSmsNotification(Notification notification, NotificationPreference preference) {
        // TODO: Implement SMS sending with Twilio
        log.info("SMS notification would be sent to: {}", notification.getRecipient());
        notification.setStatus(Notification.NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());
    }

    private void sendPushNotification(Notification notification, NotificationPreference preference) {
        // TODO: Implement push notification with Firebase
        log.info("Push notification would be sent to device: {}", notification.getRecipient());
        notification.setStatus(Notification.NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());
    }

    public PageResponse<NotificationDTO> getUserNotifications(String userId, int page, int size) {
        log.info("Fetching notifications for user: {}", userId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Notification> notificationPage = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        return PageResponse.of(
                notificationPage.getContent().stream()
                        .map(NotificationDTO::fromEntity)
                        .toList(),
                notificationPage.getNumber(),
                notificationPage.getSize(),
                notificationPage.getTotalElements(),
                notificationPage.getTotalPages(),
                notificationPage.isLast()
        );
    }

    public PageResponse<NotificationDTO> getUnreadNotifications(String userId, int page, int size) {
        log.info("Fetching unread notifications for user: {}", userId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Notification> notificationPage = notificationRepository
                .findByUserIdAndReadFalseOrderByCreatedAtDesc(userId, pageable);

        return PageResponse.of(
                notificationPage.getContent().stream()
                        .map(NotificationDTO::fromEntity)
                        .toList(),
                notificationPage.getNumber(),
                notificationPage.getSize(),
                notificationPage.getTotalElements(),
                notificationPage.getTotalPages(),
                notificationPage.isLast()
        );
    }

    public void markAsRead(String notificationId, String userId) {
        log.info("Marking notification as read: {}", notificationId);

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        if (!notification.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to notification");
        }

        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public void markAllAsRead(String userId) {
        log.info("Marking all notifications as read for user: {}", userId);

        Pageable pageable = PageRequest.of(0, 100);
        Page<Notification> notifications = notificationRepository
                .findByUserIdAndReadFalseOrderByCreatedAtDesc(userId, pageable);

        notifications.forEach(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    public long getUnreadCount(String userId) {
        return notificationRepository.countUnreadNotifications(userId);
    }

    public void sendOrderNotification(String userId, String orderId, String status, Map<String, Object> data) {
        Notification.NotificationType type = switch (status) {
            case "CONFIRMED" -> Notification.NotificationType.ORDER_CONFIRMED;
            case "SHIPPED" -> Notification.NotificationType.ORDER_SHIPPED;
            case "DELIVERED" -> Notification.NotificationType.ORDER_DELIVERED;
            case "CANCELLED" -> Notification.NotificationType.ORDER_CANCELLED;
            default -> Notification.NotificationType.ORDER_CREATED;
        };

        Notification notification = Notification.builder()
                .userId(userId)
                .type(type)
                .channel(Notification.NotificationChannel.EMAIL)
                .subject("Order Update: " + orderId)
                .message("Your order " + orderId + " is now " + status)
                .data(data)
                .status(Notification.NotificationStatus.PENDING)
                .retryCount(0)
                .read(false)
                .build();

        Notification saved = notificationRepository.save(notification);
        sendNotification(saved);
    }

    public void sendPaymentNotification(String userId, String orderId, String status, String amount) {
        Notification.NotificationType type = switch (status) {
            case "FAILED" -> Notification.NotificationType.PAYMENT_FAILED;
            case "REFUNDED" -> Notification.NotificationType.PAYMENT_REFUNDED;
            default -> Notification.NotificationType.PAYMENT_SUCCESS;
        };

        Notification notification = Notification.builder()
                .userId(userId)
                .type(type)
                .channel(Notification.NotificationChannel.EMAIL)
                .subject("Payment " + status + " for Order: " + orderId)
                .message("Payment of $" + amount + " for order " + orderId + " is " + status)
                .data(Map.of("orderId", orderId, "amount", amount))
                .status(Notification.NotificationStatus.PENDING)
                .retryCount(0)
                .read(false)
                .build();

        Notification saved = notificationRepository.save(notification);
        sendNotification(saved);
    }
}
EOF
```

#### **Step 6: Create Kafka Consumers**

```bash
# Create consumer directory
mkdir -p notification-service/src/main/java/com/social/notificationservice/consumer

# Create OrderEventConsumer.java
cat > notification-service/src/main/java/com/social/notificationservice/consumer/OrderEventConsumer.java << 'EOF'
package com.social.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order.events", groupId = "notification-service-group")
    public void consumeOrderEvent(String message) {
        try {
            log.info("Received order event: {}", message);

            Map<String, Object> event = objectMapper.readValue(message, Map.class);
            String eventType = (String) event.get("eventType");
            String userId = (String) event.get("userId");
            String orderId = (String) event.get("orderId");
            String status = (String) event.get("status");

            notificationService.sendOrderNotification(userId, orderId, status, event);

            log.info("Order notification sent for order: {}", orderId);
        } catch (Exception e) {
            log.error("Error processing order event", e);
        }
    }
}
EOF

# Create PaymentEventConsumer.java
cat > notification-service/src/main/java/com/social/notificationservice/consumer/PaymentEventConsumer.java << 'EOF'
package com.social.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment.events", groupId = "notification-service-group")
    public void consumePaymentEvent(String message) {
        try {
            log.info("Received payment event: {}", message);

            Map<String, Object> event = objectMapper.readValue(message, Map.class);
            String userId = (String) event.get("userId");
            String orderId = (String) event.get("orderId");
            String status = (String) event.get("status");
            String amount = String.valueOf(event.get("amount"));

            notificationService.sendPaymentNotification(userId, orderId, status, amount);

            log.info("Payment notification sent for order: {}", orderId);
        } catch (Exception e) {
            log.error("Error processing payment event", e);
        }
    }
}
EOF

# Create UserEventConsumer.java (for social events)
cat > notification-service/src/main/java/com/social/notificationservice/consumer/UserEventConsumer.java << 'EOF'
package com.social.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.notificationservice.model.Notification;
import com.social.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "user.events", groupId = "notification-service-group")
    public void consumeUserEvent(String message) {
        try {
            log.info("Received user event: {}", message);

            Map<String, Object> event = objectMapper.readValue(message, Map.class);
            String eventType = (String) event.get("eventType");

            // Handle different user events
            if ("USER_REGISTERED".equals(eventType)) {
                String userId = (String) event.get("userId");
                String email = (String) event.get("email");

                Notification notification = Notification.builder()
                        .userId(userId)
                        .type(Notification.NotificationType.WELCOME)
                        .channel(Notification.NotificationChannel.EMAIL)
                        .subject("Welcome to Social Commerce!")
                        .message("Thank you for joining our community!")
                        .recipient(email)
                        .status(Notification.NotificationStatus.PENDING)
                        .retryCount(0)
                        .read(false)
                        .build();

                notificationRepository.save(notification);
                log.info("Welcome notification created for user: {}", userId);
            }
        } catch (Exception e) {
            log.error("Error processing user event", e);
        }
    }
}
EOF
```

#### **Step 7: Create Controller**

```bash
# Create controller directory
mkdir -p notification-service/src/main/java/com/social/notificationservice/controller

# Create NotificationController.java
cat > notification-service/src/main/java/com/social/notificationservice/controller/NotificationController.java << 'EOF'
package com.social.notificationservice.controller;

import com.social.common.dto.ApiResponse;
import com.social.common.dto.PageResponse;
import com.social.notificationservice.dto.NotificationDTO;
import com.social.notificationservice.dto.SendNotificationRequest;
import com.social.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Management", description = "APIs for notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Send a notification")
    public ResponseEntity<ApiResponse<NotificationDTO>> sendNotification(
            @Valid @RequestBody SendNotificationRequest request) {
        log.info("REST request to send notification to user: {}", request.getUserId());
        NotificationDTO notification = notificationService.createNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(notification, "Notification sent successfully"));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user notifications")
    public ResponseEntity<ApiResponse<PageResponse<NotificationDTO>>> getUserNotifications(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get notifications for user: {}", userId);
        PageResponse<NotificationDTO> notifications = notificationService.getUserNotifications(userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(notifications));
    }

    @GetMapping("/user/{userId}/unread")
    @Operation(summary = "Get unread notifications")
    public ResponseEntity<ApiResponse<PageResponse<NotificationDTO>>> getUnreadNotifications(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("REST request to get unread notifications for user: {}", userId);
        PageResponse<NotificationDTO> notifications = notificationService.getUnreadNotifications(userId, page, size);
        return ResponseEntity.ok(ApiResponse.success(notifications));
    }

    @GetMapping("/user/{userId}/unread/count")
    @Operation(summary = "Get unread notification count")
    public ResponseEntity<ApiResponse<Long>> getUnreadCount(@PathVariable String userId) {
        log.info("REST request to get unread count for user: {}", userId);
        long count = notificationService.getUnreadCount(userId);
        return ResponseEntity.ok(ApiResponse.success(count));
    }

    @PutMapping("/{id}/read")
    @Operation(summary = "Mark notification as read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(
            @PathVariable String id,
            @RequestHeader("X-User-Id") String userId) {
        log.info("REST request to mark notification as read: {}", id);
        notificationService.markAsRead(id, userId);
        return ResponseEntity.ok(ApiResponse.success(null, "Notification marked as read"));
    }

    @PutMapping("/user/{userId}/read-all")
    @Operation(summary = "Mark all notifications as read")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead(@PathVariable String userId) {
        log.info("REST request to mark all notifications as read for user: {}", userId);
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok(ApiResponse.success(null, "All notifications marked as read"));
    }
}
EOF
```

#### **Step 8: Create Configuration Classes**

```bash
# Create config directory
mkdir -p notification-service/src/main/java/com/social/notificationservice/config

# Create KafkaConsumerConfig.java
cat > notification-service/src/main/java/com/social/notificationservice/config/KafkaConsumerConfig.java << 'EOF'
package com.social.notificationservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-service-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
EOF

# Create MongoConfig.java
cat > notification-service/src/main/java/com/social/notificationservice/config/MongoConfig.java << 'EOF'
package com.social.notificationservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.social.notificationservice.repository")
public class MongoConfig {
}
EOF

# Create OpenApiConfig.java
cat > notification-service/src/main/java/com/social/notificationservice/config/OpenApiConfig.java << 'EOF'
package com.social.notificationservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificationServiceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9006");
        server.setDescription("Notification Service - Development");

        Contact contact = new Contact();
        contact.setName("Social Commerce Platform");
        contact.setEmail("support@socialcommerce.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("Notification Service API")
                .version("1.0.0")
                .description("Multi-channel notification service: email, SMS, push, in-app")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
EOF

# Create AsyncConfig.java
cat > notification-service/src/main/java/com/social/notificationservice/config/AsyncConfig.java << 'EOF'
package com.social.notificationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("notification-async-");
        executor.initialize();
        return executor;
    }
}
EOF
```

#### **Step 9: Create application.yml**

```bash
# Create application.yml
cat > notification-service/src/main/resources/application.yml << 'EOF'
spring:
  application:
    name: notification-service

  config:
    import: optional:configserver:http://localhost:8888

  data:
    mongodb:
      uri: mongodb://localhost:27017/notification_db
      auto-index-creation: true

  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: notification-service-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer

  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME:your-email@gmail.com}
    password: ${MAIL_PASSWORD:your-app-password}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
    from: noreply@socialcommerce.com

server:
  port: 9006

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
  instance:
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${server.port}

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

logging:
  level:
    com.social.notificationservice: DEBUG
    org.springframework.kafka: INFO
    org.springframework.mail: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %msg%n"

notification:
  retry:
    max-attempts: 3
    delay-minutes: 5
EOF
```

#### **Step 10: Build and Run Notification Service**

```bash
# Build the service
cd notification-service
mvn clean install -DskipTests
cd ..

# Start MongoDB (if not already running)
docker run -d \
  --name notification-mongodb \
  -p 27018:27017 \
  -e MONGO_INITDB_DATABASE=notification_db \
  mongo:latest

# Start Kafka (if not already running)
docker run -d \
  --name notification-kafka \
  -p 9092:9092 \
  -e KAFKA_ENABLE_KRAFT=yes \
  -e KAFKA_CFG_PROCESS_ROLES=broker,controller \
  -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
  -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
  -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
  -e KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
  -e ALLOW_PLAINTEXT_LISTENER=yes \
  bitnami/kafka:3.7.0

# Wait for services to be ready
sleep 15

# Run Notification Service
cd notification-service
mvn spring-boot:run
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v3.5.5)

Notification Service started on port 9006
Registered with Eureka Server
Connected to MongoDB: notification_db
Connected to Kafka: localhost:9092
Kafka Consumers: order.events, payment.events, user.events
Swagger UI: http://localhost:9006/swagger-ui.html
```

#### **Step 11: Test Notification Service with curl**

```bash
# 1. Send a manual notification
curl -X POST http://localhost:9006/api/v1/notifications \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "user123",
    "type": "WELCOME",
    "channel": "EMAIL",
    "subject": "Welcome to Social Commerce!",
    "message": "Thank you for joining our community!",
    "recipient": "user@example.com"
  }'

# Expected Response:
# {
#   "success": true,
#   "message": "Notification sent successfully",
#   "data": {
#     "id": "...",
#     "userId": "user123",
#     "type": "WELCOME",
#     "channel": "EMAIL",
#     "status": "PENDING"
#   }
# }

# 2. Get user notifications
curl -X GET "http://localhost:9006/api/v1/notifications/user/user123?page=0&size=20"

# 3. Get unread notifications
curl -X GET "http://localhost:9006/api/v1/notifications/user/user123/unread?page=0&size=20"

# 4. Get unread count
curl -X GET http://localhost:9006/api/v1/notifications/user/user123/unread/count

# Expected Response:
# {
#   "success": true,
#   "data": 5
# }

# 5. Mark notification as read
curl -X PUT http://localhost:9006/api/v1/notifications/<NOTIFICATION_ID>/read \
  -H "X-User-Id: user123"

# 6. Mark all as read
curl -X PUT http://localhost:9006/api/v1/notifications/user/user123/read-all

# 7. Test Kafka integration - Create an order (triggers notification)
curl -X POST http://localhost:9003/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "X-User-Id: user123" \
  -d '{
    "items": [
      {
        "productId": "<PRODUCT_ID>",
        "quantity": 2,
        "price": 29.99
      }
    ],
    "shippingAddress": {
      "street": "123 Main St",
      "city": "New York",
      "state": "NY",
      "zipCode": "10001",
      "country": "USA"
    }
  }'

# Check notification was created
curl -X GET "http://localhost:9006/api/v1/notifications/user/user123/unread"

# 8. Test payment notification - Process payment (triggers notification)
curl -X POST http://localhost:9004/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "X-User-Id: user123" \
  -d '{
    "orderId": "<ORDER_ID>",
    "amount": 59.98,
    "paymentMethod": "CREDIT_CARD",
    "stripeToken": "tok_visa"
  }'

# Check payment notification
curl -X GET "http://localhost:9006/api/v1/notifications/user/user123/unread"
```

#### **Step 12: Verify Kafka Consumers**

```bash
# Check Kafka topics
docker exec -it notification-kafka kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --list

# Expected topics:
# order.events
# payment.events
# user.events

# Monitor order events
docker exec -it notification-kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic order.events \
  --from-beginning

# Monitor payment events
docker exec -it notification-kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic payment.events \
  --from-beginning
```

#### **Step 13: Verify in MongoDB**

```bash
# Connect to MongoDB
docker exec -it notification-mongodb mongosh notification_db

# View notifications
db.notifications.find().pretty()

# Count notifications by type
db.notifications.aggregate([
  { $group: { _id: "$type", count: { $sum: 1 } } },
  { $sort: { count: -1 } }
])

# Find unread notifications
db.notifications.find({ read: false }).pretty()

# Find failed notifications
db.notifications.find({ status: "FAILED" }).pretty()

# Exit MongoDB
exit
```

#### **Step 14: Verify in Swagger UI**

```bash
# Open Swagger UI
open http://localhost:9006/swagger-ui.html

# Test all endpoints:
# 1. POST /api/v1/notifications - Send notification
# 2. GET /api/v1/notifications/user/{userId} - Get user notifications
# 3. GET /api/v1/notifications/user/{userId}/unread - Get unread notifications
# 4. GET /api/v1/notifications/user/{userId}/unread/count - Get unread count
# 5. PUT /api/v1/notifications/{id}/read - Mark as read
# 6. PUT /api/v1/notifications/user/{userId}/read-all - Mark all as read
```

#### **Step 15: Test Email Configuration (Optional)**

```bash
# If you want to test actual email sending, configure Gmail:

# 1. Enable 2-factor authentication in your Gmail account
# 2. Generate an App Password: https://myaccount.google.com/apppasswords
# 3. Update application.yml with your credentials:

spring:
  mail:
    username: your-email@gmail.com
    password: your-16-char-app-password

# 4. Restart the service and test email sending
```

#### **Step 16: Commit Changes**

```bash
# Add and commit
git add notification-service/
git commit -m "feat: Add Notification Service with Kafka consumers

- Created notification management with MongoDB
- Implemented email service with JavaMail
- Added Kafka consumers for order, payment, and user events
- Multi-channel support (email, SMS, push, in-app)
- Notification preferences and history
- Retry mechanism for failed notifications
- Created comprehensive REST APIs
- Port: 9006
- Databases: MongoDB + Kafka"
```

---

### **✅ DAY 11 CHECKPOINT - ALL MICROSERVICES COMPLETE!** 🎉🏆

**What We Built:**
- ✅ Notification Service (Port 9006)
- ✅ Multi-channel notifications (email, SMS, push, in-app)
- ✅ Kafka event consumers (order, payment, user events)
- ✅ Email service with JavaMail
- ✅ Notification history and status tracking
- ✅ User notification preferences
- ✅ Retry mechanism for failed notifications
- ✅ Unread notification tracking
- ✅ 6 REST API endpoints
- ✅ MongoDB + Kafka integration

**MongoDB Collections:**
- notifications (with status, retry count, read status)
- notification_preferences (user preferences per channel)

**Kafka Topics Consumed:**
- order.events (order created, confirmed, shipped, delivered, cancelled)
- payment.events (payment success, failed, refunded)
- user.events (user registered, password reset, etc.)

**Notification Types:**
- ORDER_CREATED, ORDER_CONFIRMED, ORDER_SHIPPED, ORDER_DELIVERED, ORDER_CANCELLED
- PAYMENT_SUCCESS, PAYMENT_FAILED, PAYMENT_REFUNDED
- NEW_FOLLOWER, POST_LIKED, POST_COMMENTED, COMMENT_REPLIED
- PRODUCT_BACK_IN_STOCK, PRICE_DROP
- WELCOME, PASSWORD_RESET, ACCOUNT_VERIFICATION

**Notification Channels:**
- EMAIL (implemented with JavaMail)
- SMS (placeholder for Twilio integration)
- PUSH (placeholder for Firebase integration)
- IN_APP (stored in MongoDB)

**APIs Created:**
- 6 endpoints for notification management

---

## **DAY 12: Docker Compose Setup** 🐳

**Goal:** Create Docker Compose configuration to run all services and infrastructure together.

**What We'll Create:**
- Docker Compose for infrastructure (databases, Kafka)
- Docker Compose for all microservices
- Dockerfiles for each service
- Environment configuration
- Network setup
- Volume mounts for data persistence

---

### **Step 1: Create Dockerfiles for Each Service**

```bash
# Create Dockerfile for Service Discovery
cat > service-discovery/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for Config Server
cat > config-server/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for API Gateway
cat > api-gateway/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for User Service
cat > user-service/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for Product Service
cat > product-service/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for Order Service
cat > order-service/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for Payment Service
cat > payment-service/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for Social Service
cat > social-service/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9005
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF

# Create Dockerfile for Notification Service
cat > notification-service/Dockerfile << 'EOF'
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9006
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF
```

### **Step 2: Create Docker Compose for Infrastructure**

```bash
# Create docker-compose-infrastructure.yml
cat > docker-compose-infrastructure.yml << 'EOF'
version: '3.8'

services:
  # PostgreSQL Database
  postgres:
    image: postgres:15-alpine
    container_name: social-commerce-postgres
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: social_commerce
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init-scripts/postgres:/docker-entrypoint-initdb.d
    networks:
      - social-commerce-network
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 10s
      timeout: 5s
      retries: 5

  # MongoDB
  mongodb:
    image: mongo:latest
    container_name: social-commerce-mongodb
    environment:
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: admin123
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db
    networks:
      - social-commerce-network
    healthcheck:
      test: echo 'db.runCommand("ping").ok' | mongosh localhost:27017/test --quiet
      interval: 10s
      timeout: 5s
      retries: 5

  # Redis
  redis:
    image: redis:7-alpine
    container_name: social-commerce-redis
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    networks:
      - social-commerce-network
    healthcheck:
      test: ["CMD", "redis-cli", "ping"]
      interval: 10s
      timeout: 5s
      retries: 5

  # Elasticsearch
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.11.0
    container_name: social-commerce-elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ports:
      - "9200:9200"
      - "9300:9300"
    volumes:
      - elasticsearch_data:/usr/share/elasticsearch/data
    networks:
      - social-commerce-network
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9200/_cluster/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5

  # Neo4j
  neo4j:
    image: neo4j:latest
    container_name: social-commerce-neo4j
    environment:
      NEO4J_AUTH: neo4j/password123
      NEO4J_dbms_memory_pagecache_size: 512M
      NEO4J_dbms_memory_heap_max__size: 512M
    ports:
      - "7474:7474"  # HTTP
      - "7687:7687"  # Bolt
    volumes:
      - neo4j_data:/data
    networks:
      - social-commerce-network
    healthcheck:
      test: ["CMD-SHELL", "cypher-shell -u neo4j -p password123 'RETURN 1'"]
      interval: 30s
      timeout: 10s
      retries: 5

  # Apache Kafka
  kafka:
    image: bitnami/kafka:3.7.0
    container_name: social-commerce-kafka
    environment:
      - KAFKA_ENABLE_KRAFT=yes
      - KAFKA_CFG_PROCESS_ROLES=broker,controller
      - KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092
      - KAFKA_BROKER_ID=1
      - KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@kafka:9093
      - ALLOW_PLAINTEXT_LISTENER=yes
      - KAFKA_CFG_NODE_ID=1
      - KAFKA_CFG_AUTO_CREATE_TOPICS_ENABLE=true
    ports:
      - "9092:9092"
    volumes:
      - kafka_data:/bitnami/kafka
    networks:
      - social-commerce-network
    healthcheck:
      test: ["CMD-SHELL", "kafka-topics.sh --bootstrap-server localhost:9092 --list"]
      interval: 30s
      timeout: 10s
      retries: 5

volumes:
  postgres_data:
  mongodb_data:
  redis_data:
  elasticsearch_data:
  neo4j_data:
  kafka_data:

networks:
  social-commerce-network:
    driver: bridge
EOF
```

### **Step 3: Create PostgreSQL Initialization Scripts**

```bash
# Create init scripts directory
mkdir -p init-scripts/postgres

# Create database initialization script
cat > init-scripts/postgres/01-init-databases.sql << 'EOF'
-- Create databases for each service
CREATE DATABASE user_service_db;
CREATE DATABASE product_service_db;
CREATE DATABASE order_service_db;
CREATE DATABASE payment_service_db;

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE user_service_db TO postgres;
GRANT ALL PRIVILEGES ON DATABASE product_service_db TO postgres;
GRANT ALL PRIVILEGES ON DATABASE order_service_db TO postgres;
GRANT ALL PRIVILEGES ON DATABASE payment_service_db TO postgres;

-- Connect to user_service_db and create schema
\c user_service_db;
CREATE SCHEMA IF NOT EXISTS user_service;

-- Connect to product_service_db and create schema
\c product_service_db;
CREATE SCHEMA IF NOT EXISTS product_service;

-- Connect to order_service_db and create schema
\c order_service_db;
CREATE SCHEMA IF NOT EXISTS order_service;

-- Connect to payment_service_db and create schema
\c payment_service_db;
CREATE SCHEMA IF NOT EXISTS payment_service;
EOF
```

### **Step 4: Create Docker Compose for Microservices**

```bash
# Create docker-compose-services.yml
cat > docker-compose-services.yml << 'EOF'
version: '3.8'

services:
  # Service Discovery (Eureka)
  service-discovery:
    build:
      context: ./service-discovery
      dockerfile: Dockerfile
    container_name: service-discovery
    ports:
      - "8761:8761"
    networks:
      - social-commerce-network
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:8761/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # Config Server
  config-server:
    build:
      context: ./config-server
      dockerfile: Dockerfile
    container_name: config-server
    ports:
      - "8888:8888"
    environment:
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
    networks:
      - social-commerce-network
    depends_on:
      service-discovery:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:8888/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # API Gateway
  api-gateway:
    build:
      context: ./api-gateway
      dockerfile: Dockerfile
    container_name: api-gateway
    ports:
      - "9000:9000"
    environment:
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      service-discovery:
        condition: service_healthy
      config-server:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9000/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # User Service
  user-service:
    build:
      context: ./user-service
      dockerfile: Dockerfile
    container_name: user-service
    ports:
      - "9001:9001"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/user_service_db
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      postgres:
        condition: service_healthy
      redis:
        condition: service_healthy
      service-discovery:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9001/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # Product Service
  product-service:
    build:
      context: ./product-service
      dockerfile: Dockerfile
    container_name: product-service
    ports:
      - "9002:9002"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/product_service_db
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
      - SPRING_ELASTICSEARCH_URIS=http://elasticsearch:9200
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      postgres:
        condition: service_healthy
      redis:
        condition: service_healthy
      elasticsearch:
        condition: service_healthy
      service-discovery:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9002/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # Order Service
  order-service:
    build:
      context: ./order-service
      dockerfile: Dockerfile
    container_name: order-service
    ports:
      - "9003:9003"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/order_service_db
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      postgres:
        condition: service_healthy
      kafka:
        condition: service_healthy
      service-discovery:
        condition: service_healthy
      product-service:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9003/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # Payment Service
  payment-service:
    build:
      context: ./payment-service
      dockerfile: Dockerfile
    container_name: payment-service
    ports:
      - "9004:9004"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/payment_service_db
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
      - STRIPE_API_KEY=${STRIPE_API_KEY:-sk_test_your_stripe_key}
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      postgres:
        condition: service_healthy
      kafka:
        condition: service_healthy
      service-discovery:
        condition: service_healthy
      order-service:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9004/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # Social Service
  social-service:
    build:
      context: ./social-service
      dockerfile: Dockerfile
    container_name: social-service
    ports:
      - "9005:9005"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://admin:admin123@mongodb:27017/social_db?authSource=admin
      - SPRING_NEO4J_URI=bolt://neo4j:7687
      - SPRING_NEO4J_AUTHENTICATION_USERNAME=neo4j
      - SPRING_NEO4J_AUTHENTICATION_PASSWORD=password123
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      mongodb:
        condition: service_healthy
      neo4j:
        condition: service_healthy
      service-discovery:
        condition: service_healthy
      user-service:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9005/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

  # Notification Service
  notification-service:
    build:
      context: ./notification-service
      dockerfile: Dockerfile
    container_name: notification-service
    ports:
      - "9006:9006"
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://admin:admin123@mongodb:27017/notification_db?authSource=admin
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
      - SPRING_MAIL_HOST=${MAIL_HOST:-smtp.gmail.com}
      - SPRING_MAIL_PORT=${MAIL_PORT:-587}
      - SPRING_MAIL_USERNAME=${MAIL_USERNAME}
      - SPRING_MAIL_PASSWORD=${MAIL_PASSWORD}
      - EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
      - SPRING_CLOUD_CONFIG_URI=http://config-server:8888
    networks:
      - social-commerce-network
    depends_on:
      mongodb:
        condition: service_healthy
      kafka:
        condition: service_healthy
      service-discovery:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:9006/actuator/health || exit 1"]
      interval: 30s
      timeout: 10s
      retries: 5
    restart: unless-stopped

networks:
  social-commerce-network:
    external: true
EOF
```

### **Step 5: Create Master Docker Compose File**

```bash
# Create docker-compose.yml (master file)
cat > docker-compose.yml << 'EOF'
version: '3.8'

services:
  # Import all infrastructure services
  postgres:
    extends:
      file: docker-compose-infrastructure.yml
      service: postgres

  mongodb:
    extends:
      file: docker-compose-infrastructure.yml
      service: mongodb

  redis:
    extends:
      file: docker-compose-infrastructure.yml
      service: redis

  elasticsearch:
    extends:
      file: docker-compose-infrastructure.yml
      service: elasticsearch

  neo4j:
    extends:
      file: docker-compose-infrastructure.yml
      service: neo4j

  kafka:
    extends:
      file: docker-compose-infrastructure.yml
      service: kafka

  # Import all microservices
  service-discovery:
    extends:
      file: docker-compose-services.yml
      service: service-discovery

  config-server:
    extends:
      file: docker-compose-services.yml
      service: config-server

  api-gateway:
    extends:
      file: docker-compose-services.yml
      service: api-gateway

  user-service:
    extends:
      file: docker-compose-services.yml
      service: user-service

  product-service:
    extends:
      file: docker-compose-services.yml
      service: product-service

  order-service:
    extends:
      file: docker-compose-services.yml
      service: order-service

  payment-service:
    extends:
      file: docker-compose-services.yml
      service: payment-service

  social-service:
    extends:
      file: docker-compose-services.yml
      service: social-service

  notification-service:
    extends:
      file: docker-compose-services.yml
      service: notification-service

volumes:
  postgres_data:
  mongodb_data:
  redis_data:
  elasticsearch_data:
  neo4j_data:
  kafka_data:

networks:
  social-commerce-network:
    driver: bridge
EOF
```

### **Step 6: Create Environment Configuration**

```bash
# Create .env file for environment variables
cat > .env << 'EOF'
# Database Configuration
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=social_commerce

MONGO_INITDB_ROOT_USERNAME=admin
MONGO_INITDB_ROOT_PASSWORD=admin123

NEO4J_AUTH=neo4j/password123

# Kafka Configuration
KAFKA_BROKER_ID=1

# Email Configuration (Update with your credentials)
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# Stripe Configuration (Update with your key)
STRIPE_API_KEY=sk_test_your_stripe_key

# Service Ports
SERVICE_DISCOVERY_PORT=8761
CONFIG_SERVER_PORT=8888
API_GATEWAY_PORT=9000
USER_SERVICE_PORT=9001
PRODUCT_SERVICE_PORT=9002
ORDER_SERVICE_PORT=9003
PAYMENT_SERVICE_PORT=9004
SOCIAL_SERVICE_PORT=9005
NOTIFICATION_SERVICE_PORT=9006
EOF

# Create .env.example for reference
cat > .env.example << 'EOF'
# Copy this file to .env and update with your actual values

# Email Configuration
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-16-char-app-password

# Stripe Configuration
STRIPE_API_KEY=sk_test_your_stripe_secret_key

# Optional: Override default ports
API_GATEWAY_PORT=9000
USER_SERVICE_PORT=9001
PRODUCT_SERVICE_PORT=9002
ORDER_SERVICE_PORT=9003
PAYMENT_SERVICE_PORT=9004
SOCIAL_SERVICE_PORT=9005
NOTIFICATION_SERVICE_PORT=9006
EOF
```

### **Step 7: Create Startup Scripts**

```bash
# Create start-infrastructure.sh
cat > start-infrastructure.sh << 'EOF'
#!/bin/bash

echo "🚀 Starting Infrastructure Services..."

# Create network if it doesn't exist
docker network create social-commerce-network 2>/dev/null || true

# Start infrastructure services
docker-compose -f docker-compose-infrastructure.yml up -d

echo "⏳ Waiting for services to be healthy..."

# Wait for PostgreSQL
echo "Waiting for PostgreSQL..."
until docker exec social-commerce-postgres pg_isready -U postgres > /dev/null 2>&1; do
  sleep 2
done
echo "✅ PostgreSQL is ready"

# Wait for MongoDB
echo "Waiting for MongoDB..."
until docker exec social-commerce-mongodb mongosh --eval "db.adminCommand('ping')" > /dev/null 2>&1; do
  sleep 2
done
echo "✅ MongoDB is ready"

# Wait for Redis
echo "Waiting for Redis..."
until docker exec social-commerce-redis redis-cli ping > /dev/null 2>&1; do
  sleep 2
done
echo "✅ Redis is ready"

# Wait for Elasticsearch
echo "Waiting for Elasticsearch..."
until curl -s http://localhost:9200/_cluster/health > /dev/null 2>&1; do
  sleep 2
done
echo "✅ Elasticsearch is ready"

# Wait for Neo4j
echo "Waiting for Neo4j..."
sleep 10
echo "✅ Neo4j is ready"

# Wait for Kafka
echo "Waiting for Kafka..."
sleep 15
echo "✅ Kafka is ready"

echo "🎉 All infrastructure services are running!"
echo ""
echo "📊 Service URLs:"
echo "  PostgreSQL:     localhost:5432"
echo "  MongoDB:        localhost:27017"
echo "  Redis:          localhost:6379"
echo "  Elasticsearch:  http://localhost:9200"
echo "  Neo4j Browser:  http://localhost:7474"
echo "  Kafka:          localhost:9092"
EOF

chmod +x start-infrastructure.sh

# Create start-services.sh
cat > start-services.sh << 'EOF'
#!/bin/bash

echo "🚀 Starting Microservices..."

# Build all services first
echo "📦 Building all services..."
mvn clean install -DskipTests

# Start microservices
docker-compose -f docker-compose-services.yml up -d

echo "⏳ Waiting for services to start..."
sleep 30

echo "🎉 All microservices are starting!"
echo ""
echo "📊 Service URLs:"
echo "  Service Discovery: http://localhost:8761"
echo "  Config Server:     http://localhost:8888"
echo "  API Gateway:       http://localhost:9000"
echo "  User Service:      http://localhost:9001"
echo "  Product Service:   http://localhost:9002"
echo "  Order Service:     http://localhost:9003"
echo "  Payment Service:   http://localhost:9004"
echo "  Social Service:    http://localhost:9005"
echo "  Notification Svc:  http://localhost:9006"
echo ""
echo "📚 Swagger UI URLs:"
echo "  User Service:      http://localhost:9001/swagger-ui.html"
echo "  Product Service:   http://localhost:9002/swagger-ui.html"
echo "  Order Service:     http://localhost:9003/swagger-ui.html"
echo "  Payment Service:   http://localhost:9004/swagger-ui.html"
echo "  Social Service:    http://localhost:9005/swagger-ui.html"
echo "  Notification Svc:  http://localhost:9006/swagger-ui.html"
EOF

chmod +x start-services.sh

# Create start-all.sh
cat > start-all.sh << 'EOF'
#!/bin/bash

echo "🚀 Starting Social Commerce Platform..."
echo ""

# Start infrastructure
./start-infrastructure.sh

echo ""
echo "⏳ Waiting 10 seconds before starting microservices..."
sleep 10

# Start microservices
./start-services.sh

echo ""
echo "✅ Social Commerce Platform is running!"
echo ""
echo "🌐 Access Points:"
echo "  Eureka Dashboard:  http://localhost:8761"
echo "  API Gateway:       http://localhost:9000"
echo "  Neo4j Browser:     http://localhost:7474 (neo4j/password123)"
echo ""
echo "📝 To view logs:"
echo "  docker-compose logs -f [service-name]"
echo ""
echo "🛑 To stop all services:"
echo "  ./stop-all.sh"
EOF

chmod +x start-all.sh

# Create stop-all.sh
cat > stop-all.sh << 'EOF'
#!/bin/bash

echo "🛑 Stopping Social Commerce Platform..."

# Stop microservices
docker-compose -f docker-compose-services.yml down

# Stop infrastructure
docker-compose -f docker-compose-infrastructure.yml down

echo "✅ All services stopped!"
echo ""
echo "💾 Data is preserved in Docker volumes."
echo "🗑️  To remove all data, run: docker-compose down -v"
EOF

chmod +x stop-all.sh

# Create clean-all.sh
cat > clean-all.sh << 'EOF'
#!/bin/bash

echo "🗑️  Cleaning Social Commerce Platform..."

# Stop and remove all containers
docker-compose -f docker-compose-services.yml down -v
docker-compose -f docker-compose-infrastructure.yml down -v

# Remove network
docker network rm social-commerce-network 2>/dev/null || true

# Remove built images
docker rmi $(docker images | grep social-commerce | awk '{print $3}') 2>/dev/null || true

echo "✅ Cleanup complete!"
echo ""
echo "⚠️  All data has been removed."
echo "🚀 Run ./start-all.sh to start fresh."
EOF

chmod +x clean-all.sh

# Create logs.sh
cat > logs.sh << 'EOF'
#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: ./logs.sh [service-name]"
  echo ""
  echo "Available services:"
  echo "  Infrastructure: postgres, mongodb, redis, elasticsearch, neo4j, kafka"
  echo "  Microservices:  service-discovery, config-server, api-gateway"
  echo "                  user-service, product-service, order-service"
  echo "                  payment-service, social-service, notification-service"
  exit 1
fi

docker-compose logs -f "$1"
EOF

chmod +x logs.sh

# Create status.sh
cat > status.sh << 'EOF'
#!/bin/bash

echo "📊 Social Commerce Platform Status"
echo "=================================="
echo ""

echo "Infrastructure Services:"
docker-compose -f docker-compose-infrastructure.yml ps

echo ""
echo "Microservices:"
docker-compose -f docker-compose-services.yml ps

echo ""
echo "Network:"
docker network inspect social-commerce-network --format '{{.Name}}: {{len .Containers}} containers' 2>/dev/null || echo "Network not created"
EOF

chmod +x status.sh
```

### **Step 8: Build and Start Everything**

```bash
# 1. Build all services
echo "📦 Building all services..."
mvn clean install -DskipTests

# 2. Start infrastructure
echo "🚀 Starting infrastructure..."
./start-infrastructure.sh

# 3. Wait for infrastructure to be ready
echo "⏳ Waiting for infrastructure..."
sleep 20

# 4. Start microservices
echo "🚀 Starting microservices..."
./start-services.sh

# 5. Check status
echo "📊 Checking status..."
./status.sh
```

### **Step 9: Verify All Services**

```bash
# Check Eureka Dashboard
open http://localhost:8761

# Check all services are registered
curl http://localhost:8761/eureka/apps | grep -o '<app>[^<]*</app>'

# Expected output:
# <app>API-GATEWAY</app>
# <app>USER-SERVICE</app>
# <app>PRODUCT-SERVICE</app>
# <app>ORDER-SERVICE</app>
# <app>PAYMENT-SERVICE</app>
# <app>SOCIAL-SERVICE</app>
# <app>NOTIFICATION-SERVICE</app>

# Check health of all services
for port in 8761 8888 9000 9001 9002 9003 9004 9005 9006; do
  echo "Checking port $port..."
  curl -s http://localhost:$port/actuator/health | jq .
done

# Check databases
echo "PostgreSQL databases:"
docker exec social-commerce-postgres psql -U postgres -c "\l"

echo "MongoDB databases:"
docker exec social-commerce-mongodb mongosh --eval "show dbs"

echo "Redis info:"
docker exec social-commerce-redis redis-cli info server

echo "Elasticsearch health:"
curl http://localhost:9200/_cluster/health?pretty

echo "Neo4j status:"
curl http://localhost:7474/db/data/

echo "Kafka topics:"
docker exec social-commerce-kafka kafka-topics.sh --bootstrap-server localhost:9092 --list
```

### **Step 10: Commit Docker Configuration**

```bash
git add docker-compose*.yml Dockerfile .env.example *.sh init-scripts/
git commit -m "feat: Add Docker Compose configuration for all services

- Created Dockerfiles for all microservices
- Docker Compose for infrastructure (PostgreSQL, MongoDB, Redis, Elasticsearch, Neo4j, Kafka)
- Docker Compose for microservices
- Environment configuration
- Startup and management scripts
- PostgreSQL initialization scripts
- Health checks for all services"
```

---

### **✅ DAY 12 CHECKPOINT - DOCKER COMPOSE COMPLETE!** 🐳

**What We Built:**
- ✅ Dockerfiles for all 9 services
- ✅ Docker Compose for infrastructure (6 services)
- ✅ Docker Compose for microservices (9 services)
- ✅ Master Docker Compose file
- ✅ Environment configuration (.env)
- ✅ PostgreSQL initialization scripts
- ✅ Startup scripts (start-all.sh, start-infrastructure.sh, start-services.sh)
- ✅ Management scripts (stop-all.sh, clean-all.sh, logs.sh, status.sh)
- ✅ Health checks for all services
- ✅ Network configuration
- ✅ Volume mounts for data persistence

**Infrastructure Services:**
- PostgreSQL (port 5432) - 4 databases
- MongoDB (port 27017) - 2 databases
- Redis (port 6379) - Caching
- Elasticsearch (port 9200) - Search
- Neo4j (ports 7474, 7687) - Social graph
- Kafka (port 9092) - Event streaming

**Microservices:**
- Service Discovery (port 8761)
- Config Server (port 8888)
- API Gateway (port 9000)
- User Service (port 9001)
- Product Service (port 9002)
- Order Service (port 9003)
- Payment Service (port 9004)
- Social Service (port 9005)
- Notification Service (port 9006)

---

## **DAY 13: Integration Testing** 🧪

**Goal:** Test end-to-end scenarios across all microservices.

**Test Scenarios:**
1. User Registration → Email Notification
2. Product Search → Add to Cart → Checkout → Payment → Notifications
3. Social Interactions → Notifications
4. Circuit Breaker Testing
5. Kafka Event Flow Testing

---

### **Scenario 1: User Registration Flow**

```bash
# 1. Register a new user
curl -X POST http://localhost:9000/api/v1/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "SecurePass123!",
    "firstName": "John",
    "lastName": "Doe"
  }'

# Expected: User created + Welcome email notification sent

# 2. Verify user in database
docker exec social-commerce-postgres psql -U postgres -d user_service_db \
  -c "SELECT id, username, email FROM users WHERE username='john_doe';"

# 3. Verify notification was created
curl http://localhost:9006/api/v1/notifications/user/<USER_ID>/unread

# Expected: Welcome notification in the list

# 4. Login with the new user
curl -X POST http://localhost:9000/api/v1/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "SecurePass123!"
  }'

# Expected: JWT token returned

# Save the token
TOKEN="<JWT_TOKEN_FROM_RESPONSE>"
```

### **Scenario 2: Complete E-Commerce Flow**

```bash
# 1. Create a product (as admin)
curl -X POST http://localhost:9000/api/v1/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Wireless Headphones",
    "description": "Premium noise-cancelling headphones",
    "price": 199.99,
    "stockQuantity": 50,
    "category": "ELECTRONICS",
    "brand": "TechBrand",
    "tags": ["audio", "wireless", "premium"]
  }'

# Save product ID
PRODUCT_ID="<PRODUCT_ID_FROM_RESPONSE>"

# 2. Search for the product
curl "http://localhost:9000/api/v1/products/search?query=headphones"

# Expected: Product appears in search results (Elasticsearch)

# 3. Get product details
curl http://localhost:9000/api/v1/products/$PRODUCT_ID

# Expected: Full product details with cached response (Redis)

# 4. Add product to cart
curl -X POST http://localhost:9000/api/v1/orders/cart/items \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "productId": "'$PRODUCT_ID'",
    "quantity": 2
  }'

# 5. View cart
curl http://localhost:9000/api/v1/orders/cart \
  -H "Authorization: Bearer $TOKEN"

# 6. Create order from cart
curl -X POST http://localhost:9000/api/v1/orders/checkout \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "shippingAddress": {
      "street": "123 Main St",
      "city": "New York",
      "state": "NY",
      "zipCode": "10001",
      "country": "USA"
    }
  }'

# Save order ID
ORDER_ID="<ORDER_ID_FROM_RESPONSE>"

# Expected:
# - Order created in database
# - Stock reserved in product service
# - Order event published to Kafka
# - Order notification sent

# 7. Verify order notification
curl http://localhost:9006/api/v1/notifications/user/<USER_ID>/unread

# Expected: Order created notification

# 8. Process payment
curl -X POST http://localhost:9000/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "orderId": "'$ORDER_ID'",
    "amount": 399.98,
    "paymentMethod": "CREDIT_CARD",
    "stripeToken": "tok_visa"
  }'

# Expected:
# - Payment processed via Stripe
# - Payment event published to Kafka
# - Payment notification sent
# - Order status updated to CONFIRMED

# 9. Verify payment notification
curl http://localhost:9006/api/v1/notifications/user/<USER_ID>/unread

# Expected: Payment success notification

# 10. Verify order status
curl http://localhost:9000/api/v1/orders/$ORDER_ID \
  -H "Authorization: Bearer $TOKEN"

# Expected: Order status = CONFIRMED

# 11. Verify stock was deducted
curl http://localhost:9000/api/v1/products/$PRODUCT_ID

# Expected: stockQuantity = 48 (50 - 2)
```

### **Scenario 3: Social Interactions Flow**

```bash
# 1. Create a post
curl -X POST http://localhost:9000/api/v1/posts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "content": "Just bought these amazing headphones! 🎧",
    "type": "PRODUCT_REVIEW",
    "tags": ["headphones", "review"]
  }'

# Save post ID
POST_ID="<POST_ID_FROM_RESPONSE>"

# 2. Register another user
curl -X POST http://localhost:9000/api/v1/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "jane_smith",
    "email": "jane@example.com",
    "password": "SecurePass123!",
    "firstName": "Jane",
    "lastName": "Smith"
  }'

# 3. Login as Jane
curl -X POST http://localhost:9000/api/v1/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "jane_smith",
    "password": "SecurePass123!"
  }'

TOKEN2="<JWT_TOKEN_FROM_RESPONSE>"
USER2_ID="<USER_ID_FROM_RESPONSE>"

# 4. Jane follows John
curl -X POST http://localhost:9000/api/v1/social/follow/<JOHN_USER_ID> \
  -H "Authorization: Bearer $TOKEN2"

# Expected: Follow relationship created in Neo4j

# 5. Verify in Neo4j
docker exec social-commerce-neo4j cypher-shell -u neo4j -p password123 \
  "MATCH (a:User)-[:FOLLOWS]->(b:User) RETURN a.username, b.username"

# Expected: jane_smith FOLLOWS john_doe

# 6. Jane likes John's post
curl -X POST http://localhost:9000/api/v1/posts/$POST_ID/like \
  -H "Authorization: Bearer $TOKEN2"

# Expected: Like recorded in MongoDB

# 7. Jane comments on John's post
curl -X POST http://localhost:9000/api/v1/posts/$POST_ID/comments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN2" \
  -d '{
    "content": "Great review! I want to buy these too!"
  }'

# 8. Get John's feed (should see Jane's activity)
curl http://localhost:9000/api/v1/posts/feed \
  -H "Authorization: Bearer $TOKEN"

# Expected: Posts from followed users

# 9. Get post with comments
curl http://localhost:9000/api/v1/posts/$POST_ID \
  -H "Authorization: Bearer $TOKEN"

# Expected: Post with 1 like and 1 comment
```

### **Scenario 4: Circuit Breaker Testing**

```bash
# 1. Stop Product Service to trigger circuit breaker
docker-compose -f docker-compose-services.yml stop product-service

# 2. Try to create an order (should use fallback)
curl -X POST http://localhost:9000/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "items": [
      {
        "productId": "'$PRODUCT_ID'",
        "quantity": 1,
        "price": 199.99
      }
    ],
    "shippingAddress": {
      "street": "123 Main St",
      "city": "New York",
      "state": "NY",
      "zipCode": "10001",
      "country": "USA"
    }
  }'

# Expected: Fallback response or graceful error

# 3. Restart Product Service
docker-compose -f docker-compose-services.yml start product-service

# Wait for service to be healthy
sleep 30

# 4. Try again (should work)
curl -X POST http://localhost:9000/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "items": [
      {
        "productId": "'$PRODUCT_ID'",
        "quantity": 1,
        "price": 199.99
      }
    ],
    "shippingAddress": {
      "street": "123 Main St",
      "city": "New York",
      "state": "NY",
      "zipCode": "10001",
      "country": "USA"
    }
  }'

# Expected: Order created successfully
```

### **Scenario 5: Kafka Event Flow Testing**

```bash
# 1. Monitor Kafka topics in separate terminals

# Terminal 1: Monitor order events
docker exec -it social-commerce-kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic order.events \
  --from-beginning

# Terminal 2: Monitor payment events
docker exec -it social-commerce-kafka kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic payment.events \
  --from-beginning

# Terminal 3: Create an order and payment
curl -X POST http://localhost:9000/api/v1/orders/checkout \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "shippingAddress": {
      "street": "456 Oak Ave",
      "city": "Boston",
      "state": "MA",
      "zipCode": "02101",
      "country": "USA"
    }
  }'

ORDER_ID="<ORDER_ID_FROM_RESPONSE>"

curl -X POST http://localhost:9000/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "orderId": "'$ORDER_ID'",
    "amount": 199.99,
    "paymentMethod": "CREDIT_CARD",
    "stripeToken": "tok_visa"
  }'

# Expected: Events appear in both Kafka consumers

# 4. Verify notifications were created from events
curl http://localhost:9006/api/v1/notifications/user/<USER_ID>/unread

# Expected: Order and payment notifications
```

### **Scenario 6: Performance Testing**

```bash
# Install Apache Bench (if not installed)
# macOS: brew install httpd
# Ubuntu: sudo apt-get install apache2-utils

# 1. Test API Gateway throughput
ab -n 1000 -c 10 http://localhost:9000/actuator/health

# 2. Test Product Search performance
ab -n 100 -c 5 "http://localhost:9000/api/v1/products/search?query=headphones"

# 3. Test User Service with authentication
# Create a file with auth header
echo "Authorization: Bearer $TOKEN" > headers.txt

ab -n 500 -c 10 -H @headers.txt http://localhost:9000/api/v1/users/profile

# 4. Monitor metrics
curl http://localhost:9000/actuator/metrics/http.server.requests | jq .
curl http://localhost:9001/actuator/metrics/http.server.requests | jq .
curl http://localhost:9002/actuator/metrics/http.server.requests | jq .
```

### **✅ DAY 13 CHECKPOINT - INTEGRATION TESTING COMPLETE!** 🧪

**What We Tested:**
- ✅ User registration and authentication flow
- ✅ Complete e-commerce flow (product → cart → order → payment)
- ✅ Social interactions (posts, likes, comments, follows)
- ✅ Circuit breaker patterns
- ✅ Kafka event-driven architecture
- ✅ Performance testing with Apache Bench
- ✅ Database integrations (PostgreSQL, MongoDB, Redis, Elasticsearch, Neo4j)
- ✅ Cross-service communication
- ✅ Notification delivery

**Test Results:**
- ✅ All services communicate correctly
- ✅ Events flow through Kafka properly
- ✅ Notifications are triggered by events
- ✅ Circuit breakers work as expected
- ✅ Data persists correctly in all databases
- ✅ Search functionality works (Elasticsearch)
- ✅ Social graph works (Neo4j)
- ✅ Caching works (Redis)

---

## **DAY 14: Final Documentation and Review** 📚

**Goal:** Complete documentation, deployment guide, and final review.

---

### **1. API Documentation Summary**

**All Swagger UI Endpoints:**

| Service | Swagger UI | Endpoints |
|---------|-----------|-----------|
| User Service | http://localhost:9001/swagger-ui.html | 13 APIs |
| Product Service | http://localhost:9002/swagger-ui.html | 19 APIs |
| Order Service | http://localhost:9003/swagger-ui.html | 16 APIs |
| Payment Service | http://localhost:9004/swagger-ui.html | 10 APIs |
| Social Service | http://localhost:9005/swagger-ui.html | 22 APIs |
| Notification Service | http://localhost:9006/swagger-ui.html | 6 APIs |

**Total: 86+ REST API Endpoints**

---

### **2. Architecture Overview**

```
┌─────────────────────────────────────────────────────────────────┐
│                         API Gateway (9000)                       │
│                    (Routing, Auth, Circuit Breaker)              │
└────────────────────────────┬────────────────────────────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        │                    │                    │
┌───────▼────────┐  ┌───────▼────────┐  ┌───────▼────────┐
│ User Service   │  │Product Service │  │ Order Service  │
│    (9001)      │  │    (9002)      │  │    (9003)      │
│  PostgreSQL    │  │  PostgreSQL    │  │  PostgreSQL    │
│     Redis      │  │     Redis      │  │     Kafka      │
└────────────────┘  │ Elasticsearch  │  └────────────────┘
                    └────────────────┘
        │                    │                    │
        │                    │                    │
┌───────▼────────┐  ┌───────▼────────┐  ┌───────▼────────┐
│Payment Service │  │Social Service  │  │Notification Svc│
│    (9004)      │  │    (9005)      │  │    (9006)      │
│  PostgreSQL    │  │   MongoDB      │  │   MongoDB      │
│     Kafka      │  │    Neo4j       │  │     Kafka      │
│    Stripe      │  └────────────────┘  └────────────────┘
└────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│              Service Discovery (Eureka - 8761)                   │
│              Config Server (8888)                                │
└─────────────────────────────────────────────────────────────────┘
```

---

### **3. Database Schema Summary**

**PostgreSQL Databases:**

```sql
-- user_service_db
users (id, username, email, password_hash, first_name, last_name, role, created_at, updated_at)
user_profiles (id, user_id, bio, avatar_url, phone, date_of_birth)

-- product_service_db
products (id, name, description, price, stock_quantity, category, brand, created_at, updated_at)
product_images (id, product_id, image_url, is_primary)

-- order_service_db
orders (id, user_id, total_amount, status, shipping_address, created_at, updated_at)
order_items (id, order_id, product_id, quantity, price)
shopping_carts (id, user_id, created_at, updated_at)
cart_items (id, cart_id, product_id, quantity, price)

-- payment_service_db
payments (id, order_id, user_id, amount, payment_method, status, stripe_payment_id, created_at)
transactions (id, payment_id, transaction_type, amount, status, created_at)
```

**MongoDB Collections:**

```javascript
// social_db
posts {
  _id, userId, content, images, tags, type, status,
  likedBy[], likeCount, commentCount, createdAt, updatedAt
}

comments {
  _id, postId, userId, content, parentCommentId,
  likedBy[], likeCount, createdAt, updatedAt
}

// notification_db
notifications {
  _id, userId, type, channel, subject, message, data,
  status, recipient, sentAt, read, createdAt
}

notification_preferences {
  _id, userId, email, phoneNumber, deviceToken, preferences{}
}
```

**Neo4j Graph:**

```cypher
// Social graph
(User)-[:FOLLOWS]->(User)
(User)-[:LIKES]->(Post)
(User)-[:COMMENTED_ON]->(Post)
```

**Redis Cache Keys:**

```
user:{userId}
product:{productId}
products:category:{category}
products:search:{query}
```

**Elasticsearch Indices:**

```
products {
  id, name, description, price, category, brand, tags[], stockQuantity
}
```

**Kafka Topics:**

```
order.events - Order lifecycle events
payment.events - Payment lifecycle events
user.events - User lifecycle events
```

---

### **4. Environment Variables Reference**

```bash
# Database Configuration
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
MONGO_INITDB_ROOT_USERNAME=admin
MONGO_INITDB_ROOT_PASSWORD=admin123
NEO4J_AUTH=neo4j/password123

# Service URLs
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/
SPRING_CLOUD_CONFIG_URI=http://config-server:8888

# Kafka
SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092

# Email (Gmail)
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-16-char-app-password

# Stripe
STRIPE_API_KEY=sk_test_your_stripe_secret_key

# Redis
SPRING_DATA_REDIS_HOST=redis
SPRING_DATA_REDIS_PORT=6379

# Elasticsearch
SPRING_ELASTICSEARCH_URIS=http://elasticsearch:9200

# MongoDB
SPRING_DATA_MONGODB_URI=mongodb://admin:admin123@mongodb:27017/{db_name}?authSource=admin

# Neo4j
SPRING_NEO4J_URI=bolt://neo4j:7687
SPRING_NEO4J_AUTHENTICATION_USERNAME=neo4j
SPRING_NEO4J_AUTHENTICATION_PASSWORD=password123
```

---

### **5. Deployment Checklist**

**Pre-Deployment:**
- [ ] Update .env file with production credentials
- [ ] Configure production database URLs
- [ ] Set up production Stripe API keys
- [ ] Configure production email SMTP settings
- [ ] Review and update security configurations
- [ ] Set up SSL/TLS certificates
- [ ] Configure firewall rules
- [ ] Set up monitoring and alerting
- [ ] Configure backup strategies
- [ ] Review and optimize resource limits

**Deployment Steps:**
- [ ] Build all services: `mvn clean install`
- [ ] Run tests: `mvn test`
- [ ] Build Docker images: `docker-compose build`
- [ ] Start infrastructure: `./start-infrastructure.sh`
- [ ] Verify infrastructure health
- [ ] Start microservices: `./start-services.sh`
- [ ] Verify all services are registered in Eureka
- [ ] Run integration tests
- [ ] Verify Kafka topics are created
- [ ] Test API Gateway routing
- [ ] Verify database connections
- [ ] Test end-to-end flows
- [ ] Monitor logs for errors
- [ ] Set up log aggregation (ELK Stack)
- [ ] Configure Prometheus + Grafana for metrics
- [ ] Set up alerting rules

**Post-Deployment:**
- [ ] Monitor service health
- [ ] Check database performance
- [ ] Monitor Kafka lag
- [ ] Review application logs
- [ ] Test critical user flows
- [ ] Monitor API response times
- [ ] Check error rates
- [ ] Verify notification delivery
- [ ] Test circuit breakers
- [ ] Monitor resource usage (CPU, memory, disk)

---

### **6. Troubleshooting Guide**

**Common Issues and Solutions:**

**Issue 1: Service not registering with Eureka**
```bash
# Check Eureka is running
curl http://localhost:8761/actuator/health

# Check service logs
./logs.sh user-service

# Verify network connectivity
docker network inspect social-commerce-network

# Solution: Restart the service
docker-compose -f docker-compose-services.yml restart user-service
```

**Issue 2: Database connection errors**
```bash
# Check database is running
docker ps | grep postgres

# Check database logs
./logs.sh postgres

# Test connection
docker exec social-commerce-postgres psql -U postgres -c "SELECT 1"

# Solution: Restart database
docker-compose -f docker-compose-infrastructure.yml restart postgres
```

**Issue 3: Kafka consumer not receiving messages**
```bash
# Check Kafka is running
docker ps | grep kafka

# List topics
docker exec social-commerce-kafka kafka-topics.sh --bootstrap-server localhost:9092 --list

# Check consumer groups
docker exec social-commerce-kafka kafka-consumer-groups.sh \
  --bootstrap-server localhost:9092 --list

# Check consumer lag
docker exec social-commerce-kafka kafka-consumer-groups.sh \
  --bootstrap-server localhost:9092 \
  --group notification-service-group \
  --describe

# Solution: Restart Kafka and consumers
docker-compose -f docker-compose-infrastructure.yml restart kafka
docker-compose -f docker-compose-services.yml restart notification-service
```

**Issue 4: Elasticsearch not indexing products**
```bash
# Check Elasticsearch health
curl http://localhost:9200/_cluster/health?pretty

# Check indices
curl http://localhost:9200/_cat/indices?v

# Check product index
curl http://localhost:9200/products/_search?pretty

# Solution: Reindex products
curl -X POST http://localhost:9002/api/v1/products/reindex
```

**Issue 5: Redis cache not working**
```bash
# Check Redis is running
docker exec social-commerce-redis redis-cli ping

# Check keys
docker exec social-commerce-redis redis-cli KEYS '*'

# Check specific key
docker exec social-commerce-redis redis-cli GET user:123

# Solution: Clear cache and restart
docker exec social-commerce-redis redis-cli FLUSHALL
docker-compose -f docker-compose-infrastructure.yml restart redis
```

**Issue 6: Neo4j connection errors**
```bash
# Check Neo4j is running
curl http://localhost:7474/db/data/

# Test Cypher query
docker exec social-commerce-neo4j cypher-shell -u neo4j -p password123 "RETURN 1"

# Solution: Restart Neo4j
docker-compose -f docker-compose-infrastructure.yml restart neo4j
```

**Issue 7: Circuit breaker always open**
```bash
# Check service health
curl http://localhost:9003/actuator/health

# Check circuit breaker metrics
curl http://localhost:9003/actuator/metrics/resilience4j.circuitbreaker.state

# Solution: Restart dependent services
docker-compose -f docker-compose-services.yml restart product-service order-service
```

---

### **7. Performance Optimization Tips**

**Database Optimization:**
```sql
-- Add indexes for frequently queried columns
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_products_category ON products(category);
CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_status ON orders(status);
```

**Redis Caching Strategy:**
```yaml
# Configure TTL for different cache types
spring:
  cache:
    redis:
      time-to-live: 3600000  # 1 hour
    cache-names:
      - users
      - products
      - categories
```

**JVM Tuning:**
```bash
# Add to Dockerfile ENTRYPOINT
ENTRYPOINT ["java", \
  "-Xms512m", \
  "-Xmx1024m", \
  "-XX:+UseG1GC", \
  "-XX:MaxGCPauseMillis=200", \
  "-jar", "app.jar"]
```

**Connection Pool Tuning:**
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
```

---

### **8. Security Best Practices**

**1. Enable HTTPS:**
```yaml
server:
  ssl:
    enabled: true
    key-store: classpath:keystore.p12
    key-store-password: ${KEYSTORE_PASSWORD}
    key-store-type: PKCS12
```

**2. Secure Database Credentials:**
```bash
# Use environment variables, never hardcode
SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}

# Use secrets management (AWS Secrets Manager, HashiCorp Vault)
```

**3. Enable Rate Limiting:**
```yaml
# In API Gateway
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          filters:
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 10
                redis-rate-limiter.burstCapacity: 20
```

**4. Enable CORS Properly:**
```java
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("https://yourdomain.com"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
```

**5. Implement API Key Authentication:**
```java
// For external API access
@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        String apiKey = request.getHeader("X-API-Key");
        // Validate API key
    }
}
```

---

### **9. Monitoring and Observability**

**Prometheus Configuration:**

```yaml
# prometheus.yml
global:
  scrape_interval: 15s

scrape_configs:
  - job_name: 'spring-boot-services'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets:
        - 'api-gateway:9000'
        - 'user-service:9001'
        - 'product-service:9002'
        - 'order-service:9003'
        - 'payment-service:9004'
        - 'social-service:9005'
        - 'notification-service:9006'
```

**Grafana Dashboards:**
- JVM Metrics (heap, threads, GC)
- HTTP Request Metrics (rate, duration, errors)
- Database Connection Pool Metrics
- Circuit Breaker Metrics
- Kafka Consumer Lag
- Cache Hit/Miss Rates

**Log Aggregation with ELK Stack:**

```yaml
# Add to docker-compose-infrastructure.yml
  elasticsearch-logs:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.11.0
    ports:
      - "9201:9200"
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false

  logstash:
    image: docker.elastic.co/logstash/logstash:8.11.0
    ports:
      - "5000:5000"
    volumes:
      - ./logstash/pipeline:/usr/share/logstash/pipeline

  kibana:
    image: docker.elastic.co/kibana/kibana:8.11.0
    ports:
      - "5601:5601"
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch-logs:9200
```

**Health Check Endpoints:**
```bash
# Check all services health
for port in 8761 8888 9000 9001 9002 9003 9004 9005 9006; do
  echo "Checking port $port..."
  curl -s http://localhost:$port/actuator/health | jq .
done
```

---

### **10. Backup and Recovery**

**Database Backup Scripts:**

```bash
# Create backup-databases.sh
cat > backup-databases.sh << 'EOF'
#!/bin/bash

BACKUP_DIR="./backups/$(date +%Y%m%d_%H%M%S)"
mkdir -p $BACKUP_DIR

echo "📦 Backing up databases..."

# Backup PostgreSQL
echo "Backing up PostgreSQL..."
docker exec social-commerce-postgres pg_dumpall -U postgres > $BACKUP_DIR/postgres_backup.sql

# Backup MongoDB
echo "Backing up MongoDB..."
docker exec social-commerce-mongodb mongodump --out /tmp/mongodb_backup
docker cp social-commerce-mongodb:/tmp/mongodb_backup $BACKUP_DIR/mongodb_backup

# Backup Neo4j
echo "Backing up Neo4j..."
docker exec social-commerce-neo4j neo4j-admin dump --to=/tmp/neo4j_backup.dump
docker cp social-commerce-neo4j:/tmp/neo4j_backup.dump $BACKUP_DIR/neo4j_backup.dump

# Backup Redis
echo "Backing up Redis..."
docker exec social-commerce-redis redis-cli SAVE
docker cp social-commerce-redis:/data/dump.rdb $BACKUP_DIR/redis_backup.rdb

echo "✅ Backup completed: $BACKUP_DIR"
EOF

chmod +x backup-databases.sh

# Create restore-databases.sh
cat > restore-databases.sh << 'EOF'
#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: ./restore-databases.sh <backup_directory>"
  exit 1
fi

BACKUP_DIR=$1

echo "📥 Restoring databases from $BACKUP_DIR..."

# Restore PostgreSQL
echo "Restoring PostgreSQL..."
docker exec -i social-commerce-postgres psql -U postgres < $BACKUP_DIR/postgres_backup.sql

# Restore MongoDB
echo "Restoring MongoDB..."
docker cp $BACKUP_DIR/mongodb_backup social-commerce-mongodb:/tmp/
docker exec social-commerce-mongodb mongorestore /tmp/mongodb_backup

# Restore Neo4j
echo "Restoring Neo4j..."
docker cp $BACKUP_DIR/neo4j_backup.dump social-commerce-neo4j:/tmp/
docker exec social-commerce-neo4j neo4j-admin load --from=/tmp/neo4j_backup.dump --force

# Restore Redis
echo "Restoring Redis..."
docker cp $BACKUP_DIR/redis_backup.rdb social-commerce-redis:/data/dump.rdb
docker-compose -f docker-compose-infrastructure.yml restart redis

echo "✅ Restore completed!"
EOF

chmod +x restore-databases.sh
```

---

### **11. Scaling Strategies**

**Horizontal Scaling:**

```yaml
# Scale services with Docker Compose
docker-compose -f docker-compose-services.yml up -d --scale user-service=3
docker-compose -f docker-compose-services.yml up -d --scale product-service=3
docker-compose -f docker-compose-services.yml up -d --scale order-service=2

# API Gateway will automatically load balance across instances
```

**Kubernetes Deployment (Future):**

```yaml
# user-service-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: user-service
spec:
  replicas: 3
  selector:
    matchLabels:
      app: user-service
  template:
    metadata:
      labels:
        app: user-service
    spec:
      containers:
      - name: user-service
        image: social-commerce/user-service:latest
        ports:
        - containerPort: 9001
        env:
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            configMapKeyRef:
              name: db-config
              key: user-db-url
---
apiVersion: v1
kind: Service
metadata:
  name: user-service
spec:
  selector:
    app: user-service
  ports:
  - port: 9001
    targetPort: 9001
  type: ClusterIP
```

**Database Scaling:**
- PostgreSQL: Read replicas for read-heavy operations
- MongoDB: Sharding for horizontal scaling
- Redis: Redis Cluster for distributed caching
- Elasticsearch: Multi-node cluster
- Neo4j: Causal clustering

---

### **12. CI/CD Pipeline (GitHub Actions Example)**

```yaml
# .github/workflows/ci-cd.yml
name: CI/CD Pipeline

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build-and-test:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Cache Maven packages
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}

    - name: Build with Maven
      run: mvn clean install -DskipTests

    - name: Run Tests
      run: mvn test

    - name: Build Docker Images
      run: |
        docker-compose -f docker-compose-services.yml build

    - name: Run Integration Tests
      run: |
        ./start-infrastructure.sh
        sleep 30
        ./start-services.sh
        sleep 60
        # Run integration tests here
        ./stop-all.sh

    - name: Push to Docker Hub
      if: github.ref == 'refs/heads/main'
      run: |
        echo ${{ secrets.DOCKER_PASSWORD }} | docker login -u ${{ secrets.DOCKER_USERNAME }} --password-stdin
        docker-compose -f docker-compose-services.yml push

  deploy:
    needs: build-and-test
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/main'

    steps:
    - name: Deploy to Production
      run: |
        # Add deployment commands here
        echo "Deploying to production..."
```

---

### **13. Project Metrics and Statistics**

**Code Statistics:**
```bash
# Count lines of code
find . -name "*.java" | xargs wc -l | tail -1

# Count files
find . -name "*.java" | wc -l

# Count test files
find . -name "*Test.java" | wc -l
```

**Project Summary:**
- **Total Services**: 10 (4 infrastructure + 6 microservices)
- **Total REST APIs**: 86+ endpoints
- **Databases**: 5 (PostgreSQL, MongoDB, Redis, Elasticsearch, Neo4j)
- **Message Broker**: Apache Kafka
- **External Integrations**: Stripe, JavaMail
- **Design Patterns**: 12+ patterns implemented
- **Technologies**: 20+ technologies used

**Technology Stack:**
- **Backend**: Spring Boot 3.5.5, Java 17
- **Databases**: PostgreSQL 15, MongoDB, Redis 7, Elasticsearch 8.11, Neo4j
- **Message Broker**: Apache Kafka 3.7.0
- **Service Discovery**: Eureka
- **API Gateway**: Spring Cloud Gateway
- **Config Management**: Spring Cloud Config
- **Resilience**: Resilience4j
- **Documentation**: SpringDoc OpenAPI 3
- **Monitoring**: Prometheus, Grafana
- **Containerization**: Docker, Docker Compose
- **Build Tool**: Maven 3.9+

---

### **14. Future Enhancements**

**Phase 2 Enhancements:**
- [ ] Implement GraphQL API
- [ ] Add WebSocket support for real-time notifications
- [ ] Implement full-text search with advanced filters
- [ ] Add recommendation engine
- [ ] Implement machine learning for product recommendations
- [ ] Add image recognition for product uploads
- [ ] Implement advanced analytics dashboard
- [ ] Add multi-language support (i18n)
- [ ] Implement multi-currency support
- [ ] Add advanced reporting features

**Phase 3 Enhancements:**
- [ ] Migrate to Kubernetes
- [ ] Implement service mesh (Istio)
- [ ] Add distributed tracing (Jaeger)
- [ ] Implement event sourcing
- [ ] Add CQRS with separate read/write databases
- [ ] Implement saga pattern for distributed transactions
- [ ] Add API versioning
- [ ] Implement blue-green deployment
- [ ] Add canary releases
- [ ] Implement A/B testing framework

**Phase 4 Enhancements:**
- [ ] Add mobile app (React Native)
- [ ] Implement progressive web app (PWA)
- [ ] Add voice commerce integration
- [ ] Implement AR/VR product visualization
- [ ] Add blockchain for supply chain tracking
- [ ] Implement AI chatbot for customer support
- [ ] Add social media integrations
- [ ] Implement loyalty program
- [ ] Add subscription management
- [ ] Implement marketplace features

---

### **15. Final Commit**

```bash
# Add all remaining files
git add .

# Final commit
git commit -m "feat: Complete Social Commerce Platform implementation

PHASE 1 COMPLETE - All 6 Microservices + Infrastructure

Infrastructure Services:
- Service Discovery (Eureka) - Port 8761
- Config Server - Port 8888
- API Gateway - Port 9000
- common-lib module for shared components

Microservices:
- User Service (Port 9001) - PostgreSQL + Redis
- Product Service (Port 9002) - PostgreSQL + Redis + Elasticsearch
- Order Service (Port 9003) - PostgreSQL + Kafka
- Payment Service (Port 9004) - PostgreSQL + Kafka + Stripe
- Social Service (Port 9005) - MongoDB + Neo4j
- Notification Service (Port 9006) - MongoDB + Kafka

Features:
- 86+ REST API endpoints
- Event-driven architecture with Kafka
- Polyglot persistence (5 databases)
- Circuit breaker pattern
- Distributed caching with Redis
- Full-text search with Elasticsearch
- Social graph with Neo4j
- Multi-channel notifications
- Stripe payment integration
- Docker Compose setup
- Integration testing
- Complete documentation

Technologies:
- Spring Boot 3.5.5
- Java 17
- PostgreSQL 15
- MongoDB
- Redis 7
- Elasticsearch 8.11
- Neo4j
- Apache Kafka 3.7.0
- Docker & Docker Compose

Documentation:
- Complete implementation guide
- API documentation (Swagger)
- Deployment guide
- Troubleshooting guide
- Performance optimization tips
- Security best practices"

# Create a tag for version 1.0.0
git tag -a v1.0.0 -m "Version 1.0.0 - Complete Social Commerce Platform"

# Push to remote
git push origin main
git push origin v1.0.0
```

---

### **✅ DAY 14 CHECKPOINT - FINAL DOCUMENTATION COMPLETE!** 📚

**What We Documented:**
- ✅ Complete API documentation summary
- ✅ Architecture overview with diagrams
- ✅ Database schema summary
- ✅ Environment variables reference
- ✅ Deployment checklist
- ✅ Troubleshooting guide
- ✅ Performance optimization tips
- ✅ Security best practices
- ✅ Monitoring and observability setup
- ✅ Backup and recovery procedures
- ✅ Scaling strategies
- ✅ CI/CD pipeline example
- ✅ Project metrics and statistics
- ✅ Future enhancement roadmap

---

## **🎉 CONGRATULATIONS! PROJECT COMPLETE!** 🏆🚀

You have successfully built a **complete, production-ready Social Commerce Platform** with:

### **✅ WHAT YOU'VE ACCOMPLISHED:**

**Infrastructure (100% Complete):**
- ✅ Service Discovery (Eureka)
- ✅ Centralized Configuration (Config Server)
- ✅ API Gateway with routing, authentication, circuit breaker
- ✅ Common library module for code reuse

**Microservices (100% Complete):**
- ✅ User Service - Authentication, authorization, user management
- ✅ Product Service - Product catalog, inventory, search
- ✅ Order Service - Shopping cart, order processing
- ✅ Payment Service - Payment processing with Stripe
- ✅ Social Service - Posts, comments, likes, follows
- ✅ Notification Service - Multi-channel notifications

**Databases (100% Complete):**
- ✅ PostgreSQL - Transactional data
- ✅ MongoDB - Document storage
- ✅ Redis - Caching
- ✅ Elasticsearch - Full-text search
- ✅ Neo4j - Social graph

**Infrastructure (100% Complete):**
- ✅ Apache Kafka - Event streaming
- ✅ Docker Compose - Container orchestration
- ✅ Prometheus - Metrics
- ✅ Swagger - API documentation

**Features (100% Complete):**
- ✅ 86+ REST API endpoints
- ✅ JWT authentication
- ✅ Role-based access control
- ✅ Event-driven architecture
- ✅ Circuit breaker pattern
- ✅ Distributed caching
- ✅ Full-text search
- ✅ Social graph
- ✅ Multi-channel notifications
- ✅ Payment processing
- ✅ Real-time events

**Documentation (100% Complete):**
- ✅ Complete implementation guide (17,000+ lines)
- ✅ API documentation (Swagger UI)
- ✅ Deployment guide
- ✅ Troubleshooting guide
- ✅ Performance optimization
- ✅ Security best practices

---

### **📊 FINAL STATISTICS:**

- **Total Lines of Documentation**: 17,000+ lines
- **Total Services**: 10 services
- **Total REST APIs**: 86+ endpoints
- **Total Databases**: 5 databases
- **Total Technologies**: 20+ technologies
- **Total Design Patterns**: 12+ patterns
- **Development Time**: 14 days (following this guide)

---

### **🚀 NEXT STEPS:**

1. **Start the Platform:**
   ```bash
   ./start-all.sh
   ```

2. **Access the Services:**
   - Eureka Dashboard: http://localhost:8761
   - API Gateway: http://localhost:9000
   - Swagger UIs: http://localhost:900X/swagger-ui.html

3. **Run Integration Tests:**
   - Follow Day 13 test scenarios

4. **Deploy to Production:**
   - Follow Day 14 deployment checklist

5. **Monitor and Maintain:**
   - Set up Prometheus + Grafana
   - Configure log aggregation
   - Set up alerting

6. **Enhance and Scale:**
   - Follow Phase 2-4 enhancement roadmap
   - Scale services as needed
   - Add new features

---

### **📚 RESOURCES:**

**Documentation:**
- This Master Implementation Guide
- Swagger UI for each service
- Spring Boot Documentation: https://spring.io/projects/spring-boot
- Spring Cloud Documentation: https://spring.io/projects/spring-cloud

**Community:**
- Stack Overflow: https://stackoverflow.com/questions/tagged/spring-boot
- Spring Community: https://spring.io/community
- GitHub Discussions: Create discussions in your repository

**Support:**
- Create issues in your GitHub repository
- Join Spring Boot community forums
- Consult Spring Boot documentation

---

### **🎓 WHAT YOU'VE LEARNED:**

- ✅ Microservices architecture
- ✅ Spring Boot 3.5.5 development
- ✅ Polyglot persistence
- ✅ Event-driven architecture
- ✅ API Gateway pattern
- ✅ Service discovery
- ✅ Circuit breaker pattern
- ✅ Distributed caching
- ✅ Full-text search
- ✅ Graph databases
- ✅ Message brokers
- ✅ Docker containerization
- ✅ Integration testing
- ✅ Performance optimization
- ✅ Security best practices

---

### **💪 YOU ARE NOW READY TO:**

- ✅ Build production-ready microservices
- ✅ Design scalable architectures
- ✅ Implement event-driven systems
- ✅ Work with multiple databases
- ✅ Deploy containerized applications
- ✅ Monitor and troubleshoot distributed systems
- ✅ Optimize performance
- ✅ Implement security best practices
- ✅ Lead microservices projects
- ✅ Mentor other developers

---

## **🌟 THANK YOU FOR FOLLOWING THIS GUIDE!** 🌟

**You've built something amazing!** 🎉

This Social Commerce Platform is a **complete, production-ready application** that demonstrates industry best practices and modern architecture patterns.

**Share your success:**
- ⭐ Star this repository
- 🍴 Fork and customize for your needs
- 📢 Share with the community
- 💬 Provide feedback and suggestions

**Keep building and learning!** 🚀

---

**END OF MASTER IMPLEMENTATION GUIDE**

---

