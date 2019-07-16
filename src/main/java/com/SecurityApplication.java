package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiyun
 * @date 2019/7/15
 * @描述
 */
@SpringBootApplication
@RestController
public class SecurityApplication {

    @GetMapping("/")
    public String hello(){
       return "security hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}