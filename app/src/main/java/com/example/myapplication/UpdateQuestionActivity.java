package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.myapplication.model.QuestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class UpdateQuestionActivity extends AppCompatActivity {

    EditText quiztitle, op1, op2, op3, op4;
    RadioButton so1, so2, so3, so4;
    Button btn_update;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String key = "";
    String courseId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

        quiztitle = findViewById(R.id.update_titlequiz);
        op1 = findViewById(R.id.update_op1);
        op2 = findViewById(R.id.update_op2);
        op3 = findViewById(R.id.update_op3);
        op4 = findViewById(R.id.update_op4);
        so1 = findViewById(R.id.update_solution1);
        so2 = findViewById(R.id.update_solution2);
        so3 = findViewById(R.id.update_solution3);
        so4 = findViewById(R.id.update_solution4);
        btn_update = findViewById(R.id.update_question);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            quiztitle.setText(bundle.getString("quiztitle"));
            op1.setText(bundle.getString("op1"));
            op2.setText(bundle.getString("op2"));
            op3.setText(bundle.getString("op3"));
            op4.setText(bundle.getString("op4"));
            String solution = bundle.getString("solution");
            key = bundle.getString("key");
            courseId = bundle.getString("courseId");
            switch (solution){
                case "1":
                    so1.setChecked(true);
                    break;
                case "2":
                    so2.setChecked(true);
                    break;
                case "3":
                    so3.setChecked(true);
                    break;
                case "4":
                    so4.setChecked(true);
                    break;
            }
        }

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiztitle.getText().toString().isEmpty() ||
                        op1.getText().toString().isEmpty() ||
                        op2.getText().toString().isEmpty() ||
                        op3.getText().toString().isEmpty() ||
                        op4.getText().toString().isEmpty()) {
                    Toast.makeText(UpdateQuestionActivity.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                String _quiztitle = quiztitle.getText().toString().trim();
                String _op1 = op1.getText().toString().trim();
                String _op2 = op2.getText().toString().trim();
                String _op3 = op3.getText().toString().trim();
                String _op4 = op4.getText().toString().trim();
                String solution = "";
                if(so1.isChecked()){
                    solution = "1";
                }else if(so2.isChecked()){
                    solution = "2";
                }else if(so3.isChecked()){
                    solution = "3";
                }else if(so4.isChecked()){
                    solution = "4";
                }

                QuestionModel questionSave = new QuestionModel(_quiztitle, _op1, _op2, _op3, _op4, Integer.parseInt(solution), courseId);
                databaseReference =  FirebaseDatabase.getInstance().getReference("Courses").child(courseId).child("questions");
                databaseReference.setValue(questionSave);
                Toast.makeText(UpdateQuestionActivity.this, "Updated", Toast.LENGTH_SHORT).show();

            }
        });

    }
}