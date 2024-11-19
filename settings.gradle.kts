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
    "apps_info"
)

project(":core").projectDir = File("cores/core")
project(":core_data").projectDir = File("cores/core_data")
project(":apps_info").projectDir = File("features/apps_info")
