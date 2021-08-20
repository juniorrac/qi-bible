package notorious.qibible.coin;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import notorious.qibible.MenuActivityHome;
import notorious.qibible.R;

public class MenuCoinActivity extends AppCompatActivity {

    List<LevelCoin> lstLevelCoin;
    TextView totalScoreLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_coin);

        TextView totalScoreLabel = findViewById(R.id.coinText);

        lstLevelCoin = new ArrayList<>();
        lstLevelCoin.add(new LevelCoin("La Genese","Categorie LevelCoin","Description book",R.drawable.genese));
        lstLevelCoin.add(new LevelCoin("Matthieu","Categorie LevelCoin","Description book",R.drawable.matthieu));
        lstLevelCoin.add(new LevelCoin("Jean","Categorie LevelCoin","Description book",R.drawable.jean));
        lstLevelCoin.add(new LevelCoin("Luc","Categorie LevelCoin","Description book",R.drawable.genese1));
        lstLevelCoin.add(new LevelCoin("Genese test ...","Categorie LevelCoin","Description book",R.drawable.clock_grey));
        lstLevelCoin.add(new LevelCoin("Marc","Categorie LevelCoin","Description book",R.drawable.marc));
        lstLevelCoin.add(new LevelCoin("Maria Semples","Categorie LevelCoin","Description book",R.drawable.genese));
        lstLevelCoin.add(new LevelCoin("The Martian","Categorie LevelCoin","Description book",R.drawable.genese1));
        lstLevelCoin.add(new LevelCoin("He Died with...","Categorie LevelCoin","Description book",R.drawable.genese));
        lstLevelCoin.add(new LevelCoin("The Vegitarian","Categorie LevelCoin","Description book",R.drawable.genese1));
        lstLevelCoin.add(new LevelCoin("The Wild Robot","Categorie LevelCoin","Description book",R.drawable.genese));
//        lstLevelCoin.add(new LevelCoin("Maria Semples","Categorie LevelCoin","Description book",R.drawable.mariasemples));
//        lstLevelCoin.add(new LevelCoin("The Martian","Categorie LevelCoin","Description book",R.drawable.themartian));
//        lstLevelCoin.add(new LevelCoin("He Died with...","Categorie LevelCoin","Description book",R.drawable.hediedwith));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstLevelCoin);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);



        // coin

        int score = getIntent().getIntExtra("COIN_POINT", 0);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);

        int totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0);
        totalScore += score;

        //totalScore += score;

//        resultLabel.setText(score +" Bonne Reponse");
        totalScoreLabel.setText(""+totalScore);

        // Update total score.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_SCORE", totalScore);
        editor.apply();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivityHome.class);
        startActivity(intent);
        finish();
    }
}