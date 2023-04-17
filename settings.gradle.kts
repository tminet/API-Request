pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "API-Request"

include(":app")
include(":core:model")
include(":core:common")
include(":core:design")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":feature:users")
include(":feature:userprofile")
include(":feature:posts")
include(":feature:albums")
include(":feature:photos")