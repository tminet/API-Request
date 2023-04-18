plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    setSourceCompatibility(libs.versions.java.get())
    setTargetCompatibility(libs.versions.java.get())
}