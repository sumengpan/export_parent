package com.smp.service.system.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.system.user.IUserDao;
import com.smp.domain.system.user.User;
import com.smp.service.system.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao iUserDao;
    @Override
    public PageInfo<User> findByPage(int curr, int pageSize,String companyId) {
        //设置参数
        PageHelper.startPage(curr,pageSize);
        //调用全查
        List<User> list = iUserDao.findAll(companyId);
        //包装成PageInfo
        PageInfo<User> pi = new PageInfo<>(list);
        return pi;
    }
    @Override
    public User findUserById(String userId) {
        return iUserDao.findById(userId);
    }

    @Override
    public boolean deleteUser(String userId) {
        iUserDao.deleteById(userId);
        return true;
    }

    @Override
    public List<User> findAllUsers(String companyId) {
        return iUserDao.findAll(companyId);
    }

    @Override
    public User findUserByEmail(String email) {
        return iUserDao.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        String uuid= UUID.randomUUID().toString();
        user.setUserId(uuid);
        iUserDao.save(user);
    }
    @Override
    public void updateUser(User user) {
        iUserDao.update(user);
    }

}
