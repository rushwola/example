package com.example.controller.common.util;

import com.example.controller.common.entity.JsonMsg;
import com.example.controller.common.enumer.MsgCodeEnum;

/**
* @Author: leiyang
* @Description:  用于生成Jsonmsg 的帮助类
* @Date 12:05 2017/3/19
*/
public class JsonMsgUtil {


    public  static JsonMsg createSuccess(){
        JsonMsg  msgObj=new JsonMsg();
        msgObj.setCode(MsgCodeEnum.SUCCESS.getCode());
        msgObj.setMsg("OK");

        return msgObj;
    }

    public  static JsonMsg createSuccess(String msg){
        JsonMsg  msgObj=new JsonMsg();
        msgObj.setCode(MsgCodeEnum.SUCCESS.getCode());
        msgObj.setMsg(msg);
        return msgObj;
    }

    public  static JsonMsg create500(){
        JsonMsg  msgObj=new JsonMsg();
        msgObj.setCode(MsgCodeEnum.FAIL_500.getCode());
        msgObj.setMsg("服务器处理失败");
        return msgObj;
    }

    public  static JsonMsg create500(String msg){
        JsonMsg  msgObj=new JsonMsg();
        msgObj.setCode(MsgCodeEnum.FAIL_500.getCode());
        msgObj.setMsg(msg);
        return msgObj;
    }


    public  static JsonMsg create404(){
        JsonMsg  msgObj=new JsonMsg();
        msgObj.setCode(MsgCodeEnum.FAIL_404.getCode());
        msgObj.setMsg("找不到页面");
        return msgObj;
    }

}
