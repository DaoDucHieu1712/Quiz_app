package com.example.myapplication.model;

public class CourseModel {
    private String topic;
    private String title;
    private String desc;
    private String image;

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

    public CourseModel(String topic, String title, String desc, String image) {
        this.topic = topic;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
    public CourseModel(){}
}
