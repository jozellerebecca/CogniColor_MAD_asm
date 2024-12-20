package com.example.cognicolor;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class creativeMode extends AppCompatActivity {

    private TextView timeTextView;
    private Button nextLevelButton;

    private long startTime;
    private int currentLevel = 1;
    private int score = 0;
    private List<String> userMistakes = new ArrayList<>();
    private long totalTime = 0;
    private int totalLevels = 10; // Adjust this as needed
    private Set<Button> selectedButtons = new HashSet<>();

    private final String[][] correctColorsPerLevel = {
            {"#0000FF", "#B0B0B0"},
            {"#800080", "#0000FF"},
            {"#de65cd", "#B0B0B0"},
            {"#800080", "#c2644f"},
            {"#B0B0B0", "#c2644f"},
            {"#0000FF", "#de65cd"},
            {"#de65cd", "#800080"},
            {"#c2644f", "#de65cd"},
            {"#0000FF", "#800080"},
            {"#B0B0B0", "#800080"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative_mode);

        timeTextView = findViewById(R.id.time);
        nextLevelButton = findViewById(R.id.btn_next_level);

        startTime = SystemClock.elapsedRealtime();
        setupColorButtons();
        setupNextLevelButton();
    }

    private void setupColorButtons() {
        int[] buttonIds = {
                R.id.btn_first_choice, R.id.btn_second_choice, R.id.btn_third_choice,
                R.id.btn_fourth_choice, R.id.btn_fifth_choice
        };

        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(v -> {
                if (selectedButtons.contains(button)) {
                    selectedButtons.remove(button);
                    button.setAlpha(1.0f);
                } else {
                    if (selectedButtons.size() < 3) {
                        selectedButtons.add(button);
                        button.setAlpha(0.5f);
                    } else {
                        Toast.makeText(this, "You can select up to 3 colors!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setupNextLevelButton() {
        nextLevelButton.setOnClickListener(v -> {
            if (selectedButtons.size() < 2) {
                Toast.makeText(this, "Please select at least 2 colors.", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isCorrect = validateSelection();
            if (isCorrect) {
                score++;
            } else {
                userMistakes.add("Level " + currentLevel + ": Incorrect selection");
            }

            long elapsedTime = SystemClock.elapsedRealtime() - startTime;
            totalTime += elapsedTime;

            if (currentLevel < totalLevels) {
                currentLevel++;
                resetLevel();
            } else {
                showScoreScreen();
            }
        });
    }

    private boolean validateSelection() {
        Set<String> selectedColors = new HashSet<>();
        for (Button button : selectedButtons) {
            selectedColors.add(String.format("#%06X", (0xFFFFFF & button.getBackgroundTintList().getDefaultColor())));
        }

        for (String correctColor : correctColorsPerLevel[currentLevel - 1]) {
            if (!selectedColors.contains(correctColor)) {
                return false;
            }
        }
        return true;
    }

    private void resetLevel() {
        startTime = SystemClock.elapsedRealtime();
        timeTextView.setText("00:00");

        for (Button button : selectedButtons) {
            button.setAlpha(1.0f);
        }
        selectedButtons.clear();

        Toast.makeText(this, "Level " + currentLevel, Toast.LENGTH_SHORT).show();
    }

    private void showScoreScreen() {
        Intent intent = new Intent(this, scoreActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalLevels", totalLevels);
        intent.putExtra("averageTime", totalTime / totalLevels);
        intent.putStringArrayListExtra("mistakes", (ArrayList<String>) userMistakes);
        startActivity(intent);
        finish();
    }
}
