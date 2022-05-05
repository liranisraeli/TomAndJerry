package com.example.class22b_and_316332857_1.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.class22b_and_316332857_1.CallBack_List;
import com.example.class22b_and_316332857_1.MSPV3;
import com.example.class22b_and_316332857_1.MyDB;
import com.example.class22b_and_316332857_1.R;
import com.example.class22b_and_316332857_1.objects.Record;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;


public class Fragment_List extends Fragment {

        private MaterialButton[] allWinners;
        private CallBack_List callBackList;

        public void setActivity(AppCompatActivity activity) {
        }

        public void setCallBackList(CallBack_List callBackList) {
            this.callBackList = callBackList;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment__list, container, false);
            findViews(view);
            initViews();
            return view;
        }


        @SuppressLint("SetTextI18n")
        private void initViews() {
            String js = MSPV3.getMe().getString("MY_DB", "");
            MyDB md = new Gson().fromJson(js, MyDB.class);
            //md.sortByScore();
            for (int i = 0; i < md.getRecords().size(); i++) {
                Record winner = md.getSpecificRecord(i);
                allWinners[i].setVisibility(View.VISIBLE);
                allWinners[i].setText(i+1+". "+winner.getName()+"\n  Score: "+winner.getScore());
                allWinners[i].setOnClickListener(v -> callBackList.locateOnMap(winner.getLat(),winner.getLon(), winner.getName()));

            }
        }


        private void findViews(View view) {
            allWinners = new MaterialButton[]{
                    view.findViewById(R.id.frame1_BTN_winner1),
                    view.findViewById(R.id.frame1_BTN_winner2),
                    view.findViewById(R.id.frame1_BTN_winner3),
                    view.findViewById(R.id.frame1_BTN_winner4),
                    view.findViewById(R.id.frame1_BTN_winner5),
                    view.findViewById(R.id.frame1_BTN_winner6),
                    view.findViewById(R.id.frame1_BTN_winner7),
                    view.findViewById(R.id.frame1_BTN_winner8),
                    view.findViewById(R.id.frame1_BTN_winner9),
                    view.findViewById(R.id.frame1_BTN_winner10),
            };

        }
    }