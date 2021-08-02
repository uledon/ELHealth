
package com.eltech.elhealth;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eltech.elhealth.Onboarding.HelpActivity;
import com.eltech.elhealth.Onboarding.OnboardingActivity;
import com.eltech.elhealth.Tools.DataClass;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView account_text,calories_number;
    View header;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String SHARED_PREFS = "sharedPrefs",WATER_COUNT = "water_count",
            CURRENT_DATE = "current_date", WEIGHT_TEXT = "weight_text";
    ConstraintLayout points_widget, water_widget,weight_widget,calories_widget, train_button,eat_button;
    Button water_plus_button, water_minus_button, weight_record_button, calories_add_button;
    TextView water_text,points_text2;
    EditText weight_widget_layout_weight_text, add_calories_edit_text;
    ProgressBar points_progressBar;
    //for back button
    private long backPressedTime;
    private Toast backToast;
    BottomSheetDialog calories_bottom_sheet_dialog;
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //welcome_text = findViewById(R.id.welcome_text);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        if(sharedPreferences.getBoolean("Male",true)){
//            welcome_text.setText(getString(R.string.welcome_masculine));
//        }
//        else{
//            welcome_text.setText(getString(R.string.welcome_feminine));
//        }
        setNavigation_drawer();
        setPoints_widget();
        setWater_widget();
        setWeight_widget();
        setCalories_widget();
        setTrain_button();
        setEat_button();
    }
    /**
     * All functionality of the points widget is set here;
     */
    private void setPoints_widget(){
        points_widget = findViewById(R.id.points_widget);
        points_progressBar = points_widget.findViewById(R.id.points_progressBar);
        points_text2 = points_widget.findViewById(R.id.points_text2);

        @SuppressLint("SimpleDateFormat") String date =
                new SimpleDateFormat("MM").format(new Date());
        DataClass.print("date in points is: " + date);
        String date_str = sharedPreferences.getString("month",date);
        int points = 0;
        if(!date_str.equals(date)){
            editor.putInt("points",points);
            editor.putString("month",date);
            editor.apply();
        }
        else{
            points = sharedPreferences.getInt("points",0);
        }


        if (getIntent().hasExtra("finish")) {
            boolean finish = (boolean) getIntent().getExtras().get("finish");
            int finished_exercises;
            int total_exercises = (int) getIntent().getExtras().get("total_exercises");
            if (finish) {
                finished_exercises = total_exercises;
                points += 10;
                Toast.makeText(this, getString(R.string.finished_exercises_1) +
                        finished_exercises + getString(R.string.finished_exercises_2), Toast.LENGTH_SHORT).show();
            } else {
                finished_exercises = (int) getIntent().getExtras().get("finished_exercises");
                if (finished_exercises == 0) {
                    if(points -5 >=0){
                        points -= 5;
                        Toast.makeText(this,getString(R.string.lost_points),Toast.LENGTH_SHORT).show();
                    }
                    else{
                        points = 0;
                        Toast.makeText(this, getString(R.string.lost_all_points), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    int gainedPoints = Math.round((float) finished_exercises / (float) total_exercises * 10);
                    points += gainedPoints;
                    Toast.makeText(this,getString(R.string.gave_up_1) + gainedPoints + " " + getString(R.string.points),Toast.LENGTH_SHORT).show();
                }
                DataClass.print("points in pointsActivity is: " + points);

            }
            editor.putInt("points", points);
            editor.apply();
        }
        points_text2.setText(String.valueOf(points));
        points_progressBar.setProgress(points);
    }

    @Override
    protected void onRestart() {
        setPoints_widget();
        setWeight_widget();
        super.onRestart();
    }

    /**
     * All functionality of the water widget is set here;
     */
    private void setWater_widget(){
        water_widget = findViewById(R.id.water_widget);
        //gets the current date
        @SuppressLint("SimpleDateFormat") String date =
                new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //gets shared preferences to store and retrieve values
        sharedPreferences.getString(CURRENT_DATE,"0");
        water_plus_button = water_widget.findViewById(R.id.water_plus_button);
        water_minus_button = water_widget.findViewById(R.id.water_minus_button);
        water_text = water_widget.findViewById(R.id.water_text);
        water_text.setText(sharedPreferences.getString(WATER_COUNT, "0"));
        //compares the dates and resets water_text if different date
        if(!date.equals(sharedPreferences.getString(CURRENT_DATE, "0"))){
            editor.putString(CURRENT_DATE,date);
            water_text.setText("0");
            editor.putString(WATER_COUNT, String.valueOf(water_text.getText()));
            editor.apply();
        }
        water_plus_button.setOnClickListener(v -> {
            int water_glasses_int = Integer.parseInt(String.valueOf(water_text.getText()));
            int new_water = water_glasses_int+1;
            String new_water_text = "" + new_water;
           water_text.setText(new_water_text);
            editor.putString(WATER_COUNT, String.valueOf(water_text.getText()));
            editor.apply();
        });
        water_minus_button.setOnClickListener(v -> {
            if(water_text.getText().charAt(0)!='0'){
                int water_glasses_int = Integer.parseInt(String.valueOf(water_text.getText()));
                int new_water = water_glasses_int-1;
                String new_water_text = "" + new_water;
                water_text.setText(new_water_text);
                editor.putString(WATER_COUNT, String.valueOf(water_text.getText()));
                editor.apply();
            }
        });
    }

    /**
     * All functionality of the weight widget is set here;
     */
    private void setWeight_widget(){
        weight_widget = findViewById(R.id.weight_widget);
        weight_record_button = weight_widget.findViewById(R.id.weight_record_button);
        weight_widget_layout_weight_text = weight_widget.findViewById(R.id.weight_widget_layout_weight_text);
        weight_widget_layout_weight_text.setText(sharedPreferences.getString(WEIGHT_TEXT,""));
        //System.out.println("weight text before is "+ weight_widget_layout_weight_text.getText());
        weight_record_button.setOnClickListener(v->{
                if(!String.valueOf(weight_widget_layout_weight_text.getText()).equals(sharedPreferences.getString(WEIGHT_TEXT,""))){
                    weight_widget_layout_weight_text.clearFocus();
                    editor.putString(WEIGHT_TEXT, String.valueOf(weight_widget_layout_weight_text.getText()));
                    editor.apply();
                   // System.out.println("weight text is "+weight_widget_layout_weight_text.getText());
                    //System.out.println("in shared prefs is: " + sharedPreferences.getString(WEIGHT_TEXT,""));
                    Toast.makeText(HomeActivity.this,getString(R.string.recorded),Toast.LENGTH_LONG).show();
                }
                else{
                    weight_widget_layout_weight_text.clearFocus();
                }
                }
        );
    }

    /**
     * All functionality of the calories widget is set here;
     */
    private void setCalories_widget(){
        calories_widget = findViewById(R.id.calories_widget);
        calories_number = calories_widget.findViewById(R.id.calories_number);

        @SuppressLint("SimpleDateFormat") String date =
                new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        //gets shared preferences to store and retrieve values
        sharedPreferences.getString(CURRENT_DATE,"0");
        if(!date.equals(sharedPreferences.getString(CURRENT_DATE, "0"))){
            editor.putString(CURRENT_DATE,date);
            calories_number.setText("0");
            editor.putInt("calories",0);
            editor.apply();
        }
        int i = sharedPreferences.getInt("calories",0);
        calories_number.setText("" + i);
        calories_add_button = calories_widget.findViewById(R.id.calories_add_button);
        calories_add_button.setOnClickListener(v-> {
            calories_bottom_sheet_dialog = new BottomSheetDialog(this,R.style.BottomSheetTheme);
            View sheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.calories_bottom_sheet_dialog_layout,
                            findViewById(R.id.calories_bottom_sheet));
            add_calories_edit_text = sheetView.findViewById(R.id.add_calories_edit_text);
            sheetView.findViewById(R.id.add_calories_button).setOnClickListener(w->{
                int addingCalories;
                try{
                    addingCalories = Integer.parseInt(String.valueOf(add_calories_edit_text.getText()));
                    DataClass.print("addingCalories is: " + addingCalories);
                    Toast.makeText(getBaseContext(),getString(R.string.recorded),Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException e){
                    addingCalories = 0;
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(),getString(R.string.no_number_entered),Toast.LENGTH_SHORT).show();
                }
                int calories = addingCalories + sharedPreferences.getInt("calories",0);
                editor.putInt("calories",calories);
                editor.apply();
                calories_number.setText("" + calories);

            });
            sheetView.findViewById(R.id.minus_calories_button).setOnClickListener(w->{
                int subtractingCalories;
                try{
                    subtractingCalories = Integer.parseInt(String.valueOf(add_calories_edit_text.getText()));

                }
                catch (NumberFormatException e){
                    subtractingCalories = 0;
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(),getString(R.string.no_number_entered),Toast.LENGTH_SHORT).show();
                }
                int calories = sharedPreferences.getInt("calories",0) - subtractingCalories;
                if(calories >= 0){
                    Toast.makeText(getBaseContext(),getString(R.string.recorded),Toast.LENGTH_SHORT).show();
                    calories_number.setText("" + calories);
                    editor.putInt("calories",calories);
                    editor.apply();
                }
                else{
                    Toast.makeText(getBaseContext(),getString(R.string.cannnot_subtract),Toast.LENGTH_SHORT).show();// stringify
                }
            });
            calories_bottom_sheet_dialog.setContentView(sheetView);
            calories_bottom_sheet_dialog.show();
        });
    }

    /**
     * All functionality of the train button is set here;
     */
    private void setTrain_button(){
        train_button = findViewById(R.id.train_button);
        train_button.setOnClickListener(v -> {
            Intent homeIntent = new Intent(HomeActivity.this, TrainActivity.class);
            startActivity(homeIntent);
        });
    }

    /**
     * All functionality of the eat button is set here;
     */
    private void setEat_button(){
        eat_button = findViewById(R.id.eat_button);
        eat_button.setOnClickListener(v -> {
            Intent homeIntent = new Intent(HomeActivity.this, EatActivity.class);
            startActivity(homeIntent);
        });
    }

    /**
     * All functionality of the back button is set here;
     */
    @Override
    public void onBackPressed() {

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

    /**
     * All functionality of the navigation drawer is set here;
     */
    private void setNavigation_drawer(){
        //toolbar and navigation drawer
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar =  findViewById(R.id.toolbar);
        //getting header manually
        header = navigationView.inflateHeaderView(R.layout.header);
        account_text = header.findViewById(R.id.account_name);
        account_text.setText(sharedPreferences.getString("name",""));
        Runnable runnable1 = () -> {
            setSupportActionBar(toolbar);
            navigationView.bringToFront();
            ActionBarDrawerToggle toggle =
                    new ActionBarDrawerToggle(
                            HomeActivity.this,
                            drawerLayout,toolbar,
                            R.string.navigation_drawer_open,
                            R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(HomeActivity.this);
        };
        //using different thread
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help_menu_item:
                Intent helpIntent = new Intent(HomeActivity.this, HelpActivity.class);
                startActivity(helpIntent);
                finish();
                break;
            case R.id.edit_your_profile_item:
                Intent onboardingIntent = new Intent(HomeActivity.this, OnboardingActivity.class);
                startActivity(onboardingIntent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
