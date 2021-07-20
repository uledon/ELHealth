package com.eltech.elhealth.Workouts;

import com.eltech.elhealth.R;

public class cobra_stretch extends Workout {
    @Override
    public int getTitle() {
        return R.string.cobra_stretch;
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
            return 25;
        }
        else if(level > 3 && level <= 6 ){
            return 30;
        }
        else if(level > 6){
            return 35;
        }
        else{
            return 0;
        }
    }

    @Override
    public int getImage() {
        return R.drawable.cobra_stretch_icon;
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
