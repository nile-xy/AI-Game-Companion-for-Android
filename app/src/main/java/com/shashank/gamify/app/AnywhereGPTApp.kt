package com.shashank.gamify.app

import android.app.Application
import android.content.Context
import com.shashank.gamify.BuildConfig
import com.shashank.gamify.R
import com.shashank.gamify.utils.SharedPref
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AnywhereGPTApp : Application() {

    companion object {
        var instance: AnywhereGPTApp? = null
            private set
        var appContext: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        instance = this
        appContext = applicationContext
        SharedPref.setStringPref(
            this,
            SharedPref.KEY_TOKEN_LENGTH,
            getString(R.string.token_length)
        )
    }
}