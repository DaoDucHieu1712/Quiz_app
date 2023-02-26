package com.example.myapplication;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView user_name;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem profile, course;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();

        profile.setOnClickListener(this);
        course.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, 2);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        user_name = findViewById(R.id.user_name);
        viewPager = findViewById(R.id.view_pager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}