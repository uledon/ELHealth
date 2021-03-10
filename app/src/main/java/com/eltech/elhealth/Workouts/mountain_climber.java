package com.eltech.elhealth.Workouts;

import android.os.Parcel;
import android.os.Parcelable;

import com.eltech.elhealth.R;

public class mountain_climber extends Workout implements Parcelable {
    public mountain_climber(){

    }
    protected mountain_climber(Parcel in) {
    }

    public static final Creator<mountain_climber> CREATOR = new Creator<mountain_climber>() {
        @Override
        public mountain_climber createFromParcel(Parcel in) {
            return new mountain_climber(in);
        }

        @Override
        public mountain_climber[] newArray(int size) {
            return new mountain_climber[size];
        }
    };

    @Override
    public int getTitle() {
        return R.string.mountain_climber;
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
    public int getTimer(int timer, int level) {
        return 0;
    }

    @Override
    public int getImage() {
        return R.drawable.health_logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
