package com.eltech.elhealth.Tools;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eltech.elhealth.R;
import com.eltech.elhealth.Workouts.Workout;

import java.util.ArrayList;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ItemsViewHolder>{
    ArrayList<Workout> items;
    Context context;
    int timer, level;
    public TrainAdapter(Context context, ArrayList<Workout> items, int level,int timer){
        this.context = context;
        this.items = items;
        this.level = level;
        this.timer = timer;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_box,parent,false);
        ItemsViewHolder holder = new ItemsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Workout current = items.get(position);
        holder.workout_item_title.setText(current.getTitle());
        //holder.workout_reps_text.setText(current.getReps(level));
        if(current.hasTimer()){
            holder.workout_reps_text.setText("" + current.getTimer(timer,level) + " sec.");
        }
        else{
            holder.workout_reps_text.setText(current.getReps(level));
        }
        holder.workout_image.setImageResource(current.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        TextView workout_item_title, workout_reps_text;
        ImageView workout_image;
        public ItemsViewHolder(@NonNull View itemView){
            super(itemView);
            workout_item_title = itemView.findViewById(R.id.workout_item_title);
            workout_reps_text = itemView.findViewById(R.id.workout_reps_text);
            workout_image = itemView.findViewById(R.id.workout_image);
        }
    }
}
