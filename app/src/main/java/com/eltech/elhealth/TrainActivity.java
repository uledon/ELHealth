package com.eltech.elhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.eltech.elhealth.Tools.TrainAdapter;
import com.eltech.elhealth.Workouts.Workout;
import com.eltech.elhealth.Workouts.WorkoutsClass;
import com.eltech.elhealth.Workouts.cooldown;
import com.eltech.elhealth.Workouts.lunges;
import com.eltech.elhealth.Workouts.mountain_climber;
import com.eltech.elhealth.Workouts.skipping_without_rope;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button activity_train_start_button;
    SharedPreferences sharedPreferences;
    private final static String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        recyclerView = findViewById(R.id.activity_train_recyclerView);
        activity_train_start_button = findViewById(R.id.activity_train_start_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String level_string = sharedPreferences.getString("seekBar_current_text","0");
        int level;
        try{
            level = Integer.parseInt(level_string);
        }
        catch(NumberFormatException e){
            level = 0;
            e.printStackTrace();
        }
        //pass this to next activity
        TrainAdapter adapter = new TrainAdapter(this, WorkoutsClass.getLoseFatWorkouts(), level,10); // change this later when user input is required.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        activity_train_start_button.setOnClickListener( v->{
                    Intent intent = new Intent(TrainActivity.this, WorkingOutActivity.class);
                    startActivity(intent);
                    finish();
        });
    }
}
