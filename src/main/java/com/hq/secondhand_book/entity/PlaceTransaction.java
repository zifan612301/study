package com.hq.secondhand_book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther 黄琦
 * @create 2019 04 02
 */

@Entity
@Data
@Table( name = "place_transaction")
public class PlaceTransaction {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;     //交易地点编号

    @Column(name = "place_name")
    private String placeName;       //交易地点名称

    @Column(name = "place_father_id")
    private Integer placeFatherId;      //交易地点父类编号

    @Column(name = "place_level")
    private Integer placeLevel;     //地点级别

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
