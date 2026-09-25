plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ute.btl"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.ute.btl"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }
}

// ── THÊM KHỐI DEPENDENCIES NÀY VÀO CUỐI FILE ────────────────────────
dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}