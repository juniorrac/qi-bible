package notorious.qibible.activiteListCoin;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import notorious.qibible.R;
import notorious.qibible.coin.MenuCoinActivity;
import notorious.qibible.models.Question;

public class EnhancedResultActivity extends AppCompatActivity {

    private TextView scoreText, percentageText, pointsValueText, totalPointsText;
    private ProgressBar scoreProgressBar;
    private LinearLayout difficultyBreakdown;
    
    private int correctAnswers;
    private int totalQuestions;
    private int totalPointsEarned;
    private ArrayList<Question.Difficulty> questionDifficulties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_coin_new);

        initializeViews();
        getIntentData();
        displayResults();
        animateResults();
        updateTotalScore();
    }

    private void initializeViews() {
        scoreText = findViewById(R.id.scoreText);
        percentageText = findViewById(R.id.percentageText);
        pointsValueText = findViewById(R.id.pointsValueText);
        totalPointsText = findViewById(R.id.totalPointsText);
        scoreProgressBar = findViewById(R.id.scoreProgressBar);
        difficultyBreakdown = findViewById(R.id.difficultyBreakdown);
    }

    private void getIntentData() {
        correctAnswers = getIntent().getIntExtra("CORRECT_ANSWERS", 0);
        totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 5);
        totalPointsEarned = getIntent().getIntExtra("POINTS_EARNED", 0);
        
        // Récupérer les difficultés des questions (passées sous forme de chaînes)
        ArrayList<String> difficultyStrings = getIntent().getStringArrayListExtra("QUESTION_DIFFICULTIES");
        questionDifficulties = new ArrayList<>();
        
        if (difficultyStrings != null) {
            for (String diffStr : difficultyStrings) {
                try {
                    questionDifficulties.add(Question.Difficulty.valueOf(diffStr));
                } catch (Exception e) {
                    questionDifficulties.add(Question.Difficulty.FACILE); // Par défaut
                }
            }
        }
    }

    private void displayResults() {
        // Score principal
        scoreText.setText(correctAnswers + "/" + totalQuestions);
        
        // Pourcentage
        int percentage = (int) ((float) correctAnswers / totalQuestions * 100);
        percentageText.setText(percentage + "% de réussite");
        
        // Couleur du score selon le pourcentage
        if (percentage >= 80) {
            scoreText.setTextColor(Color.parseColor("#4CAF50")); // Vert
        } else if (percentage >= 60) {
            scoreText.setTextColor(Color.parseColor("#FF9800")); // Orange
        } else {
            scoreText.setTextColor(Color.parseColor("#F44336")); // Rouge
        }
        
        // Points gagnés
        pointsValueText.setText("+" + totalPointsEarned);
        
        // Total des points
        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0);
        totalPointsText.setText("Total: " + totalScore + " points");
        
        // Affichage détaillé des difficultés
        displayDifficultyBreakdown();
    }

    private void displayDifficultyBreakdown() {
        // Compter les réussites par difficulté
        int facileCorrect = 0, moyenCorrect = 0, difficileCorrect = 0, expertCorrect = 0;
        int facileTotal = 0, moyenTotal = 0, difficileTotal = 0, expertTotal = 0;
        
        for (int i = 0; i < questionDifficulties.size() && i < totalQuestions; i++) {
            Question.Difficulty diff = questionDifficulties.get(i);
            boolean wasCorrect = i < correctAnswers; // Simplification - dans un vrai cas, il faudrait passer les réponses individuelles
            
            switch (diff) {
                case FACILE:
                    facileTotal++;
                    if (wasCorrect) facileCorrect++;
                    break;
                case MOYEN:
                    moyenTotal++;
                    if (wasCorrect) moyenCorrect++;
                    break;
                case DIFFICILE:
                    difficileTotal++;
                    if (wasCorrect) difficileCorrect++;
                    break;
                case EXPERT:
                    expertTotal++;
                    if (wasCorrect) expertCorrect++;
                    break;
            }
        }
        
        // Créer les lignes de détail pour chaque difficulté présente
        if (facileTotal > 0) {
            addDifficultyRow(Question.Difficulty.FACILE, facileCorrect, facileTotal);
        }
        if (moyenTotal > 0) {
            addDifficultyRow(Question.Difficulty.MOYEN, moyenCorrect, moyenTotal);
        }
        if (difficileTotal > 0) {
            addDifficultyRow(Question.Difficulty.DIFFICILE, difficileCorrect, difficileTotal);
        }
        if (expertTotal > 0) {
            addDifficultyRow(Question.Difficulty.EXPERT, expertCorrect, expertTotal);
        }
    }

    private void addDifficultyRow(Question.Difficulty difficulty, int correct, int total) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0, 8, 0, 8);
        
        TextView difficultyLabel = new TextView(this);
        difficultyLabel.setText(difficulty.getDisplayName() + ":");
        difficultyLabel.setTextSize(14);
        difficultyLabel.setTextColor(Color.parseColor(difficulty.getColor()));
        difficultyLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        
        TextView scoreLabel = new TextView(this);
        scoreLabel.setText(correct + "/" + total + " (" + (correct * difficulty.getPoints()) + " pts)");
        scoreLabel.setTextSize(14);
        scoreLabel.setTextColor(Color.parseColor("#666666"));
        
        row.addView(difficultyLabel);
        row.addView(scoreLabel);
        difficultyBreakdown.addView(row);
    }

    private void animateResults() {
        // Animation de la barre de progression
        int percentage = (int) ((float) correctAnswers / totalQuestions * 100);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(scoreProgressBar, "progress", 0, percentage);
        progressAnimator.setDuration(2000);
        progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        
        // Démarrer l'animation après un court délai
        new Handler().postDelayed(() -> progressAnimator.start(), 500);
    }

    private void updateTotalScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int currentTotal = sharedPreferences.getInt("TOTAL_SCORE", 0);
        int newTotal = currentTotal + totalPointsEarned;
        
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_SCORE", newTotal);
        editor.apply();
        
        totalPointsText.setText("Total: " + newTotal + " points");
    }

    public void returnTop(View view) {
        Intent intent = new Intent(this, MenuCoinActivity.class);
        intent.putExtra("COIN_POINT", totalPointsEarned);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        returnTop(null);
    }
}