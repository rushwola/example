package com.example.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/3/18.
 */
public class SinginForm {

    /**
     *账号名
     */
    @NotBlank(message = "{accountName.not.blank}")
    private  String accountName;

    /**
     * 账号密码
     */
    @NotBlank(message = "{accountPW.not.blank}")
    private String accountPW;



    public String getAccountPW() {
        return accountPW;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountPW(String accountPW) {
        this.accountPW = accountPW;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "SinginForm{" +
                "accountName='" + accountName + '\'' +
                ", accountPW='" + accountPW + '\'' +
                '}';
    }
}
