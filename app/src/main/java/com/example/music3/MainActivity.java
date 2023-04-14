package com.example.music3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {
    public static MediaPlayer musicSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicSound = MediaPlayer.create(this, R.raw.music);

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        nin();
    }

    public void nin() {
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putBoolean("mus", false);
        editor.putBoolean("musStop", false);
        editor.putInt("position", 0);
        editor.putBoolean("switch", true);
        editor.apply();
    }
}