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

    //通过companyId查询该公司的所有角色列表（给用户授予角色的时候要显示所有角色列表，勾选授权）
    List<Role> findAll(String companyId);
    //通过userid查询用户的角色列表
    List<Role> findRolesByUserId(String userId);
    //更新用户的角色 （包括删除角色(deleteUserRoleByUserId)、新增角色两个(saveUserRole)）
    void updateUserRole(String userId, String[] roleIds);

}
