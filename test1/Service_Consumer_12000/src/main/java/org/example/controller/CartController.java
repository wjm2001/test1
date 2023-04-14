package org.example.controller;

import org.example.entity.User;
import org.example.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/getUserById/{userId}")
    public User getUserById(@PathVariable Integer userId){
        return userFeignService.getUserById(userId);
    }
}
