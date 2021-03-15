package com.eltech.elhealth.Workouts;

public class WorkoutsClass {
    public static Workout[] getLoseFatWorkouts(){
        Workout[] workouts = new Workout[]{
//                new mountain_climber(),
//                new lunges(),
//                new lunges(),
                new mountain_climber(),
                new skipping_without_rope(),
                new mountain_climber(),
                new skipping_without_rope(),
                new mountain_climber(),
                new mountain_climber()

        };
        return workouts;
    }
}
