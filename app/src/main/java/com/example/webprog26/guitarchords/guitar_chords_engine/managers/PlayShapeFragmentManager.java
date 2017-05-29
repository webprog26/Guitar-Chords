package com.example.webprog26.guitarchords.guitar_chords_engine.managers;


import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.fretboard.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadNotesSoundsEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by webpr on 23.05.2017.
 */

public class PlayShapeFragmentManager {

    private static final String TAG = "PSFManager";

    private final PlayableShape mPlayableShape;
    private final Fretboard mFretboard;
    private SoundPool mSoundPool;
    private boolean shouldDrawBar = false;
    private int barStartPlace = PlayableShape.UNEXISTING_COORDINATE;
    private int barEndPlace = PlayableShape.UNEXISTING_COORDINATE;


    public PlayShapeFragmentManager(PlayableShape playableShape) {
        this.mPlayableShape = playableShape;
        this.mFretboard = new Fretboard(playableShape.getStringMutedHolder());

        if(playableShape.isHasBar()){
            Log.i(TAG, "shape has bar");
            setShouldDrawBar(true);

            int barStartPlace = playableShape.getBarStartPoint().x;
            int barEndPlace = playableShape.getBarEndPoint().x;


            if(barStartPlace != PlayableShape.UNEXISTING_COORDINATE){
                setBarStartPlace(barStartPlace);
            }

            if(barEndPlace != PlayableShape.UNEXISTING_COORDINATE){
                setBarEndPlace(barEndPlace);
            }
        }

            if(playableShape.getNotes().size() > 0){
                int index = (playableShape.getNotes().size() - 1);
                Log.i("GuitarString", index + " index");

                for(int i = 0; i < 6; i++){
                    GuitarString guitarString = getFretboard().getGuitarString(i);
                    if(!guitarString.isMuted()){
                        Note note = playableShape.getNotes().get(index);
                        Log.i("GuitarString", note.getNoteTitle());
                        guitarString.setNote(note);
                        if(index > 0){
                            index--;
                        }
                    }
                }
            }
    }

    public void setStringsCoordinates(float startX, float endX, int index){
        GuitarString guitarString = getGuitarString(index);
        guitarString.setStartX(startX);
        guitarString.setEndX(endX);
    }

    public void setStringPlayableY(float playableY, int index){
        GuitarString guitarString = getGuitarString(index);
        guitarString.setPlayableY(playableY);
    }

    private GuitarString getGuitarString(int index){
        return getFretboard().getGuitarString(index);
    }

    public void playNote(int guitarStringIndex){
        GuitarString guitarString = getGuitarString(guitarStringIndex);

        if(guitarString != null){

            Note noteToPlay = guitarString.getNote();

            if(noteToPlay != null){

                int noteSound = noteToPlay.getNoteSound();

                if(noteSound != -1){

                    SoundPool soundPool = getSoundPool();

                    if(soundPool != null){

                        soundPool.play(noteSound, 1, 1, 0, 0, 1);
                    }
                }
            }
        }
    }

    public PlayableShape getPlayableShape() {
        return mPlayableShape;
    }

    public Fretboard getFretboard() {
        return mFretboard;
    }

    public SoundPool getSoundPool() {
        return mSoundPool;
    }

    private void setSoundPool(SoundPool mSoundPool) {
        this.mSoundPool = mSoundPool;
    }

    public void releaseSoundPool(){
        SoundPool soundPool = getSoundPool();
        if(soundPool != null){
            soundPool.release();
            setSoundPool(null);
        }
    }

    public void createSoundPool(){
        SoundPool soundPool = getSoundPool();
        if(soundPool == null){
            setSoundPool(new SoundPool(10, AudioManager.STREAM_MUSIC,0));
            EventBus.getDefault().post(new LoadNotesSoundsEvent());
        }
    }

    public boolean isShouldDrawBar() {
        return shouldDrawBar;
    }

    public void setShouldDrawBar(boolean shouldDrawBar) {
        this.shouldDrawBar = shouldDrawBar;
    }

    public int getBarStartPlace() {
        return barStartPlace;
    }

    public void setBarStartPlace(int barStartPlace) {
        this.barStartPlace = barStartPlace;
    }

    public int getBarEndPlace() {
        return barEndPlace;
    }

    public void setBarEndPlace(int barEndPlace) {
        this.barEndPlace = barEndPlace;
    }
}
