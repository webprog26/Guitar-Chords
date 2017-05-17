package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.LoadShapeFragmentCommand;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayableShapeManager extends GuitarChordsManager{

    private final PlayableShape mPlayableShape;

    public PlayableShapeManager(FragmentManager fragmentManager,
                                int containerViewId,
                                Context context,
                                PlayableShape mPlayableShape) {
        super(fragmentManager, containerViewId, context);
        this.mPlayableShape = mPlayableShape;
    }

    public void setPlayableShapeFragment(){
        new LoadShapeFragmentCommand(getFragmentManager(), getContainerViewId(), getPlayableShape()).execute();
    }

    @Override
    public void showReference(String referenceText) {

    }

    private PlayableShape getPlayableShape() {
        return mPlayableShape;
    }
}
