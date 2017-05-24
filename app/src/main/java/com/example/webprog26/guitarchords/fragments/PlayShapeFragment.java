package com.example.webprog26.guitarchords.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.app.GuitarChordsApp;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadNotesBitmapsEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.FretTouchListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayShapeFragmentManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by webpr on 17.05.2017.
 */

public class PlayShapeFragment extends Fragment {

    private static final String TAG = "PlayShapeFragment";

    public static final String FRAGMENT_PLAYABLE_SHAPE_POSITION = "activity_playable_shape_position";

    @BindView(R.id.gl_fret)
    GridLayout mGlFret;

    private Unbinder unbinder;
    private PlayShapeFragmentManager mPlayShapeFragmentManager;


    public static PlayShapeFragment newInstance(final int playableShapePosition){
        Bundle args = new Bundle();
        args.putSerializable(FRAGMENT_PLAYABLE_SHAPE_POSITION, playableShapePosition);
        PlayShapeFragment playShapeFragment = new PlayShapeFragment();
        playShapeFragment.setArguments(args);
        return playShapeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        EventBus.getDefault().register(this);
        if(getArguments() != null){

            final int playableShapePosition = getArguments().getInt(FRAGMENT_PLAYABLE_SHAPE_POSITION, -1);

            if(playableShapePosition != -1){
//                Log.i(TAG, "playableShapePosition: " + playableShapePosition);
                if(GuitarChordsApp.getShapesHolder().getShapesSize() > 0){
//                    Log.i(TAG, "GuitarChordsApp.getShapesHolder().getShapesSize(): " + GuitarChordsApp.getShapesHolder().getShapesSize());
                    final PlayableShape playableShape = GuitarChordsApp.getShapesHolder().getChordPlayableShapes().get(playableShapePosition);

                    if(playableShape != null){

                    mPlayShapeFragmentManager = new PlayShapeFragmentManager(playableShape);

                        Log.i(TAG, playableShape.toString());
                        for(Note note: playableShape.getNotes()){
                            Log.i(TAG, note.toString());

                            if(note.getNoteTitleDrawable() != null){
                                Log.i(TAG, note.getNoteTitleDrawable().toString());

                            } else {
//                                Log.i(TAG, "note.getNoteTitleDrawable() is null");
                            }
                        }

                        if(playableShape.isHasBar()){
//                            Log.i(TAG, "bar starts at " + playableShape.getBarStartPoint().toString());
//                            Log.i(TAG, "bar ends at " + playableShape.getBarEndPoint().toString());
                        }

//                        EventBus.getDefault().post(new LoadNotesBitmapsEvent());
                    }
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadNotesBitmapsEvent(LoadNotesBitmapsEvent loadNotesBitmapsEvent){
//        mPlayNotesManager.addNotesBitmapsToPlayableShape();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playable_shape_layout, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){

            if(playShapeFragmentManager.getPlayableShape() != null){

                if(playShapeFragmentManager.getPlayableShape().getNotes() != null){

                    for(Note note: playShapeFragmentManager.getPlayableShape().getNotes()){

                        if(note.getNoteTitleDrawable() != null){

                            RelativeLayout fretRelativeLayout = (RelativeLayout) getFret().getChildAt(note.getNotePlace());
                            ImageView stringImageView = (ImageView) fretRelativeLayout.getChildAt(0);
                            stringImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                            stringImageView.requestLayout();
                            stringImageView.setImageDrawable(note.getNoteTitleDrawable());

                        } else {

//                            Log.i(TAG, "note.getNoteTitleDrawable() is null");

                        }
                    }
                }
            }
        }

        getFret().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getFret().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int stringIndex = 0;


                if(playShapeFragmentManager != null){

                    for(int i = 4; i < getFret().getChildCount(); i+= 5){
                        RelativeLayout relativeLayout = (RelativeLayout) getFret().getChildAt(i);

                        if(relativeLayout != null){

                            ImageView stringImageView = (ImageView) relativeLayout.getChildAt(0);

                            if(stringImageView != null){
                                playShapeFragmentManager.setStringsCoordinates((relativeLayout.getX() + stringImageView.getX() - 20),
                                        (relativeLayout.getX() + stringImageView.getX()) + stringImageView.getWidth() + 20, stringIndex);
                                stringIndex++;
                            }
                        }
                    }
                }

                if(playShapeFragmentManager != null){

                    Fretboard fretboard = playShapeFragmentManager.getFretboard();

                    if(fretboard != null){

                        for(int i = 0; i < 6; i++){

                            GuitarString currentGuitarString = fretboard.getGuitarString(i);

                            if(currentGuitarString != null){

                                Note currentNote = currentGuitarString.getNote();

                                if(currentNote != null){

                                    float stringPlayableY;

                                    if(currentNote.getNoteCoordinates().y == 0){
                                        stringPlayableY = 0;
                                    } else {
                                        stringPlayableY = (float) getFret().getChildAt(currentNote.getNoteCoordinates().y - 1).getBottom();
                                    }

//                                    Log.i(TAG, "note y: " + currentNote.getNoteCoordinates().y);

                                    Log.i(TAG, currentGuitarString.getTitle() + " with note " + currentGuitarString.getNote().getNoteTitle()
                                            + " stringPlayableY " + stringPlayableY);
                                    playShapeFragmentManager.setStringPlayableY(stringPlayableY, i);

                                } else {
                                    Log.i(TAG, "currentNote is null");
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

            catchFretboardTouches();
            }
        });
    }

    private void catchFretboardTouches(){
        getFret().setOnTouchListener(new FretTouchListener(getPlayShapeFragmentManager()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public PlayShapeFragmentManager getPlayShapeFragmentManager() {
        return mPlayShapeFragmentManager;
    }

    private GridLayout getFret(){
        return mGlFret;
    }
}
