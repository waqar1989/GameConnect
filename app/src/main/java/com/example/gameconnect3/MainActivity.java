package com.example.gameconnect3;
import androidx.appcompat.app.AppCompatActivity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Array;

public class MainActivity extends AppCompatActivity {

    // 1 : yellow  0: red 2: empty

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions =  {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

    boolean activeGame= true;

    int activePlayer = 1;


    public void dropin (View view){

        ImageView counter = (ImageView)view;
        Log.i("imageTag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && activeGame){
        gameState[tappedCounter] = activePlayer;
        counter.setTranslationY(-1500);
        if (activePlayer==1) {

            counter.setImageResource(R.drawable.yellow);
            activePlayer=0;
        }

        else
        {
            counter.setImageResource(R.drawable.red);
            activePlayer=1;
        }
        counter.animate().alpha(1).translationYBy(+1500).rotation(3600).setDuration(300);

        for(int[] winningPosition : winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                String message = "";
                activeGame= false;


                if (activePlayer==0)
                {
                 message = "yellow";
                }

                else
                    {
                message = "red";
                }


                TextView result =(TextView)findViewById(R.id.result);
                Button playAgain =(Button) findViewById(R.id.playAgain);
                result.setText(message  +" has won");
                result.setVisibility(view.VISIBLE);
                playAgain.setVisibility(view.VISIBLE);


            }
        }}}

    public void PlayAgain(View view)
    {

//        Log.i("result", "button pressed");
        TextView result =(TextView)findViewById(R.id.result);
        Button playAgain =(Button) findViewById(R.id.playAgain);
        result.setVisibility(view.INVISIBLE);
        playAgain.setVisibility(view.INVISIBLE);
        try {
        GridLayout myGridView =(GridLayout) findViewById(R.id.myGridView);

        for(int i=0; i<myGridView.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridView.getChildAt(i);
            counter.setImageDrawable(null);
        }}catch(Exception e){

            Log.e("MYAPP", "exception: " + e.getMessage());

        }

        for (int i=0; i<gameState.length; i++){

            gameState[i] = 2;
        }
        activePlayer = 1;
        activeGame= true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}