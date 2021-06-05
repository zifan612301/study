package com.hq.secondhand_book.service;

import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;

public interface CollectionService {
    ResultResp cancel(String userName,int bookId);

    ResultResp start(String userName,int bookId);

    ResultResp startList(String userName, int pageIndex,int pageSize);
}
