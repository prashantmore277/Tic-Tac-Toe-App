package com.practicle.tictoctoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;


    // 0 - X
    // 1 - 0
    int activePlayer = 0;
    int[] gameStateNull = {2,2,2,2,2,2,2,2,2};
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // State meanings
    // 0 - X
    // 1 - 0
    // 2 - Null
    int[][] winPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImage =Integer.parseInt(img.getTag().toString());
        if(gameActive && !gameActive){
            for(int i= 0; i<gameState.length; i++){
                if( gameState[i] != gameStateNull[i] ){
                    gameReset(view);
                }
                else{
                    break;
                }
            }

        }
        if(!gameActive) {
            gameReset(view);
        }
        if(gameState[tappedImage]==2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer== 0) {
                img.setImageResource(R.drawable.xx);
                activePlayer = 1;
                TextView status = findViewById(R.id.textView2);
                status.setText("O`s Turn -- TAP TO PLAY");
            }
            else{
                img.setImageResource(R.drawable.oo);
                activePlayer = 0;
                TextView status = findViewById(R.id.textView2);
                status.setText("X`s Turn -- TAP TO PLAY");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }


        // Check If any player has won

        for (int[] winPosition : winPositions){
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2 )
            {
                // Somebody has won! - Find out who !

                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has Won";
                }
                else {
                    winnerStr = "O has Won";
                }
                TextView status = findViewById(R.id.textView2);
                status.setText(winnerStr);
            }
        }

    }
    public void gameReset(View view){
        gameActive =true;
        activePlayer = 0;
        for(int i= 0; i<gameState.length; i++){
            gameState[i] =2;

        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}