plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

val vlfVersion: String by project
version = vlfVersion
group = "io.backvision.vlf"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            // kotlinOptions.jvmTarget = "11"
        }
    }

    jvm {
        compilations.all {
            // kotlinOptions.jvmTarget = "11"
        }
    }

    /*
    js(IR) {
        nodejs()
        browser()
        binaries.executable()
    }


    wasmJs {
        nodejs()
        binaries.executable()
    }


    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
     */

    sourceSets {
        commonMain.dependencies {
            api(project(":vlf"))
            api(libs.jb.composeRuntime)
            api(libs.jb.composeFoundation)
        }
        androidMain.dependencies {
        }
        nativeMain.dependencies {
        }
        wasmJsMain.dependencies {
        }
        jsMain.dependencies {
        }
    }
}

val androidCompileSDK: String by project
val androidMinSDK: String by project

android {
    namespace = "io.backvision.vlf"
    compileSdk = androidCompileSDK.toInt()
    defaultConfig {
        minSdk = androidMinSDK.toInt()
    }
    buildFeatures {
        buildConfig = false
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

apply(from = file("../gradle/publish.gradle.kts"))
