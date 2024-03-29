package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity {
    private CardView cv_logout;
    private CardView cv_Start;
    private CardView cv_question;
    private CardView cv_search;

    private ImageView img_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView tv_username = findViewById(R.id.tv_usernameHome);
        cv_logout = findViewById(R.id.cv_logout);
        cv_Start = findViewById(R.id.cvStartQuiz);
        cv_question = findViewById(R.id.cvHistory);
        img_profile = findViewById(R.id.img_profile);
        cv_search = findViewById(R.id.cvRule);


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        System.out.println(firebaseUser);

        if (firebaseUser != null) {
            String emailUser = firebaseUser.getEmail();
            tv_username.setText("初めまして, " + emailUser + "!");
            Toast.makeText(this, "Hello " + emailUser + "!", Toast.LENGTH_SHORT).show();
        }

        //logout
        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomePageActivity.this, "Logout successful, See u next time", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cv_Start.setOnClickListener(new View.OnClickListener() {   //button: Cac hoc phan
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,ListCourseActivity.class));
            }
        });
//        cv_question.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomePageActivity.this,ListQuestion.class));
//            }
//        });

        img_profile.setOnClickListener(new View.OnClickListener() {    //img view profile
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, ProfileActivity.class));
            }
        });

        cv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, SearchViewActivity.class));
            }
        });


    }
}