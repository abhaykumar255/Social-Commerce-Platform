### Build common-lib
```
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