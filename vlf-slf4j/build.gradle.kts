import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//plugins {
//    alias(libs.plugins.kotlinMultiplatform)
//}
plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "io.backvision.vlf"
version = "0.0.1"

repositories {
    mavenCentral()
}

//dependencies {
//    testImplementation(kotlin("test"))
//}

//tasks.test {
//    useJUnitPlatform()
//}
val sourcesJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.map { it.allSource.sourceDirectories })
}

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

//val javadocJar = tasks.getByName("javadocJar")
//val sourcesJar = tasks.getByName("sourcesJar")


configure<PublishingExtension> {
    publications {
        register<MavenPublication>("java") {
            artifact(sourcesJar)
//            artifact(javadocJar)
            afterEvaluate {
                from(components["java"])
            }
            pom {
                name.set("Slf4j Vlf processor")
//                description.set("KOIN - Kotlin simple Dependency Injection Framework")
//                url.set("https://insert-koin.io/")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
//                    url.set("https://github.com/InsertKoinIO/koin")
//                    connection.set("scm:git:https://github.com/InsertKoinIO/koin.git")
                }
                developers {
                    developer {
                        name.set("Johan Öhman")
//                        email.set("arnaud@kotzilla.io")
                    }
                }
            }
        }
    }
}