package com.hq.secondhand_book.vo.placeTransacion;

import lombok.Data;

@Data
public class PlaceTransactionListVo {
    private int placeTransactionId;//交易地点编号
    private int placeFatherId;//父交易地点编号
    private String placeFatherName;//父交易地点名
    private String placeName;//交易地点名
    private int placeLevel;//交易地点级别
}
