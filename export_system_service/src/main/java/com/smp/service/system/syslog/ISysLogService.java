package com.smp.service.system.syslog;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.syslog.SysLog;

public interface ISysLogService {
    PageInfo<SysLog> findByPage(int curr,int pageSize,String companyId);
    void saveSysLog(SysLog sysLog);
}
