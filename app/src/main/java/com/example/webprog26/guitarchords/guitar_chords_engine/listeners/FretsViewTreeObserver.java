package com.example.webprog26.guitarchords.guitar_chords_engine.listeners;

import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.webprog26.guitarchords.chord_shapes.fretboard.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.FretViewsHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.interfaces.OnFretboardReadyToCatchTouchesCallback;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayShapeFragmentManager;

import static android.content.ContentValues.TAG;

/**
 * Created by webpr on 30.05.2017.
 */

public class FretsViewTreeObserver implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = "FretsViewTreeObserver";

    private static final int FIRST_STRING_LAST_FRET_INDEX = 4;
    private static final int FRETS_PER_STRING_NUMBER = 5;

    private static final int STRING_IMAGE_VIEW_INDEX = 0;
    private static final int NOTE_IMAGE_VIEW_INDEX = 1;

    private static final int TRASH_HOLD_SIZE = 20;

    private final GridLayout fretboardLayout;
    private final PlayShapeFragmentManager playShapeFragmentManager;
    private final FretViewsHelper fretViewsHelper;
    private final OnFretboardReadyToCatchTouchesCallback mTouchesCallback;

    public FretsViewTreeObserver(GridLayout fretboardLayout,
                                 PlayShapeFragmentManager playShapeFragmentManager,
                                 FretViewsHelper fretViewsHelper,
                                 OnFretboardReadyToCatchTouchesCallback touchesCallback) {
        this.fretboardLayout = fretboardLayout;
        this.playShapeFragmentManager = playShapeFragmentManager;
        this.fretViewsHelper = fretViewsHelper;
        this.mTouchesCallback = touchesCallback;
    }

    @Override
    public void onGlobalLayout() {
        GridLayout fretGridLayout = getFretViewsHelper().getFretBoardLayout();

        if(fretGridLayout != null){
            fretGridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int stringIndex = 0;


            if(playShapeFragmentManager != null){

                for(int i = FIRST_STRING_LAST_FRET_INDEX; i < fretGridLayout.getChildCount(); i+= FRETS_PER_STRING_NUMBER){
                    RelativeLayout fretRelativeLayout = getFretViewsHelper().getFretLayout(i);

                    if(fretRelativeLayout != null){

                        ImageView stringImageView = getFretViewsHelper().getStringImageView(fretRelativeLayout, STRING_IMAGE_VIEW_INDEX);

                        if(stringImageView != null){
                            playShapeFragmentManager.setStringsCoordinates((fretRelativeLayout.getX() + stringImageView.getX() - TRASH_HOLD_SIZE),
                                    (fretRelativeLayout.getX() + stringImageView.getX()) + stringImageView.getWidth() + TRASH_HOLD_SIZE, stringIndex);
                            stringIndex++;
                        }
                    }
                }
            }

            if(playShapeFragmentManager != null){

                Fretboard fretboard = playShapeFragmentManager.getFretboard();

                if(fretboard != null){

                    for(int i = 0; i < Fretboard.STRINGS_COUNT; i++){

                        GuitarString currentGuitarString = fretboard.getGuitarString(i);

                        if(currentGuitarString != null){

                            Note currentNote = currentGuitarString.getNote();

                            if(currentNote != null){

                                float stringPlayableY;

                                if(currentNote.getNoteCoordinates().y == 0){
                                    stringPlayableY = 0;
                                } else {
                                    stringPlayableY = (float) getFretViewsHelper().getFretBoardLayout().getChildAt(currentNote.getNoteCoordinates().y - 1).getBottom();
                                }


                                Log.i(TAG, currentGuitarString.getTitle() + " with note " + currentGuitarString.getNote().getNoteTitle()
                                        + " stringPlayableY " + stringPlayableY);
                                playShapeFragmentManager.setStringPlayableY(stringPlayableY, i);

                            } else {
                                Log.i(TAG, "currentNote is null at " + currentGuitarString.getTitle());
                            }
                        } else {
                            Log.i(TAG, "currentGuitarString is null");
                        }
                    }
                } else {
                    Log.i(TAG, "fretboard is null");
                }
            } else {
                Log.i(TAG, "playShapeFragmentManager is null");
            }

            if(mTouchesCallback != null){
                mTouchesCallback.onFretboardReadyToCatchTouches();
            }
            fretGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    private GridLayout getFretboardLayout() {
        return fretboardLayout;
    }

    private PlayShapeFragmentManager getPlayShapeFragmentManager() {
        return playShapeFragmentManager;
    }

    private FretViewsHelper getFretViewsHelper() {
        return fretViewsHelper;
    }
}

