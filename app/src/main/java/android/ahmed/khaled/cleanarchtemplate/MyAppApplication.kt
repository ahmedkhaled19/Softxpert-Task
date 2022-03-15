package android.ahmed.khaled.cleanarchtemplate

import android.ahmed.khaled.core.utils.NetworkingUtils
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyAppApplication : Application() {

    init {
        NetworkingUtils.setNetworkingApplicationContext(this)
    }
}