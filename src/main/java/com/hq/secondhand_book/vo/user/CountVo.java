package com.hq.secondhand_book.vo.user;

import lombok.Data;

@Data
public class CountVo {
    private int collectionNum;//我的收藏
    private int orderNum;//我的订单
    private int adressNum;//收货地址
    private int sellBookNum;//我发布的
}
