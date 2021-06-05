package com.hq.secondhand_book.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   //用户编号

    @Column(name = "user_name")
    private String userName;   //用户名

    @Column(name = "user_real_name")
    private String userRelName;   //用户真实姓名

    @Column(name = "user_student_id")
    private String userStuId;   //用户学号

    @Column(name = "user_sex")
    private String userSex;   //用户性别

    @Column(name = "user_birthday")
    private String userBirthday;   //用户出生年月年月

    @Column(name = "user_pwd")
    private String userPwd;   //用户密码

    @Column(name = "user_tel")
    private String userTel;   //用户电话

    @Column(name = "user_email")
    private String userEmail;      //用户邮箱

    @Column(name = "user_role")
    private Integer userRole;   //用户角色

    @Column(name = "user_pic")
    private String userPic;   //用户头像

    @Column(name = "is_usable")
    private Integer usable;   //是否可用：0-否 1-是

    @Column(name = "cst_create")
    private Date cstCreate;   //数据的创建时间

    @Column(name = "cst_modify")
    private Date cstModify;   //数据的修改时间
}
