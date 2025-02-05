enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "TaskMaster"
include(
    ":app",

    ":core",
    ":core_data",
    ":core_compose",

    ":hub:api",
    ":hub:impl",

    ":apps_info:api",
    ":apps_info:impl",

    ":util"
)

project(":core").projectDir = File(settingsDir, "cores/core")
project(":core_data").projectDir = File("cores/core_data")
project(":core_compose").projectDir = File("cores/core_compose")

project(":hub:api").projectDir = File("features/hub/api")
project(":hub:impl").projectDir = File("features/hub/impl")

project(":apps_info:api").projectDir = File("features/apps_info/api")
project(":apps_info:impl").projectDir = File("features/apps_info/impl")

project(":util").projectDir = File("util")
