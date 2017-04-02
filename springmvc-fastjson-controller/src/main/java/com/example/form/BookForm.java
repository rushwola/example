package com.example.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/3/31.
 */
public class BookForm {

    /**
    * @Author: leiyang
    * @Description: 作者
    * @Date 21:43 2017/3/31
    */
    @NotNull(message = "author.empty")
    @NotEmpty(message = "author.empty")
    private String author;

    /**
    * @Author: leiyang
    * @Description:书名
    * @Date 21:43 2017/3/31
    */
    @NotNull(message = "title.empty")
    @NotEmpty(message = "title.empty")
    private String title;


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BookForm{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
