package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


    /**
     * 查询全部员工
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询全部员工");
        List<Emp> empList = empService.empList();

        return Result.success(empList);


    }


    /**
     * 分页查询员工列表
     */
    @GetMapping
    public Result page(String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询员工列表:{},{},{},{},{},{}",name,gender,begin,end,page,pageSize);

        PageResult pageResult  = empService.page(name,gender,begin,end,page,pageSize);

        return Result.success(pageResult);
    }

    /**
     * 新增员工信息
     */

    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("新增员工信息");
        empService.addEmp(emp);
        return Result.success();
    }

    /**
     * 批量删除员工
     */
    @DeleteMapping
    public Result deleteEmp(@RequestParam List<Integer> ids){
        log.info("批量删除员工:{}",ids);
        empService.deleteEmp(ids);
        return Result.success();

    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public Result selectEmpById(@PathVariable Integer id){
        log.info("根据id查询员工信息");

        Emp emp = empService.selectEmpById(id);

        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */

    @PutMapping
    public Result updateEmp(@RequestBody Emp emp){
        log.info("修改员工信息");
        empService.updateEmp(emp);
        return Result.success();
    }
}
