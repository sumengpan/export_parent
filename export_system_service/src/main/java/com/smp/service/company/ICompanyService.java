package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.company.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll();
    //添加用户
    void saveCompany(Company company);
    //根据id进行删除
    void deleteById(String id);
    //根据id进行修改
    Company findById(String id);
    //修改
    void updateCompany(Company company);
    //分页
    PageInfo<Company> findPage(int currentPage, int pageSize);
}
