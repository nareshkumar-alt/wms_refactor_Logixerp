# Installing Monte Media Library for Video Recording

The Monte Media Library is not available in standard Maven Central repositories. You need to install it manually.

## Option 1: Download and Install Manually (Recommended)

1. **Download Monte Media JAR:**
   - Go to: https://github.com/stephenc/monte-screen-recorder/releases
   - Download the latest JAR file (e.g., `monte-screen-recorder-0.7.7.0.jar`)

2. **Install to Local Maven Repository:**
   ```bash
   mvn install:install-file \
     -Dfile=monte-screen-recorder-0.7.7.0.jar \
     -DgroupId=org.monte \
     -DartifactId=media \
     -Dversion=0.7.7.0 \
     -Dpackaging=jar
   ```

3. **Update pom.xml:**
   Replace the Monte Media dependency with:
   ```xml
   <dependency>
       <groupId>org.monte</groupId>
       <artifactId>media</artifactId>
       <version>0.7.7.0</version>
   </dependency>
   ```

## Option 2: Use System Dependency (Alternative)

1. **Download the JAR** and place it in `lib/` folder in your project

2. **Update pom.xml** to use system scope:
   ```xml
   <dependency>
       <groupId>org.monte</groupId>
       <artifactId>media</artifactId>
       <version>0.7.7.0</version>
       <scope>system</scope>
       <systemPath>${project.basedir}/lib/monte-screen-recorder-0.7.7.0.jar</systemPath>
   </dependency>
   ```

## Option 3: Disable Video Recording (Temporary)

If you don't need video recording right now, you can disable it:

1. **Remove the Monte Media dependency** from pom.xml
2. **Set in config.properties:**
   ```properties
   video_recording_enabled=false
   ```

The framework will work without video recording functionality.

## Quick Fix Script

Run this script to download and install automatically:

```bash
#!/bin/bash
# Download and install Monte Media Library

cd /tmp
wget https://github.com/stephenc/monte-screen-recorder/releases/download/v0.7.7.0/monte-screen-recorder-0.7.7.0.jar

mvn install:install-file \
  -Dfile=monte-screen-recorder-0.7.7.0.jar \
  -DgroupId=org.monte \
  -DartifactId=media \
  -Dversion=0.7.7.0 \
  -Dpackaging=jar

echo "Monte Media Library installed successfully!"
```

