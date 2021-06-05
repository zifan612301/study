package com.hq.secondhand_book.vo.book;

import lombok.Data;

import java.util.List;

@Data
public class BookAdminListPageVo {
    private List<BookAdminListVo> list;
    private int rowCount;
}
