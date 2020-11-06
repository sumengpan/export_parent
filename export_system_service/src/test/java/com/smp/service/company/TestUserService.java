package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.user.User;
import com.smp.service.system.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestUserService {
    private static final Logger l = LoggerFactory.getLogger(TestUserService.class);

    @Autowired
    IUserService iUserService;

    //增*删改查*
    @Test
    public void test01() {
        //分页列表
        //页面上显示分页列表，就要求业务方法中提供查询PageInfo的方法
        String companyId = "1";
        //作为一个公司用户的管理员，只能查自己公司的员工。
        PageInfo<User> pi = iUserService.findByPage(1, 3, companyId);
        l.info("pi = " + pi);
    }

    @Test
    public void test02() {
        //
        //将一个表单数据保存在javaBean中，再将javaBean存到数据库
        User user = new User();
        user.setUserName("用户rose");
        user.setDeptId("97f88a8c-90fc-4d52-aed7-2046f62fb498");
        iUserService.saveUser(user);

    }

    @Test
    public void test03() {
        //
        //更新业务  先根据id查找出对应的一条记录，编辑它的值，再将记录保存到数据库中

        String userId = "b891b14b-1316-4a79-8b88-695be1e3f42f";
        User user = iUserService.findUserById(userId);
        l.info("user=" + user);
        //修改
        user.setUserName("user jack");
        //保存
        iUserService.updateUser(user);

    }

    @Test
    public void test04() {
        //
        //删除业务，就是根据指定的id，删除数据库中的记录
        String userId = "11111";
        //删除
        boolean flag = iUserService.deleteUser(userId);
        l.info(flag + "");

    }

    @Test
    public void test05() {
        //A公司管理员，只能查A公司用户或者员工
        String companyId = "1";
        List<User> list = iUserService.findAllUsers(companyId);
        l.info("pi = " + list);
    }

    @Test
    public void test06() {
        //根据 email查询对应的用户
        String email = "lw@export.com";
        String password = "123";
        User user = iUserService.findUserByEmail(email);
        l.info("test06 user " + user);
        if (user != null) {
            //1:找到
            //比较账号密码
            if (user.getPassword().equals(password)) {
                //正确
                l.info("正确");
            } else {
                //密码不对
                l.info("密码不对");
            }
        } else {
            //2:没找到
            //用户不存在
            l.info("用户不存在");
        }
    }
}
