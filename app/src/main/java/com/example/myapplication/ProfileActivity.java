package com.example.myapplication;

import com.example.myapplication.model.User;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import java.util.Date;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    private TextView user_name;
    private ImageView avt;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String idUser = mAuth.getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        avt.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, 2);
        viewPager.setAdapter(adapter);

        user_name = findViewById(R.id.user_name);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    String userKey = itemSnapshot.getKey();
                    if (userKey.equals(idUser)) {
                        User currentUser = itemSnapshot.getValue(User.class);
                        user_name.setText(currentUser.getFullName());

                        Picasso.get().load(currentUser.getImage()).into(avt);
                        break; // exit the loop once the current user is found
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        user_name = findViewById(R.id.user_name);
        avt = findViewById(R.id.avt);
        viewPager = findViewById(R.id.view_pager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avt:
                User u = new User("vinhlq", "123456", "lam vinh", "meo.jpg", new Date(25/12/2001), true, null);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("User").child("kym8nqBhgAfVmhZZsWdACsfdNlm2");
                myRef.setValue(u);
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }
    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

        }
    }
}