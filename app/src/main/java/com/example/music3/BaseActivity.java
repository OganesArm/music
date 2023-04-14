package com.example.music3;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.example.music3.MainActivity.musicSound;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "MyLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

    }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("mus", true);
        editor.apply();
        Log.d(TAG, "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        Log.d(TAG, "onResume");


        if (save.getBoolean("mus", false) & !musicSound.isPlaying()
                & !save.getBoolean("musStop", false)){

            int position = save.getInt("position", 0);
            musicSound.start();
            musicSound.seekTo(position);
            musicSound.setLooping(true);
            editor.putBoolean("mus", true);
            editor.apply();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        Log.d(TAG, "onStop");


        if (!save.getBoolean("mus", false) & !save.getBoolean("musStop", false)){
            musicSound.pause();
            int pos = musicSound.getCurrentPosition();
            editor.putInt("position", pos);
            editor.apply();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        if (!save.getBoolean("musStop", false)) {
            editor.putBoolean("mus", false);
            editor.apply();
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("mus", true);
        editor.apply();
        Log.d(TAG, "onRestart");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }


}
