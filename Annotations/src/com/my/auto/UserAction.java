package com.my.auto;

import com.my.auto.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

public class UserAction {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public void save() {
        this.userService.save();
        System.out.println("这是userAction里的save()");
    }
}
