package com.eltech.elhealth.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.eltech.elhealth.R;
import com.eltech.elhealth.Workouts.Workout;

import java.util.ArrayList;
import java.util.Locale;

public class WorkingOut_PagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    Workout[] workouts;
    int level, timer;
    long timeLeft;
    public int currentSlide;
    CountDownTimer countDownTimer;
    public WorkingOut_PagerAdapter(Context context, Workout[] workouts, int level,int timer){
        this.context = context;
        this.workouts = workouts;
        this.level = level;
        this.timer = timer *1000;
    }
    @Override
    public int getCount() {
        return workouts.length;
    }
    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        Workout current = workouts[position];
        DataClass.print("current class is: " + current.getTitle());
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_working_out_slide_layout,container,false);
        ImageView slideImageView = view.findViewById(R.id.activity_working_out_slide_image);
        TextView slideHeading = view.findViewById(R.id.activity_working_out_slide_heading);
        TextView slideDesc = view.findViewById(R.id.activity_working_out_slide_desc);
        slideImageView.setImageResource(current.getImage());
        slideHeading.setText(current.getTitle());
        DataClass.print("current in adapter is: " + String.valueOf(current.getTitle()));
        if(current.hasTimer()){
            DataClass.print("level is: " + level);
            slideDesc.setText("" + current.getTimer(timer,level) + " sec.");
        }
        else{
            slideDesc.setText(current.getReps(level));
        }
        container.addView(view);
        return view;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }

    public void startTimer(long timeLeftOutisde){
        timeLeft = timeLeftOutisde;
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                DataClass.print("we are inside the tick");
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                DataClass.print("we are inside the onFinish");
            }
        }.start();
        DataClass.print("we are outside");
    }
    private void updateCountDownText() {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        //slideDesc.setText(timeLeftFormatted);
        DataClass.print("timeformatted is: " + timeLeftFormatted);
       // DataClass.print("getTimeLeft is: " + getTimeLeft());
    }
}
