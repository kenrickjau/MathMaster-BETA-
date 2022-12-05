package com.example.mathquizappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    Button home_start, home_settings, home_exit,close_settings;
    Switch music_switch;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_start = findViewById(R.id.home_start);
        home_settings = findViewById(R.id.home_settings);
        home_exit = findViewById(R.id.home_exit);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.background_music);
        mediaPlayer.setLooping(true);

        home_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                startActivity(intent);
            }
        });

        home_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        home_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }
    private void openDialog() {

        Dialog my_dialog = new Dialog(this);

        //set layout
        my_dialog.setContentView(R.layout.settings_pop_up);

        //close pop up
        close_settings = my_dialog.findViewById(R.id.close_settings);
        close_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_dialog.dismiss();
            }
        });

        //switch music on/off
        music_switch = my_dialog.findViewById(R.id.music_switch);
        music_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    // Add music (new code)
                    mediaPlayer.start();
                }else{
                    mediaPlayer.pause();
                }
            }
        });
        my_dialog.show();
    }
}