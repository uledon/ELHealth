package com.eltech.elhealth.Workouts;

import com.eltech.elhealth.R;

public class reverse_crunches extends Workout{
    @Override
    public int getTitle() {
        return R.string.reverse_crunches;
    }

    @Override
    public String getReps(int level) {
        if (level <= 3){
            return "x 10";
        }
        else if(level > 3 && level <= 6 ){
            return "x 15";
        }
        else if(level > 6){
            return "x 20";
        }
        else{
            return "not found";
        }
    }

    @Override
    public boolean hasTimer() {
        return false;
    }

    @Override
    public long getTimer(long timer, int level) {
        return 0;
    }

    @Override
    public int getImage() {
        return R.drawable.reverse_crunches_icon;
    }

    @Override
    public boolean affectsKnee() {
        return false;
    }

    @Override
    public boolean requiresEquipments() {
        return false;
    }

    @Override
    public boolean requiresWeights() {
        return false;
    }
}
