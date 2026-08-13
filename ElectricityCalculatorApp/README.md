# Electricity Consumption Calculator (Android app)

A thin Android WebView wrapper around the standalone `Electricity_Consumption_Calculator.html`
calculator, packaged as an installable APK. All the calculator logic is unchanged — it runs
entirely offline inside the app (no internet permission is requested).

## Build via GitHub Actions (recommended — no local Android SDK needed)

1. Create a new GitHub repository (or reuse an existing one) and push everything in this
   folder to it.
2. The included workflow (`.github/workflows/build-apk.yml`) runs automatically on every
   push to `main`/`master`, and can also be triggered manually from the **Actions** tab
   ("Run workflow").
3. Once the run finishes, open it and download the **electricity-calculator-debug-apk**
   artifact — it contains `app-debug.apk`.
4. Copy that APK to an Android phone and install it (allow "install from unknown sources"
   if prompted).

## Build locally (requires Android Studio / Android SDK)

```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

## Project structure

```
ElectricityCalculatorApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/sid121287/electricitycalculator/MainActivity.kt
│   │   ├── assets/calculator.html   (the original calculator, loaded in a WebView)
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── .github/workflows/build-apk.yml
├── build.gradle
├── settings.gradle
└── gradlew / gradlew.bat / gradle/wrapper/
```
