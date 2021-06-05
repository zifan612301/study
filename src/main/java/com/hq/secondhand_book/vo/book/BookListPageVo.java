package com.hq.secondhand_book.vo.book;

import lombok.Data;

import java.util.List;


@Data
public class BookListPageVo {
    private List<BookListVo> list;
    private int rowCount;
}
