package notorious.qibible;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import notorious.qibible.activiteListCoin.ResultActivity;

public class TimerActivity extends AppCompatActivity {

    private TextView question, mTextViewCountDown, noIndicator;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4, next;
    private LinearLayout optionsContainer;

    private ProgressBar progressBar;

    private CountDownTimer mCountDownTimer;
    int timeValue = 60;

    private String rightAnswer;


    private static final long START_TIME_IN_MILLIS = 31000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

//    private int count= 0;
//    private int position = 0;
//    private List<QuestionModel> list;

    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    Typeface tb, sb;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String[][] quizData = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Que signifie le nom Jérémie ?", "Dieu élève", "Le Seigneur est mon Dieu", "Tristesse", "Beni de Dieu"},
            {"Où est-ce que Moïse va mourir ?",  "Au mont Nébo" , "Dans le Desert", "Au mont Sinaï ", "En terre de Canaan"},
            { "Qui est la mère de Joseph fils de Jacob ?", "Rachel", "Ruth", "Sarah", "Lea"},
            { "Le dernier repas de JESUS avec ses disciples se nomme ?", "La cène", "l'Eucharistie", "Le repas", "La Scene", "La cène"},
            {"Quel miracle Jésus accomplit-il sur la barque de Simon ?", "la pêche miraculeuse", "la marche sur l’eau", " la pêche au thon", "la tempête calmée"},
            {"La signification du nom Noé ?", "Repos ou Réconfort", "Sauve des eaux", "Sagesse", "Dieu parmis nous"},
            {"Le signe de l’alliance entre Noé et Dieu est : ", "Un arc-en-ciel", "Les dix Commandements", "La fin du Deluge", "Le soleil"},
            {"Dieu n'a pas permis au roi David de construire son temple car il : ", "était pas bon à ça", "a versé trop de sang", "allait bientôt mourir ", "etait un mauvais roi"},
            {"Notre Rédempteur nous supplie de pardonner à notre prochain ...", "70 × 7", "17 × 7", "7 × 7", "77 × 7"},
            {"Selon la bible, Quel jour Dieu créa l'homme ? ", "6", "5", "7", "3"},
            { "Quel est le premier livre de la BIBLE ?", "Genese", "Jeunesse", "Psaume", "Apocalypse"},
            { "Dans le poème de la création, quel jour DIEU crée la terre, la Nature?", "Le quatrième","Le premier", "Le deuxième", "Le troisième"},
            {"Où furent appelés \"CHRETIEN\" les disciples de Jésus ?", "Antioche", "Rome","Jerusalem", "Damas"},
            {"Pourquoi Abraham fut-il appelé l'ami de Dieu ?","il eut confiance dans les promesses de Dieu", "il etait un Patriarche", "il faisait le bien", "il priait beaucoup"},
            { "Qui fut le premier roi d'Israël ?", "Saül", "Isaac", "David", "Jeremi"},
            {"Quel est le plus grand commandement Dieu ? ", "Aimer DIEU de tout ton coeur", "Aime ton prochain comme toi même", "Tu ne tueras point", "Aimons nous les un et les autres"},
            {"Judas livre Jésus pour : ", "30 pieces d'argent", "30 pieces d'or", "30 deniers", "30 pieces de Yen"},
            {"La signification de Ève", "vivante", "Femme toute belle", "Femme de Dieu", "Mère de toute l'humanité"},
            {"Le nom du troisième fils d'Adam et Ève", "Set", "Benjamin", "Abel", "Cain"},
            {"Jacob a douze fils. Lequel est l’aîné?", "Ruben","Benjamin", "Juda","Levi"},
            { "Quel est le nom de la femme d’Isaac?", "Rébecca","Sarah", "Dina", "Zilpa"},
            {"Joseph, fils de Jacob, avait combien de frères ? ", "11", "12", "10", "13"},
            {"Quelle est la première chose que Noé a fait en sortant de l’arche ?","Offrir un sacrifice", "Planter une vigne", "Embrasser la terre","Louer Dieu"},
            {"À quel âge est-ce que Noé va mourir?", "950 ans", "750 ans", "350 ans", "150"},
            {"Quel évangile ne transmet pas le récit du partage du pain et du vin entre Jésus et ses disciples ?","Jean","Marc","Luc","Matthieu"},
            {"Qui a tué le géant au six doigts sur chaque main et orteils sur chaque pied ?","Jonathan (fils de Sanaa)","David (fils de Jesse) ","Saul"," Samson"},
            {"Qui prophétisa sur les os secs","Ezekiel","Esaïe","Moïse","Jeremie"},
            {"Dieu fait tomber la Manne pour son peuple dans le désert pendant ","40 ans","30 ans","50 ans","20 ans"},
            {"Jésus changea l’eau en vin au : ","Mariage de Cana","Mariage de Canne","Mariage de Channel","Mariage de Cain"},
            {"Quel riche publicain monta sur un sycomore pour voir Jésus ?","Zachée","Mathieu","Le Soldat","Pierre"},
            {"Qui fut la première femme a rencontrer JESUS ressuscité selon l'evangile de Jean","Marie Madeleine","Marie sa mere","Marthe","Esther"},
            {"Quel disciple trahit jésus ","Pierre","Andre","Luc","Saul"},
            {"Qui fut le premier polygame dans la Bible ?","Lémec","Cain","Abraham","Moise"},
            {"Caïn, pour avoir tué son frère, sera vengé combien de fois selon les Saintes Écritures ?","7 fois","77 fois","17 fois","77 fois 7"},
            {"Qui interprété les songes du Pharaon ?","Joseph","Daniel","Jacob","Moise"},
            {"Dans Jean 10:27-29 : JESUS nous donne :","La Vie Eternelle","La Paix","La Joie","La Guerrison"},
            {"Sur le dos de quel animal JESUS entra a Jerusalem","Un ânon","une ânesse","un Un âne","un cheval"},
            {"Lors de la transfiguration, Quel prophète était avec jésus","Moïse et Elie","Moïse et Jacob","Elie et Abraham","Elie et Daniel"},
            {"Dans quel Evangile, Jésus dit : \"En vérité, en vérité, je vous le dis, avant qu'Abraham fût, je suis \" ","Jean 8 : 58","Marc 8 : 58","Mathieu 8 : 58","Luc 8 : 58"},
            {"Quel Prophète a fait flotter une hache à la surface de l'eau ?","Élisée","Samuel","Nathan","Esaïe"},
            {"Abraham eu son premier Fils a : ","100 ans","110 ans"," 95 ans","115 ans"},
            {"A la question “ Qui es tu ? “ par Moïse , le seigneur notre DIEU repondit ","Je suis","Je suis Dieu","je suis le tout puissant","Je suis l\'eternel"},
            {"De qui Joseph refuse-t-il les avances ?", "La femme de Potiphar","Sa soeur","La fille de pharaon","La femme du pharaon"},
            {"Qui sont les fils de Joseph et sa femme Asnath ?","Manassé et Éphraïm","Jacob et Ésaü","Jabez et Salomon ","Simeon et Loth"},
            {"Quel est le nom de l’Église qui a été construite sur le lieu de l’ensevelissement et de la résurrection de Jésus?","Le Saint-Sépulcre","Le Saint Tombeau","La basilique de la résurrection","La basilique de Saint Pierre"},
            {"Dans Marc 14, le Christ ressuscité précède ses disciples :","En Galille","A Jerusalem","A Emmaus","A Golgotha "},
            {"Quel évangile raconte l’apparition aux disciples d’Emmaüs?","Luc","Matthieu","Marc","Jean"},
            {"Dans l\'evangile de Luc, quel est le dernièr lieu ou apparait JESUS avant sa monte au ciel ?","Béthanie","Galilée","Damas","Egypte"},
            {"Quel est le surnom de Thomas, celui qui doutait de la résurrection de JESUS?","Didyme","Zelote","Fils du Tonnerre","Zebede"},
            {"Selon la Bible, quel âge avait Noé lorsque le déluge a commencé?","600 ans","450 ans","700 ans"," 250 ans"},
            {"Pourquoi est-ce que Moïse s’enfuit au désert ?"," Il a assassiné un Égyptien qui avait frappé un Hébreu","Il a tenu tête au pharaon","Il a commis l’adultère","il voulait prier Dieu"},
            {"Quel livre prophétique associ Bethléem comme lieu de naissance du Messie ?"," Michée","Jérémie","Isaïe","Job"},
            {"Après la destruction de Jérusalem, où est-ce que le prophete Jérémie se réfugie?","En Egypte","Dans le nord d’Israël","En Babylonie","En Chypre"},
            {"Quel fut le nom du premier martyr chrétien? ","Etienne","Philippe","Jacques","Paul"},
            {"Que signifie le mot EGLISE ?","Une Assemblée","Maison de DIEU","Un Edifice","un Temple"},
            {"Livre Biblique qui raconte comment une femme juive, devenue reine, sauva son peuple de l’extermination","Esther","Ruth","Judith","Marie"},
            {"Combien de pierres David a-t-il prises dans le torrent contre Goliath ? ","5","6","8","10"},
            {"Quel est le dernier livre de la Bible ?","Apocalypse ","Genese","Psaume","Actes des Apôtres,"},
            {"Combien y a-t-il de livres dans le Nouveau Testament ?","27","37","39","17"},
            {"Combien y a-t-il de livres dans l’Ancien Testament ? ","39","41","29","31"},
            {"Combien de pains et de poissons le petit garçon a-t-il donné à Jésus ? ","5 pains et 2 poissons ","2 pains et 5 poissons ","6 pains et 3 poissons ","3 pains et 2 poissons "},
            {"Quel est le 5e commandement de la loi de Dieu ? ","Honore ton père et ta mère","Tu ne mentira point","Aime ton prochain","Tu ne volera pas"},
            {"Quel personnage biblique a été avalé par un grand poisson ","Jonas","Abdias","Job","Ezekiel"},
            {"Comment s'appelait l'homme le plus fort dans la Bible ?","Samson","David","Eli","Saul"},
            {"Quel prophète a annoncé l'arrivée de Jésus ?","Ésaïe","Elie","Jeremi","Moise"},
            {"Qui la personne qui a vécu le plus longtemps sur terre selon la Bible","Mathusalem","Noe","Lemec","Adam"},
            {"Qui a douté que Jésus ait marché sur l'eau ?","Pierre","Thomas","Jacques","Simon"},
            {"Que signifie Moïse ","Sauver des eaux","Sauveur d'israel","Beni de DIEU","le Bon Pasteur"},
            {"Combien y a-t-il de générations d'Abraham à Jésus ?","42","30","52","60"},
            {"L'envoi du Saint-Esprit a duré combien de jours après l'ascension ?","40 jours","12 jours","30 jours","15 jours"},
            {"Selon la Bible, il n'est pas mort mais il est monté au ciel, qui ?","Elie","Abraham","Paul","Moise"},
            {"Qui a écrit le \"Pentateuque\" ?","Moise","Aaron","Lot","Job"},
            {"Il était le plus sage roi en Israël , il s'appelait :","Salomon","David","Jaos","Saul"},
            {"Qui est l'auteur des “Actes des Apôtres” ?","Luc","Simon","Andre","Jean"},
            {"Où Dieu confondit-il le langage de toute la terre?","La tour de Babel","La tour de Babylone","La tour de Baal","La tour de Parse"},
            {"La Bible contient combien de livres?","66","39","27","54"},
            {"Combien de temps dura la servitude des enfants d'Israël en Égypte?","430 ans","500 ans","230 ans"," 100 ans"},
            {"À quel âge Jésus commença-t-il son ministère?","Environ 30 ans","a l'age de 12 ans","Environ 35 ans","Environ 27 ans"},
            {"Qui aida l'homme qui descendait vers Jéricho et qui fut dépoullé et battu par des brigands? ","Le bon Samaritain","Zachée ","Jésus","Un Serviteur"},
            {"Si le salaire du péché c'est la mort, qu'est donc le don gratuit de Dieu?","La vie éternelle en Jésus-Christ","le pardon de nos péché","Nous serons sauvés","L'esprit Saint"},
            {"Combien épîtres ou lettres l'apôtre Paul a écrit ?","13","14","10","14"},
            {"Sara la femme d'abraham mourut à ","127 ans","120 ans","134 ans","150 ans"},
            {"Il fut le septième disciple choisi, qui ?","Mathieu","Thomas","Philippe","Etienne"},
            {"Qui occupait le poste de docteur parmi les 12 disciples ?","Luc","Jean","Andre","Jacques"},
            {"“Je serre ta parole dans mon cœur, afin de ne pas pécher contre toi”\n" +
                    "Qui parle dans ce verset ?","David","Amos","Samuel","Pierre"},
            {"Qui a dit : “Mon Dieu sera ton Dieu” ?","Abraham","Elie","Jude","Lot"},
            {"Dans le jargon biblique Que signifie le mot « testament »?","Alliance","Livre","Transmission","Ere"},
            {"Comment est-ce que les musulmans appellent Abraham?","Khalil Allah « l’ami de Dieu »","Le père de tous les croyants","El-Ab Ismaël « Le père d’Ismaël »","Le Patriarche"},
            {"Qui sont les parents de la vierge Marie","Anne, Joachim","Abraham et Sara","Job et Esther","Marie et Joseph"},
            {"J'ai pris un bateau pour fuir l'appel de Dieu :","Jonas","Corneille","Enoch","Absalom"},
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

    };

    private int pStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTextViewCountDown = findViewById(R.id.text_view_progress);
        question = findViewById(R.id.question);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        // le progress bar dans le timer

