package com.example.tianhao.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    // 0: yellow, 1: red 2 : empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2&& gameActive) {


            gameState[tappedCounter] = activePlayer;
            Log.i(" Tag gamestate", String.valueOf(gameState[tappedCounter]));

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                //Log.i("winning positiion[0]",String.valueOf(winningPosition[0])  );

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone has won
                    gameActive = false;
                    String winner ="";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button playAgain = (Button)findViewById(R.id.button);
                    TextView winText =(TextView) findViewById(R.id.winnerText);
                    winText.setText(winner + " has won!");
                    playAgain.setVisibility(View.VISIBLE);
                    winText.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    public void playAgain(View view){
        Button playAgain = (Button)findViewById(R.id.button);
        TextView winText =(TextView) findViewById(R.id.winnerText);
        playAgain.setVisibility(View.INVISIBLE);
        winText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i<gameState.length; i++)
        {
            gameState[i]=2;
        }
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
