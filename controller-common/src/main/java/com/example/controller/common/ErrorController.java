package com.example.controller.common;

import com.example.controller.common.entity.JsonMsg;
import com.example.controller.common.util.JsonMsgUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: leiyang
 * @Description: 错误返回界面
 * @Date 12:11 2017/3/19
 */
@RestController
@RequestMapping("")
public class ErrorController extends BaseController {


    @RequestMapping(value = "/error_404", produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMsg error_404() throws Exception {
        return JsonMsgUtil.create404();
    }

    @RequestMapping(value = "/error_500", produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public JsonMsg error_500() throws Exception {
        return JsonMsgUtil.create500();
    }


}
