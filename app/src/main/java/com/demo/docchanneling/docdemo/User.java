package com.demo.docchanneling.docdemo;


public class User
{
    public User() {
    }

    public User(String phone, String email, String name, String userName, String password) {
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    private String phone;

    private String email;

    private String name;

    private String userName;

    private String password;

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phone = "+phone+", email = "+email+", name = "+name+", userName = "+userName+", password = "+password+"]";
    }
}