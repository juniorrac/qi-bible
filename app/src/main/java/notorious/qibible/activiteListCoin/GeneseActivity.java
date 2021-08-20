package notorious.qibible.activiteListCoin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import notorious.qibible.R;
import notorious.qibible.coin.MenuCoinActivity;

public class GeneseActivity extends AppCompatActivity {

    private TextView questionLabel, coinText, titre;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    int coinValue   = 0;
    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    Typeface tb, sb;
    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"Question", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Dans le poème de la création, lors de quel jour Dieu crée le soleil et la lune?","Le quatrième","Le deuxième","Le premier","Le sixième"},
            {"Quelle est la première action de Noé une fois sur terre après le déluge?","Il sacrifie des animaux au Seigneur","Il plante une vigne","Il bénit deux de ses fils","Il pria Dieu"},
            {"La descendance d’Abraham sera plus nombreuse que…","les étoiles dans le ciel","la poussière de Canaan","les grain de sable","les roches en Palestine"},
            {"Quelle est la réaction d’Abraham lorsqu’il apprend que sa femme concevra un enfant?","Il rit","Il pleure","Il rend grace","Il offrit un sacrifice"},
            {"Quel est le livre situé entre le Lévitique et le Deutéronome?","Nombres","Exode","Ruth","Psaumes"},
            {"Cinquième livre de la Bible, son nom signifie « deuxième loi »","Deutéronome","Exode","Siracide","Lévitique"},

            {"Quel âge avait Moïse à sa mort ?","120 ans","110 ans","80 ans","100 ans"},
            {"Comment s'appelait la femme de Moïse ?","Sephora","Marie","Dina","Saphira"},
            {"De quelle tribu Moïse était-il originaire ?","Levi","Gad","Juda","Simeon"},
            {"Comment s'appelait la femme Abraham après la mort de Sara.","Ketura","Debora","Dina","Rebecca"},
            {"\"N'aimons pas en paroles et avec la langue, mais en action et avec vérité !\"","Jean","Apollos","Paul","Jesus"},
            {"\"Où tu iras j'irai, ton peuple sera mon peuple, et ton Dieu sera mon Dieu.\"","Ruth","Sara","Rahab","Elisabeth"},
            {"De quelle tribu Jesus de Nazareth était-il originaire ?","Juda","Benjamin","Dan","Simeon"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genese);

//        countLabel = findViewById(R.id.countLabel);

        titre = findViewById(R.id.titre);
        coinText = findViewById(R.id.coinText);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail") ;