//        progressBar = findViewById(R.id.progress_bar);
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (pStatus <= 100) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressBar.setProgress(pStatus);
//                            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//                            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//                            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//                            mTextViewCountDown.setText(timeLeftFormatted);
// //                           mTextViewCountDown.setText(pStatus + " %");
//                        }
//                    });
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    pStatus++;
//                }
//            }
//        }).start();

        startTimer();

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

//    private void startCountDown() {
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//            @Override
//            public void onFinish() {
//                mTimeLeftInMillis = 0;
//                updateCountDownText();
////                checkAnswer();
//            }
//        }.start();
//    }


    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void timeUp() {
        Intent intent = new Intent(this, Time_Up.class);
        startActivity(intent);
        finish();
    }

//    private void playAnim(final View view, final int value, final String data){
//        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
//                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                if(value == 0 && count < 4){
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
//                    playAnim(optionsContainer.getChildAt(count), 0,option);
//                    count++;
//                }
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                if(value == 0 ){
//                    try{
//                        ((TextView) view).setText(data);
//                    }catch (ClassCastException e){
//                        ((Button) view).setText(data);
//                    }
//                    view.setTag(data);
//                    playAnim(view,1,data);
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//    }

    public void showNextQuizagain() {

        // Generate random number between 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        question.setText(quiz.get(0));
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
//        quizArray.remove(randomNum);
    }

    public void showNextQuiz() {

        // Generate random number between 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        question.setText(quiz.get(0));
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

//    private void checkAnswer(Button selectOption){
//        enableOption(false);
////        next.setEnabled(true);
////        next.setAlpha(1);
//
//        if(selectOption.getText().toString().equals(list.get(position).getCorrectAns())){
//            // correct
//            selectOption.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
//            // mettre ici le score
//
//        }else {
//            // incorrect
//                selectOption.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
////                Button correctOption = optionsContainer.findViewWithTag(list.get(position).getCorrectAns());
////                correctOption.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
//        }
//    }
//
//    private void enableOption(boolean enable){
//        for (int i =0 ; i < 4; i++){
//            optionsContainer.getChildAt(i).setEnabled(enable);
//        }
//    }

    public void resetColor() {
        answerBtn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
        answerBtn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
        answerBtn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
        answerBtn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_borders));
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                startActivity(intent);
                finish();

//                mButtonStartPause.setText("Start");
//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
//        mButtonStartPause.setText("pause");
//        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mTimeLeftInMillis = mTimeLeftInMillis + 5000;
        mCountDownTimer.cancel();
        mTimerRunning = false;
//        mButtonStartPause.setText("Start");
//        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void pauseTimerContinue() {
//        mTimeLeftInMillis = mTimeLeftInMillis + 5000;
        mCountDownTimer.cancel();
        mTimerRunning = false;
//        mButtonStartPause.setText("Start");
//        mButtonReset.setVisibility(View.VISIBLE);
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        mCountDownTimer.start();
        showNextQuizagain();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        mCountDownTimer.cancel();
    }

    //This will pause the time
    @Override
    protected void onPause() {
        super.onPause();
        mCountDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivityHome.class);
        startActivity(intent);
        finish();
    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {

            answerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borders_button_true));
            // Correct

            pauseTimer();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(alertTitle);
