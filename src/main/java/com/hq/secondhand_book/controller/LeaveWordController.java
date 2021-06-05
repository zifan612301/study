package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.dto.LeaveWordDto;
import com.hq.secondhand_book.service.LeaveWordService;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class LeaveWordController {
    @Resource
    LeaveWordService leaveWordService;

    /**
     * 用户跟针对图书发布留言
     * @param leaveWordDto
     * @return
     */
    @PostMapping("/addleaveword")
    public ResultResp addLeaveWord(@RequestBody LeaveWordDto leaveWordDto){
        System.out.println(leaveWordDto);
        if(leaveWordDto.getUserName().isEmpty()){
            Response.dataErr("请登录");
        }if(leaveWordDto.getBookId() == 0){
            Response.dataErr("图书不存在");
        }
        return leaveWordService.addLeaveWord(leaveWordDto);
    }

    //留言列表
    @GetMapping("/leavewordlist")
    public ResultResp bookCategoryList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return leaveWordService.leaveWordList(pageIndex, pageSize);
    }

    //按内容查找留言列表
    @GetMapping("/findleavewordlist")
    public ResultResp bookCategoryList(@RequestParam String leaveContant,
                                       @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return leaveWordService.findLeaveWordList(leaveContant,pageIndex, pageSize);
    }

    /**
     * 删除留言
     * @param leaveId
     * @return
     */
    @DeleteMapping("/deleteleaveword")
    public ResultResp deleteByBookCategoryName(@RequestParam int leaveId){
        return leaveWordService.deleteLeaveWord(leaveId);
    }
}
