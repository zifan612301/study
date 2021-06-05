package com.hq.secondhand_book.vo.bookcategory;

import lombok.Data;

@Data
public class BookCategoryListVo {
    private int bookCategoryId;
    private String bookCategoryName;//图书类别名
    private String bookCategoryFatherName;//图书父类别名
    private int booklevel;//图书类别的级别
}
