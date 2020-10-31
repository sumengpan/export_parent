package com.smp.service.system.module.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.system.module.IModuleDao;
import com.smp.domain.system.module.Module;
import com.smp.domain.system.role.Role;
import com.smp.service.system.module.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements IModuleService {
    @Autowired
    IModuleDao iModuleDao;
    @Override
    public PageInfo<Module> findByPage(int curr, int pageSize) {
            //设置参数
            PageHelper.startPage(curr,pageSize);
            //调用全查
            List<Module> list=iModuleDao.findAll();
            //包装成pageInfo
            PageInfo<Module> pi= new PageInfo<>(list);
            return pi;
    }

    @Override
    public void saveModule(Module module) {
        String uuid= UUID.randomUUID().toString();
        module.setModuleId(uuid);
        iModuleDao.save(module);
    }

    @Override
    public Module findModuleById(String moduleId) {

        return null;
    }

    @Override
    public void updateModule(Module module) {
        iModuleDao.update(module);
    }

    @Override
    public void deleteModule(String moduleId) {

    }
}
