package com.example.myapplication.domain;

//@IgnoreExtraProperties
public class User {
    public String name;
    public String email;
    public String photo;

    public User() { }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //public void setUserid(Long userid) {this.userId = userid; }

    public void setPhoto(String photo) {this.photo = photo;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    //public Long getUserid(){return  userId;}

    public String getPhoto(){return photo;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" +
                '}';
    }
}
