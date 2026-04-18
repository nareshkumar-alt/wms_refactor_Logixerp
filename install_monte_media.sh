#!/bin/bash
# Script to download and install Monte Media Library for video recording

echo "Installing Monte Media Library for video recording..."

# Create temp directory
TEMP_DIR="/tmp/monte_media_install"
mkdir -p "$TEMP_DIR"
cd "$TEMP_DIR"

# Download Monte Screen Recorder
echo "Downloading Monte Screen Recorder..."
wget -q https://github.com/stephenc/monte-screen-recorder/releases/download/v0.7.7.0/monte-screen-recorder-0.7.7.0.jar

if [ ! -f "monte-screen-recorder-0.7.7.0.jar" ]; then
    echo "ERROR: Failed to download Monte Screen Recorder"
    echo "Please download manually from: https://github.com/stephenc/monte-screen-recorder/releases"
    exit 1
fi

# Install to local Maven repository
echo "Installing to local Maven repository..."
mvn install:install-file \
  -Dfile=monte-screen-recorder-0.7.7.0.jar \
  -DgroupId=org.monte \
  -DartifactId=media \
  -Dversion=0.7.7.0 \
  -Dpackaging=jar

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Monte Media Library installed successfully!"
    echo ""
    echo "Next steps:"
    echo "1. Uncomment the Monte Media dependency in pom.xml"
    echo "2. Run: mvn clean compile"
    echo "3. Run your tests - videos will be recorded automatically"
else
    echo "ERROR: Failed to install Monte Media Library"
    exit 1
fi

# Cleanup
cd -
rm -rf "$TEMP_DIR"

