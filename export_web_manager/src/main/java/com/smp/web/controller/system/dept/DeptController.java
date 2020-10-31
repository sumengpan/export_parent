package com.smp.web.controller.system.dept;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.dept.Dept;
import com.smp.service.system.dept.IDeptService;
import com.smp.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("system/dept")
public class DeptController extends BaseController{
    @Autowired
    IDeptService iDeptService;

    private static final Logger l= LoggerFactory.getLogger(DeptController.class);

    @RequestMapping(path = "/toList",method = {RequestMethod.GET,RequestMethod.POST})
    public  String toList(Model model,@RequestParam(defaultValue = "1") Integer curr,
                          @RequestParam(defaultValue = "10")  Integer pageSize){
        l.info("toList curr"+curr);//当前页数
        l.info("toList pageSize"+pageSize);//每页记录数
        l.info("toList companyId"+super.getLoginCompanyId());//指定公司id
        //查询一个分页
        PageInfo<Dept> pi=iDeptService.findByPage(curr,pageSize,super.getLoginCompanyId());

        l.info("toList pi="+pi);
        model.addAttribute("pi",pi);
        return "system/dept/dept-list";
    }
    @RequestMapping(path="/toAdd",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toAdd(Model model){
        //需要为下拉菜单查询出所有的部门，一个部门对应一个选项
        //根据companyId查询出部门，不做分页
        l.info("toAdd companyId="+super.getLoginCompanyId());
        List<Dept> list=iDeptService.findAll(super.getLoginCompanyId());
        l.info("toAdd list="+list);
        model.addAttribute("list",list);
        return "system/dept/dept-add";
    }
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    public String add(Dept dept,String parentId){
        l.info("add dept="+dept);
        l.info("add parentId="+parentId);
        //默认公司id为1，后面可修改

        dept.setCompanyId(super.getLoginCompanyId());
        dept.setCompanyName(super.getLoginCompanyName());

        Dept parent =new Dept();
        parent.setDeptId(parentId);

        dept.setParent(parent);
        //保存到数据库
        iDeptService.saveDept(dept);
        return "redirect:/system/dept/toList.do";
    }
    //修改编辑
    //${path}/system/dept/toUpdate.do?deptId=${dept.deptId}
    @RequestMapping(path="/toUpdate",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toUpdate(Model model, String deptId){

        String companyId = super.getLoginCompanyId();
        l.info("toUpdate deptId="+deptId);

        //查询部门
        Dept dept = iDeptService.findById(deptId);
        l.info("toUpdate dept="+dept);

        List<Dept> list = iDeptService.findAll(companyId);

        model.addAttribute("dept",dept);
        model.addAttribute("list",list);

        return "system/dept/dept-update";
    }
    //修改回显
    //action="${path}/system/dept/update.do"
    @RequestMapping(path="/update",method ={ RequestMethod.GET, RequestMethod.POST})
    public String update(Dept dept,String parentId){

        l.info("update dept="+dept);
        l.info("update parentId="+parentId);

        //当前写死companyId与companyName以后再修改
        dept.setCompanyName(super.getLoginCompanyName());
        dept.setCompanyId(super.getLoginCompanyId());

        Dept parent = new Dept();//下拉菜单
        parent.setDeptId(parentId);
        dept.setParent(parent);

        l.info("update dept="+dept);
        //2 保存到数据库
        iDeptService.updateDept(dept);
        //修改完成之后跳到列表页面
        return "redirect:/system/dept/toList.do";
    }

    // location.href="${path}/system/dept/delete.do?depId="+deptId;
    @RequestMapping(path="/delete",method ={ RequestMethod.GET})
    public String delete(String deptId){
        l.info("delete deptId="+deptId);

        iDeptService.deleteDeptById(deptId);
        //修改完成之后跳到列表页面
        return "redirect:/system/dept/toList.do";
    }

    /*
    @Autowired
    HttpSession httpSession;
     */
    @RequestMapping(path="/testRequest",method ={ RequestMethod.GET})
    public String testRequest(HttpServletRequest request){//springmvc会赋值
        l.info("sessionId"+session.getId());
        l.info("testRequest request="+request.getParameter("age"));
        request.setAttribute("jack","rose");

        return "result";
    }

}

