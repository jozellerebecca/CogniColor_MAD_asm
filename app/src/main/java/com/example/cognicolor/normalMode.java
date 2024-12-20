package com.example.cognicolor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class normalMode extends AppCompatActivity {

    private GridLayout gridLayout;
    private ProgressBar progressBar;
    private TextView timerText;
    private int correctCellIndex;
    private int score = 0;
    private int totalRounds = 10;
    private int currentRound = 0;
    private List<Mistake> mistakes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_mode);

        gridLayout = findViewById(R.id.grid_layout);
        progressBar = findViewById(R.id.progress_bar);
        timerText = findViewById(R.id.timer);

        startGame();
    }

    private void startGame() {
        if (currentRound < totalRounds) {
            setUpGrid();
            startTimer();
        } else {
            endGame();
        }
    }

    private void setUpGrid() {
        gridLayout.removeAllViews();
        int numCells = 9;
        Random random = new Random();

        int baseColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int oddColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        correctCellIndex = random.nextInt(numCells);
        int[] gridColors = new int[numCells];

        for (int i = 0; i < numCells; i++) {
            gridColors[i] = (i == correctCellIndex) ? oddColor : baseColor;

            View cell = new View(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 200;
            params.height = 200;
            params.setMargins(10, 10, 10, 10);
            cell.setLayoutParams(params);
            cell.setBackgroundColor(gridColors[i]);

            final int index = i;
            cell.setOnClickListener(v -> {
                if (index == correctCellIndex) {
                    score += 10;
                    progressBar.setProgress(score);
                } else {
                    mistakes.add(new Mistake(gridColors, correctCellIndex, index));
                }
                currentRound++;
                startGame();
            });

            gridLayout.addView(cell);
        }
    }

    private void startTimer() {
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time Left: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                Toast.makeText(normalMode.this, "Time's up!", Toast.LENGTH_SHORT).show();
                mistakes.add(new Mistake(new int[0], correctCellIndex, -1));
                currentRound++;
                startGame();
            }
        }.start();
    }

    private void endGame() {
        Intent intent = new Intent(this, normalModeReview.class);
        intent.putExtra("score", score);
        intent.putExtra("totalRounds", totalRounds);
        intent.putExtra("mistakes", (Serializable) mistakes);
        startActivity(intent);
        finish();
    }

    public static class Mistake implements Serializable {
        int[] gridColors;
        int correctCellIndex;
        int userSelection;

        Mistake(int[] gridColors, int correctCellIndex, int userSelection) {
            this.gridColors = gridColors;
            this.correctCellIndex = correctCellIndex;
            this.userSelection = userSelection;
        }
    }
}