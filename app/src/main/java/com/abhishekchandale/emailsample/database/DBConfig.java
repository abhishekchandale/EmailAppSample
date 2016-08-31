package com.abhishekchandale.emailsample.database;

import android.content.Context;

import com.abhishekchandale.emailsample.model.MessageModel;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by Barun on 26-02-2016.
 */
public class DBConfig {

    public static final String DB_NAME = "emaildb.db";
    public static final int AA_DB_VERSION = 1;

    public static void createDatabase(Context context, String dbName) {
        Configuration.Builder config = new Configuration.Builder(context);
        config.setDatabaseVersion(AA_DB_VERSION);
        config.setDatabaseName(dbName);
        config.addModelClass(MessageModel.class);
        ActiveAndroid.initialize(config.create());
    }
}

