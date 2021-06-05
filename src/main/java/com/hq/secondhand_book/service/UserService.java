package com.hq.secondhand_book.service;


import com.hq.secondhand_book.dto.UserDto;
import com.hq.secondhand_book.entity.User;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.LoginVo;
import com.hq.secondhand_book.vo.RegisterVo;
import com.hq.secondhand_book.vo.user.UserInfoVo;


public interface UserService {
    ResultResp isUsernameExist(String username);

    boolean register(RegisterVo registerVo);

    ResultResp login(LoginVo loginVo);

    ResultResp addUser(String userName,String userPwd);

    UserInfoVo getUser(String userName);

    ResultResp updateUser(UserDto userDto);

    ResultResp userList(int page, int size);

    ResultResp findByUserNameList(String userName, int page,int size);

    ResultResp adminUserList(int page, int size);

    ResultResp findByAdminUserNameList(String userName, int page,int size);

    ResultResp deleteUser(int userId);

    ResultResp adminAddUser(int userId);

    ResultResp resetPwd(int userId);

    ResultResp findByUserId(int userId);

    ResultResp findByUserName(String userName);

    ResultResp rePwd(String userName,String userPwd, String newPwd);

    ResultResp myCount(String userName);
}
