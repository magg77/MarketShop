// ----------------------------
//  Versiones centralizadas
// ----------------------------

plugins {
    alias(libs.plugins.android.application) apply false
    //alias(libs.plugins.kotlin.android) apply false
    //alias(libs.plugins.androidLib) apply false

    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false

    alias(libs.plugins.kotlinCompose) apply false
    alias(libs.plugins.safeArgs) apply false
    alias(libs.plugins.roomPlugin) apply false
}