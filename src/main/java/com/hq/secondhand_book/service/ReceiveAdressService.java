package com.hq.secondhand_book.service;

import com.hq.secondhand_book.dto.ReceiveAdressDto;
import com.hq.secondhand_book.util.resp.ResultResp;

public interface ReceiveAdressService {
    ResultResp getRececiveAdress(String userName);
    ResultResp addRececiveAdress(ReceiveAdressDto dto);
    ResultResp editRececiveAdress(ReceiveAdressDto dto);
    ResultResp deleteReceiveAdress(int id);
}
