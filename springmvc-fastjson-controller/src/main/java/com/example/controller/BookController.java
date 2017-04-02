package com.example.controller;

import com.example.controller.common.entity.JsonMsg;
import com.example.controller.common.util.ParamValidateUtils;
import com.example.form.BookForm;
import com.example.form.SinginForm;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Administrator on 2017/3/31.
 * Book Controller
 */
@RestController
@RequestMapping("/book")
public class BookController extends DefaultController {

    @RequestMapping(value = "/find")
    public JsonMsg find(@Valid BookForm form) {
        System.out.print(form);
        return buildSuccess();
    }


    /*
   * Bind出错，这里是最高优先级的处理
   */
    @ExceptionHandler({BindException.class})
    public ModelAndView handleBindException(final BindException e) {

        return ParamValidateUtils.getParamErrors(e);
    }


}
