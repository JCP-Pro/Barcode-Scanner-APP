plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.palmare"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.palmare"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val fragment_version = "1.5.7"
    val nav_version = "2.5.2"
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
//    Fragment
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
//    Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    // Retrofit: For REST Webservice calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Retrofit with Scalar Converter: Returns Webservice response JSON as string
    implementation("com.squareup.retrofit2:converter-gson:2.7.0")
//    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    // Coroutines to make the HTTP requests asynchronous(In the background thread)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Okhttp3 for the POST requests
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    // Gson to convert raw JSON to pretty JSON
    implementation("com.google.code.gson:gson:2.8.9")
    //authenticate library
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}