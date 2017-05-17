package com.example.webprog26.guitarchords.guitar_chords_engine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.events.ChordImageClickEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Chord shapes adapter
 */

public class ChordsShapesAdapter extends RecyclerView.Adapter<ChordsShapesAdapter.ChordShapesViewholder>{

    private ArrayList<ChordShape> mChordShapesList;
    private final Context mContext;

    public ChordsShapesAdapter(ArrayList<ChordShape> chordShapesList, Context context) {
        this.mChordShapesList = chordShapesList;
        this.mContext = context;
    }

    private ArrayList<ChordShape> getChordShapesList() {
        return mChordShapesList;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ChordsShapesAdapter.ChordShapesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChordsShapesAdapter.ChordShapesViewholder(LayoutInflater.from(getContext()).inflate(R.layout.chord_image_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ChordsShapesAdapter.ChordShapesViewholder holder, int position) {
        final ChordShape chordShape = getChordShapesList().get(position);

        ImageView ivChordImage = holder.getIvChordImage();

        Bitmap shapeImage = chordShape.getShapeImage();

        if(shapeImage != null)
            ivChordImage.setImageBitmap(shapeImage);

        ivChordImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ChordImageClickEvent(chordShape));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getChordShapesList().size();
    }

    class ChordShapesViewholder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_chord_image)
        ImageView mIvChordImage;

        ChordShapesViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        ImageView getIvChordImage() {
            return mIvChordImage;
        }
    }

    private void setChordShapesList(ArrayList<ChordShape> chordShapes) {
        this.mChordShapesList = chordShapes;
    }

    public void updateAdapterData(ArrayList<ChordShape> chordShapes){
        setChordShapesList(chordShapes);
        notifyDataSetChanged();
    }
}
