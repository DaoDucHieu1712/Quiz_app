package com.example.myapplication.model;

import java.util.Date;
import java.util.Map;

public class User {
    private String username;
    private String pass;
    private String fullName;
    private String image;
    private String Key;
    private Date dob;
    private boolean gender;
    private Map<String, CourseModel> courses;

    public User(String key, String username, String pass, String fullName, String image, Date dob, boolean gender, Map<String, CourseModel>  courses) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.image = image;
        Key = key;
        this.dob = dob;
        this.gender = gender;
        this.courses = courses;
    }

    public User(String username, String pass, String fullName, String image, Date dob, boolean gender, Map<String, CourseModel>  courses) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.image = image;
        this.dob = dob;
        this.gender = gender;
        this.courses = courses;
    }

    public User(String username, String pass, String fullName, Date dob) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.dob = dob;
    }
    public User() {
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Map<String, CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, CourseModel> courses) {
        this.courses = courses;
    }
}
