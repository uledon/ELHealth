package com.eltech.elhealth.Workouts;

public class WorkoutsClass {
    public static Workout[] getLoseFatWorkouts(){
        return new Workout[]{
                new backward_lunge(),
                new burpees(),
                new crunches_with_legs_raised(),
                new flutter_kicks(),
                new heel_touches(),
                new high_stepping(),
                new inchworms(),
                new jumping_jacks(),
                new long_arm_crunches(),
                new lunges(),
                new mountain_climber(),
                new plank_jacks(),
                new push_ups(),
                new reclined_oblique_twist(),
                new reverse_crunches(),
                new scissors(),
                new skipping_without_rope(),
                new squats(),
                new standing_bicycle_crunches(),
                new tricept_dips()
        };
    }
    public static Workout[] getBuildMuscleWorkouts(){
        return new Workout[]{
                new cooldown(),
                new push_ups()
        };
    }
    public static Workout[] getImproveEnduranceWorkouts(){
        return new Workout[]{
                new cooldown()
        };

    }
    public static Workout[] getMaintainBodyShapeWorkouts(){
        return new Workout[]{
                new cooldown()
        };

    }
    public static Workout[] getImproveAthleticSkillsWorkouts(){
        return new Workout[]{
                new cooldown()
        };

    }
}