//            builder.setMessage("Answer : " + rightAnswer);
            builder.setMessage(" BRAVO ");
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
  //                  if (quizCount == QUIZ_COUNT) {
                        if (quizCount == quizData.length) {
                        // Show Result.
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
//                        intent.putExtra("QUESTIONS_COUNT", quizData.length);
                        startActivity(intent);

                    } else {
                        resetColor();
                        startTimer();
                        quizCount++;
                        showNextQuiz();
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();

            rightAnswerCount++;

        } else {
            //           alertTitle = "Wrong...";

            answerBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.borders_button_false));
            pauseTimerContinue();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(alertTitle);
            builder.setMessage("Answer : " + rightAnswer);
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //if (quizCount == QUIZ_COUNT) {
                    if(quizCount == quizData.length){
                        // Show Result.
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                        startActivity(intent);

                    } else {
                        resetColor();
                        startTimer();
                        quizCount++;
                        showNextQuiz();
                    }
                }
            });
            builder.setCancelable(false);
            builder.show();


        }
//
//        // Create AlertDialog.
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        builder.setTitle(alertTitle);
//        builder.setMessage("Answer : " + rightAnswer);
//        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if (quizCount == QUIZ_COUNT) {
//                    // Show Result.
//                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
//                    startActivity(intent);
//
//                } else {
//                    resetColor();
//                    startTimer();
//                    quizCount++;
//                    showNextQuiz();
//                }
//            }
//        });
//        builder.setCancelable(false);
//        builder.show();
    }

}