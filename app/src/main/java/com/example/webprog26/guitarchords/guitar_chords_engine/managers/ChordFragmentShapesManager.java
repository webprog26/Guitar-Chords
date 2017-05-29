package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.webprog26.guitarchords.app.GuitarChordsApp;
import com.example.webprog26.guitarchords.chord_shapes.shapes_holder.ShapesHolder;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.FillChordWithDataEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadShapesFromDatabaseEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.FingerIndexDrawableIDHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.LoadBitmapsFromAssetsHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.NoteBitmapsHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Manages {@link com.example.webprog26.guitarchords.fragments.ChordFragment} work
 */

public class ChordFragmentShapesManager {


    private final Chord mChord;
    private final AssetManager mAssetManager;
    private ArrayList<ChordShape> mChordShapes = new ArrayList<>();
    private final Context mContext;
    public ChordFragmentShapesManager(Chord chord, AssetManager assetManager, Context context) {
        this.mChord = chord;
        this.mAssetManager = assetManager;
        this.mContext = context;
    }

    public void fillChordWithData(){
        //Asking DatabaseProvider for Chord's second title and chord shapes table title
        EventBus.getDefault().post(new FillChordWithDataEvent(getChord()));
    }

    public void loadShapesFromSQLiteDb(){
        EventBus.getDefault().post(new LoadShapesFromDatabaseEvent(getChord()));
    }

    public void addChordShapesToShapesHolder(){
        ShapesHolder shapesHolder = GuitarChordsApp.getShapesHolder();
        if(shapesHolder.getShapesSize() == 0){
            for(ChordShape chordShape: getChordShapes()){
                shapesHolder.addChordShape(new PlayableShape(chordShape));
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void addShapesBitmapsImages(){
        ArrayList<ChordShape> chordShapes = getChordShapes();
        Resources resources = mContext.getResources();

        if(chordShapes.size() > 0){
            for(ChordShape chordShape: getChordShapes()){
                Bitmap shapeImage = LoadBitmapsFromAssetsHelper.loadBitmapFromAssets(getAssetManager(),
                        chordShape.getImagePath());
                if(shapeImage != null){
                    chordShape.setShapeImage(shapeImage);
                }

                for(Note note: chordShape.getNotes()){
                    if(note.getNoteCoordinates().y != 0){
                        note.setNoteMainDrawable(resources.getDrawable(NoteBitmapsHelper.getNoteDrawable(note.getNoteTitle())));
                        note.setNoteFingerIndexDrawable(resources.getDrawable(FingerIndexDrawableIDHelper.getFingerIndexDrawableId(note.getFingerIndex())));
                    }
                }
            }
        }
    }

    public ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }

    public void setChordShapes(ArrayList<ChordShape> mChordShapes) {
        this.mChordShapes = mChordShapes;
    }

    public Chord getChord() {
        return mChord;
    }

    private AssetManager getAssetManager() {
        return mAssetManager;
    }
}
