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
@Table(name = "receiving_address")
public class ReceivingAddress {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Integer id;     //收货地址编号

    @Column(name = "user_id")
    private Integer userId;     //用户编号

    @Column(name = "receiver_name")
    private String receiverName;       //收货人姓名

    @Column(name = "receiver_tel")
    private String receiverTel;     //收货人电话

    @Column(name = "place_name")
    private String placeName;       //交易地点

    @Column(name = "is_default")
    private Integer isDefault;   //是否为默认值：0-否 1-是

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间，即留言时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
