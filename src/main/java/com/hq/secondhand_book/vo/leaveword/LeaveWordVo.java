package com.hq.secondhand_book.vo.leaveword;

import lombok.Data;


@Data
public class LeaveWordVo {
    private String userName;//用户名
    private String userPic;//用户头像
    private int leaveId;//留言编号
    private String leaveContent;//留言内容
    private int leaveFatherId;//回复留言ID
    private String leaveDate;//留言时间
}
