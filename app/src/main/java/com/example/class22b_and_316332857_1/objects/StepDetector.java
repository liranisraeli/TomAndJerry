package com.example.class22b_and_316332857_1.objects;

import android.os.Handler;

public class StepDetector {

        private int stepCount = 0;
        public StepDetector() { }
        public void start(){
            final Handler handler = new Handler();
            final int delay = 1000;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stepCount++;
                    handler.postDelayed(this,delay);
                }
            } , delay);
        }
        public int getStepCount(){
            return stepCount;
        }

        public void setStepCount(int stepCount) {
            this.stepCount = stepCount;
        }
    }

