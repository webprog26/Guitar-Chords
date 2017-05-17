package com.example.webprog26.guitarchords.guitar_chords_engine.events;

/**
 * Runs when data has been read from .json file in assets directory
 */

public class JSONDataHasBeenReadEvent {

    private final String mJSONDataString;

    public JSONDataHasBeenReadEvent(String JSONDataString) {
        this.mJSONDataString = JSONDataString;
    }

    public String getJSONDataString() {
        return mJSONDataString;
    }
}
