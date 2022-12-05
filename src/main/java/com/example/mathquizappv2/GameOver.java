package com.example.mathquizappv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private Button StartGameAgain, EndGame;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_gameover);

        StartGameAgain = (Button) findViewById(R.id.button_yes);

        StartGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent (GameOver.this, OptionActivity.class);
                startActivity(mainIntent);
            }
        });

        EndGame = (Button) findViewById(R.id.button_no);

        EndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitIntent = new Intent (GameOver.this, MainActivity.class);
                startActivity(exitIntent);
            }
        });



    }
}
