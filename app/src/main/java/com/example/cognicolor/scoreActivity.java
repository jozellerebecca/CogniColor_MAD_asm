package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class scoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView scoreTextView = findViewById(R.id.score);
        Button reviewMistakesButton = findViewById(R.id.btn_review_mistakes);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalLevels = intent.getIntExtra("totalLevels", 0);
        long averageTime = intent.getLongExtra("averageTime", 0);
        List<String> mistakes = intent.getStringArrayListExtra("mistakes");

        scoreTextView.setText("Score: " + score + "/" + totalLevels + "\nAvg Time: " + averageTime + " ms");

        reviewMistakesButton.setOnClickListener(v -> {
            Intent reviewIntent = new Intent(this, reviewMistakes.class);
            reviewIntent.putStringArrayListExtra("mistakes", (ArrayList<String>) mistakes);
            startActivity(reviewIntent);
        });
    }
}
