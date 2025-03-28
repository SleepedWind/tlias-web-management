package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzAddParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ClazzMapper {
    //根据条件查询总记录数

    Long count(String name, LocalDate begin, LocalDate end);

    List<Clazz> selectByNameOrTime(String name, LocalDate begin, LocalDate end);

    //根据条件查询班级列表
    @Select("select c.id, c.name, c.room, c.begin_date, c.end_date, c.master_id, c.subject, c.create_time, c.update_time,e.name masterName " +
            "from clazz c left join emp e on c.master_id = e.id")
    List<Clazz> findAll();

    void add(ClazzAddParam clazzAddParam);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id=#{id}")
    Clazz queryById(Integer id);

    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject},update_time=#{updateTime} where id=#{id}")
    void updateClazzById(Clazz clazzInfo);

    @Delete("delete from clazz where id = #{id}")
    void deleteClazzById(Integer id);
}
