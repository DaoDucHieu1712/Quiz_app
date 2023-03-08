package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CourseDetailActivity extends AppCompatActivity {
    TextView detailDesc, detailTitle, detailTopic;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imgUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailTopic = findViewById(R.id.detailTopic);
        detailImage = findViewById(R.id.detailImage);
        deleteButton=findViewById(R.id.deleteCourse);
        editButton=findViewById(R.id.updateCourse);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailTopic.setText(bundle.getString("Topic"));
            key = bundle.getString("Key");
            imgUrl = bundle.getString("Image");
            Glide.with(this) .load(bundle.getString("Image")).into(detailImage);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Courses");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imgUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(CourseDetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ListCourseActivity.class));
                        finish();
                    }
                });
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailActivity.this, UpdateCourseActivity.class)
                        .putExtra("Image", imgUrl)
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Topic", detailTopic.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}