package org.example.controller;

import org.example.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/getUserById/{userId}")
    public User GetUserById(@PathVariable("userId") Integer userId){
        User user = new User(userId,"小明","123456");
        return user;
    }
}
