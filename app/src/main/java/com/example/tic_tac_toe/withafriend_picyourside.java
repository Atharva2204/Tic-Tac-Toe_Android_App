package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class withafriend_picyourside extends AppCompatActivity
{
    Button cont;
    TextInputEditText player1,player2;
    String player1_name,player2_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.withafriend_picyourside);

        cont = findViewById(R.id.cont);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        cont.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                player1_name = player1.getText().toString();
                player2_name = player2.getText().toString();

                Intent intent = new Intent(withafriend_picyourside.this, withafriend_game.class);
                intent.putExtra("player1_name",player1_name);
                intent.putExtra("player2_name",player2_name);
                startActivity(intent);
                finish();
            }
        });
    }
}