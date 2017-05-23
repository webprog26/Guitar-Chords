package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import com.example.webprog26.guitarchords.R;

/**
 * Created by webpr on 22.05.2017.
 */

public class NoteBitmapsHelper {

    private static final int C_DRAWABLE = R.drawable.c_drawable;
    private static final int G_DRAWABLE = R.drawable.g_drawable;
    private static final int E_DRAWABLE = R.drawable.e_drawable;

    public static int getNoteDrawable(String noteTitle){
       switch (noteTitle){
           case "c":
               return C_DRAWABLE;
           case "g":
               return G_DRAWABLE;
           case "e":
               return  E_DRAWABLE;
           default:
               return -1;
       }
    }
}
