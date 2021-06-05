package com.hq.secondhand_book.vo.book;

import lombok.Data;

@Data
public class BookAdminListVo {
    private String bookName;//图书名称
    private String bookPicture;//图书图片，主图片，数据库中第一张图
    private String bookPrice;// 图书价格
    private int bookId;
    private String userName;
    private String bookStatus;
}
