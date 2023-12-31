@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.com.android.library)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.jvmTargetVersion.get()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "core-network"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            // Stdlib
            implementation(libs.kotlin.stdlib)

            // coroutine
            implementation(libs.coroutines.core)

            // Ktor
            implementation(libs.bundles.ktor.common)

            // Core-Logger Module
            implementation(project(":shared:core-logger"))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            // Ktor (OkHttp Engine)
            implementation(libs.bundles.ktor.android)
        }

        iosMain.dependencies {
            // Ktor (Darwin Engine)
            implementation(libs.bundles.ktor.ios)
        }

    }
}

android {
    namespace = "com.spotify.app.core_network.shared"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()
    }
}
