package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //  0=Yellow, 1=red
    int activeplayer=1;
    boolean isGameActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View v){

        ImageView counter= (ImageView) v;
        //System.out.println(counter.getTag().toString());
        int tappedcounter= Integer.parseInt(counter.getTag().toString());

    if(gameState[tappedcounter]==2 && isGameActive) {
        gameState[tappedcounter] = activeplayer;
        counter.setTranslationY(-1000f);
        //counter.setImageResource(R.drawable.yellow);
        counter.animate().translationYBy(1000f).setDuration(500);

        if (activeplayer == 1) {
            counter.setImageResource(R.drawable.red);
            activeplayer = 0;
        }
        else {
            counter.setImageResource(R.drawable.yellow);
            activeplayer = 1;
        }

        for (int[] winningpos : winningpositions) {
            if (gameState[winningpos[0]] == gameState[winningpos[1]] && gameState[winningpos[1]] == gameState[winningpos[2]] && gameState[winningpos[0]] != 2)
            {    isGameActive=false;
                String winner ="Red has Won!!";

                if (gameState[winningpos[0]] == 0)
                {
                    winner="Yellow has Won!!";
                }
                TextView winnerMessage = (TextView) findViewById(R.id.winnermessage);

                 //Toast.makeText(MainActivity.this,"Yellow Win", Toast.LENGTH_SHORT).show();
                //Log.i("yoo","Yellow Win");}
                winnerMessage.setText(winner);
                LinearLayout layout = (LinearLayout) findViewById(R.id.winnerboxlayout);
                layout.setVisibility(View.VISIBLE);
                    //  Toast.makeText(MainActivity.this,"Red Win",Toast.LENGTH_SHORT).show();
                    //Log.i("t","Red win"); }


            }
           else{
                boolean gameisover=true;

                for(int counterstate : gameState){
                    if(counterstate==2)
                        gameisover=false;
                }

                if(gameisover){
                    TextView winnerMessage = (TextView) findViewById(R.id.winnermessage);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.winnerboxlayout);
                    layout.setVisibility(View.VISIBLE);
                    winnerMessage.setText("It's a Draw!!");

                }

            }

            }

        }

    }


    public void playAgain(View view){
        isGameActive=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.winnerboxlayout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer=0;

        for(int p=0;p<9;p++)
        {gameState[p]=2;
       }
//        //Arrays.fill(gameState, 2);
//
        GridLayout gridl =(GridLayout) findViewById(R.id.grid);
        for (int i=0;i<gridl.getChildCount();i++){
            ((ImageView) gridl.getChildAt(i)).setImageResource(0);
        }
       // Log.i("uj","Clicked");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
}