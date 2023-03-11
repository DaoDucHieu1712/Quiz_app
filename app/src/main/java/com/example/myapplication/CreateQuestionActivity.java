package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.model.CourseModel;
import com.example.myapplication.model.QuestionModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateQuestionActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mOption1EditText;
    private EditText mOption2EditText;
    private EditText mOption3EditText;
    private EditText mOption4EditText;
    private RadioButton mSolution1RadioButton;
    private RadioButton mSolution2RadioButton;
    private RadioButton mSolution3RadioButton;
    private RadioButton mSolution4RadioButton;
    private Spinner mCourseSpinner;
    private Button mSaveButton;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        // Khởi tạo database reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ImageView img_back = findViewById(R.id.img_back);

        mTitleEditText = findViewById(R.id.edt_titlequiz);
        mOption1EditText = findViewById(R.id.op1);
        mOption2EditText = findViewById(R.id.op2);
        mOption3EditText = findViewById(R.id.op3);
        mOption4EditText = findViewById(R.id.op4);

        mSolution1RadioButton = findViewById(R.id.solution1);
        mSolution2RadioButton = findViewById(R.id.solution2);
        mSolution3RadioButton = findViewById(R.id.solution3);
        mSolution4RadioButton = findViewById(R.id.solution4);
        mCourseSpinner = findViewById(R.id.course_spinner);
        mSaveButton = findViewById(R.id.btn_save);

        // Đặt adapter cho Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.course,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCourseSpinner.setAdapter(adapter);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestion();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trở về màn hình trước đó
                finish();
            }
        });
    }

    private void createQuestion() {
        String title = mTitleEditText.getText().toString();
        String option1 = mOption1EditText.getText().toString();
        String option2 = mOption2EditText.getText().toString();
        String option3 = mOption3EditText.getText().toString();
        String option4 = mOption4EditText.getText().toString();
//        String solution = "";
//        if (mSolution1RadioButton.isChecked()) {
//            solution = option1;
//        } else if (mSolution2RadioButton.isChecked()) {
//            solution = option2;
//        } else if (mSolution3RadioButton.isChecked()) {
//            solution = option3;
//        } else if (mSolution4RadioButton.isChecked()) {
//            solution = option4;
//        }
        int solution = -1;
        if (mSolution1RadioButton.isChecked()) {
            solution = 1;
        } else if (mSolution2RadioButton.isChecked()) {
            solution = 2;
        } else if (mSolution3RadioButton.isChecked()) {
            solution = 3;
        } else if (mSolution4RadioButton.isChecked()) {
            solution = 4;
        }
        String course = mCourseSpinner.getSelectedItem().toString();
        QuestionModel question = new QuestionModel(title, option1, option2, option3, option4, solution, course);
        mDatabase.child("questions").push().setValue(question).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(CreateQuestionActivity.this, "Question created successfully", Toast.LENGTH_SHORT).show();
                clearForm();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateQuestionActivity.this, "Error creating question", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void clearForm() {
        mTitleEditText.setText("");
        mOption1EditText.setText("");
        mOption2EditText.setText("");
        mOption3EditText.setText("");
        mOption4EditText.setText("");
        mSolution1RadioButton.setChecked(true);
        mCourseSpinner.setSelection(0);
    }

}