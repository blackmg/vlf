import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//plugins {
//    alias(libs.plugins.kotlinMultiplatform)
//}
plugins {
    kotlin("jvm")
}

//group = "io.backvision.vlf"
//version = "unspecified"

repositories {
    mavenCentral()
}

//dependencies {
//    testImplementation(kotlin("test"))
//}

//tasks.test {
//    useJUnitPlatform()
//}

tasks.withType<KotlinCompile>().all {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    api(project(":vlf"))
    // https://mvnrepository.com/artifact/org.slf4j.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.17")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
    testImplementation(libs.kotlin.test)

}

kotlin {
//    jvm()
    jvmToolchain(17)
}