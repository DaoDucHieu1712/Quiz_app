package com.example.myapplication.model;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.ListCourseActivity;
import com.example.myapplication.ProfileActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  FragmentProfile extends Fragment {
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    TextView full_name, user_name, gender;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String idUser = mAuth.getCurrentUser().getUid();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        full_name = view.findViewById(R.id.full_name);
        user_name = view.findViewById(R.id.user_name);
        gender = view.findViewById(R.id.gender);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    String userKey = itemSnapshot.getKey();
                    if (userKey.equals(idUser)) {
                        User currentUser = itemSnapshot.getValue(User.class);
                        full_name.setText("Full Name : " + currentUser.getFullName());
                        user_name.setText("User Name : " + currentUser.getUsername());
                        gender.setText("Gender : " + currentUser.isGender());

                        break; // exit the loop once the current user is found
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        return view;
    }
}
