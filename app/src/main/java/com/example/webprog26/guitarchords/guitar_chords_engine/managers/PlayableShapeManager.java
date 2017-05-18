package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.chord_shapes.shapes_holder.ShapesHolder;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.LoadShapeFragmentCommand;

import java.util.ArrayList;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayableShapeManager extends GuitarChordsManager{

    private int playableShapePosition;
    private final ArrayList<PlayableShape> mChordPlayableShapes = ShapesHolder.getChordPlayableShapes();

    public PlayableShapeManager(FragmentManager fragmentManager,
                                int containerViewId,
                                Context context,
                                int playableShapePosition) {
        super(fragmentManager, containerViewId, context);
        this.playableShapePosition = playableShapePosition;
    }

    public void setPlayableShapeFragment(){
        new LoadShapeFragmentCommand(getFragmentManager(), getContainerViewId(), getPlayableShape()).execute();
    }

    @Override
    public void showReference(String referenceText) {

    }

    private ArrayList<PlayableShape> getChordPlayableShapes() {
        return mChordPlayableShapes;
    }

    private int getPlayableShapePosition() {
        return playableShapePosition;
    }

    public void setPlayableShapePosition(int playableShapePosition) {
        this.playableShapePosition = playableShapePosition;
    }

    private PlayableShape getPlayableShape() {
        return getChordPlayableShapes().get(getPlayableShapePosition());
    }
}
