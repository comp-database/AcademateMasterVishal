pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AcademateMaster"
include(":app")
include(":facultyModule")
include(":hrMoudule")
include(":studentMoudule")
include(":hodMoudule")
include(":admissionModule")
include(":common")
