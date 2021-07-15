package com.eltech.elhealth.Workouts;

import androidx.annotation.DrawableRes;

import com.eltech.elhealth.R;

public class high_stepping extends Workout {
    @Override
    public int getTitle() {
        return R.string.high_stepping;
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
            return 30;
        }
        else if(level > 6){
            return 40;
        }
        else{
            return 0;
        }
    }

    @Override
    public @DrawableRes int getImage() {
        return R.drawable.high_stepping_icon;
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
