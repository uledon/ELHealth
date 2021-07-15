package com.eltech.elhealth.Workouts;

import com.eltech.elhealth.R;

public class backward_lunge extends Workout{
    @Override
    public int getTitle() {
        return R.string.backward_lunge;
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
            return "x20";
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
        return 0;
    }

    @Override
    public boolean affectsKnee() {
        return true;
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
