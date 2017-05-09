package com.example.webprog26.guitarchords.engine.events;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class LoadChordsImagesFromAssetsEvent {

    private final String mPathString;

    public LoadChordsImagesFromAssetsEvent(String pathString) {
        this.mPathString = pathString;
    }

    public String getPathString() {
        return mPathString;
    }
}
