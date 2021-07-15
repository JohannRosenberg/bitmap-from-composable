package dev.wirespec.sample

import android.app.Activity
import android.app.Application
import android.os.Bundle

class App : Application() {

    private val activityLifecycleTracker: AppLifecycleTracker = AppLifecycleTracker()

    override fun onCreate() {
        super.onCreate()
        context = this

        registerActivityLifecycleCallbacks(activityLifecycleTracker)
    }

    companion object {
        lateinit var context: App
    }


    // Returns the current activity.
    var currentActivity: Activity?
        get() = activityLifecycleTracker.currentActivity
        private set(value) {}


    /**
     * Callbacks for handling the lifecycle of activities.
     */
    class AppLifecycleTracker : ActivityLifecycleCallbacks {

        private var currentAct: Activity? = null

        var currentActivity: Activity?
            get() = currentAct
            private set(value) {}

        override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
            currentAct = activity
        }

        override fun onActivityPaused(p0: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
            if ((currentAct != null) && (activity == currentAct))
                currentAct = null
        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        }

        override fun onActivityDestroyed(p0: Activity) {
        }
    }
}
