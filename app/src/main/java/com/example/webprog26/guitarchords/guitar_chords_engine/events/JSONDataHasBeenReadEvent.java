package com.example.webprog26.guitarchords.guitar_chords_engine.events;

/**
 * Created by webpr on 15.05.2017.
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
