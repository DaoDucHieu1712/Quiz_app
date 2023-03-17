package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseModel {
    private String idUser;
    private String topic;
    private String title;
    private String desc;
    private String image;
    private String key;
    private Map<String, QuestionModel> questions;

    public CourseModel(String idUser, String topic, String title, String desc, String image, String key, Map<String, QuestionModel> questions) {
        this.idUser = idUser;
        this.topic = topic;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.key = key;
        this.questions = questions;
    }

    public CourseModel(String idUser, String topic, String title, String desc, String image, Map<String, QuestionModel> questions) {
        this.idUser = idUser;
        this.topic = topic;
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.questions = questions;
    }

    public Map<String, QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, QuestionModel> questions) {
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
}
