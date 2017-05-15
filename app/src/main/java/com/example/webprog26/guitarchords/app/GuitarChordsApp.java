package com.example.webprog26.guitarchords.app;

import android.app.Application;

import com.example.webprog26.guitarchords.chord_shapes.db.DatabaseProvider;

/**
 * Created by webpr on 15.05.2017.
 */

public class GuitarChordsApp extends Application {

    private static DatabaseProvider mDatabaseProvider;


    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseProvider = new DatabaseProvider(this);
    }

    public static DatabaseProvider getDatabaseProvider() {
        return mDatabaseProvider;
    }
}
