package com.GraduationDesign.MusicPlayer.Web;

import android.widget.Toast;

import com.GraduationDesign.MusicPlayer.MusicPlayerApplication;


/**
 * Created by zhao on 2016/10/26.
 */

public class TextHelper {

    public static void showText(final String text){

        MusicPlayerApplication.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MusicPlayerApplication.getInstance(),text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showLongText(final String text){

        MusicPlayerApplication.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MusicPlayerApplication.getInstance(),text, Toast.LENGTH_LONG).show();
            }
        });
    }
}