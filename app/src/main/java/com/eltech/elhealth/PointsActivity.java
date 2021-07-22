package com.eltech.elhealth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PointsActivity extends AppCompatActivity {

    TextView textView6,textView7,textView8;
    Button resetButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private final static String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        resetButton = findViewById(R.id.resetButton);
        if(getIntent().getExtras().get("from_home")!=null){
            textView6.setText("From Home"); //// stringify
        }
        else{
            if(getIntent().getExtras().get("finish")!= null){
                boolean finish = (boolean) getIntent().getExtras().get("finish");
                int finished_exercises = 0 ;
                int points = sharedPreferences.getInt("points",0);
                int total_exercises = (int)getIntent().getExtras().get("total_exercises");
                if(finish){
                    finished_exercises = total_exercises;
                    points += 2;
                    textView6.setText("Congratulations you have completed all exercises");/// stringify
                    textView7.setText("all " + String.valueOf(finished_exercises)+ " exercises completed");

                }
                else{
                    if(points -- <=0){
                        points--;
                    }
                    finished_exercises = (int)getIntent().getExtras().get("finished_exercises");
                    textView6.setText("Why you give up man?");// stringify
                    textView7.setText(String.valueOf(finished_exercises) + " exercises completed");
                }
                textView8.setText("out of " + String.valueOf(total_exercises) + " exercises");
                editor.putInt("points",points);
                editor.apply();
            }
        }
        resetButton.setOnClickListener(v->{
            new AlertDialog.Builder(PointsActivity.this)
                    .setTitle("reset?")  //stringify
                    .setMessage("you sure u wanna reset?") // stringify
                    .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                        editor.putInt("points",0);
                        editor.apply();
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        });
    }
}