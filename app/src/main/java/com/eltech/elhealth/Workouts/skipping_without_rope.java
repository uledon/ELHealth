package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import com.eltech.elhealth.R;

public class skipping_without_rope extends Workout{

    public skipping_without_rope  (){
    }
    @Override
    public int getTitle() {
        return R.string.skipping_without_rope;
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
    public int getTimer(int timer, int level) {

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
