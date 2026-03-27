plugins {
    kotlin("jvm") version "1.9.21"
    application
}

application {
    mainClass.set("VoitureMain")
}

repositories {
    maven {
        url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/maven-central/")
        isAllowInsecureProtocol = true
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.2")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")
    testImplementation("net.sourceforge.plantuml:plantuml:1.2023.5")
    testImplementation(files("libs/univ.nantes.umlchecker-2.1.jar"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
