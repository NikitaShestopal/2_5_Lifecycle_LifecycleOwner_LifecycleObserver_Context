package com.example.a25lifecyclelifecycleownerlifecycleobservercontext
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import timber.log.Timber

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(BackgroundDetector())

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            private fun logState(activity: Activity, callbackName: String) {
                val screenName = activity.javaClass.simpleName
                val currentState = (activity as? LifecycleOwner)?.lifecycle?.currentState
                Timber.d("[$callbackName] Screen: $screenName | Current State: $currentState")
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                logState(activity, "onActivityCreated")
            }

            override fun onActivityStarted(activity: Activity) {
                logState(activity, "onActivityStarted")
            }

            override fun onActivityResumed(activity: Activity) {
                logState(activity, "onActivityResumed")
            }

            override fun onActivityPaused(activity: Activity) {
                logState(activity, "onActivityPaused")
            }

            override fun onActivityStopped(activity: Activity) {
                logState(activity, "onActivityStopped")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {
                logState(activity, "onActivityDestroyed")
            }
        })
    }
}