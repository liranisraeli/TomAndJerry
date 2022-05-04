package com.example.class22b_and_316332857_1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.class22b_and_316332857_1.R;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class Activity_GameOver extends AppCompatActivity {
    private TextView gameOver_LBL_title_score_player;
    private TextView gameOver_LBL_score_player;
    private MaterialButton gameOver_BTN_start_over;
    private MaterialButton gameOver_BTN_Back_Menu;
    private String playerName;
    private int score;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        if (getIntent().getBundleExtra("Bundle") != null){
            this.bundle = getIntent().getBundleExtra("Bundle");
            playerName=bundle.getString("playerName");
            score=bundle.getInt("PlayerScore");
        } else {
            this.bundle = new Bundle();
        }
        findViews();
        initBtnClick();
        presentScoreName();
    }


    private void presentScoreName() {
        gameOver_LBL_score_player.setText(String.valueOf(score));
        gameOver_LBL_title_score_player.setText(playerName.toUpperCase(Locale.ROOT)+" YOUR SCORE IS ");

    }


    private void replaceActivity() {
        Intent intent = new Intent(this, Activity_Main.class);
        intent.putExtra("Bundle",bundle);
        startActivity(intent);

        }



    private void initBtnClick() {

        gameOver_BTN_start_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceActivity();
            }
        });

        gameOver_BTN_Back_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToMenu();
            }
        });

    }

    private void BackToMenu() {
        finish();
    }

    private void findViews() {
        gameOver_BTN_start_over = findViewById(R.id.gameOver_BTN_start_over);
        gameOver_BTN_Back_Menu = findViewById(R.id.gameOver_BTN_Back_Menu);
        gameOver_LBL_title_score_player=findViewById(R.id.gameOver_LBL_title_score_player);
        gameOver_LBL_score_player=findViewById(R.id.gameOver_LBL_score_player);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    protected void onStart() {
        super.onStart();
    }


}