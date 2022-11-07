package com.example.myapplication.domain;

//@IgnoreExtraProperties
public class User {
    public Long userid;
    public String name;
    public String email;

    public User() { }
    public User(Long id,String name, String email) {
        this.userid = id;
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserid(Long userid) {this.userid = userid; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserid(){return  userid;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" +
                '}';
    }
}
