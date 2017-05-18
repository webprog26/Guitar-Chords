package com.example.webprog26.guitarchords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayShapeFragment extends Fragment {

    private static final String TAG = "PlayShapeFragment";

    public static final String FRAGMENT_PLAYABLE_SHAPE = "activity_playable_shape";

    public static PlayShapeFragment newInstance(final PlayableShape playableShape){
        Bundle args = new Bundle();
        args.putSerializable(FRAGMENT_PLAYABLE_SHAPE, playableShape);
        PlayShapeFragment playShapeFragment = new PlayShapeFragment();
        playShapeFragment.setArguments(args);
        return playShapeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){

            final PlayableShape playableShape = (PlayableShape) getArguments().getSerializable(FRAGMENT_PLAYABLE_SHAPE);

            if(playableShape != null){
                Log.i(TAG, playableShape.toString());
                for(Note note: playableShape.getNotes()){
                    Log.i(TAG, note.toString());
                }
            }
        }
    }
}
