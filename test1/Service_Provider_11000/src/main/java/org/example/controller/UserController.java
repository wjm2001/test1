package org.example.controller;

import org.example.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/get")
    public User get(){
        User user = new User(1,"小王","123");
        return user;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        User user1 = new User(user.getUserId(),user.getUserName(),user.getPassword());
        return user1;
    }

    @PutMapping("/put/{userId}")
    public User put(@PathVariable Integer userId,@RequestBody User user){
        User user1 = new User(user.getUserId(),user.getUserName(),user.getPassword());
        user1.setUserId(userId);
        return user1;
    }

    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable Integer userId){
        return "已删除id为"+userId+"的用户";
    }
}
