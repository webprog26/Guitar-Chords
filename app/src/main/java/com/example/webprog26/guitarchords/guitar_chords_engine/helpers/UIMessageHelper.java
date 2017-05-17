package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.manager.ChordsManager;

/**
 * Helper class to show user messages via {@link Snackbar}
 */

public class UIMessageHelper {

    private final View mContextView;
    private final ChordsManager mChordsManager;

    private static final int UI_MESSAGE_DURATION = 5000;

    public UIMessageHelper(ChordsManager chordsManager) {
        this.mChordsManager = chordsManager;

        this.mContextView = ((Activity) chordsManager.getContext()).findViewById(chordsManager.getContainerViewId());
    }

    /**
     * Shows user message
     * @param fromChord {@link String}
     * @param toChord {@link String}
     */
    //Todo make it more modulable and independent
    public void sendUiMessage(final String fromChord, final String toChord){
        final ChordsManager chordsManager = getChordsManager();
        final Context context = chordsManager.getContext();

        Snackbar.make(getContextView(), context.getString(R.string.wrong_chord, fromChord, toChord), UI_MESSAGE_DURATION)
                .setAction(context.getString(R.string.why), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chordsManager.showReference(fromChord);
                    }
                }).show();
    }



    private View getContextView() {
        return mContextView;
    }

    private ChordsManager getChordsManager() {
        return mChordsManager;
    }
}
