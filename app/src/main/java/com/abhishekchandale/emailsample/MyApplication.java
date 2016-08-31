package com.abhishekchandale.emailsample;

import android.content.Context;
import android.util.Log;

import com.abhishekchandale.emailsample.database.DBConfig;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by abhishek on 29/8/16.
 */
public class MyApplication extends com.activeandroid.app.Application {

    private final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate called for Active Android");
        ActiveAndroid.initialize(this);
        DBConfig.createDatabase(getApplicationContext(), DBConfig.DB_NAME);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate called for Active Android");
        ActiveAndroid.dispose();
    }
}
