package com.smp.web.controller.system.syslog;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.syslog.SysLog;
import com.smp.service.system.syslog.ISysLogService;
import com.smp.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system/syslog")
public class SysLogController extends BaseController {
    private static final Logger l= LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    ISysLogService iSysLogService;

    @RequestMapping(path = "/toList",method = {RequestMethod.GET,RequestMethod.POST})
    public String toList(@RequestParam(defaultValue = "1") int curr,
                         @RequestParam(defaultValue = "15") int pageSize){
        //调查分页列表的方法
        PageInfo<SysLog> pi=iSysLogService.findByPage(curr,pageSize,getLoginCompanyId());
        //将pi添加到页面
        request.setAttribute("pi",pi);
        return "system/syslog/log-list";
    }
}
