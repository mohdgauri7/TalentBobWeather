plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.mohdgauri.talentbobweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mohdgauri.talentbobweather"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxjava:3.0.0")
    implementation("com.google.code.gson:gson:2.8.6")

    //networking
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.10.0")
    implementation("com.squareup.okhttp3:okhttp:3.12.1")
    implementation("com.squareup.retrofit2:converter-scalars:2.3.0")

    //rxjava networking
    implementation("io.reactivex.rxjava2:rxjava:2.1.12")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.3.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.0.2")
    implementation("com.google.android.gms:play-services-location:17.1.0")
    implementation("com.squareup.picasso:picasso:2.8")

}