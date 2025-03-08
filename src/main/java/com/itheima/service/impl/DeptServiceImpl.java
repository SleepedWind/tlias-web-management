package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询所有部门数据
     * @return 部门数据列表
     */
    @Override
    public List<Dept> findAllDept() {

        return deptMapper.findAllDept();
    }

    /**
     * 通过ID删除部门信息
     * @param id
     */
    @Override
    public void deleteDeptById(Integer id) {
        deptMapper.deleteDeptById(id);
    }

    @Override
    public void addDept(Dept dept) {
        LocalDateTime now = LocalDateTime.now();
        dept.setCreateTime(now);
        dept.setUpdateTime(now);
        deptMapper.addDept(dept);
    }

    @Override
    public Dept selectDeptById(Integer id) {

        Dept dept = deptMapper.selectDeptById(id);

        return dept;
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
