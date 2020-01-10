package com.example.baitapquanlyuser.model;

public class AppUserForm {
    private String userName;

    public AppUserForm() {

    }

    public AppUserForm(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
