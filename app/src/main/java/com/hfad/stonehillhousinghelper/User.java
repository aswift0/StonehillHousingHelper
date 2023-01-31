package com.hfad.stonehillhousinghelper;


public class User {
    private String username;
    private String password;
    private String year;
    private String name;
    private String gender;
    public User(String username, String password, String year, String name, String gender) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.year = year;
    }

    public User() {
        name = "default";
        username = "default";
        password = "default";
        gender = "0";
        year = "2022";
    }

    public String getYear() { return year; }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getUsername(){ return username;}
    public String getPassword() {return password;}
    public String toString() {
        return this.name + this.gender;
    }
}
