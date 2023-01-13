// Remove once https://github.com/gradle/gradle/issues/22797 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    val javaVersion = JavaVersion.VERSION_1_8

    namespace = libs.versions.appId.get() + ".core.data"
    compileSdk = libs.versions.androidApiTarget.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()

        jvmTarget = javaVersion.toString()

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))

    implementation(libs.coroutines.android)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}