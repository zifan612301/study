package com.hq.secondhand_book.service;

import com.hq.secondhand_book.dto.LeaveWordDto;
import com.hq.secondhand_book.util.resp.ResultResp;


public interface LeaveWordService {
    ResultResp addLeaveWord(LeaveWordDto leaveWordDto);

    ResultResp leaveWordList(int page,int size);

    ResultResp findLeaveWordList(String leaveWordContant, int page,int size);

    ResultResp deleteLeaveWord(int leaveId);
}
