plugins {
    kotlin("jvm") version "1.9.21"
    application
}

application {
    mainClass.set("TriangleRectangleKt")
}

repositories {
    maven {
        url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/maven-central/")
        isAllowInsecureProtocol = true
    }
    mavenCentral()
}

dependencies {
    implementation(files("libs/info.but1.collections.nutarray-1.0.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
