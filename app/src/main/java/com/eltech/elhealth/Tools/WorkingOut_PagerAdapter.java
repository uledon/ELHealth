package com.eltech.elhealth.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.eltech.elhealth.R;
import com.eltech.elhealth.Workouts.Workout;

import java.util.ArrayList;

public class WorkingOut_PagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Workout> workouts;
    int level, timer;
    public WorkingOut_PagerAdapter(Context context, ArrayList<Workout> workouts, int level,int timer){
        this.context = context;
        this.workouts = workouts;
        this.level = level;
        this.timer = timer;
    }


    @Override
    public int getCount() {
        return workouts.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_working_out_slide_layout,container,false);
        ImageView slideImageView = view.findViewById(R.id.activity_working_out_slide_image);
        TextView slideHeading = view.findViewById(R.id.activity_working_out_slide_heading);
        TextView slideDesc = view.findViewById(R.id.activity_working_out_slide_desc);
        slideImageView.setImageResource(workouts.get(position).getImage());
        slideHeading.setText(workouts.get(position).getTitle());
        if(workouts.get(position).hasTimer()){
            slideDesc.setText("" + workouts.get(position).getTimer(timer,level) + " sec.");
        }
        else{
            slideDesc.setText(workouts.get(position).getReps(level));
        }
        container.addView(view);
        return view;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }
}
