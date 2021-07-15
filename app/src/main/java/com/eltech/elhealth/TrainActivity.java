package com.eltech.elhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.eltech.elhealth.Tools.DataClass;
import com.eltech.elhealth.Tools.TrainAdapter;
import com.eltech.elhealth.Workouts.Workout;
import com.eltech.elhealth.Workouts.WorkoutsClass;
import com.eltech.elhealth.Workouts.cooldown;
import com.eltech.elhealth.Workouts.lunges;
import com.eltech.elhealth.Workouts.mountain_climber;
import com.eltech.elhealth.Workouts.skipping_without_rope;

import java.util.ArrayList;
import java.util.Random;

public class TrainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button activity_train_start_button;
    SharedPreferences sharedPreferences;
    public static Workout[] todayWorkouts;
    private final static String SHARED_PREFS = "sharedPrefs";
    final String lose_fat_chip_string = "lose_fat_chip";
    final String build_muscle_chip_string = "build_muscle_chip";
    final String improve_endurance_chip_string = "improve_endurance_chip";
    final String maintain_body_shape_chip_string = "maintain_body_shape_chip";
    final String improve_athletic_skills_chip_string = "improve_athletic_skills_chip";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        recyclerView = findViewById(R.id.activity_train_recyclerView);
        activity_train_start_button = findViewById(R.id.activity_train_start_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String level_string = sharedPreferences.getString("seekBar_current_text","0");
        // types of exercise
        boolean lose_fat_chip = sharedPreferences.getBoolean(lose_fat_chip_string,false);
        boolean build_muscle_chip = sharedPreferences.getBoolean(build_muscle_chip_string,false);
        boolean improve_endurance_chip = sharedPreferences.getBoolean(improve_endurance_chip_string,false);
        boolean maintain_body_shape = sharedPreferences.getBoolean(maintain_body_shape_chip_string,false);
        boolean improve_athletic_skills_chip = sharedPreferences.getBoolean(improve_athletic_skills_chip_string,false);//
        // equipment
        boolean using_bodyweight_chip = sharedPreferences.getBoolean("using_bodyweight_chip",false);
        boolean using_gym_equipment_chip = sharedPreferences.getBoolean("using_gym_equipment_chip",false);
        boolean using_weights_chip = sharedPreferences.getBoolean("using_weights_chip",false);
        // body condition
        boolean im_fine_chip = sharedPreferences.getBoolean("im_fine_chip",false);
        boolean no_jumping_chip = sharedPreferences.getBoolean("no_jumping_chip",false);
        boolean low_impact_chip = sharedPreferences.getBoolean("low_impact_chip",false);

        int level;
        try{
            level = Integer.parseInt(level_string);
        }
        catch(NumberFormatException e){
            level = 0;
            e.printStackTrace();
        }
        /// create new array list based on types of exercises the user wants;
//        boolean[] options = new boolean[]{
//            lose_fat_chip,
//            build_muscle_chip,
//            improve_endurance_chip,
//            maintain_body_shape,
//            improve_athletic_skills_chip
//        };
        ArrayList<String> availableOptions = new ArrayList<>();
                if(lose_fat_chip){
                    availableOptions.add(lose_fat_chip_string);
                }
                if(build_muscle_chip){
                    availableOptions.add(build_muscle_chip_string);
                }
                if(improve_endurance_chip){
                    availableOptions.add(improve_endurance_chip_string);
                }
                if(maintain_body_shape){
                    availableOptions.add(maintain_body_shape_chip_string);
                }
                if(improve_athletic_skills_chip){
                    availableOptions.add(improve_athletic_skills_chip_string);
                }
                DataClass.print("int available exercises are: " + availableOptions.size());

        Random random = new Random();
        int chosenDailyExercises = random.nextInt(availableOptions.size());
        String todays_chosen_exercise = availableOptions.get(chosenDailyExercises);
        DataClass.print("chosen exercise is: " + todays_chosen_exercise);
        switch (todays_chosen_exercise) {
            case lose_fat_chip_string:
                todayWorkouts = WorkoutsClass.getLoseFatWorkouts();
                break;
            case build_muscle_chip_string:
                todayWorkouts = WorkoutsClass.getBuildMuscleWorkouts();
                break;
            case improve_endurance_chip_string:
                todayWorkouts = WorkoutsClass.getImproveEnduranceWorkouts();
                break;
            case maintain_body_shape_chip_string:
                todayWorkouts = WorkoutsClass.getMaintainBodyShapeWorkouts();
                break;
            case improve_athletic_skills_chip_string:
                todayWorkouts = WorkoutsClass.getImproveAthleticSkillsWorkouts();
                break;
        }
        /// select appropriate amount of exercises
        /// add warm up
        /// add cool-down
        /// add plank after each set
        /// add cobra stretch to end the routine.
        TrainAdapter adapter = new TrainAdapter(this, todayWorkouts, level,10); // change this later when user input is required.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        activity_train_start_button.setOnClickListener( v->{
                    Intent intent = new Intent(TrainActivity.this, WorkingOutActivity.class);
                    startActivity(intent);
                    finish();
        });
    }
}
