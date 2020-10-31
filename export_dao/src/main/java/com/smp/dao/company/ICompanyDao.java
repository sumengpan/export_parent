package com.smp.dao.company;

import com.smp.domain.company.Company;

import java.util.List;

public interface ICompanyDao {

    //查询所有的公司纪录
    //select * from ss_company;
    List<Company> findAll();
    //添加用户
    void save(Company company);
    //删除用户
    //delete from ss_company where id=#{id}
    void deleteById(String id);
    //修改查找回显
    Company findById(String id);
    //修改传值
    void update(Company company);
}
