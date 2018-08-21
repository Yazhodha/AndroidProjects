package com.demo.docchanneling.docdemo;

public class UserProfile {
    public String name;
    public String email;
    public String age;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public UserProfile(){

    }


    public UserProfile(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
