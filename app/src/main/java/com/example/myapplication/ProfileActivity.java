package com.example.myapplication;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        FragmentManager manager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(manager, 2);
        viewPager.setAdapter(adapter);
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        user_name = findViewById(R.id.user_name);
        profile = findViewById(R.id.profile);
        course = findViewById(R.id.course);
        viewPager = findViewById(R.id.view_pager);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                viewPager.setCurrentItem(1);
                break;
            case R.id.course:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}