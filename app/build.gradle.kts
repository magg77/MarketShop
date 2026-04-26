plugins {
    alias(libs.plugins.android.application)
    //alias(libs.plugins.kotlin.android)
    //alias(libs.plugins.androidLib)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.roomPlugin)
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        //languageVersion = org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3
    }
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
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            applicationIdSuffix = ".Debug"
            isDebuggable = true

            manifestPlaceholders["cleartextTrafficPermitted"] = true
            resValue("string", "nameApp", "MarketShop [DEBUG]")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
        resValues = true
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

    packaging {
        resources {
            // Esto elimina la colisión entre hilt-compiler y dagger-compiler
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }

}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    // lifeCycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Coroutines
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.animated)
    implementation(libs.ui.text.google.fonts)

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.navigation.common.ktx)

    // workmanager
    implementation(libs.work.runtime.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.lifecycle.viewmodel)

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
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.work.testing)
}