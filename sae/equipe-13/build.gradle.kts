plugins {
    kotlin("jvm") version "2.3.0"
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
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

dependencies {
    // Bibliothèque Flip7 fournie sous forme de .jar (cf. libs/iut.info1.flip7-<version>.jar).
    //implementation(fileTree("libs") { include("iut.info1.flip7-*.jar") })
    implementation(fileTree("libs") { include("iut.info1.flip7-1.7.jar") })
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
}

kotlin {
    jvmToolchain(11)
}

javafx {
    version = "17"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}