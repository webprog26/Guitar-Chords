package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.NoteClickListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayShapeFragmentManager;

/**
 * Created by webpr on 30.05.2017.
 */

public class FretViewsHelper {

    private static final int NOTE_IMAGE_VIEW_INDEX = 1;

    private final GridLayout fretBoardLayout;
    private final PlayShapeFragmentManager playShapeFragmentManager;
    private final ImageView[] mStringMutedImageViews;
    private final Resources notesResources;

    public FretViewsHelper(final Context context, final GridLayout fretBoardLayout,
                           final PlayShapeFragmentManager playShapeFragmentManager,
                           final ImageView[] stringMutedImageViews) {
        this.fretBoardLayout = fretBoardLayout;
        this.playShapeFragmentManager = playShapeFragmentManager;
        this.mStringMutedImageViews = stringMutedImageViews;
        this.notesResources = context.getResources();
    }

    public void initMutedStrings(){
        PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){

            Fretboard fretboard = getPlayShapeFragmentManager().getFretboard();

            if(fretboard != null){

                for(int i = 0; i < Fretboard.STRINGS_COUNT; i++){

                    GuitarString guitarString = fretboard.getGuitarString(i);

                    if(guitarString != null){

                        if(guitarString.isMuted()){

                            ImageView stringIsMutedImageView = getStringMutedImageViews()[i];

                            if(stringIsMutedImageView != null){

                                if(stringIsMutedImageView.getVisibility() == View.INVISIBLE){

                                    stringIsMutedImageView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void setBarImage(int index){
        ImageView stringImageView = getStringImageView(getFretLayout(index), NOTE_IMAGE_VIEW_INDEX);
        stringImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        stringImageView.requestLayout();
        stringImageView.setImageDrawable(getNotesResources().getDrawable(R.drawable.bar));
        stringImageView.bringToFront();
    }

    private void initSingleNoteWithImages(final Note note){
        if(note.getNoteMainDrawable() != null){

            ImageView stringImageView = getStringImageView(getFretLayout(note.getNotePlace()), NOTE_IMAGE_VIEW_INDEX);
            stringImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            stringImageView.requestLayout();
            stringImageView.setImageDrawable(note.getNoteMainDrawable());
            stringImageView.bringToFront();
            stringImageView.setOnClickListener(new NoteClickListener(note));
        }
    }

    public RelativeLayout getFretLayout(int fretIndex){
        return (RelativeLayout) getFretBoardLayout().getChildAt(fretIndex);
    }

    public void drawBar(){

        PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){

            final int barStartPlace = playShapeFragmentManager.getBarStartPlace();
            final int barEndPlace = playShapeFragmentManager.getBarEndPlace();

            if(barStartPlace != PlayableShape.UNEXISTING_COORDINATE
                    && barEndPlace != PlayableShape.UNEXISTING_COORDINATE){

                for(int i = barStartPlace; i < barEndPlace; i += 5){
                    setBarImage(i);
                }
            }
        }
    }



    public void initNotesImages(){

        PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null) {

            if (playShapeFragmentManager.getPlayableShape().getNotes() != null) {

                for (final Note note : playShapeFragmentManager.getPlayableShape().getNotes()) {

                    initSingleNoteWithImages(note);
                }
            }
        }
    }

    public ImageView getStringImageView(RelativeLayout fretLayout, int index){
        return (ImageView) fretLayout.getChildAt(index);
    }

    private Resources getNotesResources() {
        return notesResources;
    }

    private ImageView[] getStringMutedImageViews() {
        return mStringMutedImageViews;
    }

    private GridLayout getFretBoardLayout() {
        return fretBoardLayout;
    }

    private PlayShapeFragmentManager getPlayShapeFragmentManager() {
        return playShapeFragmentManager;
    }
}
