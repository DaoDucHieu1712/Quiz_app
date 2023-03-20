package com.example.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CourseDetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.CourseModel;
import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<CourseModel> list;
    public AdapterClass(ArrayList<CourseModel> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.topic.setText(list.get(position).getTopic());
        holder.desc.setText(list.get(position).getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseModel courseModel = list.get(holder.getAdapterPosition());
                Intent intent = new Intent(v.getContext(), CourseDetailActivity.class);
                intent.putExtra("my_object", (Serializable) courseModel);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView topic,  desc;
        public MyViewHolder(@NotNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
