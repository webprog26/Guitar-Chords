package com.example.webprog26.guitarchords.fragments;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
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
import android.widget.TextView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.app.GuitarChordsApp;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadNotesBitmapsEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.LoadNotesSoundsEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.NoteSoundsLoadedEvent;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.FretNumbersTransformHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.listeners.FretTouchListener;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.PlayShapeFragmentManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import butterknife.BindViews;
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
    @BindView(R.id.tv_fret_to_start)
    TextView mTvFretToStart;
    @BindViews({R.id.first_string_muted,
                R.id.second_string_muted,
                R.id.third_string_muted,
                R.id.fourth_string_muted,
                R.id.fifth_string_muted,
                R.id.sixth_string_muted})
    ImageView[] mStringMutedImageViews;

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

    @Override
    public void onResume() {
        super.onResume();

        PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){
            playShapeFragmentManager.createSoundPool();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){
            playShapeFragmentManager.releaseSoundPool();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadNotesBitmapsEvent(LoadNotesBitmapsEvent loadNotesBitmapsEvent){
//        mPlayNotesManager.addNotesBitmapsToPlayableShape();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_shape_fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){

            Fretboard fretboard = playShapeFragmentManager.getFretboard();

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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoadNotesSoundsEvent(LoadNotesSoundsEvent loadNotesSoundsEvent){
        SoundPool soundPool = getPlayShapeFragmentManager().getSoundPool();

        try {
            AssetManager assetManager = getActivity().getAssets();
            AssetFileDescriptor descriptor;

            PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

            if(playShapeFragmentManager != null){

                Fretboard fretboard = playShapeFragmentManager.getFretboard();

                if(fretboard != null){

                    for(int i = 0; i < Fretboard.STRINGS_COUNT; i++){
                        GuitarString guitarString = fretboard.getGuitarString(i);

                       if(guitarString != null){

                           Note note = guitarString.getNote();

                           if(note != null){

                               String noteSoundPath = note.getNoteSoundPath();

                               if(noteSoundPath != null){

                                   descriptor = assetManager.openFd(noteSoundPath);
                                   note.setNoteSound(soundPool.load(descriptor, 0));
                               }
                           }
                       }
                    }
                }
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        EventBus.getDefault().post(new NoteSoundsLoadedEvent());
    }

    private void catchFretboardTouches(){
        getFret().setOnTouchListener(new FretTouchListener(getPlayShapeFragmentManager()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNoteSoundsLoadedEvent(NoteSoundsLoadedEvent noteSoundsLoadedEvent){
        final PlayShapeFragmentManager playShapeFragmentManager = getPlayShapeFragmentManager();

        if(playShapeFragmentManager != null){

            if(playShapeFragmentManager.getPlayableShape() != null){

                getTvFretToStart().setText(
                        FretNumbersTransformHelper
                                .getFretToStartString(playShapeFragmentManager
                                                        .getPlayableShape().getStartFretNumber()));

                if(playShapeFragmentManager.getPlayableShape().getNotes() != null){

                    for(final Note note: playShapeFragmentManager.getPlayableShape().getNotes()){

                        if(note.getNoteTitleDrawable() != null){

                            RelativeLayout fretRelativeLayout = (RelativeLayout) getFret().getChildAt(note.getNotePlace());
                            ImageView stringImageView = (ImageView) fretRelativeLayout.getChildAt(0);
                            stringImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                            stringImageView.requestLayout();
                            stringImageView.setImageDrawable(note.getNoteTitleDrawable());
                            stringImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Drawable noteImageDrawable;
                                    Log.i(TAG, "note.isFingerIndexVisible() " + note.isFingerIndexVisible());
                                    if(!note.isFingerIndexVisible()){
                                        noteImageDrawable = note.getNoteFingerIndexDrawable();
                                        note.setFingerIndexVisible(true);
                                    } else {
                                        noteImageDrawable = note.getNoteTitleDrawable();
                                        note.setFingerIndexVisible(false);
                                    }

                                    if(noteImageDrawable != null){
                                        ((ImageView) v).setImageDrawable(noteImageDrawable);
                                    }
                                }
                            });
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
                GridLayout fretGridLayout = getFret();

                if(fretGridLayout != null){
                    fretGridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int stringIndex = 0;


                    if(playShapeFragmentManager != null){

                        for(int i = 4; i < fretGridLayout.getChildCount(); i+= 5){
                            RelativeLayout relativeLayout = (RelativeLayout) fretGridLayout.getChildAt(i);

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

                            for(int i = 0; i < Fretboard.STRINGS_COUNT; i++){

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
                    fretGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
                }
            }
        });
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

    private PlayShapeFragmentManager getPlayShapeFragmentManager() {
        return mPlayShapeFragmentManager;
    }

    private GridLayout getFret(){
        return mGlFret;
    }

    private TextView getTvFretToStart(){
        return mTvFretToStart;
    }

    private ImageView[] getStringMutedImageViews() {
        return mStringMutedImageViews;
    }
}
