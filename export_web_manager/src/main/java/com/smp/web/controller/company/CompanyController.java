package com.smp.web.controller.company;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.smp.domain.company.Company;
import com.smp.service.company.ICompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private static final Logger l= LoggerFactory.getLogger(CompanyController.class);
    //@Autowired 没有rpc功能
    //就使用有rpc功能的Reference
    @Reference
    ICompanyService iCompanyService;

    //list.action->list------>查询
    //访问company/list.do
    //@RequiresPermissions("企业管理")//当前用户需要【企业管理】权限
    @RequestMapping(path = "/toList",method = RequestMethod.GET)
    public String toList(Integer curr,Integer pageSize,Model model){
        //调service数据
        if(curr==null){
            //如果当前页面为空，则设置为1
            curr=1;
        }
        if(pageSize==null){
            //每页数为空，则设置为10
            pageSize=10;
        }
        //添加权限判断
//        Subject subject = SecurityUtils.getSubject();
//        subject.checkPermission("企业管理");
        PageInfo<Company> pi=iCompanyService.findPage(curr,pageSize);

        List<Company> list=iCompanyService.findAll();
        l.info("list list="+list);
        model.addAttribute("list",list);

        l.info("toList pi="+pi);
        model.addAttribute("pi",pi);
        return "company/company-list";
    }
    //修改传值
    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public String update(Company company){
        l.info("update company="+company);
        iCompanyService.updateCompany(company);
        //跳转到列表页面
        return "redirect:/company/toList.do";
    }
    //修改-->回显
    @RequestMapping(path="/toEdit",method = RequestMethod.GET)
    public String toEdit(String id,Model model){
        l.info("toEdit id="+id);
        Company company=iCompanyService.findById(id);
        l.info("toEdit company="+company);
        model.addAttribute(company);
        return "company/company-update";
    }
    //删除
    @RequestMapping(path = "/delete",method = RequestMethod.GET)
    public  String delete(String id){
        l.info("delete id="+id);
        iCompanyService.deleteById(id);
        //跳转到列表页面
        return "redirect:/company/toList.do";
    }
    //添加-->请求
    //${path}/company/add.do
    @RequestMapping(path="/add",method = RequestMethod.POST)
    public String add(Company company){
        l.info("add company="+company);
        iCompanyService.saveCompany(company);
        //跳转到列表页面
        return "redirect:/company/toList.do";
    }
    //添加   -->打开添加界面
    @RequestMapping(path="/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "company/company-add";
    }

    //查询 1 打开列表页面
    @Deprecated
    @RequestMapping(path="/toList1",method = RequestMethod.GET)
    public String toList1(Model model){
        //调service获取数据
        List<Company> list = iCompanyService.findAll();
        l.info("toList list="+list);
        model.addAttribute("list",list);
        //将数据发到页面，使用标签
        return "company/company-list";
    }
}
