package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.model.CourseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListCourseActivity extends AppCompatActivity {
FloatingActionButton fab;
RecyclerView courseListRV;
List<CourseModel> listCourse;
DatabaseReference databaseReference;
ValueEventListener eventListener;
CourseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

        fab = findViewById(R.id.fab);
        courseListRV = findViewById(R.id.listCourse);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListCourseActivity.this, 1);
        courseListRV.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(ListCourseActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        listCourse = new ArrayList<>();

        adapter = new CourseAdapter(ListCourseActivity.this, listCourse);
        courseListRV.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Courses");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCourse.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    CourseModel courseModel = itemSnapshot.getValue(CourseModel.class);
                    courseModel.setKey(itemSnapshot.getKey());
                    listCourse.add(courseModel);
                    System.out.println(itemSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListCourseActivity.this, CreateCourseActivity.class);
                startActivity(intent);
            }
        });
    }
}