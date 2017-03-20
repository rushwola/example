package com.example.controller.common.entity;


/**
* @Author: leiyang
* @Description:  接入层返回给前端的json报文
* @Date 11:29 2017/3/19
*/
public class JsonMsg {

    /**
     * @Description:  状态码
     */
    private Integer code;

    /**
     * @Description: 报文提示消息
     */
    private String msg;

    /**
     * @Description:  数据体
     */
    private  Object data;

    public JsonMsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonMsg() {
    }

    public JsonMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
