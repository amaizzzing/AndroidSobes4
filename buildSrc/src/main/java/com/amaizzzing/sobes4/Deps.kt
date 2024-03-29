import java.util.*

private const val kotlinVersion = "1.5.21"
private const val kotlinCoreCtxVersion = "1.7.0"
private const val androidGradleVersion = "7.0.3"

//support libs
private const val appcompatVersion = "1.4.0-alpha03"
private const val constraintLayoutVersion = "2.0.4"
private const val materialVersion = "1.4.0"
private const val fragmentVersion = "1.3.2"
private const val coordinatorLayoutVersion = "1.1.0"

//test libs
private const val junitVersion = "4.12"
private const val runnerVersion = "1.1.2"
private const val espressoVersion = "3.3.0"

//retrofit
private const val retrofitVersion = "2.9.0"

//room
private const val roomVersion = "2.3.0"
private const val roomCompilerVersion = "2.3.0-rc01"

//dagger
private const val daggerVersion = "2.38.1"

//glide
private const val glideVersion = "4.12.0"

//lifecycle
private const val lifecycle_version = "2.4.0-alpha03"

//coroutines
private const val coroutines_android_version = "1.5.1"

//navigationAndroid
private const val navigation_fragment_version = "2.3.5"

//jodaTime
private const val joda_time_version = "2.10.10"

//worker service
private const val worker_version = "2.7.0-beta01"

//paging
private const val paging_version = "3.1.0-rc01"

object Dependencies{
    object Android {
        const val minSdkVersion = 21
        const val targetSdkVersion = 31
        const val compileSdkVersion = 31
        const val applicationId = "com.example.sobes4"
        const val versionCode = 1
        const val versionName = "1.0.0"
    }
    object Kotlin{
        const val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
        const val kotlin_stdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val kotlinCoreKtx = "androidx.core:core-ktx:$kotlinCoreCtxVersion"
    }

    object BuildPlugins {
        const val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val googleServicesMaps = "com.google.android.gms:play-services-maps:18.0.0"
    }
    object SupportLibs {
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:$coordinatorLayoutVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"
    }
    object TestLibs {
        const val junit = "junit:junit:$junitVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val runner = "androidx.test:runner:$runnerVersion"
    }
    object Room {
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val room_ktx = "androidx.room:room-ktx:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    }
    object Dagger {
        const val dagger = "com.google.dagger:dagger:$daggerVersion"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
        const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    }
    object Glide {
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    }
    object LifeCycle {
        const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
        const val lifeCycleExtensions = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    }
    object Coroutines {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_android_version"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_android_version"
    }
    object AndroidNavigation {
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigation_fragment_version"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigation_fragment_version"
    }
    object JodaTime {
        const val jodaTime = "joda-time:joda-time:$joda_time_version"
    }
    object Worker {
        const val workRuntimeKtx = "androidx.work:work-runtime-ktx:$worker_version"
        const val workGcm = "androidx.work:work-gcm:$worker_version"
        const val workMultiprocess = "androidx.work:work-multiprocess:$worker_version"
    }
}