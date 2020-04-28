package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class singleplayer_picyourside extends AppCompatActivity
{
    RadioButton radio_btn1,radio_btn2;
    RadioGroup radioGroup;
    Button cont;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_picyourside);

        radioGroup = findViewById(R.id.radiogrp);
        radio_btn1 = findViewById(R.id.radio_btn1);
        radio_btn2 = findViewById(R.id.radio_btn2);
        cont = findViewById(R.id.cont);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {

                }

            }
        });

        cont.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                withafriend_game();
            }
        });
    }

    void withafriend_game()
    {
        Intent intent = new Intent(this, withafriend_game.class);
        startActivity(intent);
    }
}