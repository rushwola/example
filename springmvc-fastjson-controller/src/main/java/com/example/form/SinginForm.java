package com.example.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "{password.empty}")
    @NotNull(message = "password.empty")
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
