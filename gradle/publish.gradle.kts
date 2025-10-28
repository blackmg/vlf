apply(plugin = "maven-publish")

//val javadocJar = tasks.getByName("javadocJar")

configure<PublishingExtension> {
    publications {
        withType<MavenPublication> {
            //          artifact(javadocJar)
            pom {
                name.set("Vlf")
                description.set("VLF - Vision Log Facade")
                //url.set("https://insert-koin.io/")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
                    //url.set("https://github.com/InsertKoinIO/koin")
                    //connection.set("scm:git:https://github.com/InsertKoinIO/koin.git")
                }
                developers {
                    developer {
                        name.set("Johan Öhman")
                        // email.set("arnaud@kotzilla.io")
                    }
                }
            }
        }
    }
}

// apply(from = file("../../gradle/signing.gradle.kts"))