package com.example.myapplication.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.myapplication.CourseDetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.CourseModel;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    private Context context;
    private List<CourseModel> courseList;

    public CourseAdapter(Context context, List<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Glide.with(context).load(courseList.get(position).getImage()).into(holder.courseImage);
        holder.courseTitle.setText(courseList.get(position).getTitle());
        holder.courseDesc.setText(courseList.get(position).getDesc());
        holder.courseTopic.setText(courseList.get(position).getTopic());
        holder.courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("Image", courseList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Description", courseList.get(holder.getAdapterPosition()).getDesc());
                intent.putExtra("Title", courseList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Topic", courseList.get(holder.getAdapterPosition()).getTopic());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    };
}

class CourseViewHolder extends ViewHolder{
    ImageView courseImage;
    TextView courseTitle, courseTopic, courseDesc;
    CardView courseCard;

    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);
        courseImage = itemView.findViewById(R.id.courseImage);
        courseTitle = itemView.findViewById(R.id.courseTitle);
        courseTopic = itemView.findViewById(R.id.courseTopic);
        courseDesc = itemView.findViewById(R.id.courseDesc);
        courseCard = itemView.findViewById(R.id.courseCard);

    }
}
