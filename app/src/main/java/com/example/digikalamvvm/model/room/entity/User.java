package com.example.digikalamvvm.model.room.entity;

public class User {
    private String name;

    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        if (name == null ) {
            return "";
        }
        return name;
    }


    public String getPassword() {
        if (password == null) {
            return "";
        }
        return password;
    }

    public boolean isNameValid() {
        return getName().length() > 2;
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }
}