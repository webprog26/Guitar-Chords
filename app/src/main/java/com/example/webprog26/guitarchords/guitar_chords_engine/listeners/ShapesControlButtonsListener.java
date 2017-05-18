package com.example.webprog26.guitarchords.guitar_chords_engine.listeners;

import android.view.View;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayableShapeManager;

/**
 * Created by webpr on 18.05.2017.
 */

public class ShapesControlButtonsListener implements View.OnClickListener {

    private final PlayableShapeManager mPlayableShapeManager;

    public ShapesControlButtonsListener(PlayableShapeManager playableShapeManager) {
        this.mPlayableShapeManager = playableShapeManager;
    }

    @Override
    public void onClick(View v) {

        PlayableShapeManager playableShapeManager = getPlayableShapeManager();

        switch (v.getId()){
            case R.id.btn_next:
                    playableShapeManager.setNextPlayableShape();
                break;
            case R.id.btn_previous:
                    playableShapeManager.setPreviousPlayableShape();
                break;
        }
    }

    private PlayableShapeManager getPlayableShapeManager() {
        return mPlayableShapeManager;
    }
}
