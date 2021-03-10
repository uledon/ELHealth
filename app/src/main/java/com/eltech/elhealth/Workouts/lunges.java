package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import com.eltech.elhealth.R;

public class lunges extends Workout implements Parcelable {
    public lunges() {
    }
    protected lunges(Parcel in) {
    }
    public static final Creator<lunges> CREATOR = new Creator<lunges>() {
        @Override
        public lunges createFromParcel(Parcel in) {
            return new lunges(in);
        }

        @Override
        public lunges[] newArray(int size) {
            return new lunges[size];
        }
    };

    @Override
    public int getTitle() {
        return R.string.lunges;
    }

    @Override
    public String getReps(int level) {
        if (level == 1){
            return "x 10";
        }
        else if(level == 2){
            return "x 15";
        }
        else if(level == 3){
            return "x20";
        }
        else{
            return "x 25";
        }
    }

    @Override
    public boolean hasTimer() {
        return false;
    }

    @Override
    public int getTimer(int timer,int level) {
        return 0;
    }

    @Override
    public int getImage() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
