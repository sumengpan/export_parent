package com.smp.service.system.user;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.user.User;

import java.util.List;

public interface IUserService {

    PageInfo<User> findByPage(int curr, int pageSize, String companyId);

    void saveUser(User user);

    User findUserById(String userId);

    void updateUser(User user);

    boolean deleteUser(String userId);

    List<User> findAllUsers(String companyId);

    User findUserByEmail(String email);
}
