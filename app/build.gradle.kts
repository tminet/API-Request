plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    val verCode = 3
    val verName = "2.0.0"

    buildToolsVersion = libs.versions.buildTools.get()
    namespace = libs.versions.appId.get()
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        applicationId = libs.versions.appId.get()
        minSdk = libs.versions.androidApiMin.get().toInt()
        targetSdk = libs.versions.androidApiTarget.get().toInt()
        versionCode = verCode
        versionName = verName
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }

        release {
            // signing with debug key for demo purposes only
            signingConfig = signingConfigs.getByName("debug")

            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }

    kotlinOptions.jvmTarget = libs.versions.java.get()

    buildFeatures.compose = true

    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()

    packagingOptions.resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.design)

    implementation(projects.feature.users)
    implementation(projects.feature.userprofile)
    implementation(projects.feature.posts)
    implementation(projects.feature.albums)
    implementation(projects.feature.photos)

    implementation(libs.androidx.coreKtx)

    implementation(libs.navigation.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.androidCompiler)
}