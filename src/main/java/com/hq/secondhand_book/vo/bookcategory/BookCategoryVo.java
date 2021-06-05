package com.hq.secondhand_book.vo.bookcategory;

import lombok.Data;

@Data
public class BookCategoryVo {
    private int  BookCategoryId;
    private String BookCategoryName;

    public BookCategoryVo() {
    }

    public BookCategoryVo(int bookCategoryId, String bookCategoryName) {
        BookCategoryId = bookCategoryId;
        BookCategoryName = bookCategoryName;
    }
}
