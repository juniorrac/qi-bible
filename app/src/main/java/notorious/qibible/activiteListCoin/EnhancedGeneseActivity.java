package notorious.qibible.activiteListCoin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import notorious.qibible.R;
import notorious.qibible.models.Question;

public class EnhancedGeneseActivity extends AppCompatActivity {

    private TextView questionLabel, coinText, titre, difficultyLabel;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private int totalPointsEarned = 0;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    private static final int QUIZ_COUNT = 5;
    
    private ArrayList<Question> questions;
    private ArrayList<Question.Difficulty> questionDifficulties;
    private ArrayList<Boolean> correctAnswers;
    private Question currentQuestion;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genese_enhanced);

        initializeViews();
        setupQuestions();
        showNextQuiz();
    }

    private void initializeViews() {
        titre = findViewById(R.id.titre);
        coinText = findViewById(R.id.coinText);
        questionLabel = findViewById(R.id.questionLabel);
        difficultyLabel = findViewById(R.id.difficultyLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        // Configuration des données passées par l'intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        if (title != null) {
            titre.setText(title);
        }

        // Configuration des polices
        Typeface tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        coinText.setTypeface(tb);
        coinText.setText("0");

        // Initialiser les listes de suivi
        questionDifficulties = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    private void setupQuestions() {
        questions = new ArrayList<>();
        
        // Questions faciles (5 points)
        questions.add(new Question(
            "Dans le poème de la création, lors de quel jour Dieu crée le soleil et la lune?",
            "Le quatrième",
            new String[]{"Le quatrième", "Le deuxième", "Le premier", "Le sixième"},
            Question.Difficulty.FACILE
        ));
        
        questions.add(new Question(
            "Quel est le premier livre de la Bible?",
            "La Genèse",
            new String[]{"La Genèse", "L'Exode", "Le Lévitique", "Les Nombres"},
            Question.Difficulty.FACILE
        ));

        // Questions moyennes (10 points)
        questions.add(new Question(
            "Quelle est la première action de Noé une fois sur terre après le déluge?",
            "Il sacrifie des animaux au Seigneur",
            new String[]{"Il sacrifie des animaux au Seigneur", "Il plante une vigne", "Il bénit deux de ses fils", "Il pria Dieu"},
            Question.Difficulty.MOYEN
        ));

        questions.add(new Question(
            "La descendance d'Abraham sera plus nombreuse que…",
            "les étoiles dans le ciel",
            new String[]{"les étoiles dans le ciel", "la poussière de Canaan", "les grain de sable", "les roches en Palestine"},
            Question.Difficulty.MOYEN
        ));

        // Questions difficiles (15 points)
        questions.add(new Question(
            "Quelle est la réaction d'Abraham lorsqu'il apprend que sa femme concevra un enfant?",
            "Il rit",
            new String[]{"Il rit", "Il pleure", "Il rend grâce", "Il offrit un sacrifice"},
            Question.Difficulty.DIFFICILE
        ));

        questions.add(new Question(
            "Comment s'appelait la femme d'Abraham après la mort de Sara?",
            "Ketura",
            new String[]{"Ketura", "Debora", "Dina", "Rebecca"},
            Question.Difficulty.DIFFICILE
        ));

        // Questions expert (20 points)
        questions.add(new Question(
            "De quelle tribu Moïse était-il originaire?",
            "Levi",
            new String[]{"Levi", "Gad", "Juda", "Simeon"},
            Question.Difficulty.EXPERT
        ));

        questions.add(new Question(
            "Quel âge avait Moïse à sa mort?",
            "120 ans",
            new String[]{"120 ans", "110 ans", "80 ans", "100 ans"},
            Question.Difficulty.EXPERT
        ));

        // Mélanger les questions
        Collections.shuffle(questions);
    }

    public void showNextQuiz() {
        if (questions.isEmpty()) {
            // Si plus de questions, recommencer avec les questions originales
            setupQuestions();
        }

        // Prendre une question au hasard
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        currentQuestion = questions.get(randomIndex);
        questions.remove(randomIndex);

        // Afficher la question
        questionLabel.setText(quizCount + "/5: " + currentQuestion.getQuestion());
        
        // Afficher la difficulté avec couleur
        difficultyLabel.setText(currentQuestion.getDifficulty().getDisplayName());
        difficultyLabel.setTextColor(Color.parseColor(currentQuestion.getDifficulty().getColor()));

        // Mélanger les réponses
        String[] options = currentQuestion.getOptions().clone();
        ArrayList<String> optionsList = new ArrayList<>();
        for (String option : options) {
            optionsList.add(option);
        }
        Collections.shuffle(optionsList);

        // Afficher les boutons
        answerBtn1.setText(optionsList.get(0));
        answerBtn2.setText(optionsList.get(1));
        answerBtn3.setText(optionsList.get(2));
        answerBtn4.setText(optionsList.get(3));

        // Remettre les couleurs par défaut
        resetButtonColors();
    }

    private void resetButtonColors() {
        answerBtn1.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_borders));
        answerBtn2.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_borders));
        answerBtn3.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_borders));
        answerBtn4.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_borders));
    }

    public void checkAnswer(View view) {
        Button clickedButton = findViewById(view.getId());
        String selectedAnswer = clickedButton.getText().toString();
        boolean isCorrect = selectedAnswer.equals(currentQuestion.getCorrectAnswer());

        // Enregistrer la difficulté de cette question
        questionDifficulties.add(currentQuestion.getDifficulty());
        correctAnswers.add(isCorrect);

        if (isCorrect) {
            // Bonne réponse
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.borders_button_true));
            int points = currentQuestion.getDifficulty().getPoints();
            totalPointsEarned += points;
            rightAnswerCount++;
            
            coinText.setText(String.valueOf(totalPointsEarned));
            
            Toast.makeText(this, "✅ Correct! +" + points + " points", Toast.LENGTH_SHORT).show();
        } else {
            // Mauvaise réponse
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.borders_button_false));
            
            // Montrer la bonne réponse en vert
            showCorrectAnswer();
            
            Toast.makeText(this, "❌ Incorrect. La réponse était: " + currentQuestion.getCorrectAnswer(), Toast.LENGTH_LONG).show();
        }

        // Désactiver tous les boutons
        disableAllButtons();

        // Passer à la question suivante après un délai
        new Handler().postDelayed(() -> {
            if (quizCount == QUIZ_COUNT) {
                showResults();
            } else {
                quizCount++;
                enableAllButtons();
                showNextQuiz();
            }
        }, 2000);
    }

    private void showCorrectAnswer() {
        String correctAnswer = currentQuestion.getCorrectAnswer();
        
        if (answerBtn1.getText().toString().equals(correctAnswer)) {
            answerBtn1.setBackground(ContextCompat.getDrawable(this, R.drawable.borders_button_true));
        } else if (answerBtn2.getText().toString().equals(correctAnswer)) {
            answerBtn2.setBackground(ContextCompat.getDrawable(this, R.drawable.borders_button_true));
        } else if (answerBtn3.getText().toString().equals(correctAnswer)) {
            answerBtn3.setBackground(ContextCompat.getDrawable(this, R.drawable.borders_button_true));
        } else if (answerBtn4.getText().toString().equals(correctAnswer)) {
            answerBtn4.setBackground(ContextCompat.getDrawable(this, R.drawable.borders_button_true));
        }
    }

    private void disableAllButtons() {
        answerBtn1.setEnabled(false);
        answerBtn2.setEnabled(false);
        answerBtn3.setEnabled(false);
        answerBtn4.setEnabled(false);
    }

    private void enableAllButtons() {
        answerBtn1.setEnabled(true);
        answerBtn2.setEnabled(true);
        answerBtn3.setEnabled(true);
        answerBtn4.setEnabled(true);
    }

    private void showResults() {
        Intent intent = new Intent(this, EnhancedResultActivity.class);
        intent.putExtra("CORRECT_ANSWERS", rightAnswerCount);
        intent.putExtra("TOTAL_QUESTIONS", QUIZ_COUNT);
        intent.putExtra("POINTS_EARNED", totalPointsEarned);
        
        // Passer les difficultés sous forme de chaînes
        ArrayList<String> difficultyStrings = new ArrayList<>();
        for (Question.Difficulty diff : questionDifficulties) {
            difficultyStrings.add(diff.name());
        }
        intent.putStringArrayListExtra("QUESTION_DIFFICULTIES", difficultyStrings);
        
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, notorious.qibible.coin.MenuCoinActivity.class);
        startActivity(intent);
        finish();
    }
}