package com.hq.secondhand_book.vo;

import lombok.Data;


@Data
public class OrderDetailVo {
    private int orderId;//订单号
    private int orderStatus;//订单状态
    private String orderTime;//订单生成时间
    private String receiverName;//收货人姓名
    private String placeName;//收货地址
    private String receiverTel;//收货人电话
    private String img;//图书主图
    private String bookName;//图书标题
    private String bookPrice;//图书价格
}
