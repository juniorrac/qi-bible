package notorious.qibible;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import info.hoang8f.widget.FButton;
import notorious.qibible.coin.MenuCoinActivity;


public class MenuActivityHome extends AppCompatActivity {

    FButton btnTimer, btnCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initViews();

        //Play timer Game button - it will take you to the MainGameActivity
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityHome.this, TimerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Play Coin Game button - This will quit the game
        btnCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivityHome.this, MenuCoinActivity.class);
                startActivity(intent);
                finish();

//                Toast.makeText(getApplicationContext(),"En phase test",Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }

    private void initViews () {
        //initialize views here
        btnTimer = findViewById(R.id.btnTimer);
        btnCoin = findViewById(R.id.btnCoin);

        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        btnTimer.setTypeface(typeface);
        btnCoin.setTypeface(typeface);
    }
}