        titre.setText(Title);

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
                    Intent intent = new Intent(getApplicationContext(), ResultCoinActivity.class);
                    intent.putExtra("COIN_POINT", coinValue);
                    startActivity(intent);

//                    Intent intent = new Intent(getApplicationContext(), MenuCoinActivity.class);
//                    startActivity(intent);
                    finish();

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

//
//    FButton buttonA, buttonB, buttonC, buttonD;
//    TextView questionText, titre, timeText, resultText, coinText;
//    QibibleHelper qibibleHelper;
//    QibibleQuestion currentQuestion;
//    List<QibibleQuestion> list;
//    int qid = 0;
//    int timeValue = 20;
//    int coinValue   = 0;
//    CountDownTimer countDownTimer;
//    Typeface tb, sb;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_genese);
//
//        //Initializing variables
//        questionText = (TextView) findViewById(R.id.triviaQuestion);
//        buttonA = (FButton) findViewById(R.id.buttonA);
//        buttonB = (FButton) findViewById(R.id.buttonB);
//        buttonC = (FButton) findViewById(R.id.buttonC);
//        buttonD = (FButton) findViewById(R.id.buttonD);
//        titre = (TextView) findViewById(R.id.titre);
//        timeText = (TextView) findViewById(R.id.timeText);
//        resultText = (TextView) findViewById(R.id.resultText);
//        coinText = (TextView) findViewById(R.id.coinText);
//
//        //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
//        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
//        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
//        titre.setTypeface(sb);
//        questionText.setTypeface(sb);
//        buttonA.setTypeface(tb);
//        buttonB.setTypeface(tb);
//        buttonC.setTypeface(tb);
//        buttonD.setTypeface(tb);
////        timeText.setTypeface(tb);
//        resultText.setTypeface(sb);
//        coinText.setTypeface(sb);
//
//        //Our database helper class
//        qibibleHelper = new QibibleHelper(this);
//        //Make db writable
//        qibibleHelper.getWritableDatabase();
//
//        //It will check if the ques,options are already added in table or not
//        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
//        if (qibibleHelper.getAllTheQuestionsOfGenese().size() == 0) {
//            //If not added then add the ques,options in table
//            qibibleHelper.getAllTheQuestionsOfGenese();
//        }
//
//        //This will return us a list of data type QibibleQuestion
//        list = qibibleHelper.getAllTheQuestionsOfGenese();
//
//        //Now we gonna shuffle the elements of the list so that we will get questions randomly
//        Collections.shuffle(list);
//
//
//        //currentQuestion will hold the que, 4 option and ans for particular id
//        currentQuestion = list.get(qid);
//
//        //countDownTimer
//        countDownTimer = new CountDownTimer(22000, 1000) {
//            public void onTick(long millisUntilFinished) {
//
//                //here you can have your logic to set text to timeText
//                timeText.setText(String.valueOf(timeValue) + "\"");
//
//                //With each iteration decrement the time by 1 sec
//                timeValue -= 1;
//
//                //This means the user is out of time so onFinished will called after this iteration
//                if (timeValue == -1) {
//
//                    //Since user is out of time setText as time up
//                    resultText.setText(getString(R.string.timeup));
//
//                    //Since user is out of time he won't be able to click any buttons
//                    //therefore we will disable all four options buttons using this method
//                    disableButton();
//                }
//            }
//
//            //Now user is out of time
//            public void onFinish() {
//                //We will navigate him to the time up activity using below method
//                timeUp();
//            }
//        }.start();
//
//        //This method will set the que and four options
//        updateQueAndOptions();
//
//
//    }
//
//
//    public void updateQueAndOptions() {
//
//        //This method will setText for que and options
//        questionText.setText(currentQuestion.getQuestion());
//
//        buttonA.setText(currentQuestion.getOptA());
//        buttonB.setText(currentQuestion.getOptB());
//        buttonC.setText(currentQuestion.getOptC());
//        buttonD.setText(currentQuestion.getOptD());
//
//
//        timeValue = 20;
//
//        //Now since the user has ans correct just reset timer back for another que- by cancel and start
//        countDownTimer.cancel();
//        countDownTimer.start();
//
//        //set the value of coin text
//        coinText.setText(String.valueOf(coinValue));
//        //Now since user has ans correct increment the coinvalue
//        coinValue++;
//
//    }
//
//    //Onclick listener for first button
//    public void buttonA(View view) {
//        //compare the option with the ans if yes then make button color green
//        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
//            buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
//
//            qid++;
//            //get the que and 4 option and store in the currentQuestion
//            currentQuestion = list.get(qid);
//            //Now this method will set the new que and 4 options
//            updateQueAndOptions();
//            //reset the color of buttons back to white
//            resetColor();
//
//            //Check if user has not exceeds the que limit
//            if (qid < list.size() - 1) {
//
//                //Now disable all the option button since user ans is correct so
//                //user won't be able to press another option button after pressing one button
////                disableButton();
//
//                //Show the dialog that ans is correct
////                correctDialog();
//            }
//            //If user has exceeds the que limit just navigate him to GameWon activity
//            else {
//
//                gameWon();
//
//            }
//        }
//        //User ans is wrong then just navigate him to the PlayAgain activity
//        else {
//
//            gameLostPlayAgain();
//
//        }
//    }
//
//    //Onclick listener for sec button
//    public void buttonB(View view) {
//        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
//            buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
//            if (qid < list.size() - 1) {
//                disableButton();
//                correctDialog();
//            } else {
//                gameWon();
//            }
//        } else {
//            gameLostPlayAgain();
//        }
//    }
//
//    //Onclick listener for third button
//    public void buttonC(View view) {
//        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
//            buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
//            if (qid < list.size() - 1) {
//                disableButton();
//                correctDialog();
//            } else {
//                gameWon();
//            }
//        } else {
//
//            gameLostPlayAgain();
//        }
//    }
//
//    //Onclick listener for fourth button
//    public void buttonD(View view) {
//        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
//            buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
//            if (qid < list.size() - 1) {
//                disableButton();
//                correctDialog();
//            } else {
//                gameWon();
//            }
//        } else {
//            gameLostPlayAgain();
//        }
//    }
//
//    //This method will navigate from current activity to GameWon
//    public void gameWon() {
//        Intent intent = new Intent(this, GameWon.class);
//        startActivity(intent);
//        finish();
//    }
//
//    //This method is called when user ans is wrong
//    //this method will navigate user to the activity PlayAgain
//    public void gameLostPlayAgain() {
//        Intent intent = new Intent(this, PlayAgain.class);
//        startActivity(intent);
//        finish();
//    }
//
//    //This method is called when time is up
//    //this method will navigate user to the activity Time_Up
//    public void timeUp() {
//        Intent intent = new Intent(this, Time_Up.class);
//        startActivity(intent);
//        finish();
//    }
//
//    //If user press home button and come in the game from memory then this
//    //method will continue the timer from the previous time it left
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        countDownTimer.start();
//    }
//
//    //When activity is destroyed then this will cancel the timer
//    @Override
//    protected void onStop() {
//        super.onStop();
//        countDownTimer.cancel();
//    }
//
//    //This will pause the time
//    @Override
//    protected void onPause() {
//        super.onPause();
//        countDownTimer.cancel();
//    }
//
//    //On BackPressed
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this, MenuCoinActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    //This dialog is show to the user after he ans correct
//    public void correctDialog() {
//        final Dialog dialogCorrect = new Dialog(GeneseActivity.this);
//        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (dialogCorrect.getWindow() != null) {
//            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
//            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
//        }
//        dialogCorrect.setContentView(R.layout.dialog_correct);
//        dialogCorrect.setCancelable(false);
//        dialogCorrect.show();
//
//        //Since the dialog is show to user just pause the timer in background
//        onPause();
//
//
//        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
//        FButton buttonNext = (FButton) dialogCorrect.findViewById(R.id.dialogNext);
//
//        //Setting type faces
//        correctText.setTypeface(sb);
//        buttonNext.setTypeface(sb);
//
//        //OnCLick listener to go next que
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            //    This will dismiss the dialog
//                dialogCorrect.dismiss();
//                //it will increment the question number
//                qid++;
//                //get the que and 4 option and store in the currentQuestion
//                currentQuestion = list.get(qid);
//                //Now this method will set the new que and 4 options
//                updateQueAndOptions();
//                //reset the color of buttons back to white
//                resetColor();
//                //Enable button - remember we had disable them when user ans was correct in there particular button methods
//                enableButton();
//            }
//       });
//    }
//
//
//    //This method will make button color white again since our one button color was turned green
//    public void resetColor() {
//        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
//    }
//
//    //This method will disable all the option button
//    public void disableButton() {
//        buttonA.setEnabled(false);
//        buttonB.setEnabled(false);
//        buttonC.setEnabled(false);
//        buttonD.setEnabled(false);
//    }
//
//    //This method will all enable the option buttons
//    public void enableButton() {
//        buttonA.setEnabled(true);
//        buttonB.setEnabled(true);
//        buttonC.setEnabled(true);
//        buttonD.setEnabled(true);
//    }


}