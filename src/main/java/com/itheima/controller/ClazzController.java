package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryDto;
import com.itheima.pojo.QueryResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result find(ClazzQueryDto clazzQueryDto){
        log.info("根据条件查询班级信息，条件：{}",clazzQueryDto);
        QueryResult<Clazz> clazzQueryResult = clazzService.find(clazzQueryDto);
        return Result.success(clazzQueryResult);
    }

}
