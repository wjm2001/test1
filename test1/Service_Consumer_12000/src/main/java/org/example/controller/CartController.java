package org.example.controller;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping("/get")
//    public User get(){
//        User user = restTemplate.getForObject("http://localhost:11000/user/get", User.class);
//        return user;
//    }

    @GetMapping("/get")
    public String get(){
        return "这是端口为12000的微服务";
    }

    @PostMapping("/post")
    public User post(){
        User user1 = new User(5, "小李", "123");
        User user = restTemplate.postForObject("http://localhost:11000/user/post",user1, User.class);
        return user;
    }

    @PutMapping("/put/{userId}")
    public String put(@PathVariable Integer userId){
        User user1 = new User(6, "小红", "123");
        restTemplate.put("http://localhost:11000/user/put/"+userId,user1);
        return "修改成功";
    }

    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable Integer userId){
        restTemplate.delete("http://localhost:11000/user/delete/"+userId);
        return "删除成功";
    }
}
