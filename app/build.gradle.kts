
plugins {

    // Android Gradle Plugin
    alias(libs.plugins.android.application)
    //alias(libs.plugins.androidLib)

    // Kotlin
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.kotlinParcelize)
    //alias(libs.plugins.kotlinRoom)

    // KSP
    alias(libs.plugins.ksp)

    // Hilt
    alias(libs.plugins.hilt)

    // Navigation Safe Args
    alias(libs.plugins.safeArgs)

    // Room
    alias(libs.plugins.roomPlugin)


}

android {
    namespace = "com.maggiver.marketshop"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.maggiver.marketshop"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {

        debug {
            applicationIdSuffix = ".Debug"
            isDebuggable = true

            manifestPlaceholders["cleartextTrafficPermitted"] = true
            resValue("string", "nameApp", "MarketShop [DEBUG]")
        }

        release {

            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {
    // Core
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.constraintlayout.compose)

    // Coroutines
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)

    // WorkManager
    implementation(libs.work.runtime.ktx)

    // Compose + ViewModel + LiveData
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.runtime.livedata)
    implementation(libs.lifecycle.runtime.compose)

    // Compose UI
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.animated)

    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.compose.ui.tooling.debug)

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.navigation.common.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.hilt.navigation.compose)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.room.paging)

    // Retrofit & Gson
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation (libs.okhttp)
    implementation(libs.logging.interceptor) //log interceptor

    // Material 3
    implementation(libs.material3)
    implementation(libs.material.icons.extended)
    implementation(libs.material3.components.ui)
    implementation(libs.adaptive)
    implementation(libs.adaptive.layout)
    implementation(libs.adaptive.navigation)



    // SplashScreen & Accompanist
    implementation(libs.splashscreen)
    implementation(libs.accompanist.systemuicontroller)

    // Lottie
    implementation(libs.lottie.compose)

    //show images
    implementation(libs.coil.compose)

    // Timber
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.android.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bom))
}