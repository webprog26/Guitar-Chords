package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.fragments.PlayShapeFragment;

/**
 * Created by webpr on 17.05.2017.
 */

public class LoadShapeFragmentCommand extends LoadFragmentCommand {

    private static final String PLAY_FRAGMENT_TAG = "chords_fragment_tag";

    private final int mPlayableShapePosition;

    public LoadShapeFragmentCommand(FragmentManager fragmentManager, int containerViewId, int playableShapePosition) {
        super(fragmentManager, containerViewId);
        this.mPlayableShapePosition = playableShapePosition;
    }

    @Override
    public void execute() {
        FragmentManager fragmentManager = getFragmentManager();

        PlayShapeFragment playShapeFragment = (PlayShapeFragment) fragmentManager.findFragmentByTag(PLAY_FRAGMENT_TAG);

        if(playShapeFragment == null){
            fragmentManager.beginTransaction().add(R.id.play_chord_activity_content, PlayShapeFragment.newInstance(getPlayableShapePosition())).commit();
        }
    }

    private int getPlayableShapePosition(){
        return mPlayableShapePosition;
    }
}
