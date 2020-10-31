package com.smp.dao.system.module;

import com.smp.domain.system.module.Module;

import java.util.List;

public interface IModuleDao {

    List<Module> findAll();

    void save(Module module);

    void update(Module module);
}
