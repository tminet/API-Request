plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    buildToolsVersion = libs.versions.buildTools.get()
    namespace = libs.versions.appId.get() + ".core.design"
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }

    kotlinOptions.jvmTarget = libs.versions.java.get()

    buildFeatures.compose = true

    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
}

dependencies {
    api(libs.androidx.activityCompose)

    api(platform(libs.compose.bom))
    api(libs.compose.animation)
    api(libs.compose.foundation)
    api(libs.compose.runtime)
    api(libs.compose.ui)
    api(libs.compose.material3)

    api(libs.coil.compose)

    implementation(libs.compose.materialIconsExtended)

    api(libs.accompanist.systemUiController)
}