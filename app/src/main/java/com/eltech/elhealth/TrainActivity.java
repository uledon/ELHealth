package com.eltech.elhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;

import com.eltech.elhealth.Tools.DataClass;
import com.eltech.elhealth.Tools.TrainAdapter;
import com.eltech.elhealth.Workouts.Workout;
import com.eltech.elhealth.Workouts.WorkoutsClass;
import com.eltech.elhealth.Workouts.cobra_stretch;
import com.eltech.elhealth.Workouts.cooldown;
import com.eltech.elhealth.Workouts.lunges;
import com.eltech.elhealth.Workouts.mountain_climber;
import com.eltech.elhealth.Workouts.plank;
import com.eltech.elhealth.Workouts.skipping_without_rope;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TrainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button activity_train_start_button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static ArrayList<Workout> todayWorkouts;
    String workouts_for_today;
    TextView activity_train_day_number;
    private final static String SHARED_PREFS = "sharedPrefs";
    final String lose_fat_chip_string = "lose_fat_chip",
            build_muscle_chip_string = "build_muscle_chip",
            improve_endurance_chip_string = "improve_endurance_chip",
            maintain_body_shape_chip_string = "maintain_body_shape_chip",
            improve_athletic_skills_chip_string = "improve_athletic_skills_chip",
            CURRENT_DATE_WORKOUTS = "current_date_workouts",
            TODAYS_CHOSEN_EXERCISES = "todays_chosen_exercises";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        recyclerView = findViewById(R.id.activity_train_recyclerView);
        activity_train_start_button = findViewById(R.id.activity_train_start_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String level_string = sharedPreferences.getString("seekBar_current_text","0");
        activity_train_day_number = findViewById(R.id.activity_train_day_number);
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

        //select random types of exercise
        // if there is no sharedpreference current date exercises.
        // if there is already date then retrieve those exercises.
        String todays_chosen_exercise;
        @SuppressLint("SimpleDateFormat") String date =
                new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        if(!date.equals(sharedPreferences.getString(CURRENT_DATE_WORKOUTS, "0"))){
            Random random = new Random();
            int chosenDailyExercises = random.nextInt(availableOptions.size());
            todays_chosen_exercise = availableOptions.get(chosenDailyExercises);
            DataClass.print("chosen exercise is: " + todays_chosen_exercise);
            editor.putString(CURRENT_DATE_WORKOUTS,date);
            editor.putString(TODAYS_CHOSEN_EXERCISES,todays_chosen_exercise);
            editor.apply();
        }
        else{
            todays_chosen_exercise = sharedPreferences.getString(TODAYS_CHOSEN_EXERCISES,"0");
        }
        switch (todays_chosen_exercise) {
            case lose_fat_chip_string:
                todayWorkouts = prepareWorkouts(WorkoutsClass.getLoseFatWorkouts());//
                activity_train_day_number.setText(getString(R.string.lose_fat));
                break;
            case build_muscle_chip_string:
                todayWorkouts = prepareWorkouts(WorkoutsClass.getBuildMuscleWorkouts());
                activity_train_day_number.setText(getString(R.string.build_muscle));
                break;
            case improve_endurance_chip_string:
                todayWorkouts = prepareWorkouts(WorkoutsClass.getImproveEnduranceWorkouts());
                activity_train_day_number.setText(getString(R.string.improve_endurance));
                break;
            case maintain_body_shape_chip_string:
                todayWorkouts = prepareWorkouts(WorkoutsClass.getMaintainBodyShapeWorkouts());
                activity_train_day_number.setText(getString(R.string.maintain_body_shape));
                break;
            case improve_athletic_skills_chip_string:
                todayWorkouts = WorkoutsClass.getImproveAthleticSkillsWorkouts();
                todayWorkouts.add(new plank());
                todayWorkouts.addAll(todayWorkouts);
                todayWorkouts.addAll(todayWorkouts);
                todayWorkouts.add(0,new mountain_climber());
                todayWorkouts.add(new cobra_stretch());
                activity_train_day_number.setText(getString(R.string.improve_athletic_skills));
                break;
        }

        TrainAdapter adapter = new TrainAdapter(this, todayWorkouts, level,20); // change this later when user input is required.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        activity_train_start_button.setOnClickListener( v->{
                    Intent intent = new Intent(TrainActivity.this, WorkingOutActivity.class);
                    startActivity(intent);
                    finish();
        });
    }
    // create new array of max ~8 items from the list given +
    // remove any exercise that might affect user preference e.g. knee / weights etc...
    // cool down after every exercise
    // + warm up exercise
    /// add plank after each set
    /// add cobra stretch to end the routine.
    private ArrayList<Workout> prepareWorkouts(ArrayList<Workout> todaysWorkOuts){
        Random random = new Random();
        ArrayList<Workout> newWorkoutsArrayList = new ArrayList<>();
        boolean plausibleWorkout;
        while (newWorkoutsArrayList.size()<7){
            int newWorkout = random.nextInt(todaysWorkOuts.size());
            Workout workout = todaysWorkOuts.get(newWorkout);
            plausibleWorkout = true;
            // the following need to be verified more.
            if(workout.requiresEquipments()&&!sharedPreferences.getBoolean("using_gym_equipment_chip",false)){
                plausibleWorkout = false; }
            if(workout.requiresWeights()&&!sharedPreferences.getBoolean("using_weights_chip",false)){
                plausibleWorkout = false; }
            if(workout.affectsKnee()&&sharedPreferences.getBoolean("no_jumping_chip",false)){
                plausibleWorkout = false; }
            if(workout.affectsKnee()&&sharedPreferences.getBoolean("low_impact_chip",false)){
                plausibleWorkout = false; }
            if(plausibleWorkout){
            if(!newWorkoutsArrayList.contains(todaysWorkOuts.get(newWorkout))) {
                newWorkoutsArrayList.add(todaysWorkOuts.get(newWorkout)); } } }
        newWorkoutsArrayList.add(new plank());
        newWorkoutsArrayList.addAll(newWorkoutsArrayList);
        newWorkoutsArrayList.addAll(newWorkoutsArrayList);
        newWorkoutsArrayList.add(0,new mountain_climber());
        newWorkoutsArrayList.add(new cobra_stretch());
        return newWorkoutsArrayList;
    }
    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(TrainActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
