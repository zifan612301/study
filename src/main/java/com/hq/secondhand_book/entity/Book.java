package com.hq.secondhand_book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;     //图书编号

    @Column( name = "user_id")
    private Integer userId;     //用户编号

    @Column(name = "book_category_id")
    private int bookCategoryId;      //图书类别编号

    @Column( name = "book_name")
    private String bookName;        //图书名

    @Column(name = "book_synopsis")
    private String bookSysnopsis;       //图书简介

    @Column(name = "book_price")
    private Double bookPrice;       //图书价格

    @Column(name = "book_picture")
    private String bookPicture;     //图书图片

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
