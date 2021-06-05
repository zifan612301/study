package com.hq.secondhand_book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;     //收藏编号

    @Column(name = "user_id")
    private Integer userId;     //用户编号

    @Column(name = "book_id")
    private Integer bookId;     //图书编号

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
