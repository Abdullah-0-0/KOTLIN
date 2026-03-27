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
    implementation(files("libs/info.but1.collections.nutarray-1.1.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
    implementation("net.sourceforge.plantuml:plantuml:1.2023.5")
    implementation(files("libs/univ.nantes.umlchecker-2.1.jar"))
    //testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}