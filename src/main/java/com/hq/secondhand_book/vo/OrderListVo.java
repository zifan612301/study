package com.hq.secondhand_book.vo;

import lombok.Data;


@Data
public class OrderListVo {
    private int OrderId;//订单编号
    private int orderStatus;//订单状态
    private String orderTime;//订单生成时间
    private String receiverName;//收货人姓名
    private String img;//图书主图
    private String bookName;//图书标题
    private String bookPrice;//图书价格
}
