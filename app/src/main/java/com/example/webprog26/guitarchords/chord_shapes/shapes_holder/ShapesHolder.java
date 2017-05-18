package com.example.webprog26.guitarchords.chord_shapes.shapes_holder;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

import java.util.ArrayList;

/**
 * Created by webpr on 17.05.2017.
 */

public class ShapesHolder {

    private static ShapesHolder instance;

    private static ArrayList<ChordShape> mChordShapes = new ArrayList<>();

    private ShapesHolder(){
    }

    public static ShapesHolder newInstance(){
        if(instance == null){
            instance = new ShapesHolder();
        }

        return instance;
    }

    public static void addChordShape(ChordShape chordShape){
        getChordShapes().add(chordShape);
    }

    public static ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }
}
