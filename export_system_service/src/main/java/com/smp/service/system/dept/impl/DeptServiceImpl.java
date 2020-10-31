package com.smp.service.system.dept.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.system.dept.IDeptDao;
import com.smp.domain.system.dept.Dept;
import com.smp.service.system.dept.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    IDeptDao iDeptDao;
    @Override
    public PageInfo<Dept> findByPage(int curr, int pageSize, String companyId) {
        //调用dao查看所有的记录
        PageHelper.startPage(curr,pageSize);
        List<Dept> list=iDeptDao.findAll(companyId);
        return new PageInfo<>(list);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        List<Dept> list=iDeptDao.findAll(companyId);
        return list;
    }
    //新建一个部门
    @Override
    public void saveDept(Dept dept) {
        //获取随机id
        String id= UUID.randomUUID().toString();
        dept.setDeptId(id);

        iDeptDao.save(dept);
    }
    //查找指定id的部门
    @Override
    public Dept findById(String deptId) {
        return iDeptDao.findById(deptId);
    }
    //与保存的区别 》1：前者insert 后者是update  》2：前者需要产生id，后者有id
    @Override
    public void updateDept(Dept dept) {
        iDeptDao.update(dept);
    }

    @Override
    public boolean deleteDeptById(String deptId) {
        //先查询count
        int count = iDeptDao.findParentCount(deptId);
        //再根据count判断
        if(count==0){//没有给其他部门作上级
            iDeptDao.deleteById(deptId);
            return true;
        }else{
            return false;
        }
    }

}
