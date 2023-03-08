package com.example.myapplication.model;

public class CourseModel {
    private String idUser;
    private String topic;
    private String title;
    private String desc;
    private String image;
    private String key;

    public CourseModel(String idUser, String topic, String title, String desc, String image) {
        this.idUser = idUser;
        this.topic = topic;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public CourseModel() {
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
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
