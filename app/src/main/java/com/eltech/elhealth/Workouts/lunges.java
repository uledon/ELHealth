package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

import com.eltech.elhealth.R;

public class lunges extends Workout {

    @Override
    public int getTitle() {
        return R.string.lunges;
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
    public @DrawableRes
    int getImage() {
        return R.drawable.lunges_icon;
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
