package com.hq.secondhand_book.vo.leaveword;

import lombok.Data;

import java.util.List;

@Data
public class LeaveWordPageVo {
    private List<LeaveWordListVo> list;
    private int rowCount;
}
