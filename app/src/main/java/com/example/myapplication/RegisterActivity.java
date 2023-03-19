package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName, exitTextEmail, editTextDob, editTextPassword, editTextConfirmPass;
    RadioGroup radioGroup;
    RadioButton radioButtonSelected;
    TextView loginRediectText;
    Button registerButton;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.edt_fullName);
        exitTextEmail = findViewById(R.id.edt_email);
        editTextDob = findViewById(R.id.edt_dob);
        editTextPassword =findViewById(R.id.edt_password);
        editTextConfirmPass =findViewById(R.id.edt_confirm_password);

        radioGroup=findViewById(R.id.radio_group);
        radioGroup.clearCheck();


        //setting up datepicker
        editTextDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextDob.setText(dayOfMonth + "/" + (month +1) + "/" + year);
                    }
                },year,month, day);
            }
        });
        registerButton = findViewById(R.id.btn_register);
        loginRediectText = findViewById(R.id.loginRedirectText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectGenderId = radioGroup.getCheckedRadioButtonId();
                radioButtonSelected = findViewById(selectGenderId);

                String textFullName = editTextName.getText().toString();
                String textEmail = exitTextEmail.getText().toString();
                String textDob = editTextDob.getText().toString();
                String textPwd = editTextPassword.getText().toString();
                String textConfirm = editTextConfirmPass.getText().toString();
                String textGender;
                if(TextUtils.isEmpty(textFullName)){
                    Toast.makeText(RegisterActivity.this,"Please enter your full name", Toast.LENGTH_SHORT).show();
                    editTextName.setError("Name is required");
                    editTextName.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)){
                    Toast.makeText(RegisterActivity.this,"Please enter your mail", Toast.LENGTH_SHORT).show();
                    exitTextEmail.setError("Email is required");
                    exitTextEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(RegisterActivity.this,"Please re-enter your mail", Toast.LENGTH_SHORT).show();
                    exitTextEmail.setError("Invalid email");
                    exitTextEmail.requestFocus();
                } else if (TextUtils.isEmpty(textDob)){
                    Toast.makeText(RegisterActivity.this,"Please enter your dob", Toast.LENGTH_SHORT).show();
                    editTextDob.setError("Dob is required");
                    editTextDob.requestFocus();
                } else if(radioGroup.getCheckedRadioButtonId() ==-1){
                    Toast.makeText(RegisterActivity.this,"Please select your gender", Toast.LENGTH_SHORT).show();
                    radioButtonSelected.setError("Gender is required");
                    radioButtonSelected.requestFocus();
                } else if (TextUtils.isEmpty(textPwd)){
                    Toast.makeText(RegisterActivity.this,"Please enter your password", Toast.LENGTH_SHORT).show();
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                } else if(textPwd.length() <6){
                    Toast.makeText(RegisterActivity.this,"Password should be at least 6 digits", Toast.LENGTH_SHORT).show();
                    editTextPassword.setError("Too weak");
                    editTextPassword.requestFocus();
                } else if(TextUtils.isEmpty(textConfirm)){
                    Toast.makeText(RegisterActivity.this,"Please enter your confirm password", Toast.LENGTH_SHORT).show();
                    editTextConfirmPass.setError("Confirm Password is required");
                    editTextConfirmPass.requestFocus();
                } else if(!textPwd.equals(textConfirm)){
                    Toast.makeText(RegisterActivity.this,"Please enter same password", Toast.LENGTH_SHORT).show();
                    editTextConfirmPass.setError("Password must be same");
                    editTextConfirmPass.requestFocus();
                    editTextPassword.clearComposingText();
                    editTextConfirmPass.clearComposingText();
                } else {
                    textGender = radioButtonSelected.getText().toString();
                    boolean gen = "Male".equals(textGender);
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateBirth = null;
                    try {
                        dateBirth = format.parse(textDob);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    registerUser(textFullName, textEmail, dateBirth, gen, textPwd);
                }

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

    private void registerUser(String textFullName, String textEmail, Date textDob, boolean textGender, String textPwd) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                    firebaseUser.updateProfile(profileChangeRequest);


                    User user = new User(textEmail, textPwd, textFullName, textDob, textGender);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                    reference.child(firebaseUser.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "User register succesfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else{
                                Toast.makeText(RegisterActivity.this, "User register fail", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });
    }
}