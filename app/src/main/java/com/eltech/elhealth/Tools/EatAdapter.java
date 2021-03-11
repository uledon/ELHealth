package com.eltech.elhealth.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eltech.elhealth.Foods.Foods;
import com.eltech.elhealth.R;
import com.eltech.elhealth.Workouts.Workout;

import java.util.ArrayList;
import java.util.Objects;

public class EatAdapter extends RecyclerView.Adapter<EatAdapter.ItemsViewHolder> {
    String[] items;
    Context context;
    public EatAdapter(Context context,String[]items){
        this.context = context;
        this.items= items;
    }
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_box,parent,false);
        EatAdapter.ItemsViewHolder holder = new EatAdapter.ItemsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EatAdapter.ItemsViewHolder holder, int position) {
        if(!Objects.equals(items[position],"")){
            holder.workout_item_title.setText(items[position]);
        }
    }

    @Override
    public int getItemCount() {
            return items.length;
    }
    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        TextView workout_item_title;
        public ItemsViewHolder(@NonNull View itemView){
            super(itemView);
            workout_item_title = itemView.findViewById(R.id.workout_item_title);
        }
    }
}
