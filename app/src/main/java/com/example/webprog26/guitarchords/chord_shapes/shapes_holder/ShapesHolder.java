package com.example.webprog26.guitarchords.chord_shapes.shapes_holder;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;

import java.util.ArrayList;

/**
 * Created by webpr on 17.05.2017.
 */

public class ShapesHolder {

    private static ShapesHolder instance;

    private static ArrayList<PlayableShape> mChordPlayableShapes = new ArrayList<>();

    private ShapesHolder(){
    }

    public static ShapesHolder newInstance(){
        if(instance == null){
            instance = new ShapesHolder();
        }

        return instance;
    }

    public static void addChordShape(PlayableShape chordPlayableShape){
        getChordPlayableShapes().add(chordPlayableShape);
    }

    public static ArrayList<PlayableShape> getChordPlayableShapes() {
        return mChordPlayableShapes;
    }
}
