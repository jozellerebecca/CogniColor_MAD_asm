package com.example.cognicolor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class reviewMistakes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_mistakes);

        TextView mistakesTextView = findViewById(R.id.mistakes);

        List<String> mistakes = getIntent().getStringArrayListExtra("mistakes");

        if (mistakes != null && !mistakes.isEmpty()) {
            StringBuilder mistakesText = new StringBuilder("Mistakes:\n");
            for (String mistake : mistakes) {
                mistakesText.append(mistake).append("\n");
            }
            mistakesTextView.setText(mistakesText.toString());
        } else {
            mistakesTextView.setText("No mistakes made!");
        }
    }
}
