package com.hq.secondhand_book.service;

import com.hq.secondhand_book.util.resp.ResultResp;

public interface BookService {
    ResultResp bookList(int page, int size);

    ResultResp bookAdminList(int page, int size);

    ResultResp bookListByCategory(String bookCategory,int pageIndex,int pageSize);

    ResultResp getBookDetail(int bookId,String userName);

    ResultResp getbookOrder(int bookId);

    ResultResp getBookList(int page,int size);

    ResultResp deleteBook(int bookId);

    ResultResp addBook(int bookId);

    ResultResp findByBookNameList(String bookName, int page,int size);

    ResultResp findByBookNameAdminList(String bookName, int page,int size);
}
