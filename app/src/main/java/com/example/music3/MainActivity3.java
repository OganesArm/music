package com.example.music3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends BaseActivity {
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button2=findViewById(R.id.button2);

    }
    public void go4(View view) {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
}