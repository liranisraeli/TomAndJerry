package com.example.class22b_and_316332857_1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class22b_and_316332857_1.GameManager;
import com.example.class22b_and_316332857_1.LocationManager;
import com.example.class22b_and_316332857_1.MSPV3;
import com.example.class22b_and_316332857_1.MyDB;
import com.example.class22b_and_316332857_1.R;
import com.example.class22b_and_316332857_1.Sensors;
import com.example.class22b_and_316332857_1.SoundManager;
import com.example.class22b_and_316332857_1.StepDetector;
import com.example.class22b_and_316332857_1.activities.Activity_GameOver;
import com.example.class22b_and_316332857_1.objects.Record;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    private StepDetector stepDetector;
    private Bundle bundle;
    private String game;
    private TextView acc1;
    private TextView acc2;

    private Sensors sensors;
    private SensorManager sensorManager;
    private int sensorGame = 0;

    private SoundManager soundManager;

    private boolean isGotCoin = false;

    private final MyDB myDB = MyDB.initMyDB();
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameManager = new GameManager();
        stepDetector = new StepDetector();
        soundManager = new SoundManager();
        locationManager = new LocationManager(this);

        stepDetector.start();
        if (getIntent().getBundleExtra("Bundle") != null) {
            this.bundle = getIntent().getBundleExtra("Bundle");
            gameManager.getPlayer().setPlayerName(bundle.getString("playerName"));
        } else {
            this.bundle = new Bundle();
        }
        game = bundle.getString("game");
        if (game.equals("buttons")) {
            setContentView(R.layout.activity_main);
            findViews();
            initPlayerButtons();
        } else {
            setContentView(R.layout.activity_sensors);
            sensorGame = 1;
            findViews();
            sensors = new Sensors();
            initSensors();
        }
        // permission of Location
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findViews() {
        main_LBL_time = findViewById(R.id.main_LBL_time);
        main_IMG_hearts = new ImageView[]
                {
                        findViewById(R.id.main_IMG_heart1),
                        findViewById(R.id.main_IMG_heart2),
                        findViewById(R.id.main_IMG_heart3)
                };

        main_IMG_route = new ImageView[][]{
                {findViewById(R.id.main_IMG_00), findViewById(R.id.main_IMG_01), findViewById(R.id.main_IMG_02), findViewById(R.id.main_IMG_03), findViewById(R.id.main_IMG_04)},
                {findViewById(R.id.main_IMG_10), findViewById(R.id.main_IMG_11), findViewById(R.id.main_IMG_12), findViewById(R.id.main_IMG_13), findViewById(R.id.main_IMG_14)},
                {findViewById(R.id.main_IMG_20), findViewById(R.id.main_IMG_21), findViewById(R.id.main_IMG_22), findViewById(R.id.main_IMG_23), findViewById(R.id.main_IMG_24)},
                {findViewById(R.id.main_IMG_30), findViewById(R.id.main_IMG_31), findViewById(R.id.main_IMG_32), findViewById(R.id.main_IMG_33), findViewById(R.id.main_IMG_34)},
                {findViewById(R.id.main_IMG_40), findViewById(R.id.main_IMG_41), findViewById(R.id.main_IMG_42), findViewById(R.id.main_IMG_43), findViewById(R.id.main_IMG_44)},
                {findViewById(R.id.main_IMG_50), findViewById(R.id.main_IMG_51), findViewById(R.id.main_IMG_52), findViewById(R.id.main_IMG_53), findViewById(R.id.main_IMG_54)},
                {findViewById(R.id.main_IMG_60), findViewById(R.id.main_IMG_61), findViewById(R.id.main_IMG_62), findViewById(R.id.main_IMG_63), findViewById(R.id.main_IMG_64)}
        };
        if (game.equals("buttons")) {
            main_BTN_up = findViewById(R.id.main_BTN_up);
            main_BTN_left = findViewById(R.id.main_BTN_left);
            main_BTN_right = findViewById(R.id.main_BTN_right);
            main_BTN_down = findViewById(R.id.main_BTN_down);
        } else {
            acc1 = findViewById(R.id.sensor_LBL_acc1);
            acc2 = findViewById(R.id.sensor_LBL_acc2);
        }

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

    private enum TIMER_STATUS {
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
        if (timerStatus == TIMER_STATUS.RUNNING) {
            stopTimer();
            timerStatus = TIMER_STATUS.PAUSE;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (timerStatus == TIMER_STATUS.OFF) {
            startTimer();
        } else if (timerStatus == TIMER_STATUS.RUNNING) {
            stopTimer();
        } else {
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
        runLogic();
        updateUI();

    }

    public void runLogic() {
        CheckCrashWithCoin();
        updateScore();

        gameManager.randomBotDirectionMove();
        gameManager.playerMove();
        gameManager.checkCrash();
    }

    //sensors
    protected void onResume() {
        super.onResume();
        if (sensorGame == 1) {
            sensorManager.registerListener(accSensorEventListener, sensors.getAccSensor(), sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorGame==1)
            sensorManager.unregisterListener(accSensorEventListener);
    }


    private void updateUI() {
        for (int i = 0; i < main_IMG_route.length; i++) {
            for (int j = 0; j < main_IMG_route[0].length; j++) {
                main_IMG_route[i][j].setImageResource(R.drawable.ic_empty);
            }
        }

        main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_jerry);
        main_IMG_route[gameManager.getBot().getLocationX()][gameManager.getBot().getLocationY()].setImageResource(R.drawable.ic_tom);
        if (stepDetector.getStepCount() % 10 == 0) {
            isGotCoin = false;
            // Random Coin
            gameManager.getCoin().setCoin_x((int) (Math.random() * 7));
            gameManager.getCoin().setCoin_y((int) (Math.random() * 5));
        }
        if(!isGotCoin){
            main_IMG_route[gameManager.getCoin().getCoin_x()][gameManager.getCoin().getCoin_y()].setImageResource(R.drawable.ic_cheese);
            main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_jerry);
            main_IMG_route[gameManager.getBot().getLocationX()][gameManager.getBot().getLocationY()].setImageResource(R.drawable.ic_tom);
        }


        if (gameManager.getCrash()) {
            if (gameManager.getLives() > 0) {
                soundManager.setMpAndPlay((ContextWrapper)getApplicationContext(),R.raw.crash_sound);
                Toast.makeText(this, "BOOM", Toast.LENGTH_LONG).show();
                main_IMG_hearts[gameManager.getLives()].setVisibility(View.INVISIBLE);
                updateUIAfterCrash();
            } else {
                Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();
                // TODO: 05/05/2022 check
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Record rec = new Record()
                                .setName(gameManager.getPlayer().getPlayerName())
                                .setScore(counter)
                                .setLon(locationManager.getLon())
                                .setLat(locationManager.getLat());

                        myDB.addRecord(rec);
                        String json = new Gson().toJson(myDB);
                        MSPV3.getMe().putString("MY_DB", json);
                        replaceActivity();
                        finish();
                    }
                }, 500);
                gameManager.getPlayer().setScore(counter);
                stopTimer();
                replaceActivity();
                finish();
            }
        }

    }



    private void replaceActivity() {
        Intent intent = new Intent(this, Activity_GameOver.class);
        bundle.putInt("PlayerScore", gameManager.getPlayer().getScore());
        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }


    private void updateUIAfterCrash() {
        main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_empty);
        gameManager.bootLocations();
        main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_jerry);
        main_IMG_route[gameManager.getBot().getLocationX()][gameManager.getBot().getLocationY()].setImageResource(R.drawable.ic_tom);
        gameManager.setCrash(false);
    }


    //sensors
    public void initSensors() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensors.setSensorManager(sensorManager);
        sensors.initSensor();
    }

    private SensorEventListener accSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            NumberFormat formatter = new DecimalFormat("#0.00");
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];

            if (x < -5) {// move right
                gameManager.getPlayer().setDirection("RIGHT");
            } else if (x > 5) {// move left
                gameManager.getPlayer().setDirection("LEFT");
            } else if (y < -3) {// move up
                gameManager.getPlayer().setDirection("UP");
            } else if (y > 3) {// move down
                gameManager.getPlayer().setDirection("DOWN");
            }

            acc1.setText(formatter.format(x) + "\n" + formatter.format(y));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


    //coin
    private void updateScore() {
        if ((gameManager.getCoin().getCoin_x() == gameManager.getPlayer().getLocationX()) && (gameManager.getCoin().getCoin_y() == gameManager.getPlayer().getLocationY())) {
            counter += 10;
            main_LBL_time.setText("" + counter);
            Toast.makeText(this, "+10 coins", Toast.LENGTH_SHORT).show();
            stepDetector.setStepCount(0);
        }
        if ((gameManager.getCoin().getCoin_x() == gameManager.getBot().getLocationX()) && (gameManager.getCoin().getCoin_y() == gameManager.getBot().getLocationY())) {
            if (counter < 10)
                counter = 0;
            else
                counter -= 10;
            Toast.makeText(this, "Oops!", Toast.LENGTH_SHORT).show();
            stepDetector.setStepCount(0);
        }
    }



    public void CheckCrashWithCoin() {
        if ((gameManager.getCoin().getCoin_x() == gameManager.getPlayer().getLocationX())
                && (gameManager.getCoin().getCoin_y() == gameManager.getPlayer().getLocationY())) {
            main_IMG_route[gameManager.getPlayer().getLocationX()][gameManager.getPlayer().getLocationY()].setImageResource(R.drawable.ic_jerry);
            soundManager.setMpAndPlay((ContextWrapper)getApplicationContext(),R.raw.bite_sound);
            isGotCoin = true;
        } else if ((gameManager.getCoin().getCoin_x() == gameManager.getBot().getLocationX()) &&
                (gameManager.getCoin().getCoin_y() == gameManager.getBot().getLocationY())) {
            soundManager.setMpAndPlay((ContextWrapper)getApplicationContext(),R.raw.bite_sound);
            main_IMG_route[gameManager.getBot().getLocationX()][gameManager.getBot().getLocationY()].setImageResource(R.drawable.ic_tom);
            isGotCoin = true;
        }
        }
        
    }


