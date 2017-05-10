package com.example.webprog26.guitarchords.guitar_chords_engine.events;

/**
 * Runs when app needs to load chords images from specific directory in assets
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
