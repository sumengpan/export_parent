package com.smp.service.company.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.company.ICompanyDao;
import com.smp.domain.company.Company;
import com.smp.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyDao iCompanyDao;
    public List<Company> findAll() {
        return iCompanyDao.findAll();
    }

    //添加用户
    @Override
    public void saveCompany(Company company) {
        //数据库id不识自增长的
        //所以用到了UUID
        String id= UUID.randomUUID().toString();
        company.setId(id);
        iCompanyDao.save(company);
    }
    //删除用户
    @Override
    public void deleteById(String id) {
        iCompanyDao.deleteById(id);
    }
    //根据id进行查询--->修改回显
    @Override
    public Company findById(String id) {
        Company company=iCompanyDao.findById(id);
        return company;
    }
    //修改传值
    @Override
    public void updateCompany(Company company) {
        //调用操作数据库
        iCompanyDao.update(company);
    }
    //分页
    @Override
    public PageInfo<Company> findPage(int currentPage, int pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        //查询由拦截器在 select * from ss_company 基础上，生成
        //select count(*) from ss_company
        //select * from ss_company limit (currentPage-1)*pageSize ,pageSize
        List<Company> list = iCompanyDao.findAll();
        //将集合封装
        PageInfo<Company> pi = new PageInfo<>(list);
        return pi;
    }
}