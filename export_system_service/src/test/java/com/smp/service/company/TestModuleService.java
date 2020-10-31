package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.module.Module;
import com.smp.service.system.module.IModuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        String moduleId ="ad3be6e7-551f-45b6-b816-b2b751e204ed";
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
        iModuleService.deleteModule(moduleId);
    }


}
