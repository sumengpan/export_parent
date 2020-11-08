package com.smp.web.utils;

import com.smp.domain.system.syslog.SysLog;
import com.smp.domain.system.user.User;
import com.smp.service.system.syslog.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

//第一步：编写切面类
@Aspect  //配置了aop逻辑
@Component //非Controller，Service Repository
public class LogAspect {
    private Logger l = LoggerFactory.getLogger(LogAspect.class);

    public LogAspect() {
        l.info("LogAspect 无参构造方法执行");
    }

    //要对所有的Controller的方法进行配置
    //指定包名 controller 下以及它的所有子包
    //
    @Around(value = "execution(* com.smp.web.controller..*.*Controller.*(..))")
    public Object writeLog(ProceedingJoinPoint jp) {//切点
        // jp表示Controller中的任意方法 toList toAdd toUpdate add update delete
        //逻辑
        Object result = null;//返回一个表示页面的字符串，也可通是json数据
        try {
            //前置
            result = jp.proceed();//执行
            //后置
            //保存日志
            l.info("切面:writeLog");
            saveSysLog(jp);
        } catch (Throwable e) {
            //异常
        } finally {
            //最终
        }
        return result;
    }

    @Autowired
    ISysLogService iSysLogService;

    //登录成功之后session中是保存一个user对象的
    @Autowired
    HttpSession session;

    //request对象可以直接获取对方浏览器的IP
    @Autowired
    HttpServletRequest request;

    private void saveSysLog(ProceedingJoinPoint jp) {
        //将一个表单数据保存在javaBean中，再将javaBean存到数据库
        SysLog sysLog = new SysLog();

        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            //设置登录用户信息
            sysLog.setUserName(user.getUserName());
            //设置企业信息
            sysLog.setCompanyId(user.getCompanyId());
            sysLog.setCompanyName(user.getCompanyName());
        }

        //IP地址  request.getRemoteAddr()获取请求中的ip地址
        sysLog.setIp(request.getRemoteAddr());
        //设置记录时间
        sysLog.setTime(new Date());

        //执行的方法名称  jp.getSignature() 当前执行的方法 toList
        sysLog.setMethod(jp.getSignature().getName());

        //执行的类名称 jp.getTarget()目标对象
        sysLog.setAction(jp.getTarget().getClass().getName());

        l.info("saveSysLog sysLog " + sysLog);
        iSysLogService.saveSysLog(sysLog);
    }
}