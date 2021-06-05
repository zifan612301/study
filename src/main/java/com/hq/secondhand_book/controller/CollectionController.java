package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.service.CollectionService;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {
    @Autowired
    CollectionService collectionService;

    /**
     * 取消收藏
     */
    @DeleteMapping("cancelstart")
    public ResultResp cancelStart(@RequestParam String  userName,
                                  @RequestParam int bookId){
        return collectionService.cancel(userName,bookId);
    }

    /**
     * 收藏图书
     */
    @GetMapping("start")
    public ResultResp start(@RequestParam String  userName,
                                  @RequestParam int bookId){
        return collectionService.start(userName,bookId);
    }
    /**
     * 收藏图书列表
     */
    @GetMapping("startlist")
    public ResultResp startList(@RequestParam String  userName,
                            @RequestParam(required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(required = false, defaultValue = "8") int pageSize){
        return collectionService.startList(userName,pageIndex,pageSize);
    }
}
