package com.eltech.elhealth.Workouts;

import androidx.annotation.DrawableRes;

import com.eltech.elhealth.R;

public class standing_bicycle_crunches extends Workout {
    @Override
    public int getTitle() {
        return R.string.standing_bicycle_crunches;
    }

    @Override
    public String getReps(int level) {
        return null;
    }

    @Override
    public boolean hasTimer() {
        return true;
    }

    @Override
    public long getTimer(long timer, int level) {
        if (level <= 3){
            return 20;
        }
        else if(level > 3 && level <= 6 ){
            return 25;
        }
        else if(level > 6){
            return 30;
        }
        else{
            return 40;
        }
    }

    @Override
    public @DrawableRes int getImage() {
        return R.drawable.standing_bicycle_crunches_icon;
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
