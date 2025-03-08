package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 部门列表数据查询
     */
    @GetMapping
    public Result findAllDept(){
        log.info("部门列表数据查询");
        List<Dept> deptList = deptService.findAllDept();

        return Result.success(deptList);
    }

    /**
     * 通过ID删除部门
     */
    @DeleteMapping
    public Result deleteDeptById(Integer id){
        log.info("通过ID删除指定部门信息");
        deptService.deleteDeptById(id);
        return Result.success();
    }

    /**
     * 添加部门
     */
    @PostMapping
    public Result addDept(@RequestBody Dept dept){
        log.info("添加部门");
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门数据
     */

    @GetMapping("/{id}")
    public Result selectDeptById(@PathVariable Integer id){
        log.info("根据ID查询部门数据：{}",id);
        Dept dept = deptService.selectDeptById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     */
    @PutMapping
    public Result updateDept( Dept dept){
        log.info("修改部门信息:{}",dept);
        deptService.updateDept(dept);

        return Result.success();
    }
}
