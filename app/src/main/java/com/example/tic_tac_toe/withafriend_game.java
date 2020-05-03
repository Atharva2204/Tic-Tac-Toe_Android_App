package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class withafriend_game extends AppCompatActivity implements View.OnClickListener
{
    TextView player1_point,player2_point,player1_na,player2_na,status;
    int player1_points,player2_points;
    ImageButton home;
    String player1_name,player2_name;

    ImageButton [][] imagebuttons = new ImageButton[3][3];
    int [][] buttons = new int[3][3];
    boolean player1Turn = true;
    int roundcount;

    Animation blink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withafriend_game);

        player1_point = findViewById(R.id.player1_points);
        player2_point = findViewById(R.id.player2_points);
        home = findViewById(R.id.home);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        player1_name = bundle.getString("player1_name");
        player2_name = bundle.getString("player2_name");

        player1_na = findViewById(R.id.player1_name);
        player2_na = findViewById(R.id.player2_name);

        status = findViewById(R.id.status);

        if (player1_name.isEmpty())
            player1_na.setText("Player 1");
        else
            player1_na.setText(player1_name);

        if (player2_name.isEmpty())
            player2_na.setText("Player 2");
        else
            player2_na.setText(player2_name);

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

        blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blinking);

        if (player1_name.isEmpty())
            status.setText("Player 1 turn");
        else
            status.setText(player1_name+" turn");
        status.startAnimation(blink);
        status.setTextColor(getResources().getColor(R.color.black));

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

             Drawable temp = v.getBackground();

            if(temp != null)
            {
                Toast.makeText(this, "Moves Over !!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(player1Turn)
            {
                status.setVisibility(View.VISIBLE);

                if (player2_name.isEmpty())
                    status.setText("Player 2 turn");
                else
                    status.setText(player2_name+" turn");
                status.startAnimation(blink);

                //((Button) v).setText("X");
                ((ImageButton) v).setImageResource(R.drawable.tictactoe_x);
            }
            else
                {
                    status.setVisibility(View.VISIBLE);
                    if (player1_name.isEmpty())
                        status.setText("Player 1 turn");
                    else
                        status.setText(player1_name+" turn");
                    status.startAnimation(blink);

                //((Button) v).setText("O");
                ((ImageButton) v).setImageResource(R.drawable.tictactoe_o);
            }

            ((ImageButton) v).setEnabled(false);

            roundcount++;

            if(checkForWin())
            {
                if (player1Turn)
                    player1Wins();
                else
                    player2Wins();
            }
            else if(roundcount == 9)
                draw();
            else
                player1Turn = !player1Turn;


        }

        private boolean checkForWin()
        {
            String[][] field = new String[3][3];

            Drawable x = getResources().getDrawable(R.drawable.tictactoe_x);
            Drawable o = getResources().getDrawable(R.drawable.tictactoe_o);
            Drawable temp;

            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    temp = imagebuttons[i][j].getDrawable();

                    if(temp == null)
                        field[i][j] = "";
                    else if(temp.getConstantState() == (x.getConstantState()))
                        field[i][j] = "X";
                    else
                        field[i][j] = "O";
                    //field[i][j] = imagebuttons[i][j].getDrawable();
                }
            }

                //imagebuttons[0][0].setImageResource(R.drawable.tictactoe_x);

                //imagebuttons[1][0].setImageDrawable(temp);

            for(int i=0;i<3;i++)
                if (field[i][0].equals(field[i][1]) && field[i][1].equals(field[i][2]) && !field[i][0].equals(""))
                    return true;

            for(int i=0;i<3;i++)
                if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
                    return true;

            if(field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2]) && !field[0][0].equals(""))
                return true;

            if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
                return true;

            //if(imagebuttons[0][0] !=null && (field[0][0].getConstantState().equals(sDraw.getConstantState())))
             //   Toast.makeText(this, "Atharva", Toast.LENGTH_SHORT).show();

            return false;
        }

        private void player1Wins()
        {
            player1_points++;

            if (player1_name.isEmpty())
                status.setText("Player 1 Wins");
            else
                status.setText(player1_name+" Wins");
            status.setTextColor(getResources().getColor(R.color.green));
            updatePointsText();
            resetBoard();

            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setEnabled(false);
        }

        private void player2Wins()
        {
            player2_points++;

            if (player2_name.isEmpty())
                status.setText("Player 2 Wins");
            else
                status.setText(player2_name+" Wins");
            status.setTextColor(getResources().getColor(R.color.green));
            updatePointsText();
            resetBoard();

            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setEnabled(false);
        }

        private void draw()
        {
            status.setText("Draw !!");
            resetBoard();

            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setEnabled(false);
        }

        private void updatePointsText()
        {
            player1_point.setText(player1_points+"");
            player2_point.setText(player2_points+"");
        }

        private void resetBoard()
        {
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setBackground(null);
                    //imagebuttons[i][j].setBackgroundColor(0x00000000);
                    //imagebuttons[i][j].setBackgroundColor(Color.TRANSPARENT);

            roundcount=0;
            player1Turn=true;

            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setEnabled(true);
        }

        private void resetGame()
        {
            if (player1_name.isEmpty())
                status.setText("Player 1 Turn");
            else
                status.setText(player1_name+" Turn");

            status.setTextColor(getResources().getColor(R.color.black));

            //player1_points=0;
            //player2_points=0;
            updatePointsText();
            resetBoard();

            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    imagebuttons[i][j].setImageResource(0);
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