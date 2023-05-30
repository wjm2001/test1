package org.example.controller;

import org.example.entity.User;
import org.example.feign.FeignClientService;
import org.example.loadBanlanced.CustomLoadBalanceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@LoadBalancerClient(name = "provider-server",configuration = CustomLoadBalanceConfiguration.class)
public class CartController {
    @Autowired
    private FeignClientService feignClientService;

    @GetMapping("/get")
    public User get(){
        return feignClientService.get();
    }

    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable Integer userId){
        return feignClientService.delete(userId);
    }
}
