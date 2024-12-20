package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class mainEntry extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry);

        Button btnSignIn = findViewById(R.id.btn_sign_in);
        Button btnSignUp = findViewById(R.id.btn_sign_up);

        btnSignIn.setOnClickListener(view -> {
            Intent intent = new Intent(mainEntry.this, signIn.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(mainEntry.this, signUp.class);
            startActivity(intent);
        });
    }
}
