package com.application.chatsimple.data;

public class User {

    private String name;
    private Integer age;
    private String city;
    private String gender;
    private String maritalStatus;

    public User(String name, Integer age, String city, String gender, String maritalStatus) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
}
