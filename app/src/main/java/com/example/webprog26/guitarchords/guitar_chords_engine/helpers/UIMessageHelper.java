package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.ChordsManager;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.GuitarChordsManager;

/**
 * Helper class to show user messages via {@link Snackbar}
 */

public class UIMessageHelper {

    private final View mContextView;
    private final GuitarChordsManager mGuitarChordsManager;

    private static final int UI_MESSAGE_DURATION = 5000;

    public UIMessageHelper(GuitarChordsManager guitarChordsManager) {
        this.mGuitarChordsManager = guitarChordsManager;

        this.mContextView = ((Activity) guitarChordsManager.getContext()).findViewById(guitarChordsManager.getContainerViewId());
    }

    /**
     * Shows user message
     * @param fromChord {@link String}
     * @param toChord {@link String}
     */
    public void sendUiWrongChordMessage(final String fromChord, final String toChord){
        final GuitarChordsManager chordsManager = getChordsManager();
        final Context context = chordsManager.getContext();

        showSnackbarMessage(context.getString(R.string.wrong_chord, fromChord, toChord),
                context.getString(R.string.why), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chordsManager.showReference(fromChord);
                    }
                });
    }

    /**
     * Shows snackbar message directly
     * @param message {@link String}
     * @param action {@link String}
     * @param actionListener {@link android.view.View.OnClickListener}
     */
    private void showSnackbarMessage(String message, String action, View.OnClickListener actionListener){

        Snackbar uiMessageSnackbar = Snackbar.make(getContextView(), message, UI_MESSAGE_DURATION);

        if(action != null){
            if(actionListener != null){
                uiMessageSnackbar.setAction(action, actionListener);
            } else {
                uiMessageSnackbar.setAction(action, null);
            }
        }

        uiMessageSnackbar.show();
    }



    private View getContextView() {
        return mContextView;
    }

    private GuitarChordsManager getChordsManager() {
        return mGuitarChordsManager;
    }
}
