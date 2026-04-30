plugins {
    kotlin("jvm") version "2.0.21"
}

group = "iut.info1"
version = "2025.1.0"

repositories {
    maven {
        url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/public/")
        isAllowInsecureProtocol = true
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}