package com.smp.service.system.dept;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.dept.Dept;

import java.util.List;

public interface IDeptService {
    //查询指定公司id的第几个部门分页
    PageInfo<Dept> findByPage(int curr, int pageSize, String companyId);

    List<Dept> findAll(String companyId);
    //新建一个部门
    void saveDept(Dept dept);
    //查找指定id的部门
    Dept findById(String deptId);
    //保存编辑页面的部门数据
    void updateDept(Dept dept);
    //根据指定的deptId删除部门数据 当前部门有没有给其他部门作上级
    // 》1可以直接删除 》2删除报错
    boolean deleteDeptById(String deptId);
}