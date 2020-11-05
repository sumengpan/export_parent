package com.smp.web.controller.system.user;

import com.github.pagehelper.PageInfo;
import com.smp.domain.Result;
import com.smp.domain.system.dept.Dept;
import com.smp.domain.system.module.Module;
import com.smp.domain.system.role.Role;
import com.smp.domain.system.user.User;
import com.smp.service.system.dept.IDeptService;
import com.smp.service.system.module.IModuleService;
import com.smp.service.system.role.IRoleService;
import com.smp.service.system.user.IUserService;
import com.smp.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private static  final Logger l = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService iUserService;
    @Autowired
    IDeptService iDeptService;
    //列表页面
    @RequestMapping(path="/toList",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toList(@RequestParam(defaultValue = "1") int curr, @RequestParam(defaultValue = "10")int pageSize){
        //调查询分页列表的方法
        PageInfo<User> pi=iUserService.findByPage(curr,pageSize,getLoginCompanyId());
        //将pi到页面
        request.setAttribute("pi",pi);
        return "system/user/user-list";
    }
    @RequestMapping(path="/toAdd",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toAdd(){
        //页面上有一个下拉菜单，需要查询所有的部门
        List<Dept> depts= iDeptService.findAll(getLoginCompanyId());
        //添加到request
        request.setAttribute("depts",depts);
        return "system/user/user-add";
    }
    //添加
    @RequestMapping(path="/add",method ={ RequestMethod.GET, RequestMethod.POST})
    public String add(User user){
        //接收页面传过来的表单数据
        l.info("add user="+user);
        //用户或者员工属于同一家公司，所以公司conpanyId是必须的
        user.setCompanyId(getLoginCompanyId());
        user.setCompanyName(getLoginCompanyName());
        l.info("add user="+user);
        iUserService.saveUser(user);
        return "redirect:/system/user/toList.do";
    }
    //修改
    @RequestMapping(path="/toUpdate",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toUpdate(String userId){
        //需要使用参数接收提交的userId
        l.info("toUpdate userId="+userId);
        //需要根据userId查询当前模块的记录，回显

        User user =  iUserService.findUserById(userId);
        l.info("toUpdate user="+user);
        request.setAttribute("user",user);
        //页面上有一个下拉菜单 ，需要查询所有的部门
        List<Dept> depts = iDeptService.findAll(getLoginCompanyId());
        //添加到request
        request.setAttribute("depts",depts);
        return "system/user/user-update";
    }

    //${path}/system/user/update.do
    @RequestMapping(path = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(User user) {//需要接收编辑页面提交的表单数据
        l.info("update user=" + user);
        //更新
        iUserService.updateUser(user);
        return "redirect:/system/user/toList.do";
    }

    //删除
    @RequestMapping(path="/delete",method ={ RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Object delete(String userId){
        //参数接收页面js提交过来的userId
        boolean flag=iUserService.deleteUser(userId);
        if(flag){
            return Result.init(200,"删除成功",null);
        }else {
            return Result.init(-200,"删除失败",null);
        }
    }
    @Autowired
    IRoleService iRoleService;
    @RequestMapping(path = "/toUserRole", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUserRole(String userId){
        //接收页面传过来的userId
        l.info("toUserRole userId="+userId);
        //使用userId查找用户对象
        User user = iUserService.findUserById(userId);
        //转发给页面
        request.setAttribute("user",user);

        //所有角色的数据
        String companyId=getLoginCompanyId();
        List<Role> roleList =  iRoleService.findAll(companyId);
        //老王的角色数据
        List<Role> userRoleList =  iRoleService.findRolesByUserId(userId);
        l.info("toUserRole roleList = "+roleList);
        l.info("toUserRole userRoleList = "+userRoleList);

        for(Role role: roleList){
            //当前公司的所有的角色
            if(isInUserRoleList(role,userRoleList)){
                role.setChecked(true);
            }
        }
        //转发到页面
        request.setAttribute("roleList",roleList);
        request.setAttribute("userRoleList",userRoleList);
        return "system/user/user-role";
    }

    //当前的复选框 要不要打勾 取决于是否在 用户的角色列表中
    private boolean isInUserRoleList(Role role, List<Role> userRoleList) {
        for( Role r:userRoleList){
            if(r.getRoleId().equals(role.getRoleId())){
                return true;
            }
        }//end for
        return false;
    }

    //${path}/system/user/updateUserRole.do
    //userId
    //String[] roleIds
    @RequestMapping(path = "/updateUserRole", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateUserRole(String userId,String[] roleIds){//接收用户的userId与角色的roleIds
        //String userId="002108e2-9a10-4510-9683-8d8fd1d374ef";
        //String[] roleIds = {"4028a1cd4ee2d9d6014ee2df4c6a0010"};
        l.info("updateUserRole userId = "+userId);
        l.info("updateUserRole roleIds = "+ Arrays.toString(roleIds));
        //用户的角色修改
        iRoleService.updateUserRole(userId,roleIds);
        //打开列表页面
        return "redirect:/system/user/toList.do";
    }

    //${path}/system/user/login.do
    //email
    //password
    @Autowired
    IModuleService iModuleService;
    @RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(String email,String password){
        //根据 email查询对应的用户
        l.info("login email "+email);
        l.info("login password "+password);
        User user = iUserService.findUserByEmail(email);
        l.info("login user "+user);
        if (user != null) {
            //1:找到
            //比较账号密码
            if(user.getPassword().equals(password)){
                //正确
                l.info("正确");
                //保存用户信息
                session.setAttribute("loginUser",user);
                //一个 Module对象 就是左侧栏上的一个菜单项
                List<Module> menus = iModuleService.findModuleByUser(user);
                session.setAttribute("menus",menus);
                l.info("login menus "+menus);
                //跳到主页
                return "redirect:/home/toMain.do";
            }else{
                //密码不对
                l.info("密码不对");
                request.setAttribute("error","邮箱或者密码不对");
                return "forward:/login.jsp";
            }
        }else{
            //2:没找到
            //用户不存在
            l.info("用户不存在");
            request.setAttribute("error","用户不存在");
            return "forward:/login.jsp";
        }
    }

    @RequestMapping(path = "/loginOut", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginOut(){
        //删除session中的用户信息
        session.removeAttribute("loginUser");
        //让session过期
        session.invalidate();
        return "redirect:/login.jsp";//转发不会改地址的数据，只有重定向会
    }
}
