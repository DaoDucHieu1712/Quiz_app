package com.example.myapplication.model;

import java.util.Date;

public class User {
    private String username;
    private String pass;
    private String fullName;
    private String image;
    private Date dob;
    private boolean gender;
    private CourseModel course;

    public User(String username, String pass, String fullName, String image, Date dob, boolean gender, CourseModel course) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.image = image;
        this.dob = dob;
        this.gender = gender;
        this.course = course;
    }

    public User(String username, String pass, String fullName, Date dob) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
        this.dob = dob;
    }
    public User() {
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

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}
