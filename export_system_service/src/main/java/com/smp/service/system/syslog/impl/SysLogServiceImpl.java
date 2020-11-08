package com.smp.service.system.syslog.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smp.dao.system.syslog.ISysLogDao;
import com.smp.domain.system.syslog.SysLog;
import com.smp.service.system.syslog.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysLogServiceImpl implements ISysLogService {
    //service调用dao
    @Autowired
    ISysLogDao iSysLogDao;
    @Override
    public PageInfo<SysLog> findByPage(int curr, int pageSize, String companyId) {
        //设置参数
        PageHelper.startPage(curr,pageSize);
        //调用全查
        List<SysLog> list = iSysLogDao.findAll(companyId);
        //包装成PageInfo
        PageInfo<SysLog> pi = new PageInfo<>(list);
        return pi;
    }

    @Override
    public void saveSysLog(SysLog sysLog) {
        String uuid= UUID.randomUUID().toString();
        sysLog.setId(uuid);
        iSysLogDao.save(sysLog);
    }

}
