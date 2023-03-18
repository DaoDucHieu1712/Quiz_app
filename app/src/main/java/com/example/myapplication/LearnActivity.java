package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LearnActivity extends AppCompatActivity {
//    private TextView quizTime;
//
//    private RelativeLayout option1Layout, option2Layout, option3Layout, option4Layout;
//    private TextView option1TV, option2TV, option3TV, option4TV;
//    private ImageView option1Icon, option2Icon, option3Icon, option4Icon;
//
//    private TextView totalQuestionTV;
//    private TextView currentQuestion;
//
//    private DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

//        quizTime = findViewById(R.id.quizTimer);
//
//        option1Layout = findViewById(R.id.option1Layout);
//        option2Layout = findViewById(R.id.option2Layout);
//        option3Layout = findViewById(R.id.option3Layout);
//        option4Layout = findViewById(R.id.option4Layout);
//
//        option1TV = findViewById(R.id.option1TV);
//        option2TV = findViewById(R.id.option2TV);
//        option3TV = findViewById(R.id.option3TV);
//        option4TV = findViewById(R.id.option4TV);
//
//        option1Icon = findViewById(R.id.option1Icon);
//        option2Icon = findViewById(R.id.option2Icon);
//        option3Icon = findViewById(R.id.option3Icon);
//        option4Icon = findViewById(R.id.option4Icon);
//
//        totalQuestionTV = findViewById(R.id.totalQuestionTV);
//        currentQuestion = findViewById(R.id.currentQuestion);
//
//        final AppCompatButton nextBtn = findViewById(R.id.NextQuestion);
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot question : snapshot.child("questions").getChildren()){
//                    String getQuestion = question.child("title").getValue(String.class);
//                    String getOption1 = question.child("option1").getValue(String.class);
//                    String getOption2 = question.child("option2").getValue(String.class);
//                    String getOption3 = question.child("option3").getValue(String.class);
//                    String getOption4 = question.child("option4").getValue(String.class);
//                    int getAnswer = Integer.parseInt(question.child("solution").getValue(String.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}