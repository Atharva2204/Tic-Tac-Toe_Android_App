package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class withafriend_game extends AppCompatActivity implements View.OnClickListener
{
    TextView player1,player2;
    int player1_points,player2_points;
    ImageButton home;
    String player1_name,player2_name;

    ImageButton [][] imagebuttons = new ImageButton[3][3];
    int [][] buttons = new int[3][3];
    boolean player1Turn = true;
    int roundcount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withafriend_game);

        player1 = findViewById(R.id.player1_points);
        player2 = findViewById(R.id.player2_points);
        home = findViewById(R.id.home);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        player1_name = bundle.getString("player1_name");
        player2_name = bundle.getString("player2_name");

        if (player1_name.isEmpty())
            player1.setText("Player 1");
        else
            player1.setText(player1_name);

        if (player2_name.isEmpty())
            player2.setText("Player 2");
        else
            player2.setText(player2_name);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setImageResource(R.drawable.tictactoe_x);
            }
        });*/

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                imagebuttons[i][j] = findViewById(resID);
                imagebuttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

        @Override
        public void onClick(View v)
        {
            /*if(!((Button)v).getText().toString().equals(""))
                return;*/

            //if(!((ImageButton) v).getBackground())
             //   return;
            if(player1Turn)
            {
                //((Button) v).setText("X");
                ((ImageButton) v).setImageResource(R.drawable.tictactoe_x);
            }
            else
                {
                //((Button) v).setText("O");
                ((ImageButton) v).setImageResource(R.drawable.tictactoe_o);
            }

            roundcount++;

            if(checkForWin())
            {
                if (player1Turn)
                    player1Wins();
                else
                    player2Wins();
            }
            else if(roundcount ==9)
                draw();
            else
                player1Turn = !player1Turn;
        }

        private boolean checkForWin()
        {
            String[][] field = new String[3][3];

            /*for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    field[i][j] = imagebuttons[i][j].getText().toString();*/

            for(int i=0;i<3;i++)
                if(field[i][0].equals(field[i][1]) && field[i][1].equals(field[i][2]) && !field[i][0].equals(""))
                    return true;

            for(int i=0;i<3;i++)
                if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
                    return true;

            if(field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2]) && !field[0][0].equals(""))
                return true;

            if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
                return true;

            return false;
        }

        private void player1Wins()
        {
            player1_points++;
            Toast.makeText(this ,"Player 1 wins",Toast.LENGTH_LONG).show();
            updatePointsText();
            resetBoard();
        }

        private void player2Wins()
        {
            player2_points++;
            Toast.makeText(this ,"Player 2 wins",Toast.LENGTH_LONG).show();
            updatePointsText();
            resetBoard();
        }

        private void draw()
        {
            Toast.makeText(this, "Draw !!", Toast.LENGTH_LONG).show();
            resetBoard();
        }

        private void updatePointsText()
        {
            player1.setText(player1_points+"");
            player2.setText(player2_points+"");
        }

        private void resetBoard()
        {
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setBackground(null);

            roundcount=0;
            player1Turn=true;
        }

        private void resetGame()
        {
            player1_points=0;
            player2_points=0;
            updatePointsText();
            resetBoard();
        }

        @Override
        public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
        {
            super.onSaveInstanceState(outState, outPersistentState);
            outState.putInt("roundCount",roundcount);
            outState.putInt("player1Points",player1_points);
            outState.putInt("player2Points",player2_points);
            outState.putBoolean("player1Turn",player1Turn);
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState)
        {
            super.onRestoreInstanceState(savedInstanceState);
            roundcount=savedInstanceState.getInt("roundCount");
            player1_points=savedInstanceState.getInt("player1Points");
            player2_points=savedInstanceState.getInt("player2Points");
            player1Turn=savedInstanceState.getBoolean("player1Turn");
        }
}