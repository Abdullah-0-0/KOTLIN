pluginManagement {
    repositories {
        maven {
            url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/public/")
            isAllowInsecureProtocol = true
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "dev.objets.tp4.exo2"