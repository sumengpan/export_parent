package com.smp.dao.system.user;

import com.smp.domain.system.user.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll(String companyId);

    void save(User user);

    User findById(String userId);

    void update(User user);

    void deleteById(String userId);
    User findByEmail(String email);
}
