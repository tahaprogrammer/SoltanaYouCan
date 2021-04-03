package com.example.soltanayoucan.Utils;

public class DataPostModel {

    private int id;
    private String id_post;
    private String date_gmt;
    private String title;
    private String content;


    public DataPostModel(int id, String id_post, String date_gmt, String title, String content) {
        this.id = id;
        this.id_post = id_post;
        this.date_gmt = date_gmt;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getId_post() {
        return id_post;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }
}