package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 员工列表信息查询
     */
    @Override
    public List<Emp> empList() {

        return empMapper.empList();
    }

    /**
     * 新增员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.addEmp(emp);
        List<EmpExpr> empExprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(empExprList)){
            empMapper.addEmpExpr(empExprList);
        }

    }

    /**
     * 删除员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteEmp(List<Integer> ids) {
        //1.根据员工id删除 员工表emp 数据
        empMapper.deleteEmp(ids);
        //2.根据员工id删除 员工工作信息表emp_expr 数据
        empMapper.deleteEmpExpr(ids);
    }

    /**
     * 根据id查询员工信息
     */
    @Override
    public Emp selectEmpById(Integer id) {

        //根据员工id查询返回 员工基本信息表emp 数据 及 员工工作经历表emp_expr 数据
        Emp emp = empMapper.selectEmpById(id);
        return emp;
    }

    /**
     * 修改员工信息
     */
    @Override
    public void updateEmp(Emp emp) {
        //1.根据参数更新 员工基本信息表emp 数据
        empMapper.updateEmp(emp);
        //2.根据参数中的empId删除 员工工作信息表emp_expr 内相关数据
        List list = new ArrayList();
        list.add(emp.getId());
        empMapper.deleteEmpExpr(list);
        //3.根据参数内的员工工作经历列表，添加员工工作信息
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empMapper.addEmpExpr(emp.getExprList());
        }

    }

    /**
     * 登录验证
     */
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码查询员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2.判断：是否存在员工，如果存在，组装成功登录信息
        if (e != null){
            log.info("登录成功，员工信息：{}",e);
            Map<String,Object> claims = new HashMap<String,Object>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String token = JwtOperator.getToken(claims);
            return  new LoginInfo(e.getId(),e.getUsername(),e.getName(),token);
        }
        //3.不存在，返回null
        return null;
    }

    @Override
    public PageResult page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);
        List<Emp> empList = empMapper.findAllEmp(name,gender,begin,end);


        return new PageResult(empMapper.totalEmp(name,gender,begin,end),empList);
    }


}
