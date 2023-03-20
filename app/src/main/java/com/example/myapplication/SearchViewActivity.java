package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.adapter.AdapterClass;
import com.example.myapplication.model.CourseModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class SearchViewActivity extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<CourseModel> list;
    RecyclerView recyclerView;
    androidx.appcompat.widget.SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        ref = FirebaseDatabase.getInstance().getReference().child("Courses");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.search_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            list.add(ds.getValue(CourseModel.class));
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SearchViewActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if(searchView !=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });

        }
    }
    private void search(String str){
        ArrayList<CourseModel> myList = new ArrayList<>();
        for(CourseModel courseModel: list)
        {
            if(courseModel.getTitle().toLowerCase().contains(str.toLowerCase())){
                myList.add(courseModel);
            }
        }
        AdapterClass adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }
}