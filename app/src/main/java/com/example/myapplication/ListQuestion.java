package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.adapter.QuestionAdapter;
import com.example.myapplication.model.CourseModel;
import com.example.myapplication.model.QuestionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListQuestion extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView listQuestionRv;
    List<QuestionModel> listQuestion;
    QuestionAdapter questionAdapter;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);
        key = getIntent().getExtras().getString("courseId");


        fab = findViewById(R.id.fab);
        listQuestionRv = findViewById(R.id.listQuestion);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListQuestion.this, 1);
        listQuestionRv.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(ListQuestion.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        listQuestion = new ArrayList<>();

        questionAdapter = new QuestionAdapter(ListQuestion.this, listQuestion);
        databaseReference = FirebaseDatabase.getInstance().getReference("Courses").child(key).child("questions");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listQuestion.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    QuestionModel questionModel = itemSnapshot.getValue(QuestionModel.class);
                    questionModel.setKey(itemSnapshot.getKey());
                    listQuestion.add(questionModel);
                    System.out.println(itemSnapshot.getKey());
                }
                questionAdapter.notifyDataSetChanged();
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
                Intent intent = new Intent(ListQuestion.this, CreateQuestionActivity.class)
                        .putExtra("courseId", key);
                startActivity(intent);
            }
        });

    }


}