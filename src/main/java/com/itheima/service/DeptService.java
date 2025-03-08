package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAllDept();

    void deleteDeptById(Integer id);

    void addDept(Dept dept);

    Dept selectDeptById(Integer id);

    void updateDept(Dept dept);
}
