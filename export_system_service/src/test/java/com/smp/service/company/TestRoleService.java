package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.role.Role;
import com.smp.service.system.role.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")  //3:创建 spring/applicationContext-tx.xml
public class TestRoleService {
    private static final Logger l= LoggerFactory.getLogger(TestRoleService.class);


    @Autowired
    IRoleService iRoleService;
    /*增删改查*/
    @Test
    public void test01(){
        //分页列表
        //页面上显示分页列表
        PageInfo<Role> pi=iRoleService.findByPage(1,3,"1");
        l.info("pi="+pi);
    }
    @Test
    public void test02(){
        //将一个表单数据保存在javaBean中，再存到数据库
       Role role=new Role();
       role.setName("角色1");
       role.setRemark("角色1的备注");
       role.setCompanyId("1");
       role.setCompanyName("吉首大学");
       iRoleService.saveRole(role);
       //l.info("pi="+pi);
    }
    @Test
    public void test03(){
    //更新业务，先根据id查找对应记录，编辑值，在保存到数据库
        String roleId ="";
        Role role=iRoleService.findById(roleId);
        l.info("role="+role);
        //修改
        role.setName("角色2");
        role.setRemark("角色2备注");
        //保存
        iRoleService.updateRole(role);
    }
    @Test
    public void test04(){
        //删除业务，根据指定id
        String roleId="33";
        //删除
        iRoleService.deleteRole(roleId);
    }

}
