package com.example.myapplication;
import android.content.Intent;

import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class LoginActivity extends AppCompatActivity {

   private EditText username, password;
   private Button loginButton;
   TextView registerRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edt_loginusn);
        password = findViewById(R.id.edt_loginpass);
        loginButton = findViewById(R.id.btn_login);
        registerRedirect = findViewById(R.id.textRegisterRedirect);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usn = username.getText().toString();
                String pass = password.getText().toString();
//                if(usn.isEmpty() || pass.isEmpty()){
//                    Toast.makeText(LoginActivity.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
//                return;
//                }

                if(!usn.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(usn).matches()){
                    if(!pass.isEmpty()){
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(usn, pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                            String currentUserID = mAuth.getCurrentUser().getUid();
                                            // Authentication successful, start the main activity
                                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            Log.d("hello",currentUserID );
                                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // Authentication failed, display an error message
                                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else{
                        password.setError("Password cannot be empty");
                    }
                }else if(usn.isEmpty()){
                    username.setError("Email cannot be empty");
                }else{
                    username.setError("Please enter valid email.");
                }

            }
        });
        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }











}












