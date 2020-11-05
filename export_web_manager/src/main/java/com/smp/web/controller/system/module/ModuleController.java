package com.smp.web.controller.system.module;

import com.github.pagehelper.PageInfo;
import com.smp.domain.Result;
import com.smp.domain.system.module.Module;
import com.smp.service.system.module.IModuleService;
import com.smp.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("system/module")
public class ModuleController extends BaseController {

    private static final Logger l= LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    IModuleService imoduleService;

    @RequestMapping(path = "/toList",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toList(@RequestParam(defaultValue = "1") int curr, @RequestParam(defaultValue = "10")int pageSize){
        //调查询分页列表的方法
        PageInfo<Module> pi=imoduleService.findByPage(curr,pageSize);
        //将pi添加到页面
        request.setAttribute("pi",pi);
        return "system/module/module-list";
    }

    @RequestMapping(path = "/toAdd",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toAdd(){
        //页面上有一个下拉菜单，需要查询所有的模块
        List<Module> list= imoduleService.findAllModules();
        //添加到request
        request.setAttribute("list",list);
        return "system/module/module-add";
    }

    @RequestMapping(path = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public  String add(Module module){
        //接收页面提交过来的表单
        l.info("add module="+module);

        imoduleService.saveModule(module);
        return "redirect:/system/module/toList.do";
    }

    @RequestMapping(path = "/toUpdate",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toUpdate(String moduleId){
        //需要使用参数接收提交的moduleId
        l.info("toUpdate moduleId="+moduleId);
        //需要根据moduleId查询当前模块的记录，回显
        Module module=imoduleService.findModuleById(moduleId);
        l.info("toUpdate module="+module);
        //回显到页面
        request.setAttribute("module",module);

        //当前有一个下拉菜单，所以给下拉菜单查模块数据
        List<Module> modules=imoduleService.findAllModules();
        request.setAttribute("modules",modules);
        return "system/module/module-update";
    }

    @RequestMapping(path = "/update",method = {RequestMethod.GET,RequestMethod.POST})
    public  String update(Module module){
        l.info("update module="+module);
        //更新
        imoduleService.updateModule(module);
        return "redirect:/system/module/toList.do";
    }
    @RequestMapping(path = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
    public  @ResponseBody
    Object delete(String moduleId){
        boolean flag=imoduleService.deleteModule(moduleId);
        if(flag){
            return Result.init(200,"删除成功",null);
        }else {
            return Result.init(-200,"不能删除当前模块，被子模块引用！！！",null);
        }

    }

}
