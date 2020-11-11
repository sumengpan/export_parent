package com.smp.service.company.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.company.ICompanyDao;
import com.smp.domain.company.Company;
import com.smp.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Service //为了当前的实现类可以被rpc调用，要使用dubbo的@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyDao iCompanyDao;
    public List<Company> findAll() {//service要调用dao查询数据，所以要注入
        return iCompanyDao.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        //当前数据库的id不是自增长的。
        //02e1da04-43f8-42e1-a4c2-66e162c6f4a5 uuid 全球唯一
        String id = UUID.randomUUID().toString();
        company.setId(id);
        iCompanyDao.save(company);
    }

    @Override
    public void deleteById(String id) {
        iCompanyDao.deleteById(id);
    }

    @Override
    public Company findById(String id) {
        Company company = iCompanyDao.findById(id);
        return company;
    }

    @Override
    public void updateCompany(Company company) {
        //调用dao操作数据库
        iCompanyDao.update(company);
    }

    @Override
    public PageInfo<Company> findPage(int currentPage, int pageSize) {
        //设置参数
        PageHelper.startPage(currentPage,pageSize);
        //查询由拦截器在 select * from ss_company 基础上，生成
        //select count(*) from ss_company
        //select * from ss_company limit (currentPage-1)*pageSize ,pageSize
        List<Company> list = iCompanyDao.findAll();
        //将集合封装
        PageInfo<Company> pi = new PageInfo<>(list);
        return pi;
    }
}
