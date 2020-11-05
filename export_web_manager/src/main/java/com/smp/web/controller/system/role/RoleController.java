package com.smp.web.controller.system.role;

import com.github.pagehelper.PageInfo;
import com.smp.domain.Result;
import com.smp.domain.system.module.Module;
import com.smp.domain.system.role.Role;
import com.smp.service.system.module.IModuleService;
import com.smp.service.system.role.IRoleService;
import com.smp.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("system/role")
public class RoleController extends BaseController {

    private static final Logger l= LoggerFactory.getLogger(RoleController.class);

    @Autowired
    IRoleService iRoleService;
    @Autowired
    IModuleService iModuleService;
    @RequestMapping(path = "/toList",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toList(@RequestParam(defaultValue = "1")int curr,@RequestParam(defaultValue = "10")int pageSize){
        //调查询分页列表的方法
        PageInfo<Role> pi=iRoleService.findByPage(curr,pageSize,getLoginCompanyId());
        //将pi添加到页面
        request.setAttribute("pi",pi);
        return "system/role/role-list";
    }

    @RequestMapping(path = "/toAdd",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toAdd(){
        return "system/role/role-add";
    }

    @RequestMapping(path = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public  String add(Role role){
        //接收页面提交过来的表单
        l.info("add role="+role);
        role.setCompanyId(getLoginCompanyId());
        role.setCompanyName(getLoginCompanyName());
        iRoleService.saveRole(role);
        return "redirect:/system/role/toList.do";
    }

    @RequestMapping(path = "/toUpdate",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toUpdate(String roleId){
        //需要使用参数接收提交的roleId
        Role role=iRoleService.findById(roleId);
        l.info("toUpdate role="+role);
        //回显到页面
        request.setAttribute("role",role);
        return "system/role/role-update";
    }

    @RequestMapping(path = "/update",method = {RequestMethod.GET,RequestMethod.POST})
    public  String update(Role role){
        l.info("update role="+role);

        role.setCompanyId(getLoginCompanyId());
        role.setCompanyName(getLoginCompanyName());
        iRoleService.updateRole(role);
        return "redirect:/system/role/toList.do";
    }
    @RequestMapping(path = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
    public  @ResponseBody
    Object delete(String roleId){
        try {
            iRoleService.deleteRole(roleId);
            //成功
            return Result.init(200,"删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return Result.init(-200,"删除失败",null);
        }

    }

    @RequestMapping(path = "/toRoleModule",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toRoleModule(String roleId){//接收页面提交的id
        //当前授权页面需要显示 角色名称
        l.info("toRoleModule roleId="+roleId);
        Role role=iRoleService.findById(roleId);
        //数据转发到页面
        request.setAttribute("role",role);
        return "system/role/role-module";
    }
    //$.get('${path}/role/getZtreeData.do?roleId=${role.roleId}',fn,'json')

    @RequestMapping(path="/getZtreeData",method ={ RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Object getZtreeData(String roleId) {//接收页面提交的roleId
        //所有的权限查询出来
        List<Module> all = iModuleService.findAllModules();
        //转换成 List<Map<String,Object>>  { id:1, pId:0, name:"Sass管理", open:true},
        //根据 roleId查 该角色的权限
        List<Module> myList = iModuleService.findModuleByRoleId(roleId);
        List<Map<String,Object>> list = new ArrayList<>();
        //返回给页面
        for(Module m:all){
            //生成一个集合 Map<String,Object> 表示一节点
            Map<String,Object> node = new HashMap<String,Object>();
            node.put("id",m.getModuleId());
            node.put("pId",m.getParentId());
            node.put("name",m.getName());
            node.put("open",true);
            if(isInMyList(m,myList)){
                node.put("checked",true);//为了在菜单页面上打上勾。有打勾就表示有这个权限，否则就是没有
            }
            //添加到集合中
            list.add(node);
        }
        return list;//@ResponseBody将list转成json
    }

    //需要判断m是否在myList里面，如果在表示该角色有这个权限，否则没有
    private boolean isInMyList(Module m, List<Module> myList) {
        for(Module my:myList){
            if(m.getModuleId().equals(my.getModuleId())){
                l.info("isInMyList  moduleId1  "+m.getModuleId()+" "+m.getName());
                l.info("isInMyList  moduleId2  "+my.getModuleId()+" "+my.getName());
                return true;
            }
        }//end for 循环结束
        return false;
    }

    //${path}/system/role/updateRoleModule.do
    @RequestMapping(path="/updateRoleModule",method ={ RequestMethod.GET, RequestMethod.POST})
    public String updateRoleModule(String roleId,String moduleIds){//接收了页面提交的参数
        l.info("updateRoleModule roleId="+roleId);
        l.info("updateRoleModule moduleIds="+moduleIds);
        //roleId=4028a1cd4ee2d9d6014ee2df4c6a0010
        //moduleIds=202,203,204
        iModuleService.updateRoleModule(roleId,moduleIds);

        return "redirect:/system/role/toList.do";
    }
}
