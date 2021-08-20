//package notorious.qibible.coin;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import notorious.qibible.R;
//
//public class BookActivity extends AppCompatActivity {
//    private TextView tvtitle,tvdescription,tvcategory;
//    private ImageView img;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_coin);
//
//        tvtitle = (TextView) findViewById(R.id.txttitle);
//        tvdescription = (TextView) findViewById(R.id.txtDesc);
//        tvcategory = (TextView) findViewById(R.id.txtCat);
//        img = (ImageView) findViewById(R.id.bookthumbnail);
//
//        // Recieve data
//        Intent intent = getIntent();
//        String Title = intent.getExtras().getString("Title");
//        String Description = intent.getExtras().getString("Description");
//        int image = intent.getExtras().getInt("Thumbnail") ;
//
//        Log.d("bien", "wwwwwwwwwwwwwwwwwwwwwwwwwwwwww: "+Title);
//
//        if(Title=="Genese"){
//
//        }
//
//        // Setting values
//
//        tvtitle.setText(Title);
//        tvdescription.setText(Description);
//        img.setImageResource(image);
//
//
//    }
//}