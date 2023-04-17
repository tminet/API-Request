// Remove once https://github.com/gradle/gradle/issues/22797 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = libs.versions.appId.get() + ".core.domain"
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.data)

    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.androidCompiler)
}