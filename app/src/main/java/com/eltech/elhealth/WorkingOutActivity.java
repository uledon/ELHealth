package com.eltech.elhealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eltech.elhealth.Tools.DataClass;
import com.eltech.elhealth.Tools.WorkingOut_PagerAdapter;
import com.eltech.elhealth.Workouts.Workout;
import com.eltech.elhealth.Workouts.WorkoutsClass;
import com.eltech.elhealth.Workouts.cooldown;
import com.eltech.elhealth.Workouts.mountain_climber;

import java.util.ArrayList;

public class WorkingOutActivity extends AppCompatActivity {
    public ViewPager slideViewPager;
    public LinearLayout linearLayout;
    public WorkingOut_PagerAdapter workingOut_pagerAdapter;
    public TextView[] dots;
    public Button next_button;
    public Button back_button;
    public int currentPage;
    public boolean finish;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final static String SHARED_PREFS = "sharedPrefs";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_out);
        slideViewPager = findViewById(R.id.activity_working_out_slideViewPager);
        linearLayout = findViewById(R.id.activity_working_out_linearLayout);
        next_button = findViewById(R.id.activity_working_out_next_button);
        back_button = findViewById(R.id.activity_working_out_back_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String level_string = sharedPreferences.getString("seekBar_current_text","0");
        int level;
        try{
            level = Integer.parseInt(level_string);
        }
        catch(NumberFormatException e){
            level = 0;
            e.printStackTrace();
        }
        // instead of recreating activities;
        // retrieve array list from the previous activity.
        if(WorkoutsClass.getLoseFatWorkouts()!=null){
            workingOut_pagerAdapter = new WorkingOut_PagerAdapter(this, WorkoutsClass.getLoseFatWorkouts(),level,10);// make more dynamic later
            slideViewPager.setAdapter(workingOut_pagerAdapter);
            addDotsIndicator(0);
        }
        slideViewPager.setOnTouchListener((v, event) -> true);
        slideViewPager.setHorizontalScrollBarEnabled(false);
        slideViewPager.addOnPageChangeListener(viewListener);
        next_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage + 1));
        back_button.setOnClickListener(v -> {
                new AlertDialog.Builder(WorkingOutActivity.this)
                        .setTitle(getString(R.string.give_up_title))
                        .setMessage(getString(R.string.give_up_message))
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(v.getContext(),PointsActivity.class);
                                startActivityForResult(intent, 0 );
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
        });
    }

    /**
     * @param position keeps track of position
     * Gets the current position and colours the dots
     */
    public void addDotsIndicator(int position){
        dots = new TextView[workingOut_pagerAdapter.getCount()];
        linearLayout.removeAllViews();
        for(int i = 0; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.textColour));
            linearLayout.addView(dots[i]);
        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    /**
     * Sets the next and finish text on the respective buttons depending on the position
     */
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        private static final String SHARED_PREFS = "sharedPrefs", FINISH = "finish2";
        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;
            if (position == 0){
                next_button.setEnabled(true);
                next_button.setText(getString(R.string.next));
                next_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage + 1));
            }
            else if (position == dots.length-1){
                next_button.setEnabled(true);
                next_button.setText(getString(R.string.finish));
                next_button.setOnClickListener(view -> {
                    Intent myIntent = new Intent(view.getContext(), PointsActivity.class);
                    startActivityForResult(myIntent, 0);
                    finish = true;
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(FINISH,finish);
                    editor.apply();
                    finish();
                });

            }
            else{
                next_button.setEnabled(true);
                next_button.setText(getString(R.string.next));
                next_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage + 1));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(WorkingOutActivity.this)
                .setTitle(getString(R.string.give_up_title))
                .setMessage(getString(R.string.give_up_message))
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    Intent intent = new Intent(WorkingOutActivity.this,PointsActivity.class);
                    startActivityForResult(intent, 0 );
                    finish();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}