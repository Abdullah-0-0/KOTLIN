plugins {
    kotlin("jvm") version "2.3.0"
    application
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
    // plugin javafxplugin supprimé
}

group = "iut.info1"
version = "1.0"

repositories {
//    maven {
//        url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/public/")
//        isAllowInsecureProtocol = true
//    }
    mavenCentral()
}

val javafxVersion = "21"

dependencies {
    implementation(fileTree("libs") { include("iut.info1.flip7-*.jar") })
    implementation("org.openjfx:javafx-base:$javafxVersion:linux")
    implementation("org.openjfx:javafx-controls:$javafxVersion:linux")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:linux")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:linux")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("ihm.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    environment("DISPLAY", System.getenv("DISPLAY") ?: ":0")
}