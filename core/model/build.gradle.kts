// Remove once https://github.com/gradle/gradle/issues/22797 is fixed
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    setSourceCompatibility(libs.versions.java.get())
    setTargetCompatibility(libs.versions.java.get())
}