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
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        lintOptions {
            disable("ExpiredTargetSdkVersion")
        }
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

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-Xlint:unchecked")
    }
}

dependencies {
    // AndroidX e Material Components
    implementation(libs.appcompat)  // AppCompat para compatibilidade de componentes Android
    implementation ("com.google.android.material:material:1.12.0")  // Material Components para o design de interfaces
    implementation ("androidx.drawerlayout:drawerlayout:1.1.1")// Para NavigationView
    implementation ("androidx.navigation:navigation-ui:2.8.3")
    implementation ("androidx.navigation:navigation-fragment:2.8.3")
    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation(libs.activity)  // Android Activity
    implementation(libs.constraintlayout)  // ConstraintLayout para layouts responsivos

    // Testes
    testImplementation(libs.junit)  // JUnit para testes unitários
    androidTestImplementation(libs.ext.junit)  // JUnit para testes instrumentados Android
    androidTestImplementation(libs.espresso.core)  // Espresso para testes de UI

    // Google Play Services e Maps
    implementation ("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.android.gms:play-services-maps:19.0.0")  // Google Maps

    // Firebase (usando o BOM para gerenciar versões)
    implementation(platform(libs.firebase.bom))  // BOM do Firebase para garantir compatibilidade de versões
    implementation("com.google.firebase:firebase-database:21.0.0")  // Firebase Realtime Database
    implementation("com.google.firebase:firebase-auth:23.1.0")  // Firebase Auth
    implementation("com.google.firebase:firebase-storage:21.0.1")  // Firebase Storage

    // Glide para carregamento de imagens
    implementation("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")
}
