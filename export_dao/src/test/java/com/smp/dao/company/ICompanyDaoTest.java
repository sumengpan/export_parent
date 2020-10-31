package com.smp.dao.company;

import com.smp.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class ICompanyDaoTest {
    @Autowired
    ICompanyDao iCompanyDao;
    @Test
    public void findAll(){
        System.out.println(iCompanyDao);
        List<Company> list=iCompanyDao.findAll();
        System.out.println(list);
    }
}
