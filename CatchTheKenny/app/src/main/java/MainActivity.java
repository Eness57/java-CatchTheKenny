package com.enessahin.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.FileObserver;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //this hangi aktivitenin altındaysak onu işaret eder

    TextView timetext;
    TextView scoretext;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArrays;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timetext = (TextView) findViewById(R.id.timetext);
        scoretext = (TextView) findViewById(R.id.scoretext);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArrays = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,
                                        imageView6,imageView7,imageView8,imageView9};


        hideImages();

        score=0;


        new CountDownTimer(10000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timetext.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timetext.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArrays){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart");
                alert.setMessage("Are you sure restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

            }
        }.start();

    }
    public void increaseScore(View view){

        score++;
        scoretext.setText("Score: "+score);

    }
    public void hideImages(){
        for (ImageView image :imageArrays){

            image.setVisibility(View.INVISIBLE);

            handler = new  Handler();

            runnable = new Runnable() {
                @Override
                public void run() {
                    for (ImageView image:imageArrays){
                        image.setVisibility(View.INVISIBLE);
                    }
                    Random random = new Random();
                    int i = random.nextInt(9);
                    imageArrays[i].setVisibility(View.VISIBLE);

                    handler.postDelayed(this,500);

                }

            };
            handler.post(runnable);

        }

    }
}