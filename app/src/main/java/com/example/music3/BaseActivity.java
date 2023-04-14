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

        if (!save.getBoolean("musMemory", false)) {
            musicSound = MediaPlayer.create(this, R.raw.music);
            editor.putBoolean("musMemory", true);
            editor.apply();

        }
        if (save.getBoolean("mus", false) & !save.getBoolean("musStart", false)){
            editor.putBoolean("musStart", true);
            editor.apply();
            if (!musicSound.isPlaying()) {
                int position = save.getInt("position", 0);
                musicSound.start();
                musicSound.seekTo(position);
                musicSound.setLooping(true);
            }
        }
        if (!save.getBoolean("mus", false)){
            int pos = musicSound.getCurrentPosition();
            editor.putInt("position", pos);
            Log.d(TAG, String.valueOf(pos));
            musicSound.pause();
            editor.apply();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.apply();

        if (!save.getBoolean("mus", false)){
            int pos = musicSound.getCurrentPosition();
            editor.putInt("position", pos);
            Log.d(TAG, String.valueOf(pos));
            editor.putBoolean("musStart", false);
            editor.apply();
            musicSound.pause();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("mus", false);
        editor.apply();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("musStart", false);
        editor.putBoolean("musMemory", false);
        editor.putBoolean("i", false);
        editor.apply();
    }


}
