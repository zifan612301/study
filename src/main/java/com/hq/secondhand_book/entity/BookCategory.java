package com.hq.secondhand_book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "book_category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;     //图书类别编号

    @Column(name = "book_category_name")
    private String bookCategoryName;        //图书类别名

    @Column(name = "book_category_father_id")
    private Integer bookCategoryFatherId;       //图书类别父类名

    @Column(name = "book_category_level")
    private Integer bookCategoryLevel;      //图书类别等级

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
