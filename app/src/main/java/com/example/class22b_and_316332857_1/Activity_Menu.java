package com.example.class22b_and_316332857_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class Activity_Menu extends AppCompatActivity {

    private LinearLayout Menu_layout_name_player;
    private TextInputLayout Menu_LBL_player_name;
    private MaterialButton Menu_BTN_OK;

    private LinearLayout Menu_layout_game_mode;
    private MaterialButton Menu_BTN_start_Button;
    private MaterialButton Menu_BTN_start_Sensor;
    private MaterialButton Menu_BTN_top_Button;

    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MyScreenUtils.hideSystemUI(this);

        findViews();
        initButtons();

    }

    private void findViews() {

        Menu_layout_name_player=findViewById(R.id.Menu_layout_name_player);
        Menu_LBL_player_name=findViewById(R.id.Menu_LBL_player_name);
        Menu_BTN_OK=findViewById(R.id.Menu_BTN_OK);

        Menu_layout_game_mode=findViewById(R.id.Menu_layout_game_mode);
        Menu_BTN_start_Button=findViewById(R.id.Menu_BTN_start_Button);
        Menu_BTN_start_Sensor = findViewById(R.id.Menu_BTN_start_Sensor);
        Menu_BTN_top_Button = findViewById(R.id.Menu_BTN_top_Button);
    }

    private void initButtons() {

        Menu_BTN_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getName()){
                    Menu_layout_name_player.setVisibility(View.INVISIBLE);
                    Menu_layout_game_mode.setVisibility(View.VISIBLE);
                }
            }
        });

        Menu_BTN_start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceActivity("buttons");
            }
        });

        Menu_BTN_start_Sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceActivity("sensor");
            }
        });

//        Menu_BTN_top_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                replaceActivity();
//            }
//        });


    }



    private boolean getName() {
         playerName = Menu_LBL_player_name.getEditText().getText().toString();
        if(!playerName.equals("")){
            return true;
        }
        return false;
    }


    private void replaceActivity(String game) {
        Intent intent = new Intent(this,Activity_Main.class);
        Bundle bundle = new Bundle();
        bundle.putString("playerName",playerName);
        bundle.putString("game",game);
        intent.putExtra("Bundle",bundle);
        startActivity(intent);
    }



}