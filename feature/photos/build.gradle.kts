plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = libs.versions.appId.get() + ".feature.photos"
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }

    buildFeatures.compose = true

    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.design)
    implementation(projects.core.domain)

    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.lifecycle.runtimeCompose)
    implementation(libs.lifecycle.viewmodelCompose)

    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    implementation(libs.hilt.extCompose)
    kapt(libs.hilt.androidCompiler)
    kapt(libs.hilt.extCompiler)
}