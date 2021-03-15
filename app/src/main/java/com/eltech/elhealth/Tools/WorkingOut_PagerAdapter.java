package com.eltech.elhealth.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.eltech.elhealth.PointsActivity;
import com.eltech.elhealth.R;
import com.eltech.elhealth.WorkingOutActivity;
import com.eltech.elhealth.Workouts.Workout;

import java.util.Locale;

public class WorkingOut_PagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    Workout[] workouts;
    int level; long timer;
    long timeLeft;
    public CountDownTimer countDownTimer;
    WorkingOutActivity activity;//
    Workout current; ///
    ///
    public WorkingOut_PagerAdapter(WorkingOutActivity activity, Context context, Workout[] workouts, int level, long timer){
        this.context = context;
        this.workouts = workouts;
        this.level = level;
        this.activity = activity;
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_working_out_slide_layout,container,false);
            current = workouts[position];
        DataClass.print("item position: " + activity.slideViewPager.getCurrentItem());
        ImageView slideImageView = view.findViewById(R.id.activity_working_out_slide_image);
        TextView slideHeading = view.findViewById(R.id.activity_working_out_slide_heading);
        TextView slideDesc = view.findViewById(R.id.activity_working_out_slide_desc); ///
        Button startButton = view.findViewById(R.id.start_button);
        Button give_up_button= view.findViewById(R.id.give_up_button);
        give_up_button.setOnClickListener(v -> {
            new AlertDialog.Builder(activity)
                    .setTitle(context.getString(R.string.give_up_title))
                    .setMessage(context.getString(R.string.give_up_message))
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            Intent intent = new Intent(v.getContext(),PointsActivity.class);
                            activity.startActivityForResult(intent, 0 );
                            activity.finish();
                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        });
        slideImageView.setImageResource(current.getImage());
        slideHeading.setText(current.getTitle());
//        DataClass.print("current in adapter is: " + context.getString(current.getTitle()));
        if(current.hasTimer()){
            startButton.setVisibility(View.VISIBLE);
            slideDesc.setText("" + current.getTimer(timer,level) + " sec.");
            long millisInFuture = current.getTimer(timer,level) * 1000;
            DataClass.print("millis in future is: " + millisInFuture);
            startButton.setText(context.getString(R.string.start));
                startButton.setOnClickListener(v->{
                    startButton.setVisibility(View.GONE);

                    countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timeLeft = millisUntilFinished;
                            //DataClass.print("timeLeft in onTick is: " + timeLeftOutisde);
//                DataClass.print("we are inside the tick");
                            int minutes = (int) (timeLeft / 1000) / 60;
                            int seconds = (int) (timeLeft / 1000) % 60;
                            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                            slideDesc.setText(timeLeftFormatted);
                            DataClass.print("slideDesc in onTick is: " + slideDesc.getText());
                            DataClass.print("timeLeftFormatted is: " + timeLeftFormatted);
                        }

                        @Override
                        public void onFinish() {
//                DataClass.print("we are inside the onFinish");
                            if (activity.slideViewPager.getCurrentItem() == workouts.length-1){
                                Intent myIntent = new Intent(context, PointsActivity.class);
                                activity.startActivityForResult(myIntent, 0);
                                activity.finish();
                            }
                            else{
                                activity.slideViewPager.setCurrentItem(activity.slideViewPager.getCurrentItem()+1);
                            }
                        }
                    }.start();

                });
        }
        else{
            startButton.setVisibility(View.VISIBLE);
            startButton.setText(context.getString(R.string.next));
            startButton.setOnClickListener(v->{
                if (activity.slideViewPager.getCurrentItem() == workouts.length-1){
                    Intent myIntent = new Intent(context, PointsActivity.class);
                    activity.startActivityForResult(myIntent, 0);
                    activity.finish();
                }
                else{
                    activity.slideViewPager.setCurrentItem(activity.slideViewPager.getCurrentItem()+1);
                }

            });
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
}
