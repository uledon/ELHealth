package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

import com.eltech.elhealth.R;

public class cooldown extends Workout {
    public cooldown (){

    }
    @Override
    public int getTitle() {
        return R.string.cooldown;
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
        return timer;
    }

    @Override
    public @DrawableRes
    int getImage() {
        return R.drawable.cooldown_icon;
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
