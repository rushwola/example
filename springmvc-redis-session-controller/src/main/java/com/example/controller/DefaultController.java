package com.example.controller;

import com.example.controller.common.BaseController;
import com.example.controller.common.entity.JsonMsg;
import com.example.controller.common.util.JsonMsgUtil;

/**
 * Created by Administrator on 2017/3/19.
 */
public abstract class DefaultController extends BaseController {


    protected JsonMsg buildSuccess(){
        return JsonMsgUtil.createSuccess();
    }


}
