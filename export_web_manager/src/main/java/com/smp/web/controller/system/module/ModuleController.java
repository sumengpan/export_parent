package com.smp.web.controller.system.module;

import com.github.pagehelper.PageInfo;
import com.smp.domain.Result;
import com.smp.domain.system.module.Module;
import com.smp.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("system/module")
public class ModuleController extends BaseController {

    private static final Logger l= LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    //IModuleService imoduleService;

    @RequestMapping(path = "/toList",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toList(){
        //调查询分页列表的方法
        /*PageInfo<Module> pi=imoduleService.findByPage(curr,pageSize,getLoginCompanyId());
        //将pi添加到页面
        request.setAttribute("pi",pi);*/
        return "system/module/module-list";
    }

    @RequestMapping(path = "/toAdd",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toAdd(){
        return "system/module/module-add";
    }

    @RequestMapping(path = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public  String add(Module module){
        //接收页面提交过来的表单
        /*l.info("add module="+module);
        module.setCompanyId(getLoginCompanyId());
        module.setCompanyName(getLoginCompanyName());
        imoduleService.savemodule(module);*/
        return "redirect:/system/module/toList.do";
    }

    @RequestMapping(path = "/toUpdate",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toUpdate(String moduleId){
        /*//需要使用参数接收提交的moduleId
        module module=imoduleService.findById(moduleId);
        l.info("toUpdate module="+module);
        //回显到页面
        request.setAttribute("module",module);*/
        return "system/module/module-update";
    }

    @RequestMapping(path = "/update",method = {RequestMethod.GET,RequestMethod.POST})
    public  String update(Module module){
        /*l.info("update module="+module);

        module.setCompanyId(getLoginCompanyId());
        module.setCompanyName(getLoginCompanyName());
        imoduleService.updatemodule(module);*/
        return "redirect:/system/module/toList.do";
    }
    /*@RequestMapping(path = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
    public  @ResponseBody
    Object delete(String moduleId){
        try {
            imoduleService.deletemodule(moduleId);
            //成功
            return Result.init(200,"删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return Result.init(-200,"删除失败",null);
        }

    }*/

}
