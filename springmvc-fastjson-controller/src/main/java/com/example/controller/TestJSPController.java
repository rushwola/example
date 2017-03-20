package com.example.controller;

import com.example.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/18.
 */
@Controller
@RequestMapping("/test/jsp")
public class TestJSPController extends DefaultController{


    @RequestMapping("/one")
    public  String  testJsp(){


        return "test";
    }
}
