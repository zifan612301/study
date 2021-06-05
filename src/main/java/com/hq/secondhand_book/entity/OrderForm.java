package com.hq.secondhand_book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table( name = "order_form")
public class OrderForm {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;     //订单编号

    @Column(name = "book_id")
    private Integer bookId;     //图书编号

    @Column(name = "user_id")
    private Integer userId;     //用户编号


    @Column(name = "receiver_id")
    private Integer receiverId;         //收货地址编号

    @Column(name = "order_state")
    private Integer orderState;         //订单状态

    @Column( name = "order_remark")
    private String orderRemark;        //订单留言

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间，即留言时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
