// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies {
        // ...
        classpath("com.google.gms:google-services:4.3.2")
    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20-RC2" apply false
}