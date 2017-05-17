package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.webprog26.guitarchords.guitar_chords_engine.events.JSONDataHasBeenReadEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;

/**
 * This {@link Command} reads data from JSON file in assets directory
 */

public class ReadDataFromJSONCommand implements Command {

    private static final String TAG = "ReadData";

    private static final String PROPER_UTF_8_ENCODING = "UTF-8";
    //JSON file name in assets dir
    private static final String JSON_FILE_NAME = "shapes/shapes_demo.json";
    private final AssetManager mAssetManager;

    public ReadDataFromJSONCommand(AssetManager assetManager) {
        this.mAssetManager = assetManager;
    }

    @Override
    public void execute() {
        EventBus.getDefault().post(new JSONDataHasBeenReadEvent(loadJSONFromAsset(getAssetManager(), JSON_FILE_NAME)));
    }

    private AssetManager getAssetManager() {
        return mAssetManager;
    }

    /**
     * Reads .json file directly from assets directory and transform it into the {@link String}
     * @param assetManager {@link AssetManager}
     * @param jsonFilename {@link String}
     * @return {@link String}
     */
    private String loadJSONFromAsset(AssetManager assetManager, String jsonFilename) {
        Log.i(TAG, "loadJSONFromAsset()");
        String json;
        try {
            InputStream is = assetManager.open(jsonFilename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, PROPER_UTF_8_ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
