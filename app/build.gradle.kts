plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    //  todo: review change example to another name
    namespace = "com.example.movieapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        //  todo: review change example to another name
        applicationId = "com.example.movieapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
// todo: review move this to gradle properties or any other gradle config file
        buildConfigField(
            "String",
            "BEARER_TOKEN",
            "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MjRiMDM5MmJiMzkwOTg4YjI4ZWI3YzE1Yjg4MTI5NCIsIm5iZiI6MTc4OTAzNTI3NS4zNTEsInN1YiI6IjZhYTI4MzBiYTMzZjcwYWZjZmQwNTA4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FaoYuZFTNDiqYuwv7KmoUozdLjUPbwgy5AK9C1Zy9bU\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // todo: review move to toml
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("io.coil-kt:coil-compose:2.7.0")
}