package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
@RequestMapping("/test/jsp")
public class TestJSPController {


    @RequestMapping("/one")
    public  String  testJsp(){

        return "test";
    }
}
