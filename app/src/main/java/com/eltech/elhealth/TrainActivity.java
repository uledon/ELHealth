package com.eltech.elhealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.eltech.elhealth.Tools.TrainAdapter;
import com.eltech.elhealth.Workouts.Workout;
import com.eltech.elhealth.Workouts.cooldown;
import com.eltech.elhealth.Workouts.lunges;
import com.eltech.elhealth.Workouts.mountain_climber;
import com.eltech.elhealth.Workouts.skipping_without_rope;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button activity_train_start_button;
    public ArrayList<Workout> cardio_workouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        recyclerView = findViewById(R.id.activity_train_recyclerView);
        activity_train_start_button = findViewById(R.id.activity_train_start_button);
        cardio_workouts = new ArrayList<>();
        for(int i=0;i<3;i++){
            cardio_workouts.add(new mountain_climber());
            cardio_workouts.add(new cooldown());
            cardio_workouts.add(new lunges());
            cardio_workouts.add(new cooldown());
            cardio_workouts.add(new skipping_without_rope());
            cardio_workouts.add(new cooldown());
        }

        //pass this to next activity
        TrainAdapter adapter = new TrainAdapter(this, cardio_workouts, 2,10); // change this later when user input is required.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        activity_train_start_button.setOnClickListener( v->{
                    Intent intent = new Intent(TrainActivity.this, WorkingOutActivity.class);
                    System.out.println("cardio_workouts size is: " + cardio_workouts.size());
                    intent.putExtra("CardioWorkouts",cardio_workouts);
                    startActivity(intent);
                    finish();
        });
    }
}
