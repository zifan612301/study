package com.hq.secondhand_book.vo.leaveword;

import lombok.Data;

@Data
public class LeaveWordListVo {
    private String userName;//用户名
    private int leaveId;//留言编号
    private String leaveContent;//留言内容
    private String leaveDate;//留言时间
}
