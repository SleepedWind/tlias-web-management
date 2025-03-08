package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSizev);



    List<Emp> empList();

    void addEmp(Emp emp);

    void deleteEmp(List<Integer> ids);

    Emp selectEmpById(Integer id);

    void updateEmp(Emp emp);

    LoginInfo login(Emp emp);
}
