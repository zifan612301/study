package com.hq.secondhand_book.service;

import com.hq.secondhand_book.util.resp.ResultResp;


public interface OrderService {
    //ResultResp addOrder(int bookId,int addressId,String userName,String remark);
    ResultResp addOrder(int bookId,int addressId,String userName);

    ResultResp orderList(String userName);

    ResultResp orderDetail(int orderId);

    ResultResp cancelOrder(int orderId);

    ResultResp confirmReceipt(int orderId);
}
