plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(project(Modules.database))
    implementation(project(Modules.core))
    implementation(project(Modules.components))

    implementation(AndroidX.core)
    implementation(AndroidX.datastore)

    implementation(project(Modules.classGroup))
    implementation(project(Modules.todoTasks))

    implementation(AndroidX.lifeCycle)

    testImplementation(Tests.jUnit)
    androidTestImplementation(Tests.androidJUnit)
    androidTestImplementation(Tests.espresso)

    //Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    kaptAndroidTest(Hilt.compiler)

    //Compose
    implementation(Compose.ui)
    // Tooling support (Previews, etc.)
    implementation(Compose.tooling)
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(Compose.foundation)
    // Material Design
    implementation(Compose.material)
    // Material design icons
    implementation(Compose.icons)
    implementation(Compose.iconsExtended)
    // Integration with observables
    implementation(Compose.liveData)
    implementation(Compose.rxJava)
    implementation(Compose.activity)
    //Navigation
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigation)
    //Accompanist
    implementation(Accompanist.systemUiController)
    implementation(Accompanist.pager)

    //Navigation Library
    implementation(Destinations.core)
    ksp(Destinations.ksp)
    implementation(Destinations.animation)

    // UI Tests
    androidTestImplementation(ComposeTest.uiTestJunit4)
    debugImplementation(ComposeTest.uiTestManifest)
}