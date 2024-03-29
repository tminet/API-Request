plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    buildToolsVersion = libs.versions.buildTools.get()
    namespace = libs.versions.appId.get() + ".core.data"
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }

    kotlinOptions.jvmTarget = libs.versions.java.get()
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    ksp(libs.hilt.androidCompiler)
}