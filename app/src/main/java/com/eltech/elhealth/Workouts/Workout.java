package com.eltech.elhealth.Workouts;

import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public abstract class Workout implements Parcelable {
    public abstract int getTitle();
    public abstract String getReps(int level);
    public abstract boolean hasTimer();
    public abstract int getTimer(int timer,int level);
    public abstract @DrawableRes int getImage();

}
