package notorious.qibible.activiteListCoin;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import notorious.qibible.R;
import notorious.qibible.coin.MenuCoinActivity;

public class CultureGeneralActivity extends AppCompatActivity {


    private TextView questionLabel, coinText;;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    int coinValue   = 0;
    private String rightAnswer;
    private int rightAnswerCount = 0, count =0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;


    private LinearLayout optionsContainer;

    Typeface tb, sb;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            {"Jésus dit des paroles pour calmer le vent et la mer. Quelles sont ces paroles?","Silence! Tais-toi!","Mer, calme-toi!","Mer, calme-toi! Vent, Silence!","Chut !!!"},
            {"Selon la bible, dans quel jour Dieu créa-t-il l'être humain?","7","6","8","3"},
            {"Comment se nomme le dernier repas de Jesus ?", "La cène", "l'Eucharistie", "Le repas", "La Scene"},
            { "Quel miracle Jésus accomplit-il sur la barque de Simon ?", "la pêche miraculeuse", "la marche sur l’eau", "la Cene", "la tempête calmée"},
            {"Quelle est la signification du nom Noé ?", "Repos ou Reconfort", "Benediction", "Sauve des eaux", "Dieu parmis nous"},
            {"Quel est le signe de l’alliance entre Noé et DIEU?", "Un arc-en-ciel", " Dix Commandements", "La fin du Deluge", "Le soleil"},
            {"Je suis l'homme aux pieds de qui Saul de Tarse a été instruit.","Gamaliel","Apollos","Ananias","Caïphe"},
            {"Je suis un homme riche, disciple de Jésus. J'ai réclamé à Pilate le corps de Jésus","Joseph d'Arimathée","Nicodème","Simon de Cyrènne","Zachée"},
            {"Je suis le disciple qui a été tiré au sort pour remplacer Judas : ","Matthias","Barnabas","Paul","Aquilas"},
            {"Je suis un chrétien de Damas vers qui Paul est venu pour être baptisé.","Ananias","Silas","Lazare","Demas"},
            {"Je suis la femme du souverain sacrificateur Zacharie.","Elisabeth","Lydie","Marie","Anne"},
            {"Je suis la femme qu'épousa Abraham après la mort de Sara.","Ketura","Debora","Dina","Rebecca"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genese);

//        countLabel = findViewById(R.id.countLabel);

        optionsContainer = findViewById(R.id.optionsContainer);
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

        public void playAnim(final View view, final int value /* ,final String data */){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(1000).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value == 0 && count < 4){
//                    String option =  "";
//                    if(count == 0){
//                        option = list.get(position).getOptionA();
//                    }else if(count == 1){
//                        option = list.get(position).getOptionB();
//                    }else if(count == 2){
//                        option = list.get(position).getOptionC();
//                    }else if(count == 3){
//                        option = list.get(position).getOptionD();
//                    }
//                    playAnim(optionsContainer.getChildAt(count), 0/*,option */);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (value == 0){
                    playAnim(view,1);
                }

//                if(value == 0 ){
//                    try{
//                        ((TextView) view).setText(data);
//                    }catch (ClassCastException e){
//                        ((Button) view).setText(data);
//                    }
//                    view.setTag(data);
//                    playAnim(view,1,data);
//                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void showNextQuiz() {

        // Generate random number between 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

//        playAnim(questionLabel, 0 /*, quiz.get(0) */);

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

            answerBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            // Correct
//            alertTitle = "Correct!";
            coinValue++;

            coinText.setText(String.valueOf(coinValue));
            rightAnswerCount++;

        } else {
            //           alertTitle = "Wrong...";
            answerBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
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
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

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