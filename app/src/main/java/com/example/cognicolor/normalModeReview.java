package com.example.cognicolor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class normalModeReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_review);

        TextView scoreSummary = findViewById(R.id.score_summary);
        GridLayout reviewGrid = findViewById(R.id.review_grid);

        int score = getIntent().getIntExtra("score", 0);
        int totalRounds = getIntent().getIntExtra("totalRounds", 0);
        List<Mistake> mistakes = (List<Mistake>) getIntent().getSerializableExtra("mistakes");

        scoreSummary.setText("You identified " + score / 10 + "/" + totalRounds + " grids correctly.");

        if (mistakes == null || mistakes.isEmpty()) {
            scoreSummary.append("\nNo mistakes to review.");
            return;
        }

        for (Mistake mistake : mistakes) {
            GridLayout mistakeGrid = new GridLayout(this);
            mistakeGrid.setColumnCount(3); // Adjust column count based on the grid size

            for (int i = 0; i < mistake.gridColors.length; i++) {
                View cell = new View(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 150; // Adjusted cell size for better visibility
                params.height = 150;
                params.setMargins(10, 10, 10, 10); // Add margins between cells
                cell.setLayoutParams(params);

                if (i == mistake.correctCellIndex) {
                    cell.setBackgroundColor(Color.GREEN); // Correct cell
                } else if (i == mistake.userSelection) {
                    cell.setBackgroundColor(Color.RED); // User's incorrect selection
                } else {
                    cell.setBackgroundColor(mistake.gridColors[i]); // Default grid color
                }

                mistakeGrid.addView(cell);
            }

            reviewGrid.addView(mistakeGrid);
        }
    }

    public static class Mistake implements Serializable {
        public int[] gridColors;
        public int correctCellIndex;
        public int userSelection;

        public Mistake(int[] gridColors, int correctCellIndex, int userSelection) {
            this.gridColors = gridColors;
            this.correctCellIndex = correctCellIndex;
            this.userSelection = userSelection;
        }
    }
}
