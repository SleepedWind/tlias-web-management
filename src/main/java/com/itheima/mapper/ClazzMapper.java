package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    @Select("select count(*) from clazz")
    Long count();

    List<Clazz> selectByNameOrTime(String name, LocalDate begin, LocalDate end);
}
