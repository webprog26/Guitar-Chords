package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.guitar_chords_engine.commands.LoadShapeFragmentCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.interfaces.PlayChordActivityControlsEnabler;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayableShapeManager extends GuitarChordsManager{

    private static final int SHAPES_PER_CHORD_COUNT = 5;

    private int playableShapePosition;
    private final PlayChordActivityControlsEnabler mPlayChordActivityControlsEnabler;

    public PlayableShapeManager(FragmentManager fragmentManager,
                                int containerViewId,
                                Context context,
                                int playableShapePosition,
                                PlayChordActivityControlsEnabler playChordActivityControlsEnabler) {
        super(fragmentManager, containerViewId, context);
        this.playableShapePosition = playableShapePosition;
        this.mPlayChordActivityControlsEnabler = playChordActivityControlsEnabler;

        changeControlsEnabledState();
    }

    public void setPlayableShapeFragment(){
        new LoadShapeFragmentCommand(getFragmentManager(), getContainerViewId(), getPlayableShapePosition()).execute();
    }

    @Override
    public void showReference(String referenceText) {

    }

    public void setNextPlayableShape(){
        if(isNextPlayableShapeEnabled()){
            setPlayableShapePosition(getPlayableShapePosition() + 1);
            setPlayableShapeFragment();

            changeControlsEnabledState();
        }
    }

    public void setPreviousPlayableShape(){
        if(isPreviousPlayableShapeEnabled()){
            setPlayableShapePosition(getPlayableShapePosition() - 1);
            setPlayableShapeFragment();

            changeControlsEnabledState();
        }
    }

    private int getPlayableShapePosition() {
        return playableShapePosition;
    }

    private void setPlayableShapePosition(int playableShapePosition) {
        this.playableShapePosition = playableShapePosition;
    }


    private PlayChordActivityControlsEnabler getPlayChordActivityControlsEnabler() {
        return mPlayChordActivityControlsEnabler;
    }

    private boolean isNextPlayableShapeEnabled(){
        return getPlayableShapePosition() < SHAPES_PER_CHORD_COUNT;
    }

    private boolean isPreviousPlayableShapeEnabled(){
        return getPlayableShapePosition() > 0;
    }

    private void changeControlsEnabledState(){
        getPlayChordActivityControlsEnabler().setNextPlayableShapeButtonEnabled(isNextPlayableShapeEnabled());
        getPlayChordActivityControlsEnabler().setPreviousPlayableShapeButtonEnabled(isPreviousPlayableShapeEnabled());
    }
}
