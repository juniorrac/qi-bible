package notorious.qibible.activiteListCoin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import notorious.qibible.R;
import notorious.qibible.coin.MenuCoinActivity;

public class ResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = findViewById(R.id.resultLabel);
//        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);
//        int questionsLenght = getIntent().getIntExtra("QUESTIONS_COUNT",0);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0);
        totalScore += score;

        //totalScore += score;

        resultLabel.setText(score +" Bonne Reponse");
//        totalScoreLabel.setText("Total Score : " + totalScore);

        // Update total score.
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("TOTAL_SCORE", totalScore);
//        editor.apply();
    }

    public void returnTop(View view) {
        startActivity(new Intent(getApplicationContext(), MenuCoinActivity.class));
        finish();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if ((keyCode == KeyEvent.KEYCODE_BACK))
//        {
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
@Override
public void onBackPressed() {
    Intent intent = new Intent(this, MenuCoinActivity.class);
    startActivity(intent);
    finish();
}
}