package com.smp.service.system.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.system.user.IUserDao;
import com.smp.domain.system.user.User;
import com.smp.service.system.user.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private Logger l= LoggerFactory.getLogger(this.getClass());
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
        //原来保存用户使用的密码是明文，现在需要对它进行加密
        if(user.getPassword()!=null){
            Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getEmail());//参1 明文  参2 盐
            user.setPassword(md5Hash.toString());
        }
        l.info("saveUser  "+user);
        iUserDao.save(user);
    }
    @Override
    public void updateUser(User user) {
        iUserDao.update(user);
    }

}
