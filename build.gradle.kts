plugins {
    kotlin("multiplatform") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "me.zhoukaiwen"
version = "1.0-SNAPSHOT"
val ktorVersion = "1.6.0"

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android()
    macosX64("macos") {
        binaries {
            framework {
                baseName = "happy-lib"
            }
        }
    }
    iosX64("ios") {
        binaries {
            framework {
                baseName = "happy-lib"
            }
        }
    }
    iosArm64("iosArm64") {
        binaries {
            framework {
                baseName = "happy-lib"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val macosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-curl:$ktorVersion")
            }
        }

        val iosTest by getting

        val macosTest by getting {
            dependsOn(commonTest)
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}