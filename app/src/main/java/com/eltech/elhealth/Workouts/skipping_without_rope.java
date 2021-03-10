package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import com.eltech.elhealth.R;

public class skipping_without_rope extends Workout implements Parcelable {

    public skipping_without_rope  (){

    }

    protected skipping_without_rope(Parcel in) {
    }

    public static final Creator<skipping_without_rope> CREATOR = new Creator<skipping_without_rope>() {
        @Override
        public skipping_without_rope createFromParcel(Parcel in) {
            return new skipping_without_rope(in);
        }

        @Override
        public skipping_without_rope[] newArray(int size) {
            return new skipping_without_rope[size];
        }
    };

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
        if (level == 1){
            return 20;
        }
        else if(level == 2){
            return 25;
        }
        else if(level == 3){
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
