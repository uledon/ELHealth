package com.eltech.elhealth.Onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eltech.elhealth.HomeActivity;
import com.eltech.elhealth.R;
import com.eltech.elhealth.Tools.slide_layout;

public class HelpActivity extends AppCompatActivity {

    public ViewPager slideViewPager;
    public LinearLayout linearLayout;
    public com.eltech.elhealth.Tools.slide_layout slide_layout;
    public TextView[] dots;
    public Button next_button;
    public Button back_button;
    public int currentPage;
    public boolean finish;
    private static final String SHARED_PREFS = "sharedPrefs", FINISH = "finish";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        slideViewPager = findViewById(R.id.slideViewPager);
        linearLayout = findViewById(R.id.linearLayout);
        next_button = findViewById(R.id.next_button);
        back_button = findViewById(R.id.back_button);
        slide_layout = new slide_layout(this);
        slideViewPager.setAdapter(slide_layout);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);
        next_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage + 1));
        back_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage-1));
    }
//    private boolean isFirstTimeStartApp(){
//        SharedPreferences ref = getApplicationContext().getSharedPreferences("",context.MODEPRIVATE);
//    }
    public void addDotsIndicator(int position){
        dots = new TextView[slide_layout.getCount()];
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

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;
            if (position == 0){
            next_button.setEnabled(true);
            back_button.setEnabled(false);
            back_button.setVisibility(View.INVISIBLE);
            next_button.setText(getString(R.string.next));
                next_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage + 1));
            back_button.setText("");
            }
            else if (position == dots.length-1){
                next_button.setEnabled(true);
                back_button.setEnabled(true);
                back_button.setVisibility(View.VISIBLE);
                next_button.setText(getString(R.string.finish));
                next_button.setOnClickListener(view -> {
                    Intent myIntent = new Intent(view.getContext(), HomeActivity.class);
                    startActivityForResult(myIntent, 0);
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                    boolean finished = sharedPreferences.getBoolean(FINISH, false);
                    if (finished){
                        Intent homeIntent = new Intent(HelpActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                    else{
                        Intent onboardingIntent = new Intent(HelpActivity.this, OnboardingActivity.class);
                        startActivity(onboardingIntent);
                        finish();
                    }
                });

                back_button.setText(getString(R.string.back));
            }
            else{
                next_button.setEnabled(true);
                back_button.setEnabled(true);
                back_button.setVisibility(View.VISIBLE);
                next_button.setText(getString(R.string.next));
                next_button.setOnClickListener(v -> slideViewPager.setCurrentItem(currentPage + 1));
                back_button.setText(getString(R.string.back));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * Overriding the back button
     * to allow two tap exit
     */
    private long backPressedTime;
    private Toast backToast;
    @Override
    public void onBackPressed() {
    if(!sharedPreferences.getBoolean(FINISH,false)){
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            backToast = Toast.makeText(getBaseContext(), getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    else{
        Intent homeIntent = new Intent(HelpActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }

    }
}
