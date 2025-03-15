package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzAddParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ClazzMapper {
    //根据条件查询总记录数

    Long count(String name, LocalDate begin, LocalDate end);

    List<Clazz> selectByNameOrTime(String name, LocalDate begin, LocalDate end);

    //根据条件查询班级列表
    @Select("select c.id, c.name, c.room, c.begin_date, c.end_date, c.master_id, c.subject, c.create_time, c.update_time,e.name masterName from clazz c left join emp e on c.master_id = e.id")
    List<Clazz> findAll();

    void add(ClazzAddParam clazzAddParam);
}
