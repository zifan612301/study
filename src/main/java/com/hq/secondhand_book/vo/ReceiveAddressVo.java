package com.hq.secondhand_book.vo;

import lombok.Data;

@Data
public class ReceiveAddressVo {
    private int receiveId;//收货地址编号
    private String receiverName;//收货人姓名
    private String placeName;//交易地点名
    private String receiverTel;//收货人电话
    private int isDefault;//是否为默认地址
}
