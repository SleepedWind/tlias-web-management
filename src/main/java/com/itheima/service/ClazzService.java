package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzAddParam;
import com.itheima.pojo.ClazzQueryDto;
import com.itheima.pojo.QueryResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    QueryResult<Clazz> find(ClazzQueryDto clazzQueryDto);

    List<Clazz> list();

    void add(ClazzAddParam clazzAddParam);

    Clazz queryById(Integer id);
}
