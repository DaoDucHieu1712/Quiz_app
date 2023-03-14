package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, dob, password;
    TextView loginRediectText;
    Button registerButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.edt_fullname);
        email = findViewById(R.id.edt_email);
        dob = findViewById(R.id.edt_email);
        password =findViewById(R.id.edt_password);
        registerButton = findViewById(R.id.btn_register);
        loginRediectText = findViewById(R.id.loginRedirectText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("User");

                String fullName = name.getText().toString();
                String mail = email.getText().toString();

                Date birthDate = null;
                try {
                    birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dob.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String pass = password.getText().toString();

                User user = new User(mail, pass, fullName, birthDate);
                reference.child(fullName).setValue(user);

                Toast.makeText(RegisterActivity.this, "You have register successfully!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        loginRediectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}