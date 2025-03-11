# IVerify Android Application - Device List Viewer

This Android application fulfills a company requirement to provide a single-screen view of enrolled devices within the IVerify enterprise product. It allows administrators to view essential device information and search for specific devices.

## Description

The application provides a streamlined interface for enterprise administrators to monitor enrolled devices. It displays key details such as user names, last scan dates, and access codes. Given the potential for a large number of devices, a search feature is included to facilitate quick retrieval of specific device information.

Security is a paramount concern. Recognizing the potential for compromised devices, the application implements robust security measures to protect sensitive data. This includes secure network communication and secure storage of authentication tokens.

The application assumes that users have previously logged in and received an authentication token. This token is securely stored and used for subsequent API requests, eliminating the need for repeated login prompts.

## Features

* **Device List Display:** Presents a comprehensive list of enrolled devices, showing user names, last scan dates, and access codes.
* **Device Search:** Enables to search for devices by user name.
* **Secure Authentication:** Utilizes a securely stored authentication token for API requests.
* **Secure Network Communication:** Employs HTTPS to protect data during network transmission.
* **Splash Screen:** Initial loading screen.(Empty)
* **Sign-in Screen:** User authentication screen.(Not Fully Functional)
* **Device List Screen:** Main screen displaying device information.

## Technologies

* **Kotlin:** Primary development language.
* **Jetpack Compose:** Modern UI toolkit.
* **Coroutines:** Asynchronous programming.
* **Retrofit:** HTTP client.
* **OkHttp:** Network communication.
* **Hilt:** Dependency injection.
* **Encrypted Shared Preferences:** Secure data storage.
* **CLEAN + MVVM :** Architectural pattern.
* **Gradle:** Build system.

## Architecture

The application is structured using **Clean Architecture** principles, with **MVVM (Model-View-ViewModel)** for the presentation layer, promoting separation of concerns and testability:

* **Data Layer:** Handles remote data sources (API) and local data storage.
* **Domain Layer:** Contains business logic and use cases, independent of specific implementations.
* **Presentation Layer:** Manages UI state and interactions, using ViewModels to communicate with the domain layer.

## Security

* **HTTPS:** Encrypted network communication.
* **Token-Based Authentication:** Secure API access.
* **Encrypted Shared Preferences:** Secure token storage.

## Setup

1.  **Clone:** `git clone <https://github.com/ArslanAhmad0/iVerifyTask>
2.  **Android Studio:** Open the project.
3.  **Gradle Sync:** Sync project dependencies.
4.  **Run:** Build and run on an Android 13 device/emulator.

## Dependencies

* Jetpack Compose
* Kotlin Coroutines
* Retrofit
* OkHttp
* Hilt
* Encrypted Shared Preferences
