package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionDetailActivity extends AppCompatActivity {

    TextView quizTilte, op1, op2, op3, op4 , solution;
    FloatingActionButton deleteButton, editButton, listButton;
    String courseId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        quizTilte = findViewById(R.id.detail_quizTitle);
        op1 = findViewById(R.id.detail_op1);
        op2 = findViewById(R.id.detail_op2);
        op3 = findViewById(R.id.detail_op3);
        op4 = findViewById(R.id.detail_op4);
        solution = findViewById(R.id.detail_solution);
        deleteButton = findViewById(R.id.deleteQuestion);
        editButton = findViewById(R.id.updateQuestion);
        listButton = findViewById(R.id.back_list);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            quizTilte.setText(bundle.getString("quizTitle"));
            op1.setText(bundle.getString("op1"));
            op2.setText(bundle.getString("op2"));
            op3.setText(bundle.getString("op3"));
            op4.setText(bundle.getString("op4"));
            solution.setText(bundle.getString("solution"));
            courseId = bundle.getString("courseId");
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Courses").child(courseId).child("questions");
                reference.child(bundle.getString("key")).removeValue();
                Toast.makeText(QuestionDetailActivity.this, "Delete successful !!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ListQuestion.class));
                finish();
            }
        });

       editButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(QuestionDetailActivity.this, UpdateQuestionActivity.class)
                       .putExtra("quiztitle", quizTilte.getText().toString())
                       .putExtra("op1", op1.getText().toString())
                       .putExtra("op2", op2.getText().toString())
                       .putExtra("op3", op3.getText().toString())
                       .putExtra("op4", op4.getText().toString())
                       .putExtra("solution", solution.getText().toString())
                       .putExtra("key", bundle.getString("key"))
                       .putExtra("courseId", bundle.getString("courseId"));
               startActivity(intent);
           }
       });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionDetailActivity.this, ListQuestion.class)
                        .putExtra("courseId", courseId);
                startActivity(intent);
            }
        });

    }
}