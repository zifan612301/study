package com.hq.secondhand_book.dto;

import lombok.Data;


@Data
public class ReceiveAdressDto {
    private int receiveId;
    private String userName;//用户名
    private String receiverName;//收货人姓名
    private String placeName;//交易地点名
    private String receiverTel;//收货人电话
    private int isDefault;//是否为默认地址
}
