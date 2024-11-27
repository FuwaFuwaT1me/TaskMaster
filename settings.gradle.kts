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
    ":apps_info",
    ":apps_info:api",
    ":apps_info:impl"
)

project(":core").projectDir = File("cores/core")
project(":core_data").projectDir = File("cores/core_data")
project(":core_compose").projectDir = File("cores/core_compose")
project(":apps_info").projectDir = File("features/apps_info")
project(":apps_info:api").projectDir = File("features/apps_info/api")
project(":apps_info:impl").projectDir = File("features/apps_info/impl")
