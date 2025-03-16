package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 根据条件查询班级信息
     */
    @GetMapping
    public Result find(ClazzQueryDto clazzQueryDto){
        log.info("根据条件查询班级信息，条件：{}",clazzQueryDto);
        QueryResult<Clazz> clazzQueryResult = clazzService.find(clazzQueryDto);
        return Result.success(clazzQueryResult);
    }

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList.toArray(new Clazz[0]));
    }

    /**
     * 添加班级
     */
    @PostMapping
    public Result add(@RequestBody ClazzAddParam clazzAddParam){
        log.info("添加班级");
        clazzService.add(clazzAddParam);
        return Result.success();
    }

    /**
     * 根据id查询班级信息
     */
    @GetMapping("/{id}")
    public Result queyById(@PathVariable Integer id){
        log.info("根据id查询班级信息");
        Clazz clazz = clazzService.queryById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * @param clazzInfo 班级信息
     * @return result.success
     */
    @PutMapping
    public Result updateClazzInfo(@RequestBody Clazz clazzInfo){
        log.info("修改班级信息:{}",clazzInfo);
        clazzService.updateClazz(clazzInfo);
        return Result.success();
    }

    /**
     *  删除班级信息
     */
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable Integer id){
        log.info("删除班级信息:{}",id);
        clazzService.deleteClazz(id);
        return Result.success();
    }
}

