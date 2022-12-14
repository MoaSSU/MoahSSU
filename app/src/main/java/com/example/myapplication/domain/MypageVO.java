package com.example.myapplication.domain;

public class MypageVO {
    public int id;
    public String title;
    public String difficulty;
    public String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {return title; }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDifficulty() {return difficulty;}
    public void setDifficulty(String difficulty) { this.difficulty =difficulty; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
}
