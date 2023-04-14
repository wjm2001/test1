package org.example.feign;

import org.example.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("provider-server")//声明调用的服务
public interface UserFeignService {
    @GetMapping("/user/getUserById/{userId}")
    User getUserById(@PathVariable("userId") Integer userId);//OpenFeign不可省略PathVariable后面的参数


    @GetMapping("/user/hello")
    public String hello();

}
