package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.service.PlaceTransactionService;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PlaceTransactionController {
    @Autowired
    PlaceTransactionService placeTransactionService;

    /**
     * 交易地点下拉列表
     * @return
     */
    @GetMapping("/placelist")
    public ResultResp getPlace(){
        return placeTransactionService.getPlace();
    }

    //交易地点列表
    @GetMapping("/placepagelist")
    public ResultResp bookCategoryList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return placeTransactionService.placeList(pageIndex, pageSize);
    }

    //交易地点列表
    @GetMapping("/placepagebyplacenamelist")
    public ResultResp bookCategoryList(@RequestParam String placeName,
                                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return placeTransactionService.findByPlaceNameList(placeName,pageIndex, pageSize);
    }

    /**
     * 删除交易地点
     * @param placeId
     * @return
     */
    @DeleteMapping("/deleteplace")
    public ResultResp deleteByBookCategoryName(@RequestParam int placeId){
        return placeTransactionService.deletePlace(placeId);
    }

    @PostMapping("/addplace")
    public ResultResp updateCategory(@RequestParam String placeName,
                                     @RequestParam String fatherplaceName){
        return placeTransactionService.addPlace(placeName, fatherplaceName);
    }

    @PostMapping("/updateplace")
    public ResultResp updateCategory(@RequestParam int placeId,
                                     @RequestParam String placeName,
                                     @RequestParam String fatherplaceName){
        return placeTransactionService.updatePlace(placeId, placeName, fatherplaceName);
    }

}
