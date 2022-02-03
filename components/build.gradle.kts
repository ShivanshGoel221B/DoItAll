plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk

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

    implementation(project(Modules.core))

    implementation(AndroidX.core)

    implementation(AndroidX.lifeCycle)

    testImplementation(Tests.jUnit)
    androidTestImplementation(Tests.androidJUnit)
    androidTestImplementation(Tests.espresso)

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
    //Navigation
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigation)
    //Accompanist
    implementation(Accompanist.systemUiController)

    // UI Tests
    androidTestImplementation(ComposeTest.uiTestJunit4)
    debugImplementation(ComposeTest.uiTestManifest)
}