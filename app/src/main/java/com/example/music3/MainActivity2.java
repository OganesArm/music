package com.example.music3;

import static com.example.music3.MainActivity.musicSound;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity2 extends BaseActivity {
    Button button;
    SwitchMaterial switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = findViewById(R.id.button);
        switch1 = findViewById(R.id.switch1);
        switchCheck();
        switchMethod();
    }

    // Проверка на положение кнопки музик
    public void switchCheck() {
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        if (save.getBoolean("switch", true)) {
            switch1.setChecked(true);
        }
        if (!save.getBoolean("switch", true)) {
            switch1.setChecked(false);
        }
    }

    // отработка переключателя
    public void switchMethod()  {
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
            SharedPreferences.Editor editor = save.edit();

            if (isChecked) {
                editor.putBoolean("mus", true);
                editor.putBoolean("musStop", false);
                editor.putBoolean("switch", true);
                editor.apply();

                int position = save.getInt("position", 0);
                // постепенное увеличение громкости в течение  мсек
                //  Учитывай что идет задержка переключение свича(кнопки) !!!!!!!
                int duration = 100;
                float volume = 0.1f;
                float maxVolume = 1.0f;
                float volumeStep = (maxVolume - volume) / duration;
                float currentVolume = volume;
                musicSound.setVolume(currentVolume, currentVolume);
                musicSound.start();
                musicSound.seekTo(position);
                musicSound.setLooping(true);
                for (int i = 0; i < duration; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentVolume += volumeStep;
                    musicSound.setVolume(currentVolume, currentVolume);
                }
            } else {
                editor.putBoolean("mus", false);
                editor.putBoolean("musStop", true);
                editor.putInt("position", 0);
                editor.apply();
                musicSound.pause();
                int pos = musicSound.getCurrentPosition();
                editor.putInt("position", pos);
                editor.putBoolean("switch", false);
                editor.apply();
            }
        });
    }

    public void go3(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    // Кнопка назад на телефоне. Нужно назначать куда оно отправит всегда, инач баг.
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}