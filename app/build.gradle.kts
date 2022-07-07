plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
}


android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk =  Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Hilt
    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.compiler)

    // Lifecycle
    implementation(Dependencies.Lifecycle.lifecycleViewmodel)
    implementation(Dependencies.Lifecycle.lifecycleLivedata)

    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Network
    implementation(Dependencies.Network.retrofit2)
    implementation(Dependencies.Network.retrofit2Gson)
    implementation(Dependencies.Network.logging)

    // UI
    implementation(Dependencies.UI.constraintLayout)
    implementation(Dependencies.UI.recyclerView)

    // Android
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.appCompatResources)
    implementation(Dependencies.Android.material)

    // Test
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)
}