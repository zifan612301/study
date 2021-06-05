package com.hq.secondhand_book.vo.bookcategory;

import com.hq.secondhand_book.vo.bookcategory.BookCategoryListVo;
import lombok.Data;

import java.util.List;

@Data
public class BookCategoryPageVo {
    private List<BookCategoryListVo> list;
    private int rowCount;
}

