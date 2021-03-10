package com.eltech.elhealth.Tools;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eltech.elhealth.R;

public class slide_layout extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public slide_layout(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.health_logo,
            R.drawable.food_logo,
            R.drawable.yoga_logo
    };

    public int[] slide_headings={
            R.string.workout,
            R.string.eat,
            R.string.yoga
    };

    public int[] slide_description = {
            R.string.workout_desc,
            R.string.eat_desc,
            R.string.yoga_desc
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_help_slide_layout,container,false);
        ImageView slideImageView = view.findViewById(R.id.slide_image);
        TextView slideHeading = view.findViewById(R.id.slide_heading);
        slideHeading.setAllCaps(true);
        TextView slideDesc = view.findViewById(R.id.slide_desc);
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_description[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }


}
