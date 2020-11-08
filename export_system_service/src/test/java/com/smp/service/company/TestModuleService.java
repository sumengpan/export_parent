package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.module.Module;
import com.smp.domain.system.user.User;
import com.smp.service.system.module.IModuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")  //3:创建 spring/applicationContext-tx.xml
public class TestModuleService {
    private static final Logger l= LoggerFactory.getLogger(TestModuleService.class);


    @Autowired
    IModuleService iModuleService;
    /*增删改查*/
    @Test
    public void test01(){
        //分页列表
        //页面上显示分页列表
        PageInfo<Module> pi=iModuleService.findByPage(1,3);
        l.info("pi="+pi);
    }

    @Test
    public void test02(){
        //将一个表单数据保存在javaBean中，再存到数据库
       Module module=new Module();
       module.setName("模块1");

       iModuleService.saveModule(module);
       //l.info("pi="+pi);
    }
    @Test
    public void test03(){
    //更新业务，先根据id查找对应记录，编辑值，在保存到数据库
        String moduleId ="62c977ed-9ebd-41f4-b963-055873947f4b";
        Module module=iModuleService.findModuleById(moduleId);
        l.info("module="+module);
        //修改
        module.setName("模块2");

        //保存
        iModuleService.updateModule(module);
    }
    @Test
    public void test04(){
        //删除业务，根据指定id
        String moduleId="9c5eb9b0-54a4-48bb-aab4-0d1d46cdfbbd";
        //删除
        boolean flag=iModuleService.deleteModule(moduleId);
        l.info(flag+"");
    }
    @Test
    public void test05(){
        List<Module> list=iModuleService.findAllModules();
        l.info("pi="+list);
    }

    //角色权限时，正确的是3 个
    @Test
    public void test06(){
        List<Module> mylist=iModuleService.findModuleByRoleId("2");
        l.info("mylist="+mylist);
    }
    //角色B的权限，更新权限
    @Test
    public void test07(){
        //修改一个角色的权限，不仅仅是添加，也可能是减少
        String roleId ="0ae3657e-2042-4b9b-b1e9-8d2ef81f63ee";
        String moduleIds="201,202,203";
        iModuleService.updateRoleModule(roleId,moduleIds);
    }
    @Test
    public void test08(){
        User user=new User();
        user.setUserId("0f1f71fe-fe7c-4a44-a952-4f08bf5aa990");
        //user.setDegree(0);
        //user.setDegree(1);//企业管理员
        user.setDegree(4);//普通用户
        List<Module> menus=iModuleService.findModuleByUser(user);
        l.info("test08 menus="+menus);
    }
}
