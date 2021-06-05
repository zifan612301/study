package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.dto.ReceiveAdressDto;
import com.hq.secondhand_book.service.ReceiveAdressService;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiveAdressController {
    @Autowired
    ReceiveAdressService receiveAdressService;

    /**
     * 用户的收货地址列表
     * @param userName
     * @return
     */
    @GetMapping("/getreceiveadress")
    public ResultResp getReceiveAdress(String userName){
        return receiveAdressService.getRececiveAdress(userName);
    }

    /**
     * 添加收货地址
     * @return
     */
    @PostMapping("/addreceiveadress")
    public ResultResp addReceiveAdress(@RequestBody ReceiveAdressDto dto){
        return receiveAdressService.addRececiveAdress(dto);
    }

    /**
     * 删除地址
     */
    @DeleteMapping("/deleteaddress")
    public ResultResp deleteAddress(@RequestParam int id){
        return receiveAdressService.deleteReceiveAdress(id);
    }

    /**
     * 添加收货地址
     * @return
     */
    @PostMapping("/editreceiveadress")
    public ResultResp editReceiveAdress(@RequestBody ReceiveAdressDto dto){
        return receiveAdressService.editRececiveAdress(dto);
    }
}
