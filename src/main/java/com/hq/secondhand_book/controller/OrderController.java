package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.service.OrderService;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class OrderController {
    @Resource
    OrderService orderService;

    /**
     * 生成订单
     * @param userName
     * @param bookId
     * @param addressId
     * @return
     */
    @PostMapping("/addorder")
    public ResultResp addOrder(@RequestParam String userName,
                               @RequestParam int bookId,
                               @RequestParam int addressId){
        return orderService.addOrder(bookId,addressId,userName);
    }

    /**
     * 订单列表
     * @return
     */
    @GetMapping("/orderlist")
    public ResultResp orderList(@RequestParam String userName){
        return orderService.orderList(userName);
    }

    /**
     * 订单详情
     * @return
     */
    @GetMapping("/orderlist/detail")
    public ResultResp orderList(@RequestParam int orderId){
        return orderService.orderDetail(orderId);
    }

    /**
     * 确认收货
     * @return
     */
    @PostMapping("/confirmreceipt")
    public ResultResp confirmReceipt(@RequestParam int orderId){
        return orderService.confirmReceipt(orderId);
    }

    /**
     * 取消收货
     * @return
     */
    @PostMapping("/cancelorder")
    public ResultResp cancelOrder(@RequestParam int orderId){
        return orderService.cancelOrder(orderId);
    }
}
