FROM eclipse-temurin:21-jdk

# Chrome aur dependencies install karo
RUN apt-get update && apt-get install -y \
    maven \
    wget \
    curl \
    unzip \
    chromium \
    chromium-driver \
    --no-install-recommends

# Display ke liye
RUN apt-get install -y xvfb

WORKDIR /app
COPY . .

# Headless mode mein tests run karo
CMD ["mvn", "test"]
