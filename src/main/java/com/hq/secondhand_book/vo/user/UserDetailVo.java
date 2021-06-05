package com.hq.secondhand_book.vo.user;

import lombok.Data;

@Data
public class UserDetailVo {
    private String userName;   //用户名
    private String userRealName;   //真实姓名
    private String userStuId;   //学号
    private String userBirthday;   //出生年月
    private String userSex;   //用户性别
    private String userTel;   //用户电话
    private String userEmail;      //用户邮箱
    private String userPic;     //用户头像
    private String userRole;    //用户角色
    private String userStatus;  //用户状态
}
