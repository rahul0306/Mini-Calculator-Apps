FROM openjdk:17-slim

RUN apt-get update && apt-get install -y wget unzip git curl zip

ENV ANDROID_SDK_ROOT=/opt/android-sdk

# Download and install Android SDK command line tools
RUN mkdir -p $ANDROID_SDK_ROOT/cmdline-tools && \
    cd $ANDROID_SDK_ROOT/cmdline-tools && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O tools.zip && \
    unzip tools.zip && \
    rm tools.zip && \
    mv cmdline-tools latest

# Update PATH to include sdkmanager
ENV PATH="$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$PATH"

# Accept licenses and install required packages
RUN yes | sdkmanager --sdk_root=${ANDROID_SDK_ROOT} --licenses
RUN sdkmanager --sdk_root=${ANDROID_SDK_ROOT} \
    "platform-tools" \
    "platforms;android-33" \
    "build-tools;34.0.0"

# Set up your app build
WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew assembleDebug || true
