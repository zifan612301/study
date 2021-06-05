package com.hq.secondhand_book.dto;

import lombok.Data;


@Data
public class LeaveWordDto {
    private String userName;//用户名
    private String leaveContent;//留言内容
    private int bookId; //图书编号
}
