package com.example.webprog26.guitarchords.chord_shapes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Chords and chords shapes database creator
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String CHORDS_DATABASE_TITLE = "chords_database";
    private static final int CHORDS_DATABASE_VERSION = 1;

    //Chords table columns
    public static final String CHORDS_TABLE = "chords_table";
    public static final String CHORD_ID = "_id";
    public static final String CHORD_TITLE = "chord_title";
    public static final String CHORD_SECOND_TITLE = "chord_second_title";
    public static final String CHORD_TYPE = "chord_type";
    public static final String CHORD_ALTERATION = "chord_alteration";
    public static final String CHORD_SHAPES_TABLE = "chord_shapes_table";

    //Chords shapes tables titles
    private static final String C_MAJ_ORDINARY_SHAPES = "c_maj_ordinary";

    //Chord shape table columns
    public static final String SHAPE_ID = "_id";
    public static final String SHAPE_POSITION = "shape_position";
    public static final String SHAPE_START_FRET_NUMBER = "shape_start_fret_number";
//    public static final String SHAPE_NOTE_TITLE = "shape_note_title";
//    public static final String SHAPE_NOTE_FINGER_INDEX = "shape_note_finger_index";
//    public static final String SHAPE_NOTE_X = "shape_note_x";
//    public static final String SHAPE_NOTE_Y = "shape_note_y";
    public static final String SHAPE_NOTES_LIST = "shape_notes_list";
    public static final String SHAPE_HAS_BAR = "shape_has_bar";
    public static final String SHAPE_BAR_START_X = "shape_bar_start_x";
    public static final String SHAPE_BAR_START_Y = "shape_bar_start_y";
    public static final String SHAPE_BAR_END_X = "shape_bar_end_x";
    public static final String SHAPE_BAR_END_Y = "shape_bar_end_y";
    public static final String SHAPE_IMAGE_TITLE = "shape_image_title";
    public static final String SHAPE_HAS_MUTED_STRINGS = "shape_has_muted_strings";
    public static final String SHAPE_SIXTH_STRING_MUTED = "shape_sixth_string_muted";
    public static final String SHAPE_FIFTH_STRING_MUTED = "shape_fifth_string_muted";
    public static final String SHAPE_FOURTH_STRING_MUTED = "shape_fourth_string_muted";
    public static final String SHAPE_THIRD_STRING_MUTED = "shape_third_string_muted";
    public static final String SHAPE_SECOND_STRING_MUTED = "shape_second_string_muted";
    public static final String SHAPE_FIRST_STRING_MUTED = "shape_first_string_muted";

    public DatabaseHelper(Context context) {
        super(context, CHORDS_DATABASE_TITLE, null, CHORDS_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CHORDS_TABLE + "("
        + CHORD_ID + " integer primary key autoincrement, "
        + CHORD_TITLE + " varchar(100), "
        + CHORD_SECOND_TITLE + " varchar(100), "
        + CHORD_TYPE + " varchar(100), "
        + CHORD_ALTERATION + " varchar(100), "
        + CHORD_SHAPES_TABLE + " varchar(100))");

        db.execSQL("create table " + C_MAJ_ORDINARY_SHAPES + "("
                + SHAPE_ID + " integer primary key autoincrement, "
                + SHAPE_POSITION + " integer, "
                + SHAPE_START_FRET_NUMBER + " integer, "
                + SHAPE_NOTES_LIST + " text(1000), "
//                + SHAPE_NOTE_TITLE + " varchar(100), "
//                + SHAPE_NOTE_FINGER_INDEX + " integer, "
//                + SHAPE_NOTE_X + " integer, "
//                + SHAPE_NOTE_Y + " integer, "
                + SHAPE_HAS_BAR + " varchar(100), "
                + SHAPE_BAR_START_X + " integer, "
                + SHAPE_BAR_START_Y + " integer, "
                + SHAPE_BAR_END_X + " integer, "
                + SHAPE_BAR_END_Y + " integer, "
                + SHAPE_IMAGE_TITLE + " varchar(100), "
                + SHAPE_HAS_MUTED_STRINGS + " varchar(100), "
                + SHAPE_SIXTH_STRING_MUTED + " varchar(100), "
                + SHAPE_FIFTH_STRING_MUTED + " varchar(100), "
                + SHAPE_FOURTH_STRING_MUTED + " varchar(100), "
                + SHAPE_THIRD_STRING_MUTED + " varchar(100), "
                + SHAPE_SECOND_STRING_MUTED + " varchar(100), "
                + SHAPE_FIRST_STRING_MUTED + " varchar(100))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
