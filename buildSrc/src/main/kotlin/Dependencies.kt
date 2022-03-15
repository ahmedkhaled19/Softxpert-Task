/**
 * Created by Ahmed Khaled on 09/08/2021.
 * Lasr update on 31/1/2022
 */

object Versions {

    //AndroidX versions
    const val ANDROID_CORE_VERSION = "1.7.0"
    const val APP_COMPAT_VERSION = "1.4.1"
    const val LIFECYCLE_VIEW_MODEL_VERSION = "2.4.0"
    const val LIFECYCLE_LIVEDATA_VERSION = "2.4.0"
    const val LIFECYCLE_RUNTIME_VERSION = "2.4.0"
    const val LIFECYCLE_EXTENSIONS_VERSION = "2.2.0"

    //Kotlin
    const val KOTLIN_VERSION = "1.6.10"

    //Materials
    const val MATERIALS_VERSION = "1.5.0"
    const val SWIPE_REFRESH_VERSION = "1.1.0"

    //Navigation Fragment
    const val NAVIGATION_FRAGMENT_VERSION = "2.4.0"
    const val NAVIGATION_UI_VERSION = "2.4.0"

    //Network
    const val RETROFIT_VERSION = "2.9.0"
    const val RETROFIT_CONVERTER_VERSION = "2.9.0"
    const val GOOGLE_GSON_VERSION = "2.8.9"
    const val LOGGING_INTERCEPTORS_VERSION = "4.9.3"
    const val JACKSON_VERSION = "2.9.0"
    const val OKHTTP3_VERSION = "4.9.3"

    //Room
    const val ROOM_VERSION = "2.4.1"

    //Hilt
    const val HILT_VERSION = "2.40.5"
    const val HILT_VIEW_MODEL_VERSION = "1.0.0-alpha03"
    const val HILT_COMPILER_VERSION = "1.0.0"

    //Coroutines
    const val COROUTINES_VERSION = "1.3.9"

    //Glide
    const val GLIDE_VERSION = "4.12.0"

    //Auto Resize
    const val SSP_VERSION = "1.0.6"
    const val SSD_VERSION = "1.0.6"

    //Testing
    const val JUNIT_VERSION = "4.13"
    const val EXT_JUNIT_VERSION = "1.1.2"
    const val TEST_RUNNER_VERSION = "1.2.0"
    const val TEST_CORE = "2.1.0"
    const val MOCKITO_CORE_VERSION = "1.10.19"
    const val ESPRESSO_CORE_VERSION = "3.3.0"
    const val COROUTINES_TEST_VERSION = "1.5.1"
    const val GOOGLE_TRUTH_VERSION = "1.1.3"
    const val HILT_TEST_VERSION = "2.38.1"
    const val OKHTTP_MOCK_SERVER = "4.9.1"
}

object AppConfig {
    const val applicationId = "android.ahmed.khaled.cleanarchtemplate"
    const val compileSdkVersion = 32
    const val buildToolsVersion = "29.0.2"
    const val minSdkVersion = 23
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
}

object Kotlin {
    const val KOTLIN_STDLIB_JDK =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_VERSION}"
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN_VERSION}"
}

object Androidx {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.ANDROID_CORE_VERSION}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VIEW_MODEL_VERSION}"
    const val LIVEDATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_LIVEDATA_VERSION}"
    const val RUNTIME =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME_VERSION}"
    const val EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXTENSIONS_VERSION}"
}

object Material {
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIALS_VERSION}"
    const val SWIPE_REFRESH_LAYOUT =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_VERSION}"
}

object Navigation {
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION_FRAGMENT_VERSION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION_UI_VERSION}"
}

object Network {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val RETROFIT_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_CONVERTER_VERSION}"
    const val GSON = "com.google.code.gson:gson:${Versions.GOOGLE_GSON_VERSION}"
    const val LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING_INTERCEPTORS_VERSION}"
    const val JAKSON = "com.squareup.retrofit2:converter-jackson:${Versions.JACKSON_VERSION}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3_VERSION}"
}

object Room {
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    const val ROOM_KPT = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
}

object Hilt {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT_VERSION}"
    const val HILT_KPT = "com.google.dagger:hilt-android-compiler:${Versions.HILT_VERSION}"
    const val HILT_VIEW_MODEL =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_VIEW_MODEL_VERSION}"
    const val HILT_COMPILER_KPT = "androidx.hilt:hilt-compiler:${Versions.HILT_COMPILER_VERSION}"
}

object Coroutines {
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
}

object Glide {
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
}

object AutoResize {
    const val TEXTS_AUTO_RESIZE = "com.intuit.ssp:ssp-android:${Versions.SSP_VERSION}"
    const val SCAlE_AUTO_RESIZE = "com.intuit.sdp:sdp-android:${Versions.SSD_VERSION}"
}

object Testing {
    const val JUNIT_TEST = "junit:junit:${Versions.JUNIT_VERSION}"
    const val JUNIT_EXT_TEST = "androidx.test.ext:junit:${Versions.EXT_JUNIT_VERSION}"
    const val TEST_RUNNER = "androidx.test:runner:${Versions.TEST_RUNNER_VERSION}"
    const val TEST_CORE = "androidx.arch.core:core-testing:${Versions.TEST_CORE}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.MOCKITO_CORE_VERSION}"
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE_VERSION}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES_TEST_VERSION}"
    const val GOOGLE_TRUTH = "com.google.truth:truth:${Versions.GOOGLE_TRUTH_VERSION}"
    const val HILT_TEST =
        "com.google.dagger:hilt-android-testing:${Versions.HILT_TEST_VERSION}"
    const val HILT_TEST_COMPILER =
        "com.google.dagger:hilt-android-compiler:${Versions.HILT_TEST_VERSION}"
    const val OKHTTP_MOCK_WEB_SERVER =
        "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP_MOCK_SERVER}"
}