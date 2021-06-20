plugins {
    kotlin("multiplatform") version "1.5.10"
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "me.zhoukaiwen"
version = "1.0-SNAPSHOT"

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android()
    macosX64("macosX64") {
        binaries {
            framework {
                baseName = "happy-lib"
            }
        }
    }
    iosX64("iosX64") {
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
        val commonMain by getting
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

        val iosMain by sourceSets.creating {
            dependsOn(commonMain)
        }

        val macosX64Main by getting

        val iosArm64Main by getting {
            dependsOn(iosMain)
        }

        val iosX64Main by getting {
            dependsOn(iosMain)
        }

        val iosTest by creating
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