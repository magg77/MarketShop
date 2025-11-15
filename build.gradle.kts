// ----------------------------
//  Versiones centralizadas
// ----------------------------


plugins {

    // Android Gradle Plugin
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.androidLib) apply false

    // Kotlin
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinCompose) apply false

    // KSP
    alias(libs.plugins.ksp) apply false

    // Hilt
    alias(libs.plugins.hilt) apply false

    // Navigation Safe Args
    alias(libs.plugins.safeArgs) apply false

    // Room
    alias(libs.plugins.roomPlugin) apply false


}