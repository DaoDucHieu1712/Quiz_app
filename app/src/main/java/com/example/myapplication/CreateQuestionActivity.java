package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Button btn_save;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        // Khởi tạo database reference
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        String courseId = getIntent().getExtras().getString("courseId");

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
        btn_save = findViewById(R.id.btn_save);

        DatabaseReference coursesRef = FirebaseDatabase.getInstance().getReference("Courses");
        Query userCoursesQuery = coursesRef.orderByChild("idUser").equalTo(userId);
        Log.d("Iduser đang ở đây là", userId);
        userCoursesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> courseNames = new ArrayList<>();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    String courseName = courseSnapshot.child("topic").getValue(String.class);
                    courseNames.add(courseName);
                }
                // Populate the dropdown list with the course names
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CreateQuestionActivity.this, android.R.layout.simple_spinner_item, courseNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mCourseSpinner.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error retrieving user courses", databaseError.toException());
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()){
                    createQuestion(courseId);
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {   // Back before screen
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createQuestion(String courseId) {
        String title = mTitleEditText.getText().toString();
        String option1 = mOption1EditText.getText().toString();
        String option2 = mOption2EditText.getText().toString();
        String option3 = mOption3EditText.getText().toString();
        String option4 = mOption4EditText.getText().toString();

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

//        DatabaseReference courses = mDatabase.child("Courses").child(courseId);
//        Map<String, QuestionModel> childUpdates = new HashMap<>();
//        childUpdates.put("questions", question);
//        courses.push().setValue(childUpdates);

        mDatabase.child("Courses").child(courseId).child("questions").push().setValue(question).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public boolean validateInputs() {    //check input Create Form
        EditText edtTitleQuiz = findViewById(R.id.edt_titlequiz);
        EditText op1 = findViewById(R.id.op1);
        EditText op2 = findViewById(R.id.op2);
        EditText op3 = findViewById(R.id.op3);
        EditText op4 = findViewById(R.id.op4);
        RadioButton solution1 = findViewById(R.id.solution1);
        RadioButton solution2 = findViewById(R.id.solution2);
        RadioButton solution3 = findViewById(R.id.solution3);
        RadioButton solution4 = findViewById(R.id.solution4);
        Spinner courseSpinner = findViewById(R.id.course_spinner);

        if (edtTitleQuiz.getText().toString().isEmpty()) {
            edtTitleQuiz.setError("Please enter your question");
            return false;
        }

        if (op1.getText().toString().isEmpty()) {
            op1.setError("Please enter option 1");
            return false;
        }
        if (op2.getText().toString().isEmpty()) {
            op2.setError("Please enter option 2");
            return false;
        }
        if (op3.getText().toString().isEmpty()) {
            op3.setError("Please enter option 3");
            return false;
        }
        if (op4.getText().toString().isEmpty()) {
            op4.setError("Please enter option 4");
            return false;
        }

        if (!solution1.isChecked() && !solution2.isChecked() && !solution3.isChecked() && !solution4.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please select correct answer", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (courseSpinner.getSelectedItem().toString().equals("Select Course")) {
            Toast.makeText(getApplicationContext(), "Please select course", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void clearForm() {   //clear all column of InputForm for next work
        mTitleEditText.setText("");
        mOption1EditText.setText("");
        mOption2EditText.setText("");
        mOption3EditText.setText("");
        mOption4EditText.setText("");
        mSolution2RadioButton.setChecked(false);
        mSolution3RadioButton.setChecked(false);
        mSolution4RadioButton.setChecked(false);
        mSolution1RadioButton.setChecked(false);
        mCourseSpinner.setSelection(0);
    }

}