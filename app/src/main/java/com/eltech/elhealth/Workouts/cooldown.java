package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import com.eltech.elhealth.R;

public class cooldown extends Workout implements Parcelable {
    public cooldown (){

    }
    protected cooldown(Parcel in) {
    }

    public static final Creator<cooldown> CREATOR = new Creator<cooldown>() {
        @Override
        public cooldown createFromParcel(Parcel in) {
            return new cooldown(in);
        }

        @Override
        public cooldown[] newArray(int size) {
            return new cooldown[size];
        }
    };

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
    public int getTimer(int timer, int level) {
        return timer;
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
