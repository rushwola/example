package com.example.common.form;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
public class RequestFormBase implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7484305986335855048L;

    // 用于存储 修改操作时 修改的字段名列表
    private List<String> fieldList;

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

}
