pluginManagement {
    repositories {
        maven {
            url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/gradle-plugins/")
            isAllowInsecureProtocol = true
        }
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "dev.objets.tp3.exo2"