package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class CourseModel {
    private String idUser;
    private String topic;
    private String title;
    private String desc;
    private String image;
    private String key;
    private List<QuestionModel> questions;

    public CourseModel(String idUser, String topic, String title, String desc, String image, List<QuestionModel> questions) {
        this.idUser = idUser;
        this.topic = topic;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.questions = questions;
    }
    public CourseModel(){}

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }
}
