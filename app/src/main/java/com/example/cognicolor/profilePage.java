package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class profilePage extends AppCompatActivity {

    private TextView usernameTextView, emailTextView, dobTextView, countryTextView;
    private ImageView profilePictureImageView;
    private Button editButton, creativeModeHistoryButton, normalModeHistoryButton, returnButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTextView = findViewById(R.id.username);
        emailTextView = findViewById(R.id.email);
        dobTextView = findViewById(R.id.dob);
        countryTextView = findViewById(R.id.country);
        profilePictureImageView = findViewById(R.id.profile_picture);
        editButton = findViewById(R.id.edit);
        creativeModeHistoryButton = findViewById(R.id.btn_cmode_history);
        normalModeHistoryButton = findViewById(R.id.btn_nmode_history);
        returnButton = findViewById(R.id.btn_return);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String dob = intent.getStringExtra("dob");
        String country = intent.getStringExtra("country");

        if (username != null) {
            loadUserData(username, email, dob, country);
        } else {
            Toast.makeText(this, "No user details found", Toast.LENGTH_SHORT).show();
        }

        creativeModeHistoryButton.setOnClickListener(v -> {
            Intent creativeHistoryIntent = new Intent(profilePage.this, creativeHistory.class);
            startActivity(creativeHistoryIntent);
        });

        normalModeHistoryButton.setOnClickListener(v -> {
            Intent normalHistoryIntent = new Intent(profilePage.this, normalHistory.class);
            startActivity(normalHistoryIntent);
        });

        returnButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent(profilePage.this, gamePlayMode.class);
            startActivity(returnIntent);
            finish();
        });
    }

    private void loadUserData(String username, String email, String dob, String country) {
        usernameTextView.setText("@" + username);
        emailTextView.setText("EMAIL: " + email);
        dobTextView.setText("DOB: " + dob);
        countryTextView.setText("COUNTRY: " + country);
    }
}
