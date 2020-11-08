package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.syslog.SysLog;
import com.smp.service.system.syslog.ISysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestSysLogService {
    private static final Logger l = LoggerFactory.getLogger(TestSysLogService.class);
    @Autowired
    ISysLogService iSysLogService;
    //增*删改查*
    @Test
    public void test01(){
        //分页列表
        //页面上显示分页列表，就要求业务方法中提供查询PageInfo的方法
        PageInfo<SysLog> pi= iSysLogService.findByPage(1,3,"1");
        l.info("pi = "+pi);
    }
    @Test
    public void test02(){

        //将一个表单数据保存在javaBean中，再将javaBean存到数据库
        SysLog sysLog = new SysLog();
        //设置登录用户信息
        sysLog.setUserName("老玉");
        //设置企业信息
        sysLog.setCompanyId("1");
        sysLog.setCompanyName("吉首大学");
        //IP地址
        sysLog.setIp("192.168.10.11");
        //设置记录时间
        sysLog.setTime(new Date());
        //执行的方法名称
        sysLog.setMethod("toList");
        //执行的类名称
        sysLog.setAction("com.smp.web.company.CompanyController");

        sysLog.setCompanyId("1");
        sysLog.setCompanyName("吉首大学");
        iSysLogService.saveSysLog(sysLog);

    }


}
