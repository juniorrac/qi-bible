package notorious.qibible.activiteListCoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import notorious.qibible.R;
import notorious.qibible.coin.MenuCoinActivity;

public class JeanActivity extends AppCompatActivity {
    private TextView questionLabel, coinText;;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    int coinValue   = 0;
    private String rightAnswer, mot = names[(int) (Math.random() * names.length)];
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    Typeface tb, sb;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    private static final String[] names = {"Samarie","Judée","Babylone","Bethléem",
            "la crucifixion","le dernier repas","La naissance de Jésus","les béatitudes",
            "Silata","Salem","Sinatra","Siloé"




    };

    //a = +names[(int) (Math.andom() * names.length)]





    String quizData[][] = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
//            {"Quel autre nom l’évangile de Jean donne-t-il à l’Esprit Saint?","Le Paraclet","Le souffle de Dieu","L’esprit de Jésus","Esprit de vérité"},
//            {"Dans l’évangile de Jean, combien de temps dure le ministère de Jésus?","3 ans","2 ans","1 an","7 ans"},
//            {"Quel récit ne fait pas partie de l’évangile selon Jean?","les béatitudes","La parabole du fils prodigue","la samaritaine","la multiplication des pains"},
//            {"Quel récit fait partie de l’évangile selon Jean?","la crucifixion","le dernier repas","La naissance de Jésus","les béatitudes"},
//            {"Pourquoi l’aigle est-il associé à l’évangéliste Jean?","Il symbolise les êtres célestes","À cause de sa grande acuité visuelle","Il est un symbole de puissance et de victoire","Jean aime les aigles"},

//            {"Quel livre vient avant l'Évangile de Jean dans le Nouveau Testament?","Luc","Matthieu","Actes des Apotres","Marc"},
//            {"Combien de chapitres l'Évangile de Jean a-t-il?","21","25","27","30"},
//            {"Où Jésus a-t-il accompli son premier miracle en public?","Galilée","Jérusalem","Nazareth","Judée"},
//            {"Selon les Juifs combien d'années a-t-il fallu pour construire le temple?","46","45","50","49"},
//            {"Où se trouva le puits de Jacob?","Samarie","Judée","Babylone","Bethléem"},
//            {"Qu'est-ce que Jésus dit à l'homme malade à la piscine près de la porte des brebis?","Lève-toi, prends ton lit, et marche","Lève-toi, ta foi t'as sauvé","Tu es guéri, vas en paix","Vas et plonges dans la piscine"},
//            {"Vas-toi et plonges dans la piscine","12","17","13","10"},
            {"Jésus demanda à l'homme aveugle à laver quelle piscine?","Siloé",""+names[(int) (Math.random() * names.length)],""+names[(int) (Math.random() * names.length)],""+names[(int) (Math.random() * names.length)]},
//            {"","","","",""},
//            {"","","","",""},
//            {"","","","",""},

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genese);

//        countLabel = findViewById(R.id.countLabel);

        coinText = findViewById(R.id.coinText);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");

        coinText.setTypeface(tb);

        coinText.setText(String.valueOf(coinValue));

        // Receive quizCategory from StartActivity.
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY", quizCategory + "");


        // Create quizArray from quizData.
        for (int i = 0; i < quizData.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // Country
            tmpArray.add(quizData[i][1]); // Right Answer
            tmpArray.add(quizData[i][2]); // Choice1
            tmpArray.add(quizData[i][3]); // Choice2
            tmpArray.add(quizData[i][4]); // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        // Generate random number between 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        // Remove "Country" from quiz and Shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set choices.
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //On BackPressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuCoinActivity.class);
        startActivity(intent);
        finish();
    }

    public void resetColor() {
        answerBtn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
        answerBtn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
        answerBtn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
        answerBtn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));

    }
    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());

        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {

            answerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borders_button_true));
            // Correct
//            alertTitle = "Correct!";
            coinValue++;

            coinText.setText(String.valueOf(coinValue));
            rightAnswerCount++;

        } else {
            //           alertTitle = "Wrong...";
            answerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borders_button_false));
        }

        // Create AlertDialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // Show Result.
//                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
//                    startActivity(intent);

                } else {
                    resetColor();
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}