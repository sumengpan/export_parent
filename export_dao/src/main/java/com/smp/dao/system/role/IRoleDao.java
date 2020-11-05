package com.smp.dao.system.role;

import com.smp.domain.system.role.Role;

import java.util.List;

public interface IRoleDao {

    List<Role> findAll(String companyId);

    void save(Role role);

    Role findById(String roleId);

    void update(Role role);

    void deleteById(String roleId);
    //通多id查找用户
    List<Role> findByUserId(String userId);
    //通过用户id删除用户角色
    void deleteUserRoleByUserId(String userId);
    //保存用户角色
    void saveUserRole(String userId,String roleId);

}
