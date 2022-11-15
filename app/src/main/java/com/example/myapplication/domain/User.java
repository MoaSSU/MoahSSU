package com.example.myapplication.domain;

//@IgnoreExtraProperties
public class User {

    private int id;
    private String name;
    private String email;
    private String photo;
    private String uuid;

    public User() {
    }

    public User(int id, String name, String email, String photo, String uuid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
