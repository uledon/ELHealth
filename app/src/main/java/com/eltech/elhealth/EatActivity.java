package com.eltech.elhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;

import com.eltech.elhealth.Foods.Foods;
import com.eltech.elhealth.Tools.EatAdapter;
import com.eltech.elhealth.Tools.WebViewActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class EatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button find_out_more_button;
    String[] items;
    String[] loseFatFoods,buildMuscleFoods,improveEnduranceFoods,improveAthleticSkills;
    String website;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);
        recyclerView = findViewById(R.id.activity_eat_recyclerView);
        find_out_more_button = findViewById(R.id.find_out_more_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            items = new String[0];
        if(sharedPreferences.getBoolean("lose_fat_chip",false)){
            loseFatFoods = Foods.getLoseFatFoods();
            items = Stream.concat(Arrays.stream(items),Arrays.stream(loseFatFoods)).toArray(String[]::new);
        }
        if(sharedPreferences.getBoolean("build_muscle_chip",false)){
            buildMuscleFoods = Foods.getBuildMuscleFoods();
            items = Stream.concat(Arrays.stream(items),Arrays.stream(buildMuscleFoods)).toArray(String[]::new);
        }
        if(sharedPreferences.getBoolean("improve_endurance_chip",false)){
            improveEnduranceFoods = Foods.getImproveEnduranceFoods();
            items = Stream.concat(Arrays.stream(items),Arrays.stream(improveEnduranceFoods)).toArray(String[]::new);
        }
        if(sharedPreferences.getBoolean("improve_athletic_skills_chip",false)){
            improveAthleticSkills = Foods.getImproveAthleticSkills();
            items = Stream.concat(Arrays.stream(items),Arrays.stream(improveAthleticSkills)).toArray(String[]::new);
        }


        }
        find_out_more_button.setVisibility(View.GONE);
        EatAdapter eatAdapter = new EatAdapter(this, items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eatAdapter);
        find_out_more_button.setOnClickListener(v->{
            Intent myIntent = new Intent(v.getContext(), WebViewActivity.class);
            myIntent.putExtra("website",Foods.getBuildMuscleFoodsWebSite());
            startActivity(myIntent);
        });
    }
}