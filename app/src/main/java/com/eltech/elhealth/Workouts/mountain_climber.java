package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import com.eltech.elhealth.R;

public class mountain_climber extends Workout{
    public mountain_climber(){

    }
    @Override
    public int getTitle() {
        return R.string.mountain_climber;
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
