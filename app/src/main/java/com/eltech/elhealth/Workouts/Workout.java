package com.eltech.elhealth.Workouts;

import androidx.annotation.DrawableRes;

public abstract class Workout{
    public abstract int getTitle();
    public abstract String getReps(int level);
    public abstract boolean hasTimer();
    public abstract long getTimer(long timer,int level);
    public abstract @DrawableRes int getImage();
    public abstract boolean affectsKnee();
    public abstract boolean requiresEquipments();
    public abstract boolean requiresWeights();
}
