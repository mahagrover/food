# Food Ordering App (Demo)

A simple native Android demo app (Kotlin) showing a food menu with a cart, quantity
steppers, running total, and an order confirmation dialog. No backend, no internet
permission needed.

## Get the APK without installing Android Studio

This repo builds the APK entirely on GitHub's servers via GitHub Actions.

1. Create a new **empty** repository on GitHub (don't add a README there).
2. Upload all files from this folder into that repo, preserving the folder structure
   (including the hidden `.github` folder). Easiest way if you don't have git installed:
   on GitHub, use "Add file → Upload files" and drag the whole folder in, or use
   GitHub Desktop.
   - If you do have git:
     ```
     git init
     git add .
     git commit -m "Initial commit"
     git branch -M main
     git remote add origin https://github.com/<your-username>/<your-repo>.git
     git push -u origin main
     ```
3. On GitHub, open your repo → **Actions** tab. GitHub Actions is enabled by default
   for new repos; if you see a prompt to enable workflows, click it.
4. You should see a workflow run start automatically (triggered by the push). If not,
   click **Build APK** in the left sidebar → **Run workflow** → **Run workflow** (green
   button) to trigger it manually.
5. Wait ~2-4 minutes for the run to finish (green checkmark).
6. Click into the finished run → scroll to **Artifacts** at the bottom →
   download **food-ordering-app-debug-apk**. It's a zip containing `app-debug.apk`.
7. Unzip it, transfer `app-debug.apk` to your Android phone (email it to yourself,
   Google Drive, USB, etc.), then tap it to install. You'll need to allow
   "Install unknown apps" for that source when prompted — this is expected for a
   debug APK not from the Play Store.

## What the workflow does

- Checks out your code on a GitHub-hosted Ubuntu runner.
- Installs JDK 17 and the Android SDK (already partially present on the runner;
  the `android-actions/setup-android` step finalizes licenses/components).
- Generates the Gradle wrapper on the fly (so you don't need to commit large
  binary wrapper files) and runs `./gradlew assembleDebug`.
- Uploads the resulting `app-debug.apk` as a downloadable artifact.

## Customizing

- Menu items live in `app/src/main/java/com/example/foodapp/MainActivity.kt`
  (the `foodItems` list) — edit names, prices, emoji, descriptions freely.
- Colors are in `app/src/main/res/values/colors.xml`.
- App name is in `app/src/main/res/values/strings.xml`.
- To build a signed release APK instead of a debug one, you'd add a signing
  config and a keystore secret — ask if you want that added.
