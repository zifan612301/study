package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.service.BookService;
import com.hq.secondhand_book.util.resp.ResultResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    /**
     * 图书列表
     * @return
     */
    @GetMapping("/booklist")
    public ResultResp bookList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                               @RequestParam(required = false, defaultValue = "8") int pageSize){
        return bookService.bookList(pageIndex,pageSize);
    }

    /**
     * 后台图书列表
     * @return
     */
    @GetMapping("/bookadminlist")
    public ResultResp bookAdminList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                               @RequestParam(required = false, defaultValue = "8") int pageSize){
        return bookService.bookAdminList(pageIndex,pageSize);
    }

    /**
     * 通过图书名搜索后台图书列表
     * @return
     */
    @GetMapping("/findbooknameadminlist")
    public ResultResp finByBookNameAdminList(@RequestParam String bookName,
                                             @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                             @RequestParam(required = false, defaultValue = "8") int pageSize){
        return bookService.findByBookNameAdminList(bookName, pageIndex, pageSize);
    }

    @GetMapping("/findbybooknamelist")
    public ResultResp findByBookNameList(@RequestParam String bookName,
                                         @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                         @RequestParam(required = false, defaultValue = "8") int pageSize){
        return bookService.findByBookNameList(bookName,pageIndex,pageSize);
    }

    /**
     *分类查找图书
     */
    @GetMapping("/bookcategory")
    public ResultResp booListBycategory(@RequestParam String bookCategory,
                                        @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                        @RequestParam(required = false, defaultValue = "8") int pageSize){
        return bookService.bookListByCategory(bookCategory,pageIndex,pageSize);
    }

    /**
     * 图书详情
     * @param bookId 图书编号
     * @return
     */
    @GetMapping("/booklist/detail")
    public ResultResp bookDetail(@RequestParam int bookId,String userName){
        System.out.println(userName);
        return bookService.getBookDetail(bookId,userName);
    }

    /**
     * 图书订单页
     */
    @GetMapping("/detail")
    public ResultResp bookOrder(@RequestParam int bookId){
        return bookService.getbookOrder(bookId);
    }

    /**
     * 下架图书
     * @param bookId
     * @return
     */
    @DeleteMapping("/deletebook")
    public ResultResp deleteBook(@RequestParam int bookId){
        return bookService.deleteBook(bookId);
    }

    /**
     * 上架图书
     * @param bookId
     * @return
     */
    @GetMapping("/addbook")
    public ResultResp addBook(@RequestParam int bookId){
        return bookService.addBook(bookId);
    }
}
