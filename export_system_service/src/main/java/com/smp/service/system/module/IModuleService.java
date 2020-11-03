package com.smp.service.system.module;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.module.Module;

import java.util.List;

public interface IModuleService {
    //查看
    PageInfo<Module> findByPage(int curr, int pageSize);
    //保存
    void saveModule(Module module);
    //查找更新
    Module findModuleById(String moduleId);
    //更新
    void updateModule(Module module);
    //删除
    boolean deleteModule(String moduleId);

    List<Module> findAllModules();
}
