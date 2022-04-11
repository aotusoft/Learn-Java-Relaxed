package com.my.annAuto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class UserAction {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public void save() {
        this.userService.save();
        System.out.println("这是userAction里的save()");
    }
}
