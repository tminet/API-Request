// Remove once https://github.com/gradle/gradle/issues/22797 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = libs.versions.appId.get() + ".feature.users"
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
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:design"))
    implementation(project(":core:domain"))

    implementation(libs.lifecycle.runtimeKtx)
    implementation(libs.lifecycle.runtimeCompose)
    implementation(libs.lifecycle.viewmodelCompose)

    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    implementation(libs.hilt.extCompose)
    kapt(libs.hilt.androidCompiler)
    kapt(libs.hilt.extCompiler)
}