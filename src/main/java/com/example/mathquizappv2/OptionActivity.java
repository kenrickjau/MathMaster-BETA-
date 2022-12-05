package com.example.mathquizappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionActivity extends AppCompatActivity {

    TextView button_plus, button_min, button_multi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        button_plus = findViewById(R.id.button_plus);
        button_min = findViewById(R.id.button_min);
        button_multi = findViewById(R.id.button_multi);

        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        button_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, QuizActivity_substraction.class);
                startActivity(intent);
            }
        });

        button_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, QuizActivity_multiplication.class);
                startActivity(intent);
            }
        });


    }
}