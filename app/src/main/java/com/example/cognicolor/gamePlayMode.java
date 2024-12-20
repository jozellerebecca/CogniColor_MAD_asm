package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class gamePlayMode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay_options);

        ImageView btnProfile = findViewById(R.id.btn_profile);
        Button btnNormalMode = findViewById(R.id.btn_normal_mode);
        Button btnCreativeMode = findViewById(R.id.btn_creative_mode);

        btnProfile.setOnClickListener(view -> {
            Intent intent = new Intent(gamePlayMode.this, profilePage.class);
            startActivity(intent);
        });

        btnNormalMode.setOnClickListener(view -> {
            Intent intent = new Intent(gamePlayMode.this, normalMode.class);
            startActivity(intent);
        });

        btnCreativeMode.setOnClickListener(view -> {
            Intent intent = new Intent(gamePlayMode.this, creativeMode.class);
            startActivity(intent);
        });
    }
}
