package com.example.webprog26.guitarchords.guitar_chords_engine.listeners;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayShapeFragmentManager;

/**
 * Fret touches listener
 */

public class FretTouchListener implements View.OnTouchListener {

    private static final String TAG = "FretTouchListener";


    private final PlayShapeFragmentManager mPlayShapeFragmentManager;
    private float touchPointX;

    public FretTouchListener(PlayShapeFragmentManager playShapeFragmentManager) {
        this.mPlayShapeFragmentManager = playShapeFragmentManager;
        setTouchPointX(0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        switch (event.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_DOWN:
                setTouchPointX(event.getX());

                if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(0).getStartX()
                        && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(0).getEndX()
                        && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(0).getPlayableY()){
                    playShapeFragmentManager.playNote(0);
                }

                if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(1).getStartX()
                        && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(1).getEndX()
                        && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(1).getPlayableY()){
                    playShapeFragmentManager.playNote(1);
                }

                if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(2).getStartX()
                        && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(2).getEndX()
                        && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(2).getPlayableY()){
                    playShapeFragmentManager.playNote(2);
                }

                if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(3).getStartX()
                        && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(3).getEndX()
                        && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(3).getPlayableY()){
                    playShapeFragmentManager.playNote(3);
                }

                if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(4).getStartX()
                        && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(4).getEndX()
                        && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(4).getPlayableY()){
                    playShapeFragmentManager.playNote(4);
                }

                if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(5).getStartX()
                        && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(5).getEndX()
                        && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(5).getPlayableY()){
                    playShapeFragmentManager.playNote(5);
                }

                return true;
            case MotionEvent.ACTION_MOVE:

                float currentTouchPointX = event.getX();

                if(Math.abs(currentTouchPointX - getTouchPointX()) > 40){

                    if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(0).getStartX()
                            && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(0).getEndX()
                            && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(0).getPlayableY()){
                        playShapeFragmentManager.playNote(0);
                    }

                    if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(1).getStartX()
                            && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(1).getEndX()
                            && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(1).getPlayableY()){
                        playShapeFragmentManager.playNote(1);
                    }

                    if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(2).getStartX()
                            && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(2).getEndX()
                            && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(2).getPlayableY()){
                        playShapeFragmentManager.playNote(2);
                    }

                    if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(3).getStartX()
                            && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(3).getEndX()
                            && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(3).getPlayableY()){
                        playShapeFragmentManager.playNote(3);
                    }

                    if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(4).getStartX()
                            && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(4).getEndX()
                            && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(4).getPlayableY()){
                        playShapeFragmentManager.playNote(4);
                    }

                    if(event.getX() > playShapeFragmentManager.getFretboard().getGuitarString(5).getStartX()
                            && event.getX() < playShapeFragmentManager.getFretboard().getGuitarString(5).getEndX()
                            && event.getY() > playShapeFragmentManager.getFretboard().getGuitarString(5).getPlayableY()){
                        playShapeFragmentManager.playNote(5);
                    }

                    setTouchPointX(currentTouchPointX);
                }
                return false;
            case MotionEvent.ACTION_UP:

                setTouchPointX(0);
                break;
        }

        return true;
    }


    private PlayShapeFragmentManager getPlayShapeFragmentManager() {
        return mPlayShapeFragmentManager;
    }

    private float getTouchPointX() {
        return touchPointX;
    }

    private void setTouchPointX(float touchPointX) {
        this.touchPointX = touchPointX;
    }
}
