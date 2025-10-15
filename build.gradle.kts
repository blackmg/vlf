plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
    id("org.jetbrains.kotlinx.atomicfu") version "0.30.0-beta" apply true

}
