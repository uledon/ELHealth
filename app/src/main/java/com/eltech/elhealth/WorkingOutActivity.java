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
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eltech.elhealth.Tools.DataClass;
import com.eltech.elhealth.Workouts.Workout;
import com.eltech.elhealth.Workouts.cooldown;

import java.util.ArrayList;
import java.util.Locale;

public class WorkingOutActivity extends AppCompatActivity {
    public LinearLayout linearLayout;
    public TextView[] dots;
    Button next_button, back_button;
    public boolean finish;
    private final static String SHARED_PREFS = "sharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView activity_working_out_slide_image2;
    TextView activity_working_out_slide_heading2,activity_working_out_slide_desc2;
    CountDownTimer countDownTimer;
    int currentInt = 0;
    Workout currentItem;
    int level;
    int cool_down;
    long timeLeft;
    ArrayList<Workout> fullWorkOutArray = new ArrayList<>();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_out);
        linearLayout = findViewById(R.id.activity_working_out_linearLayout);
        next_button = findViewById(R.id.start_button2);
        back_button = findViewById(R.id.give_up_button2);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        activity_working_out_slide_image2 = findViewById(R.id.activity_working_out_slide_image2);
        activity_working_out_slide_heading2 = findViewById(R.id.activity_working_out_slide_heading2);
        activity_working_out_slide_desc2 = findViewById(R.id.activity_working_out_slide_desc2);
        editor = sharedPreferences.edit();
        String level_string = sharedPreferences.getString("seekBar_current_text","0");
        String cool_down_string = sharedPreferences.getString("choose_cool_down_seekBar_current_text","10");
        try{
            level = Integer.parseInt(level_string);
        }
        catch(NumberFormatException e){
            level = 0;
            e.printStackTrace();
        }

        try{
            cool_down = Integer.parseInt(cool_down_string);
        }
        catch(NumberFormatException e){
            cool_down = 10;
            e.printStackTrace();
        }
        prepareWorkouts(TrainActivity.todayWorkouts);
        currentItem = fullWorkOutArray.get(currentInt);
        setUpLayout();
    }

    /**
     * @param position keeps track of position
     * Gets the current position and colours the dots
     */
    public void addDotsIndicator(int position){
        dots = new TextView[fullWorkOutArray.size()];
        linearLayout.removeAllViews();
        for(int i = 0; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.textColour));
            linearLayout.addView(dots[i]);
        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent4));
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void setUpLayout(){
        //setting the back button to give up
        addDotsIndicator(currentInt);
        back_button.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(this.getString(R.string.give_up_title))
                    .setMessage(this.getString(R.string.give_up_message))
                    .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                        if(countDownTimer!=null){
                            countDownTimer.cancel();
                        }
                        changeScreens("give_up");
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        });
        if(currentItem.getImage()!=0){
            activity_working_out_slide_image2.setForeground(getDrawable(currentItem.getImage()));
        }
        else{
            activity_working_out_slide_image2.setForeground(null);
        }
        activity_working_out_slide_heading2.setText(currentItem.getTitle());
        if(currentItem.hasTimer()){
            next_button.setVisibility(View.VISIBLE);
            activity_working_out_slide_desc2.setText("" + currentItem.getTimer(cool_down,level) + " sec.");
            long millisInFuture = currentItem.getTimer(cool_down,level) * 1000 + 3000;// adding buffer for people to start exercises
            DataClass.print("millis in future is: " + millisInFuture);
            next_button.setText(getString(R.string.start));
            next_button.setOnClickListener(v->{
                next_button.setVisibility(View.GONE);
                countDownTimer = new CountDownTimer(millisInFuture, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeLeft = millisUntilFinished;
                        int minutes = (int) (timeLeft / 1000) / 60;
                        int seconds = (int) (timeLeft / 1000) % 60;
                        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                        activity_working_out_slide_desc2.setText(timeLeftFormatted);
                        DataClass.print("slideDesc in onTick is: " + activity_working_out_slide_desc2.getText());
                        DataClass.print("timeLeftFormatted is: " + timeLeftFormatted);
                    }

                    @Override
                    public void onFinish() {
                        if (currentInt == fullWorkOutArray.size()-1){
                            currentInt++;
                            changeScreens("finish");
                        }
                        else{
                            currentInt ++;
                            currentItem = fullWorkOutArray.get(currentInt);
                            setUpLayout();
                        }
                    }
                }.start();

            });
        }
        else{
            DataClass.print("else statement reached workingoutactivity");
            next_button.setVisibility(View.VISIBLE);
            next_button.setText(getString(R.string.next));
            next_button.setOnClickListener(v->{
                if (currentInt == fullWorkOutArray.size()-1){
                    currentInt++;
                    changeScreens("finish");
                }
                else{
                        currentInt ++;
                        currentItem = fullWorkOutArray.get(currentInt);
                    DataClass.print("workingoutactivity currentItem is:" + currentInt);
                    DataClass.print("workingoutactivity currentItem headings is:" + getString(fullWorkOutArray.get(currentInt).getTitle()));
                    setUpLayout();
                }

            });

            activity_working_out_slide_desc2.setText(currentItem.getReps(level));
        }

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(WorkingOutActivity.this)
                .setTitle(getString(R.string.give_up_title))
                .setMessage(getString(R.string.give_up_message))
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    changeScreens("give_up");
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    public void prepareWorkouts(ArrayList<Workout> todaysWorkouts){
        for(Workout workout : todaysWorkouts){
            fullWorkOutArray.add(workout);
            if(workout != todaysWorkouts.get(todaysWorkouts.size()-1)){
            fullWorkOutArray.add(new cooldown());
            }
        }
    }
    void changeScreens(String mode){
        Intent intent = new Intent(WorkingOutActivity.this,HomeActivity.class);
        switch (mode){
            case "give_up":
                intent.putExtra("finished_exercises",currentInt);
                intent.putExtra("total_exercises",TrainActivity.todayWorkouts.size());
                intent.putExtra("finish",false);
                break;
            case "finish":
                intent.putExtra("total_exercises",TrainActivity.todayWorkouts.size());
                intent.putExtra("finish",true);
                break;
        }
        startActivity(intent);
        finish();
    }
}