package com.hq.secondhand_book.controller;

import com.hq.secondhand_book.dto.UserDto;
import com.hq.secondhand_book.service.UserService;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.LoginVo;
import com.hq.secondhand_book.vo.user.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResultResp login(@RequestBody LoginVo loginVo){
        System.out.println(loginVo);
        return userService.login(loginVo);
    }

    /**
     * 根据用户名查找用户
     */
    @PostMapping("/userinfo")
    public ResultResp getUser(String userName){
        UserInfoVo vo = userService.getUser(userName);
        return Response.ok(vo);
    }

    @PostMapping("/updateuser")
    public ResultResp updateUser(UserDto userDto){
        return userService.updateUser(userDto);
    }

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/userlist")
    public ResultResp UserList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                               @RequestParam(required = false, defaultValue = "8") int pageSize){
        return userService.userList(pageIndex,pageSize);
    }

    /**
     * 我的中心数据统计
     * @return
     */
    @GetMapping("/mycount")
    public ResultResp UserList(@RequestParam String userName){
        return userService.myCount(userName);
    }

    /**
     * 通过用户名搜索
     */
    @GetMapping("/findusernamelist")
    public ResultResp finByUserNameAdminList(@RequestParam String userName,
                                             @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                             @RequestParam(required = false, defaultValue = "8") int pageSize){
        return userService.findByUserNameList(userName, pageIndex, pageSize);
    }

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/adminuserlist")
    public ResultResp adminUserList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                               @RequestParam(required = false, defaultValue = "8") int pageSize){
        return userService.adminUserList(pageIndex,pageSize);
    }

    /**
     * 通过用户名搜索
     */
    @GetMapping("/findbyadminusernamelist")
    public ResultResp findByAdminUserNameList(@RequestParam String userName,
                                             @RequestParam(required = false, defaultValue = "1") int pageIndex,
                                             @RequestParam(required = false, defaultValue = "8") int pageSize){
        return userService.findByAdminUserNameList(userName, pageIndex, pageSize);
    }

    /**
     * 通过用户编号查看详情
     */
    @GetMapping("/userdetail")
    public ResultResp findByUserId(@RequestParam String userName){
        return userService.findByUserName(userName);
    }


    /**
     * 删除用户
     */
    @DeleteMapping("/deleteuser")
    public ResultResp deleteUser(@RequestParam int userId){
        return userService.deleteUser(userId);
    }

    /**
     * 恢复用户
     */
    @GetMapping("/adduser")
    public ResultResp addUser(@RequestParam int userId){
        return userService.adminAddUser(userId);
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetpwd")
    public ResultResp resetPwd(@RequestParam int userId){
        return userService.resetPwd(userId);
    }

    /**
     * 修改密码
     */
    @GetMapping("/repwd")
    public ResultResp rePwd(@RequestParam String userName,
                            @RequestParam String userPwd,
                            @RequestParam String newPwd){
        return userService.rePwd(userName,userPwd,newPwd);
    }

    @PostMapping("/findName")
    public ResultResp findNmae(@RequestParam String userName){
        System.out.println(userName);
        return userService.isUsernameExist(userName);
    }

    /**
     * 添加用户
     */
    @GetMapping("/adduserName")
    public ResultResp adduserName(@RequestParam String userName,
                                  @RequestParam String userPwd ){
        return userService.addUser(userName, userPwd);
    }
}
