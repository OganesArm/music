package com.example.music3;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.example.music3.MainActivity.musicSound;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("mus", true);
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();


        if (save.getBoolean("mus", false) & !musicSound.isPlaying()
                & !save.getBoolean("musStop", false)){

            int position = save.getInt("position", 0);
            musicSound.start();
            musicSound.seekTo(position);
            musicSound.setLooping(true);
            editor.putBoolean("mus", true);
            editor.apply();
        }
        if (!save.getBoolean("mus", false) & !save.getBoolean("musStop", false)){
            musicSound.pause();
            int pos = musicSound.getCurrentPosition();
            editor.putInt("position", pos);
            editor.apply();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();


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

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        if (!save.getBoolean("musStop", false)) {
            editor.putBoolean("mus", false);
            editor.apply();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
