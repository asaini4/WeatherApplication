package com.hcl.androidweatherapp.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class AppLifeCycleHandler implements Application.ActivityLifecycleCallbacks {
    private  static int started;
    private  static int stopped;

    private static void incrementStarted(){
        ++started;
    }

    private static void incrementStopped(){
        ++stopped;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        incrementStarted();
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        incrementStopped();
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
