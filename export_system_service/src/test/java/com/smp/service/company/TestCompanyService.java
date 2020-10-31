package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")  //3:创建 spring/applicationContext-tx.xml
public class TestCompanyService {
    private static final Logger l = LoggerFactory.getLogger(TestCompanyService.class);

    @Autowired
    ICompanyService service;
    @Test
    public void test01(){
        //编写业务逻辑测试
        //左边接口，右边实现类
        //ICompanyService service= new CompanyServiceImpl();
        List<Company> list=service.findAll();
        System.out.println(list);
    }
    @Test
    public void test02(){
        //将表单数据传入数据库
        //String id, String name, Date expirationDate, String address, String licenseId, String representative, String phone, String companySize, String industry, String remarks, Integer state, Double balance, String city
        Company company=new Company("id1","name1",new Date(),"adress1","licenseId1","representative","phone","companySize","industry","remarks",0,100.0,"city");
        service.saveCompany(company);

    }
    @Test
    public void test03(){
        //删除
        String id = "7e304030-e528-4e02-92e8-d3108796371f";
        service.deleteById(id);
    }
    //修改--回显
    @Test
    public void test04(){
        //通过id查找显示，并修改
        String id = "b545a29f-7d1e-4e92-b40e-b96c75dac71d";
        Company company = service.findById(id);
        l.info(company+" ");
    }
    //修改--传值
    @Test
    public void test05(){
        String id="7f2044e6-96d2-46e9-ae7a-dcfbd81b1ab8";
        Company company = service.findById(id);
        l.info(company+" ");
        //模拟修改
        company.setCity("北京");
        l.info(company+"");
        service.updateCompany(company);
    }
    //测试分页
    @Test
    public void test06(){
        int currentPage = 1;
        int pageSize = 3;
        //PageInfo 包含四个整数 一个集合
        PageInfo<Company> pi = service.findPage(currentPage,pageSize);

        l.info("test06 pi = "+pi);
    }

}
