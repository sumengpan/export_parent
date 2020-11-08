package com.smp.dao.system.syslog;

import com.smp.domain.system.syslog.SysLog;

import java.util.List;

public interface ISysLogDao {
    List<SysLog> findAll(String companyId);
    void save(SysLog sysLog);
}
