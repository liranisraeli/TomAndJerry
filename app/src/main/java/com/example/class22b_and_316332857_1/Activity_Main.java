package com.example.class22b_and_316332857_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_Main extends AppCompatActivity {

    private MaterialTextView main_LBL_time;
    private ImageView[] main_IMG_hearts;
    private ImageView[][] main_IMG_route;
    private ImageButton main_BTN_up;
    private ImageButton main_BTN_left;
    private ImageButton main_BTN_right;
    private ImageButton main_BTN_down;
    private int location[][];
    private GameManager gameManager;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameManager= new GameManager();

        if (getIntent().getBundleExtra("Bundle") != null){
            this.bundle = getIntent().getBundleExtra("Bundle");
            gameManager.getPlayer().setPlayerName(bundle.getString("playerName"));
        } else {
            this.bundle = new Bundle();
        }
        findViews();
        initPlayerButtons();
    }



    private void findViews() {
        main_LBL_time= findViewById(R.id.main_LBL_time);
        main_IMG_hearts = new ImageView[]
                {
                        findViewById(R.id.main_IMG_heart1),
                        findViewById(R.id.main_IMG_heart2),
                        findViewById(R.id.main_IMG_heart3)
                };

        main_IMG_route = new ImageView[][]{
                {findViewById(R.id.main_IMG_00),findViewById(R.id.main_IMG_01),findViewById(R.id.main_IMG_02)},
                {findViewById(R.id.main_IMG_10),findViewById(R.id.main_IMG_11),findViewById(R.id.main_IMG_12)},
                {findViewById(R.id.main_IMG_20),findViewById(R.id.main_IMG_21),findViewById(R.id.main_IMG_22)},
                {findViewById(R.id.main_IMG_30),findViewById(R.id.main_IMG_31),findViewById(R.id.main_IMG_32)},
                {findViewById(R.id.main_IMG_40),findViewById(R.id.main_IMG_41),findViewById(R.id.main_IMG_42)}
        };


        main_BTN_up = findViewById(R.id.main_BTN_up);
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right= findViewById(R.id.main_BTN_right);
        main_BTN_down= findViewById(R.id.main_BTN_down);
    }

    private void initPlayerButtons() {
        main_BTN_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getPlayer().setDirection("UP");
            }
        });

        main_BTN_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getPlayer().setDirection("DOWN");
            }
        });

        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getPlayer().setDirection("LEFT");
            }
        });

        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getPlayer().setDirection("RIGHT");
            }
        });
    }


    //-------timer--------

    private Timer timer = new Timer();
    private final int DELAY = 1000; // 1000 milliseconds == 1 second
    private int counter = 0;
    private enum TIMER_STATUS{
        OFF,
        RUNNING,
        PAUSE
    }
    private TIMER_STATUS timerStatus = TIMER_STATUS.OFF;


    private void tick() {
        ++counter;
        main_LBL_time.setText("" + counter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timerStatus == TIMER_STATUS.RUNNING){
            stopTimer();
            timerStatus = TIMER_STATUS.PAUSE;
        }
    }

      @Override
    protected void onStart() {
        super.onStart();
        if(timerStatus == TIMER_STATUS.OFF){
            startTimer();
        } else if(timerStatus == TIMER_STATUS.RUNNING ){
            stopTimer();
        }else{
            startTimer();
        }
    }

    private void startTimer() {
        timerStatus = TIMER_STATUS.RUNNING;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startGame();
                    }
                });

            }
        }, 0, DELAY);
    }

    private void stopTimer() {
        timerStatus = TIMER_STATUS.OFF;
        timer.cancel();
    }

    private void startGame() {
        tick();
        gameManager.runLogic();
//        gameManager.playerMove();
//        gameManager.randomBotDirectionMove();
//        gameManager.checkCrash();
        updateUI();
    }


    private void updateUI() {
        for (int i=0; i<main_IMG_route.length;i++){
            for (int j = 0; j < main_IMG_route[0].length; j++) {
                main_IMG_route[i][j].setImageResource(R.drawable.ic_empty);
            }
        }

        main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_jerry);
        main_IMG_route[gameManager.getBot().getLocationX()][gameManager.getBot().getLocationY()].setImageResource(R.drawable.ic_tom);


        if(gameManager.getCrash()) {
            if(gameManager.getLives()>0){
                Toast.makeText(this,"BOOM",Toast.LENGTH_LONG).show();
                main_IMG_hearts[gameManager.getLives()].setVisibility(View.INVISIBLE);
                updateUIAfterCrash();
            }else{
                   Toast.makeText(this,"Game Over",Toast.LENGTH_LONG).show();
                   gameManager.getPlayer().setScore(counter);
                    stopTimer();
                    replaceActivity();
                    finish();

            }
        }

    }

    private void replaceActivity() {
        Intent intent = new Intent(this,Activity_GameOver.class);
        bundle.putInt("PlayerScore",gameManager.getPlayer().getScore());
        intent.putExtra("Bundle",bundle);
        startActivity(intent);

    }



    private void updateUIAfterCrash() {
        main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_empty);
        gameManager.bootLocations();
        main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_jerry);
        main_IMG_route[gameManager.getBot().getLocationX()][gameManager.getBot().getLocationY()].setImageResource(R.drawable.ic_tom);
        gameManager.setCrash(false);
    }






}

