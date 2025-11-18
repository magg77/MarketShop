# 📱 MarketShop Android Application Project

You can view the list of products available to you, the details of each product in full dialog, save your favorite products and view them without an internet connection, and check the network status of your device.

# Screenshots
![Test Image 2](https://github.com/magg77/MarketShop/blob/feature/favorites/screens/screns.png)


# 🛠 Requirements
MarketShop is a Kotlin application for Android mobile devices. Before running the project, please ensure you consider the following requirements:

- Android Studio Otter | 2025.2.1 ->  Build #AI-252.25557.131.2521.14344949, built on October 28, 2025
- Kotlin compiler version = "2.1.21"
- versión de Java en Gradle: Runtime version: 21.0.8+-14196175-b1038.72 amd64
- Java compileOptions = JvmTarget = "17"
- Uses AndroidX libraries and Jetpack components
- Internet connection (to download dependencies and consume external APIs, if applicable)
- Minimum version 23
- Maximum version 36

# 🚀 Instructions for running the project

Run the following commands in git bash

- git clone https://github.com/magg77/MarketShop
- cd [your-repository]
- Open the project with the Android Studio IDE
- Sync dependencies with gradle
- Run the following command in the Android Studio terminal: ./gradlew clean build

# 🚀 APIs and implemented services
- https://dummyjson.com/
- get: products
- get: products/{id}

# 📦 Technologies and libraries used in the MarketShop
This is a clean architecture app  built with

- Kotlin: Main development language.
- MVVM
- Coroutines
- Flow
- Extension Functions
- Dagger Hilt
- Retrofit
- Room
- Navigation Components
- Cache Strategy (Repository Pattern)
- Coroutines Flow
- ViewBinding
- Livedata
- Material3
- AndroidX and Jetpack Components: Modern architecture and backward compatibility.
- tom.libs: dependency management

# 🏗️ Technical Decisions
1️⃣ Project Architecture
- MVVM (Model-View-ViewModel) has been implemented to ensure separation of responsibilities and better maintainability.

2️⃣ Dependency Management
- tom.libs makes dependency management easier by centralizing versions and references into a single file, reducing redundancy and avoiding conflicts between libraries. This improves project maintainability and consistency.

3️⃣ Dependency Injection
- Hilt is used for dependency injection, which makes the code easier to scale.

![Test Image 1](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


# Support 😄

Follow me on Github [User :>](https://github.com/magg77/MoviesHD)  ⭐



