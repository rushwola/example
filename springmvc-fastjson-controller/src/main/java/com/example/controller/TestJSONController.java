package com.example.controller;

import com.example.controller.common.BaseController;
import com.example.controller.common.entity.JsonMsg;
import com.example.form.SinginForm;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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

    /**
    * @Author: leiyang
    * @Description:
    * @Date 23:24 2017/3/28
     * application/x-www-form-urlencoded  类型参数
    */
    @RequestMapping(value = "/singin")
    public JsonMsg singin(SinginForm form) {
        System.out.print(form);
        return buildSuccess();

    }

    /**
    * @Author: leiyang
    * @Description:
    * @Date 23:25 2017/3/28
     * application/json; 类型参数
    */
    @RequestMapping(value = "/singin_2")
    public JsonMsg singin_2(@RequestBody @Valid SinginForm form, BindingResult result) {
        System.out.print(form);
        if (result.hasErrors()){
            List<ObjectError> errorList = result.getAllErrors();
            for(ObjectError error : errorList){
                System.out.println(error.getDefaultMessage());
            }
        }
        return buildSuccess();

    }

    /**
    * @Author: leiyang
    * @Description:
    * @Date 23:25 2017/3/28
     * multipart/form-data 类型参数   可以传文件
    */
    @RequestMapping(value = "/singin_s")
    public JsonMsg singin_3(SinginForm form, MultipartFile file) {
        System.out.println(file);
        System.out.println(form);
        return buildSuccess();

    }

}
