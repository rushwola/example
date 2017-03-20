package com.example.controller.common.enumer;

/**
 * Created by Administrator on 2017/3/19.
 */
public enum MsgCodeEnum {

   SUCCESS(0,"成功报文"),
    FAIL_404(404,"找不到页面"),
    FAIL_500(500,"服务器处理错误");

    private  int code;

    private  String desc;


    MsgCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
