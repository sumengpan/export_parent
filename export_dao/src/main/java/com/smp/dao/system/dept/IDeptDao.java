package com.smp.dao.system.dept;

import com.smp.domain.system.dept.Dept;

import java.util.List;

public interface IDeptDao {

    List<Dept> findAll(String companyId);

    Dept findById(String deptId);
    //新建一个部门
    void save(Dept dept);
    //保存编辑页面的部门
    void update(Dept dept);

    int findParentCount(String deptId);
    void deleteById(String deptId);
}
