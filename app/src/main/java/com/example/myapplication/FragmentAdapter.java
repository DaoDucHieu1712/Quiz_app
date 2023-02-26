package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.myapplication.model.FragmentCourse;
import com.example.myapplication.model.FragmentProfile;


public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNumber;

    public FragmentAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentProfile profile = new FragmentProfile();
                return profile;
            case 1:
                FragmentCourse course = new FragmentCourse();
                return course;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "PROFILE";
            case 1:
                return "COURSE";
        }
        return null;
    }
}
