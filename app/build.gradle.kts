plugins {
    id("com.android.application")
    id("com.google.gms.google-services")  // Alias para o plugin do Google Services
}

android {
    namespace = "com.biomaamazonia.appei"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.biomaamazonia.appei"
        minSdk = 23
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)  // Dependência do AppCompat
    implementation(libs.material)  // Dependência do Material Components
    implementation(libs.activity)  // Dependência do Android Activity
    implementation(libs.constraintlayout)  // Dependência do ConstraintLayout
    testImplementation(libs.junit)  // Dependência de JUnit para testes
    androidTestImplementation(libs.ext.junit)  // Dependência de JUnit para testes Android
    androidTestImplementation(libs.espresso.core)  // Dependência do Espresso para testes
    implementation(libs.play.services.maps)  // Dependência para Play Services Maps
    implementation(libs.firebase.database)  // Dependência do Firebase Database
    implementation(libs.firebase.auth)  // Dependência do Firebase Auth
    implementation(platform(libs.firebase.bom))  // Usando o BOM do Firebase para garantir versões consistentes
    implementation("com.google.android.gms:play-services-auth:21.2.0")  // Usando o Play Services Auth do catálogo de versões
}
