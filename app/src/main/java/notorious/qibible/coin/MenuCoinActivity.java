package notorious.qibible.coin;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import notorious.qibible.R;

public class MenuCoinActivity extends AppCompatActivity {

    List<LevelCoin> lstLevelCoin;
    TextView totalScoreLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_coin);

        TextView totalScoreLabel = findViewById(R.id.coinText);

        SharedPreferences sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0);

        lstLevelCoin = new ArrayList<>();
        // Livre gratuit - toujours débloqué
        lstLevelCoin.add(new LevelCoin("La Genese","Livre Gratuit","Premier livre de la Bible - Toujours disponible",R.drawable.genese, true, 0));
        
        // Livres à débloquer avec des coûts différents
        boolean matthieuUnlocked = sharedPreferences.getBoolean("MATTHIEU_UNLOCKED", false) || totalScore >= 50;
        lstLevelCoin.add(new LevelCoin("Matthieu","Nouveau Testament","Évangile selon Matthieu",R.drawable.matthieu, matthieuUnlocked, 50));
        
        boolean jeanUnlocked = sharedPreferences.getBoolean("JEAN_UNLOCKED", false) || totalScore >= 100;
        lstLevelCoin.add(new LevelCoin("Jean","Nouveau Testament","Évangile selon Jean",R.drawable.jean, jeanUnlocked, 100));
        
        boolean lucUnlocked = sharedPreferences.getBoolean("LUC_UNLOCKED", false) || totalScore >= 150;
        lstLevelCoin.add(new LevelCoin("Luc","Nouveau Testament","Évangile selon Luc",R.drawable.genese1, lucUnlocked, 150));
        
        boolean marcUnlocked = sharedPreferences.getBoolean("MARC_UNLOCKED", false) || totalScore >= 200;
        lstLevelCoin.add(new LevelCoin("Marc","Nouveau Testament","Évangile selon Marc",R.drawable.marc, marcUnlocked, 200));
        
        // Livres bonus plus chers
        boolean cultureUnlocked = sharedPreferences.getBoolean("CULTURE_UNLOCKED", false) || totalScore >= 300;
        lstLevelCoin.add(new LevelCoin("Culture Générale","Quiz Spécial","Questions de culture biblique",R.drawable.clock_grey, cultureUnlocked, 300));
        
        // Livres premium
        lstLevelCoin.add(new LevelCoin("Les Proverbes","Ancien Testament","Livre des Proverbes",R.drawable.genese, totalScore >= 400, 400));
        lstLevelCoin.add(new LevelCoin("Les Psaumes","Ancien Testament","Livre des Psaumes",R.drawable.genese1, totalScore >= 500, 500));
        lstLevelCoin.add(new LevelCoin("L'Apocalypse","Nouveau Testament","Livre de l'Apocalypse",R.drawable.genese, totalScore >= 600, 600));
//        lstLevelCoin.add(new LevelCoin("Maria Semples","Categorie LevelCoin","Description book",R.drawable.mariasemples));
//        lstLevelCoin.add(new LevelCoin("The Martian","Categorie LevelCoin","Description book",R.drawable.themartian));
//        lstLevelCoin.add(new LevelCoin("He Died with...","Categorie LevelCoin","Description book",R.drawable.hediedwith));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstLevelCoin);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

        // Mise à jour du score total avec les nouveaux points
        int score = getIntent().getIntExtra("COIN_POINT", 0);
        totalScore += score;

        //totalScore += score;

//        resultLabel.setText(score +" Bonne Reponse");
        totalScoreLabel.setText(""+totalScore);

        // Update total score.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TOTAL_SCORE", totalScore);
        editor.apply();

        // Points de démarrage pour tester (une seule fois)
        if (!sharedPreferences.getBoolean("STARTER_POINTS_GIVEN", false)) {
            totalScore += 100; // Donner 100 points de départ
            editor.putInt("TOTAL_SCORE", totalScore);
            editor.putBoolean("STARTER_POINTS_GIVEN", true);
            editor.apply();
            Toast.makeText(this, "Bonus de démarrage: +100 points!", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MenuCoinActivity.class);
        startActivity(intent);
        finish();
    }
}