package com.hq.secondhand_book.vo.book;

import com.hq.secondhand_book.vo.leaveword.LeaveWordVo;
import lombok.Data;

import java.util.List;

/**
 * 图书详情
 */
@Data
public class BookDetails {
    private int bookId; //图书编号
    private String bookName;//图书名称
    private String bookSynopsis;    //图书简介
    private String[] bookPicture;//图书图片，主图片，数据库中第一张图
    private String bookPrice;// 图书价格
    private int isCollection;//是否已收藏 0未收藏  1-已收藏
    private List<LeaveWordVo> leaveWordList;//留言

    public BookDetails() {
    }

    public BookDetails(int bookId, String bookName, String bookSynopsis, String[] bookPicture, String bookPrice, int isCollection, List<LeaveWordVo> leaveWordList) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookSynopsis = bookSynopsis;
        this.bookPicture = bookPicture;
        this.bookPrice = bookPrice;
        this.isCollection = isCollection;
        this.leaveWordList = leaveWordList;
    }
}
