package com.application.chatsimple.data;

public class Filter {

    private Integer age;
    private String city;
    private String gender;
    private String maritalStatus;

    public Filter(Integer age, String city, String gender, String maritalStatus) {
        this.age = age;
        this.city = city;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public Filter(){

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
