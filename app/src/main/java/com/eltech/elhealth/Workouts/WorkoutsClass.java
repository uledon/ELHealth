package com.eltech.elhealth.Workouts;

import java.util.ArrayList;
import java.util.Arrays;

public class WorkoutsClass {
    public static ArrayList<Workout> getLoseFatWorkouts(){
        ArrayList<Workout> getLoseFatWorkouts = new ArrayList<>();
        getLoseFatWorkouts.addAll(Arrays.asList(
                new backward_lunge(),//
                new burpees(),
                new crunches_with_legs_raised(),
                new flutter_kicks(),
                new heel_touches(),
                new high_stepping(),//
                new inchworms(),
                new jumping_jacks(),//
                new long_arm_crunches(),
                new lunges(),//
                new mountain_climber(),
                new plank_jacks(),//
                new push_ups(),
                new reclined_oblique_twist(),
                new reverse_crunches(),
                new scissors(),
                new skipping_without_rope(),//
                new squats(),//
                new standing_bicycle_crunches(),//12 without knee || weights true
                new tricept_dips())); //20 exercises
        return getLoseFatWorkouts;
    }
    public static ArrayList<Workout> getBuildMuscleWorkouts(){
        ArrayList<Workout> getBuildMuscleWorkouts = new ArrayList<>();
        getBuildMuscleWorkouts.addAll(Arrays.asList(
                new push_ups(),
                new bench_press(), //
                new lat_pull_down(), //
                new knee_push_ups(),
                new tricept_dips(),
                new squats(),//
                new bicept_curls(),//
                new burpees(),
                new pull_up(), //
                new inchworms(),
                new standing_bicycle_crunches(),
                new crunches_with_legs_raised(),
                new long_arm_crunches(),
                new mountain_climber(),
                new lunges(), // 9 with knee || weights true
                new weighted_lunges())); // 15 exercises
        return getBuildMuscleWorkouts;
    }
    public static ArrayList<Workout> getImproveEnduranceWorkouts(){
        ArrayList<Workout> getImproveEnduranceWorkouts= new ArrayList<>();
        getImproveEnduranceWorkouts.addAll(Arrays.asList(
                new plank(),
                new squats(),  //
                new lunges(), //
                new push_ups(),
                new situps(),
                new knee_push_ups(),
                new mountain_climber(),
                new crunches_with_legs_raised(),//6 with knee || weights true
                new high_stepping())); //9
        return getImproveEnduranceWorkouts;
    }
    public static ArrayList<Workout> getMaintainBodyShapeWorkouts(){
        ArrayList<Workout> getMaintainBodyShapeWorkouts = new ArrayList<>();
        getMaintainBodyShapeWorkouts.addAll(Arrays.asList(
                new push_ups(),
                new burpees(),
                new squats(),
                new situps(),
                new plank(),
                new lunges(),
                new mountain_climber(),
                new squat_jumps(), //7 without knee || weights true
                new inchworms())); //9
        return getMaintainBodyShapeWorkouts;
    }
    public static ArrayList<Workout> getImproveAthleticSkillsWorkouts(){
        ArrayList<Workout> getLoseFatWorkouts = new ArrayList<>();
        getLoseFatWorkouts.add(new run());
        return getLoseFatWorkouts;
    }
}
