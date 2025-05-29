plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.competitionapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.competitionapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //System ui
    //noinspection UseTomlInstead
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")
    //bottom bar
    //noinspection UseTomlInstead
    implementation ("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")
    //navController
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.8")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.7.6")
    implementation ("androidx.navigation:navigation-compose:2.7.6")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.9.22")


    //Async Image
    implementation("io.coil-kt:coil-compose:2.4.0")





}