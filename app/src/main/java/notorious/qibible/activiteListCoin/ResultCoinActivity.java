package notorious.qibible.activiteListCoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import notorious.qibible.MenuActivityHome;
import notorious.qibible.R;

public class ResultCoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_coin);

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalCoin);

        int score = getIntent().getIntExtra("COIN_POINT", 0);
//        int questionsLenght = getIntent().getIntExtra("QUESTIONS_COUNT",0);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);

        int totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0);
        totalScore += score;

        //totalScore += score;

        resultLabel.setText(score +" Bonne Reponse");
       totalScoreLabel.setText("Total Score : " + totalScore);

        // Update total score.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_SCORE", totalScore);
        editor.apply();
    }

    public void returnTop(View view) {
        startActivity(new Intent(getApplicationContext(), MenuActivityHome.class));
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
        Intent intent = new Intent(this, MenuActivityHome.class);
        startActivity(intent);
        finish();
    }
}