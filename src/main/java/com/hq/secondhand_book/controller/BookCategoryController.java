package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.service.BookCategoryService;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.bookcategory.BookCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookCategoryController {
    @Autowired
    BookCategoryService bookCategoryService;
    //图书类别下拉框
    @GetMapping("/findbookcategory")
    public ResultResp findAll(){
        List<BookCategoryVo> bookCategoryVos = bookCategoryService.findAll();
        return Response.ok(bookCategoryVos);
    }

    //图书类别列表
    @GetMapping("/bookcategoryList")
    public ResultResp bookCategoryList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return bookCategoryService.bookCategoryList(pageIndex, pageSize);
    }

    /**
     * 查找
     * @param categoryName
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/findbybookbategorynamelist")
    public ResultResp findByBookCategoryNameList(@RequestParam String categoryName, @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                 @RequestParam(required = false, defaultValue = "10") int pageSize){
        System.out.println(categoryName);
        return bookCategoryService.findByBookCategoryNameList(categoryName,pageIndex,pageSize);
    }

    /**
     * 删除类别
     * @param categoryName
     * @return
     */
    @DeleteMapping("/deletebookcategory")
    public ResultResp deleteByBookCategoryName(@RequestParam String categoryName){
        return bookCategoryService.deleteBookCategory(categoryName);
    }

    /**
     * 添加类别
     */
    @PostMapping("/addcategory")
    public ResultResp addCategory(@RequestParam String catergoryName,
                                  @RequestParam String fatherCategoryName){
        return bookCategoryService.addCategory(catergoryName,fatherCategoryName);
    }

    /**
     * 更新类别
     * @param catergoryId
     * @param catergoryName
     * @param fatherCategoryName
     * @return
     */
    @PostMapping("/updatecategory")
    public ResultResp updateCategory(@RequestParam int catergoryId,
                                     @RequestParam String catergoryName,
                                     @RequestParam String fatherCategoryName){
        return bookCategoryService.updateCategory(catergoryId, catergoryName, fatherCategoryName);
    }

}
