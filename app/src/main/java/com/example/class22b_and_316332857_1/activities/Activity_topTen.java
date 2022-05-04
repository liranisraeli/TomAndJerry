package com.example.class22b_and_316332857_1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.class22b_and_316332857_1.fragments.Fragment_GoogleMap;
import com.example.class22b_and_316332857_1.R;
import com.example.class22b_and_316332857_1.fragments.Fragment_List;

public class Activity_topTen extends AppCompatActivity {

    private TextView top_ten_LBL_title;
    private TextView top_ten_LBL_map;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);


    if (getIntent().getBundleExtra("Bundle") != null){
        this.bundle = getIntent().getBundleExtra("Bundle");
    } else {
        this.bundle = new Bundle();
    }
    findViews();
    //Init Fragment map
    Fragment fragment = new Fragment_GoogleMap();

    //Open map Fragment
    getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_LAY_map, fragment)
                .commit();



    //Init Fragment list
    Fragment_List fragment_list = new Fragment_List();
    //Open list Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.game_LAY_list, fragment_list)
                .commit();

}

    private void findViews() {
        top_ten_LBL_title = findViewById(R.id.game_top_ten_LBL_title);
        top_ten_LBL_map = findViewById(R.id.game_top_ten_LBL_map);
    }




}