package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryDto;
import com.itheima.pojo.QueryResult;

public interface ClazzService {
    QueryResult<Clazz> find(ClazzQueryDto clazzQueryDto);
}
