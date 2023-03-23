import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// issue ref: https://github.com/gradle/gradle/issues/22797 (will be fixed in Gradle 8.1)
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt.android) apply false
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            jvmTarget = libs.versions.java.get()
        }
    }
}