package com.hq.secondhand_book.repository;

import com.hq.secondhand_book.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositiry extends JpaRepository<User,Integer>  {
    User getByUserNameAndUserPwdAndUserRoleAndUsable(String userName, String userPwd,Integer userRole, Integer usable);

    User getByUserName(String userName);

    Optional<User> getByUserNameAndUsable(String userName, Integer usable);

    List<User> findAll();

    User findByUserName(String userName);

    User findByIdAndUsable(int id, int usable);

    User findByUserNameAndUsable(String userName, int usable);

    User getById(int id);

    List<User> findAllByUserRole(int userRole);

    Page<User> findAllByUserRole(int userRole,Pageable pageable);

    Page<User> findByUserNameContainingAndUserRoleAndUsable(String userName,int userRole,int usable,Pageable pageable);

    List<User> findByUserNameContainingAndUserRoleAndUsable(String userName,int userRole,int usable);
}
