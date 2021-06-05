package com.hq.secondhand_book.service;

import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.bookcategory.BookCategoryVo;

import java.util.List;

public interface BookCategoryService {
    List<BookCategoryVo> findAll();

    ResultResp bookCategoryList(int page,int size);

    ResultResp PrimaryCategory();

    ResultResp Subcategory(int fatherId);

    ResultResp findByBookCategoryNameList(String categoryName, int page,int size);

    ResultResp deleteBookCategory(String categoryName);

    ResultResp addCategory(String catergoryName,String fatherCategoryName);

    ResultResp updateCategory(int catergoryId, String catergoryName,String fatherCategoryName);
}
