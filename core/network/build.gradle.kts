// Remove once https://github.com/gradle/gradle/issues/22797 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = libs.versions.appId.get() + ".core.network"
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()

        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.androidCompiler)

    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.serialization.kotlinxJson)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converterGson)
}