package com.smp.dao.system.module;

import com.smp.domain.system.module.Module;
import sun.security.timestamp.TSRequest;

import java.util.List;

public interface IModuleDao {

    List<Module> findAll();

    void save(Module module);

    void update(Module module);

    Module findById(String moduleId);

    int findParentIdCount(String moduleId);

    void deleteById(String moduleId);

    List<Module> findByRoleId(String roleId);
    void deleteRoleModule(String roleId);
    void saveRoleModule(String roleId,String mid);

    List<Module> findByBelong(String belong);//0：平台管理；1：企业管理
    List<Module> findByUserId(String userId);//使用RBAC


}
