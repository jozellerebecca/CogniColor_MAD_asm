package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class normalHistory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_history);

        Button btnReturnToProfile = findViewById(R.id.btn_return_nhistory);
        btnReturnToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(normalHistory.this, profilePage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
