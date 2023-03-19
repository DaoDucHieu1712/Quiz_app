package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuizResultActivity extends AppCompatActivity {
    private List<QuestionModel> questionModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        final TextView scoreTV = findViewById(R.id.scoreTV);
        final TextView totalScoreTV = findViewById(R.id.totalScoreTV);
        final TextView correctTV = findViewById(R.id.correctTV);
        final TextView incorrectTV = findViewById(R.id.inCorrectTV);
        final AppCompatButton reTakeBtn = findViewById(R.id.reTakeQuizBtn);

        questionModelList = (List<QuestionModel>) getIntent().getSerializableExtra("question");
        totalScoreTV.setText("/" + questionModelList.size());
        scoreTV.setText(getCorrectAnswers() + "");
        correctTV.setText(getCorrectAnswers() + "");
        incorrectTV.setText(String.valueOf(questionModelList.size() - getCorrectAnswers()));

        reTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResultActivity.this, LearnActivity.class));
            }
        });
    }

    private int getCorrectAnswers(){
        int correctAnswer = 0;
        for(int i = 0; i < questionModelList.size(); i++){
            int getUserSelectedOption = questionModelList.get(i).getUserSelectAnswer();
            int getQuestionAnswer = questionModelList.get(i).getSolution();

            if(getQuestionAnswer == getUserSelectedOption){
                correctAnswer++;
            }
        }
        return correctAnswer;
    }
}