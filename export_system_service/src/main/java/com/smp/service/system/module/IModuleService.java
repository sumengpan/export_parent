package com.smp.service.system.module;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.module.Module;

public interface IModuleService {

    PageInfo<Module> findByPage(int curr, int pageSize);

    void savemodule(Module module);

    Module findModuleById(String moduleId);

    void updatemodule(Module module);

    void deletemodule(String moduleId);
}
