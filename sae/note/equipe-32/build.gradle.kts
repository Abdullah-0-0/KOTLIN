plugins {
    kotlin("jvm") version "2.3.0"
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
}

group = "iut.info1"
version = "1.0"

repositories {
/*
maven {
  url = uri("http://nexus.dep-info.iut-nantes.univ-nantes.prive/repository/public/")
  isAllowInsecureProtocol = true
}
*/
mavenCentral()
}

dependencies {
implementation(fileTree("libs") { include("iut.info1.flip7-*.jar") })
testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")
testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
testImplementation("io.mockk:mockk:1.14.9")
}

javafx {
version = "17"
modules("javafx.controls", "javafx.fxml")
}

kotlin {
jvmToolchain(11)
}

application {
mainClass.set("iut.info1.flip7.prototype.MainKt")
}

tasks.test {
useJUnitPlatform()
}