plugins {
    id("com.android.library")
}

android {
    compileSdk = 34
    namespace = "com.example.infraestructure"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //exoplayer
    implementation (libs.exoplayer.core)
    implementation (libs.exoplayer.dash)
    implementation (libs.exoplayer.ui)
}