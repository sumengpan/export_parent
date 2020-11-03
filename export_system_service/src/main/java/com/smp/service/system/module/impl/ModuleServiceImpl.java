package com.smp.service.system.module.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.system.module.IModuleDao;
import com.smp.domain.system.module.Module;
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

        return iModuleDao.findById(moduleId);
    }

    @Override
    public void updateModule(Module module) {
        iModuleDao.update(module);
    }

    @Override
    public boolean deleteModule(String moduleId) {
        //iModuleDao.deleteById(moduleId);
        //删除一个父点，要先查询该节点的子节点数量 是==0还是>0。
        //==0 表示没有子节点，删除之后对其他的数据没有影响
        //> 0 表示有子节点，删除会影响到其他数据的parentId找不到数据
        int count = iModuleDao.findParentIdCount(moduleId);
        if (count==0){
            //没有子节点，随便删除
            iModuleDao.deleteById(moduleId);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Module> findAllModules() {
        return iModuleDao.findAll();
    }
}
