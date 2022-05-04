package com.example.class22b_and_316332857_1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.class22b_and_316332857_1.R;


public class Fragment_List extends Fragment {

//    private TextView game_top_ten_LBL_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__list, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
//        game_top_ten_LBL_title = view.findViewById(R.id.game_top_ten_LBL_title);
    }
}