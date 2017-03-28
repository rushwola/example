package com.example.controller;

import com.example.controller.common.BaseController;
import com.example.controller.common.entity.JsonMsg;
import com.example.form.SinginForm;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2017/3/18.
 */
@RestController
@RequestMapping("/test/json")
public class TestJSONController extends DefaultController {

    @RequestMapping("/exception")
    public JsonMsg exception() {

        throw new RuntimeException("这是一个测试异常");
    }

    @RequestMapping(value = "/singin")
    public JsonMsg singin(SinginForm form) {
        System.out.print(form);
        return buildSuccess();

    }

    @RequestMapping(value = "/singin_2")
    public JsonMsg singin_2( @RequestBody  SinginForm form) {
        System.out.print(form);
        return buildSuccess();

    }

    @RequestMapping(value = "/singin_s")
    public JsonMsg singin_3(SinginForm form, MultipartFile file) {
        System.out.println(file);
        System.out.println(form);
        return buildSuccess();

    }

}
