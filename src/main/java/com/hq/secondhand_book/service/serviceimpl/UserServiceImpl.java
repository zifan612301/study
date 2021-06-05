package com.hq.secondhand_book.service.serviceimpl;

import com.hq.secondhand_book.dto.UserDto;
import com.hq.secondhand_book.entity.*;
import com.hq.secondhand_book.repository.*;
import com.hq.secondhand_book.service.UserService;
import com.hq.secondhand_book.util.Constant;
import com.hq.secondhand_book.util.resp.Response;
import com.hq.secondhand_book.util.resp.ResultResp;
import com.hq.secondhand_book.vo.LoginVo;
import com.hq.secondhand_book.vo.RegisterVo;
import com.hq.secondhand_book.vo.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepositiry userRepositiry;
    @Resource
    ReceivingAddressRepository receivingAddressRepository;
    @Resource
    CollectionRepository collectionRepository;
    @Resource
    BookRepository bookRepository;
    @Resource
    OrderFormRepository orderFormRepository;

    @Override
    public ResultResp isUsernameExist(String username) {
        User user = userRepositiry.getByUserName(username);
        if( user != null){
            return Response.dataErr("用户名已存在");
        }
        return Response.ok("用户名可以使用");
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        return false;
    }

    //用户名密码正确且用户状态正常可登录
    @Override
    public ResultResp login(LoginVo loginVo) {
        int userType = 0;
        if("管理员".equals(loginVo.getUserType())){
            userType = 1;
        }
        User user = userRepositiry.getByUserNameAndUserPwdAndUserRoleAndUsable(loginVo.getUserName(),loginVo.getUserPwd(),userType, Constant.USABLE);
        if(user != null){
            return Response.ok(user);
        }
        return Response.dataErr("用户名或密码错误");
    }

    @Override
    public ResultResp addUser(String userName, String userPwd) {
        User u = userRepositiry.getByUserName(userName);
        if(u!=null){
            return Response.dataErr("用户名已存在");
        }else {
            User  user= new User();
            user.setUserName(userName);
            user.setUserRole(Constant.MEMBER);
            user.setUserPwd(userPwd);
            user.setUsable(Constant.USABLE);
            user.setUserPic("default.jpg");
            user.setCstModify(new Date());
            user.setCstCreate(new Date());
            userRepositiry.saveAndFlush(user);
            return Response.ok();
        }
    }
    /**
     *根据用户名查找用户
     */
    @Override
    public UserInfoVo getUser(String userName) {
        UserInfoVo userInfoVo = new UserInfoVo();
        Optional<User> optionalUser = userRepositiry.getByUserNameAndUsable(userName,Constant.USABLE);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String url = "../picture/user/"+user.getUserPic();
            userInfoVo = new UserInfoVo(user.getUserName(),user.getUserRelName(),user.getUserStuId(),user.getUserBirthday(),
                    user.getUserSex(),user.getUserTel(),user.getUserEmail(),url);
        }
        return userInfoVo ;
    }

    /**
     * 更新用户
     * @param userDto
     */
    @Override
    public ResultResp updateUser(UserDto userDto) {
        Optional<User> optionalUser = userRepositiry.getByUserNameAndUsable(userDto.getUserName(),1);
        if(optionalUser.isPresent()){
            User user =optionalUser.get();
            user.setUserRelName(userDto.getUserRealName());
            user.setUserBirthday(userDto.getUserBirthday());
            user.setUserSex(userDto.getUserSex());
            user.setUserTel(userDto.getUserTel());
            user.setUserEmail(userDto.getUserEmail());
            userRepositiry.saveAndFlush(user);
            return Response.ok(user);
        }
        return Response.dataErr("用户不存在");
    }

    @Override
    public ResultResp userList(int page, int size) {
        List<UserListVo> resultList=new ArrayList<>();
        UserPageVo userPageVo=new UserPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<User> pager = userRepositiry.findAllByUserRole(Constant.MEMBER,pageable);
        List<User> books = userRepositiry.findAllByUserRole(Constant.MEMBER);
        List<User> list=pager.getContent();
        if (!list.isEmpty()){
            for(User user:list){
                UserListVo userListVo=new UserListVo();
                userListVo.setUserId(user.getId());
                userListVo.setUserName(user.getUserName());
                userListVo.setUserEmail(user.getUserEmail());
                userListVo.setUserBirthday(user.getUserBirthday());
                userListVo.setUserSex(user.getUserSex());
                if(user.getUsable()==Constant.USABLE){
                    userListVo.setUserStaus("正常");
                }else {
                    userListVo.setUserStaus("失效");
                }
                resultList.add(userListVo);
            }
        }
        userPageVo.setList(resultList);
        userPageVo.setRowCount(books.size());
        return Response.ok(userPageVo);
    }

    @Override
    public ResultResp findByUserNameList(String userName, int page, int size) {
        List<UserListVo> resultList=new ArrayList<>();
        UserPageVo userPageVo=new UserPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<User> pager = userRepositiry.findByUserNameContainingAndUserRoleAndUsable(userName,Constant.MEMBER, Constant.USABLE, pageable);
        List<User> books = userRepositiry.findByUserNameContainingAndUserRoleAndUsable(userName,Constant.MEMBER, Constant.USABLE);
        List<User> list=pager.getContent();
        if (!list.isEmpty()){
            for(User user:list){
                UserListVo userListVo=new UserListVo();
                userListVo.setUserId(user.getId());
                userListVo.setUserName(user.getUserName());
                userListVo.setUserEmail(user.getUserEmail());
                userListVo.setUserBirthday(user.getUserBirthday());
                userListVo.setUserSex(user.getUserSex());
                if(user.getUsable()==Constant.USABLE){
                    userListVo.setUserStaus("正常");
                }else {
                    userListVo.setUserStaus("失效");
                }
                resultList.add(userListVo);
            }
        }
        userPageVo.setList(resultList);
        userPageVo.setRowCount(books.size());
        return Response.ok(userPageVo);
    }

    @Override
    public ResultResp adminUserList(int page, int size) {
        List<UserListVo> resultList=new ArrayList<>();
        UserPageVo userPageVo=new UserPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<User> pager = userRepositiry.findAllByUserRole(Constant.ADMIN,pageable);
        List<User> books = userRepositiry.findAllByUserRole(Constant.ADMIN);
        List<User> list=pager.getContent();
        if (!list.isEmpty()){
            for(User user:list){
                UserListVo userListVo=new UserListVo();
                userListVo.setUserId(user.getId());
                userListVo.setUserName(user.getUserName());
                userListVo.setUserEmail(user.getUserEmail());
                userListVo.setUserBirthday(user.getUserBirthday());
                userListVo.setUserSex(user.getUserSex());
                if(user.getUsable()==Constant.USABLE){
                    userListVo.setUserStaus("正常");
                }else {
                    userListVo.setUserStaus("失效");
                }
                resultList.add(userListVo);
            }
        }
        userPageVo.setList(resultList);
        userPageVo.setRowCount(books.size());
        return Response.ok(userPageVo);
    }

    @Override
    public ResultResp findByAdminUserNameList(String userName, int page, int size) {
        List<UserListVo> resultList=new ArrayList<>();
        UserPageVo userPageVo=new UserPageVo();
        if(page<1){
            return Response.dataErr("页码数不能小于1");
        }
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"cstModify");
        Page<User> pager = userRepositiry.findByUserNameContainingAndUserRoleAndUsable(userName,Constant.ADMIN, Constant.USABLE, pageable);
        List<User> books = userRepositiry.findByUserNameContainingAndUserRoleAndUsable(userName,Constant.ADMIN, Constant.USABLE);
        List<User> list=pager.getContent();
        if (!list.isEmpty()){
            for(User user:list){
                UserListVo userListVo=new UserListVo();
                userListVo.setUserId(user.getId());
                userListVo.setUserName(user.getUserName());
                userListVo.setUserEmail(user.getUserEmail());
                userListVo.setUserBirthday(user.getUserBirthday());
                userListVo.setUserSex(user.getUserSex());
                if(user.getUsable()==Constant.USABLE){
                    userListVo.setUserStaus("正常");
                }else {
                    userListVo.setUserStaus("失效");
                }
                resultList.add(userListVo);
            }
        }
        userPageVo.setList(resultList);
        userPageVo.setRowCount(books.size());
        return Response.ok(userPageVo);
    }

    @Override
    public ResultResp deleteUser(int userId) {
        User user = userRepositiry.getById(userId);
        if(user != null){
            user.setUsable(Constant.UN_USABLE);
            userRepositiry.saveAndFlush(user);
        }
        return Response.ok();
    }

    @Override
    public ResultResp adminAddUser(int userId) {
        User user = userRepositiry.getById(userId);
        if(user != null){
            user.setUsable(Constant.USABLE);
            userRepositiry.saveAndFlush(user);
        }
        return Response.ok();
    }

    @Override
    public ResultResp resetPwd(int userId) {
        User user = userRepositiry.getById(userId);
        if(user != null){
            user.setUserPwd("123456");
            userRepositiry.saveAndFlush(user);
        }
        return Response.ok();
    }

    @Override
    public ResultResp findByUserId(int userId) {
        User user = userRepositiry.getById(userId);
        UserDetailVo userDetailVo = new UserDetailVo();
        if(user!= null){
            userDetailVo.setUserName(user.getUserName());
            userDetailVo.setUserSex(user.getUserSex());
            userDetailVo.setUserRealName(user.getUserRelName());
            userDetailVo.setUserStuId(user.getUserStuId());
            String pic = "../picture/user/"+ user.getUserPic();
            userDetailVo.setUserPic(pic);
            userDetailVo.setUserBirthday(user.getUserBirthday());
            userDetailVo.setUserEmail(user.getUserEmail());
            userDetailVo.setUserTel(user.getUserTel());
            if(user.getUsable()==Constant.USABLE){
                userDetailVo.setUserStatus("正常");
            }else {
                userDetailVo.setUserStatus("失效");
            }
            if(user.getUserRole()==Constant.MEMBER){
                userDetailVo.setUserRole("普通会员");
            }else if(user.getUserRole()==Constant.ADMIN){
                userDetailVo.setUserRole("管理员");
            }
        }
        return Response.ok(userDetailVo);
    }

    @Override
    public ResultResp findByUserName(String userName) {
        User user = userRepositiry.getByUserName(userName);
        UserDetailVo userDetailVo = new UserDetailVo();
        if(user!= null){
            userDetailVo.setUserName(user.getUserName());
            userDetailVo.setUserSex(user.getUserSex());
            userDetailVo.setUserRealName(user.getUserRelName());
            userDetailVo.setUserStuId(user.getUserStuId());
            String pic = "../picture/user/"+ user.getUserPic();
            userDetailVo.setUserPic(pic);
            userDetailVo.setUserBirthday(user.getUserBirthday());
            userDetailVo.setUserEmail(user.getUserEmail());
            userDetailVo.setUserTel(user.getUserTel());
            if(user.getUsable()==Constant.USABLE){
                userDetailVo.setUserStatus("正常");
            }else {
                userDetailVo.setUserStatus("失效");
            }
            if(user.getUserRole()==Constant.MEMBER){
                userDetailVo.setUserRole("普通会员");
            }else if(user.getUserRole()==Constant.ADMIN){
                userDetailVo.setUserRole("管理员");
            }
        }
        return Response.ok(userDetailVo);
    }

    @Override
    public ResultResp rePwd(String userName, String userPwd, String newPwd) {
        Optional<User> optionalUser = userRepositiry.getByUserNameAndUsable(userName,Constant.USABLE);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getUserPwd().equals(userPwd)){
                user.setUserPwd(newPwd);
                userRepositiry.saveAndFlush(user);
                return Response.ok(user);
            }else {
                return Response.dataErr("原密码错误");
            }
        }
        return Response.dataErr("用户不存在");
    }

    @Override
    public ResultResp myCount(String userName) {
        CountVo countVo = new CountVo();
        User user = userRepositiry.findByUserName(userName);
        if( user!= null) {
            int userId = user.getId();
            List<ReceivingAddress> addresses = receivingAddressRepository.getByUserIdAndUsable(userId, Constant.USABLE);
            countVo.setAdressNum(addresses.size());
            List<Collection> collections = collectionRepository.getByUserIdAndUsable(userId,Constant.USABLE);
            countVo.setCollectionNum(collections.size());
            List<Book> books = bookRepository.getByUserId(userId);
            countVo.setSellBookNum(books.size());
            List<OrderForm> orders = orderFormRepository.findByUserIdAndUsable(userId,Constant.USABLE);
            countVo.setOrderNum(orders.size());
            return Response.ok(countVo);
        }
        return Response.dataErr("数据找不到");
    }
}
