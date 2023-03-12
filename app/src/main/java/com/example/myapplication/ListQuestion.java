package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.adapter.QuestionAdapter;
import com.example.myapplication.model.CourseModel;
import com.example.myapplication.model.QuestionModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListQuestion extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView ListQuestion;
    List<QuestionModel> listQuestion;
    QuestionAdapter questionAdapter;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListQuestion.this, 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(ListQuestion.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void InitView(){
        fab = findViewById(R.id.fab);
        ListQuestion = findViewById(R.id.listQuestion);
    }
}