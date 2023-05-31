package org.example.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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

    @GetMapping("/backend")
    @CircuitBreaker(name="backendA",fallbackMethod = "fallback")
    //@CircuitBreaker(name="backendB",fallbackMethod = "fallback")
    public User backend_get() throws InterruptedException {
        System.out.println("进入方法");
        Thread.sleep(10000L);
        User user = feignClientService.get();
        System.out.println("退出方法");
        return user;
    }

    @GetMapping("/bulkhead")
    @Bulkhead(name = "bulkheadA",fallbackMethod = "fallback",type = Bulkhead.Type.SEMAPHORE)
    public User bulkhead_get(){
        System.out.println("进入方法");
        User user = feignClientService.get();
        System.out.println("离开方法");
        return user;
    }

    @GetMapping("/ratelimiter")
    @RateLimiter(name = "ratelimiterA",fallbackMethod = "fallback")
    public User ratelimiter_get() throws InterruptedException {
        System.out.println("进入方法");
        Thread.sleep(10000L);
        User user = feignClientService.get();
        System.out.println("离开方法");
        return user;
    }

    public User fallback(Throwable e){
        e.printStackTrace();
        System.out.println("fallback已经调用！");
        User user = new User();
        return user;
    }
}
