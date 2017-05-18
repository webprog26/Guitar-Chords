package com.example.webprog26.guitarchords.app;

import android.app.Application;

import com.example.webprog26.guitarchords.chord_shapes.db.DatabaseProvider;
import com.example.webprog26.guitarchords.chord_shapes.shapes_holder.ShapesHolder;

/**
 * {@link Application} class to create single {@link DatabaseProvider} instance
 */

public class GuitarChordsApp extends Application {

    private static DatabaseProvider mDatabaseProvider;
    private static ShapesHolder mShapesHolder;


    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseProvider = new DatabaseProvider(this);
        mShapesHolder = ShapesHolder.newInstance();
    }

    public static DatabaseProvider getDatabaseProvider() {
        return mDatabaseProvider;
    }

    public static ShapesHolder getShapesHolder() {
        return mShapesHolder;
    }
}
