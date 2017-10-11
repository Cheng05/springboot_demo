package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaochuncheng on 2017/10/11.
 */
@RestController
public class SampleController {

    @RequestMapping("/hello")
    public String hello(){

        return "hello";
    }
}
