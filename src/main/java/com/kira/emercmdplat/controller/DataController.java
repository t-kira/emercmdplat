package com.kira.emercmdplat.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/2 10:53
 * @Description:
 */
@RestController
@EnableAutoConfiguration
public class DataController {

    @RequestMapping("/hello")
    public String index() {
        return "hello";
    }
}
