package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class UpdateQuestionActivity extends AppCompatActivity {

    EditText quiztitle, op1, op2, op3, op4;
    RadioButton so1, so2, so3, so4;
    Button btn_update;
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
        so2 = findViewById(R.id.update_solution3);
        so3 = findViewById(R.id.update_solution4);
        btn_update = findViewById(R.id.update_question);

    }
}