package com.hq.secondhand_book.vo.user;

import lombok.Data;

import java.util.List;

@Data
public class UserPageVo {
    private List<UserListVo> list;
    private int rowCount;
}
