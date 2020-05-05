package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class singleplayer_picyourside extends AppCompatActivity
{
    RadioButton radio_btn1,radio_btn2;
    RadioGroup radioGroup;
    CardView cardView;
    TextView player1,player2;
    String player1_name,player2_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_picyourside);

        radioGroup = findViewById(R.id.radiogrp);
        radio_btn1 = findViewById(R.id.radio_btn1);
        radio_btn2 = findViewById(R.id.radio_btn2);
        cardView =  findViewById(R.id.card_view);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        player1_name = "YOU";
        player2_name = "BOT";

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(radio_btn1.isChecked())
                {
                    player1.setText("YOU");
                    player2.setText("BOT");
                }
                else
                {
                    player1.setText("BOT");
                    player2.setText("YOU");
                }
                player1_name = player1.getText().toString();
                player2_name = player2.getText().toString();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(singleplayer_picyourside.this, singleplayer_game.class);
                intent.putExtra("player1_name",player1_name);
                intent.putExtra("player2_name",player2_name);
                startActivity(intent);
                finish();
            }
        });
    }
}