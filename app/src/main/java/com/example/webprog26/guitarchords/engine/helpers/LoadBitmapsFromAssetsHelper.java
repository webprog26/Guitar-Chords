package com.example.webprog26.guitarchords.engine.helpers;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class LoadBitmapsFromAssetsHelper {

    private static final String TAG = "BitmapsHelper";

    private static Bitmap loadBitmapFromAssets(AssetManager assetManager, String fileName){
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException ioe){
            ioe.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }

        if(bitmap != null){
            Log.i(TAG, bitmap.toString());
        } else {
            Log.i(TAG, "bitmap is nullllllll");
        }

        return bitmap;
    }

    private static ArrayList<Bitmap> getBitmapsFromAssets(AssetManager assetManager, String dirName){

        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        Log.i(TAG, "getBitmapsFromAssets");
        try {
            String[] fileNames = assetManager.list(dirName);
            Log.i(TAG, "getBitmapsFromAssets fileNames " + fileNames.length);
            for(String fileName: fileNames){
                Log.i(TAG, fileName);
                Bitmap bitmap = loadBitmapFromAssets(assetManager, dirName + "/" + fileName);
                if(bitmap != null){
                    bitmaps.add(bitmap);
                }
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        return bitmaps;
    }

    public static ArrayList<Bitmap> getBitmaps(AssetManager assetManager, String path){
        Log.i(TAG, "getBitmaps from path " + path);
        return getBitmapsFromAssets(assetManager, path);
    }
}
