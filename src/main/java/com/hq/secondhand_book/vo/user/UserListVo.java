package com.hq.secondhand_book.vo.user;

import lombok.Data;

@Data
public class UserListVo {
    private String userName;    //用户名
    private String userSex;     //用户性别
    private String userEmail;   //用户邮箱
    private String userBirthday;//出生日期
    private int userId;     //用户编号
    private String userStaus;   //用户状态
}
