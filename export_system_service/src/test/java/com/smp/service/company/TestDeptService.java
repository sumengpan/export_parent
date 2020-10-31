package com.smp.service.company;

import com.github.pagehelper.PageInfo;
import com.smp.domain.system.dept.Dept;
import com.smp.service.system.dept.IDeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")  //3:创建 spring/applicationContext-tx.xml
public class TestDeptService {
    private static final Logger l= LoggerFactory.getLogger(TestDeptService.class);

    //1、注入业务部门类
    //2、定义接口
    //3、定义实现类
    //4、在实现类上加@service
    @Autowired
    IDeptService iDeptService;

    //列表显示
    @Test
    public void test01(){
        //部门分页显示
        int curr=1;
        int pageSize=3;
        String companyId="1";
        //2、调用分页查找方法
        PageInfo<Dept> pi=iDeptService.findByPage(curr,pageSize,companyId);
        //3、打印
        l.info("test01 pi="+pi);
    }
    //查询company_id为1的部门
    @Test
    public void test02(){
        //给页面的下拉菜单按公司查找所有的部门
        String  companyId="1";
        //2 调用分页查找方法
        List<Dept> list = iDeptService.findAll(companyId);//
        //打印
        l.info("test02 list="+list);
    }
    //添加一个部门
    @Test
    public void test03(){
        //模拟表单
        Dept dept=new Dept();
        dept.setCompanyId("1");
        dept.setDeptName("java部门");
        dept.setState(1);

        Dept parent =new Dept();
        parent.setDeptId("100101101");

        dept.setParent(parent);
        iDeptService.saveDept(dept);
    }
    //查询指定dept_id的部门
    @Test
    public void test04(){
        String deptId="100101";
        Dept dept=iDeptService.findById(deptId);
        l.info("test04 dept="+dept);
    }
    //修改
    @Test
    public void test05(){
        String deptId="8a7e82be61400c000161400c05810000";
        Dept dept = iDeptService.findById(deptId);
        //1 模拟页面的修改
        dept.setDeptName("BATJM299");
        dept.setCompanyName("吉首大学");
        dept.setCompanyId("2");
        Dept parent = new Dept();//下拉菜单
        parent.setDeptId("100");
        dept.setParent(parent);
        dept.setState(0);//停用
        //2 保存到数据库
        iDeptService.updateDept(dept);
        l.info("test05  dept="+dept);
    }
    //删除
    @Test
    public void test06(){
        //给定id
        //String deptId=" ";
        String deptId="c2a4fadf-4efa-4e17-a4a4-3f93d84825d9";//有给其他部门作上级
        //根据id删除
        boolean result=iDeptService.deleteDeptById(deptId);
        l.info("test06 result="+result);
    }

}
