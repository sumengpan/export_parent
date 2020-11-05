package com.smp.service.system.role;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.role.Role;

import java.util.List;

public interface IRoleService {
    PageInfo<Role> findByPage(int curr, int pageSize, String companyId);

    
    void saveRole(Role role);

    Role findById(String roleId);

    void updateRole(Role role);

    void deleteRole(String roleId);

    List<Role> findAll(String companyId);

    List<Role> findRolesByUserId(String userId);

    void updateUserRole(String userId, String[] roleIds);
}
