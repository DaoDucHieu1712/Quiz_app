package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.QuestionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LearnActivity extends AppCompatActivity {
    private TextView quizTime;

    private RelativeLayout option1Layout, option2Layout, option3Layout, option4Layout;
    private TextView option1TV, option2TV, option3TV, option4TV;
    private ImageView option1Icon, option2Icon, option3Icon, option4Icon;

    private TextView totalQuestionTV;
    private TextView currentQuestion;
    private TextView questionTV;

//    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private final List<QuestionModel> questionModelList = new ArrayList<>();

    private CountDownTimer countDownTimer;
    private int currentQuestionPosition = 0;
    private int selectOption = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        String courseId = getIntent().getExtras().getString("courseId");
//

        quizTime = findViewById(R.id.quizTimer);

        option1Layout = findViewById(R.id.option1Layout);
        option2Layout = findViewById(R.id.option2Layout);
        option3Layout = findViewById(R.id.option3Layout);
        option4Layout = findViewById(R.id.option4Layout);

        option1TV = findViewById(R.id.option1TV);
        option2TV = findViewById(R.id.option2TV);
        option3TV = findViewById(R.id.option3TV);
        option4TV = findViewById(R.id.option4TV);

        option1Icon = findViewById(R.id.option1Icon);
        option2Icon = findViewById(R.id.option2Icon);
        option3Icon = findViewById(R.id.option3Icon);
        option4Icon = findViewById(R.id.option4Icon);

        totalQuestionTV = findViewById(R.id.totalQuestionTV);
        currentQuestion = findViewById(R.id.currentQuestion);
        questionTV = findViewById(R.id.questionTV);

        final AppCompatButton nextBtn = findViewById(R.id.NextQuestion);

        //show intructions Dialog
        IntructionsDialog intructionsDialog = new IntructionsDialog(LearnActivity.this);
        intructionsDialog.setCancelable(false);
        intructionsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        intructionsDialog.show();
      // replace with the ID of the course you want to retrieve questions for
        DatabaseReference questionsRef = FirebaseDatabase.getInstance().getReference("Courses").child(courseId).child("questions");

        questionsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<QuestionModel> questionList = new ArrayList<>();

//                final int QuizTime = Integer.parseInt(dataSnapshot.child("time").getValue(String.class));
                final int QuizTime = 120;

                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
//                    QuestionModel question = questionSnapshot.getValue(QuestionModel.class);
                    String getQuestion = questionSnapshot.child("title").getValue(String.class);
                    String getOption1 = questionSnapshot.child("option1").getValue(String.class);
                    String getOption2 = questionSnapshot.child("option2").getValue(String.class);
                    String getOption3 = questionSnapshot.child("option3").getValue(String.class);
                    String getOption4 = questionSnapshot.child("option4").getValue(String.class);
                    int getAnswer =  questionSnapshot.child("solution").getValue(Integer.class);

                    QuestionModel questionModel = new QuestionModel(getQuestion, getOption1, getOption2, getOption3, getOption4, getAnswer);
                    if (questionModel != null) {
                        questionModelList.add(questionModel);
                    }
                }
                for (int i = 0; i < questionModelList.size(); i++) {
                    QuestionModel question = questionModelList.get(i);
                    System.out.println("Question " + (i + 1) + ": " + question.getTitle());
                    System.out.println("Option 1: " + question.getOption1());
                    System.out.println("Option 2: " + question.getOption2());
                    System.out.println("Option 3: " + question.getOption3());
                    System.out.println("Option 4: " + question.getOption4());
                    System.out.println("Answer: " + question.getSolution());
                }

                currentQuestion.setText("Question" +String.valueOf(currentQuestionPosition + 1));
                questionTV.setText(questionModelList.get(currentQuestionPosition).getTitle());

                // set the options text and icons for the current question
                option1TV.setText(questionModelList.get(currentQuestionPosition).getOption1());
                option2TV.setText(questionModelList.get(currentQuestionPosition).getOption2());
                option3TV.setText(questionModelList.get(currentQuestionPosition).getOption3());
                option4TV.setText(questionModelList.get(currentQuestionPosition).getOption4());

                // use the questionList as needed

                //setting total Question to TextView
              totalQuestionTV.setText("/" + questionModelList.size());
               // start quiz with time max
                startQuizTime(QuizTime);
//                //select first question by default if the list is not empty
                if (!questionList.isEmpty()) {
                   selectQuestion(currentQuestionPosition);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // handle errors here
            }
        });

        option1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption = 1;
                selectOption(option1Layout, option1Icon);
            }
        });
        option2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption = 2;
                selectOption(option2Layout, option2Icon);
            }
        });
        option3Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption = 3;
                selectOption(option3Layout, option3Icon);
            }
        });
        option4Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption = 4;
                selectOption(option4Layout, option4Icon);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectOption != 0){
                    questionModelList.get(currentQuestionPosition).setUserSelectAnswer(selectOption);
                    selectOption = 0;
                    currentQuestionPosition++;
                    if(currentQuestionPosition < questionModelList.size()){
                        selectQuestion(currentQuestionPosition);
                    }else{
                        countDownTimer.cancel();
                        finishQuiz();
                    }
                }
                else{
                    Toast.makeText(LearnActivity.this, "Please select your option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void finishQuiz(){
        Intent intent = new Intent(LearnActivity.this, QuizResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("question", (Serializable) questionModelList);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void startQuizTime(int maxTimeInSeconds){
        countDownTimer = new CountDownTimer(maxTimeInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long getHour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                long getMinute = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                long getSecond = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);

                String generateTime = String.format(Locale.getDefault(), "%02d:02d:02d",
                        getHour,
                        getMinute - TimeUnit.HOURS.toMinutes(getHour),
                        getSecond - TimeUnit.MINUTES.toSeconds(getMinute));
                quizTime.setText(generateTime);
            }
            @Override
            public void onFinish() {
                finishQuiz();
            }
        };
        countDownTimer.start();
    }

    private void selectQuestion(int position) {
        resetOption();
        QuestionModel questionModel = questionModelList.get(position);
        currentQuestionPosition = position;

        questionTV.setText(questionModel.getTitle());
        option1TV.setText(questionModel.getOption1());
        option2TV.setText(questionModel.getOption2());
        option3TV.setText(questionModel.getOption3());
        option4TV.setText(questionModel.getOption4());

        currentQuestion.setText("Question" +String.valueOf(currentQuestionPosition + 1));
    }

//    private void selectQuestion(int questionListPostion){
//        resetOption();
//        if (questionModelList.size() > questionListPostion) {
//            questionTV.setText(questionModelList.get(questionListPostion).getTitle());
//            option1TV.setText(questionModelList.get(questionListPostion).getOption1());
//            option2TV.setText(questionModelList.get(questionListPostion).getOption2());
//            option3TV.setText(questionModelList.get(questionListPostion).getOption3());
//            option4TV.setText(questionModelList.get(questionListPostion).getOption4());
//
//            currentQuestion.setText("Question" + (questionListPostion + 1));
//        }
//    }

    private void resetOption(){
        option1Icon.setImageResource(R.drawable.round_back_white50_100);
        option2Icon.setImageResource(R.drawable.round_back_white50_100);
        option3Icon.setImageResource(R.drawable.round_back_white50_100);
        option4Icon.setImageResource(R.drawable.round_back_white50_100);
    }

    private void selectOption(RelativeLayout selectedOptionLayout, ImageView selectedOptionIcon){
        resetOption();
        selectedOptionIcon.setImageResource(R.drawable.check_icon);
        selectedOptionLayout.setBackgroundResource(R.drawable.round_back_selected_option);
    }
}