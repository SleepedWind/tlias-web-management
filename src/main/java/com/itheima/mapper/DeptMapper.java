package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> findAllDept();

    @Delete("delete from dept where id = #{id}")
    void deleteDeptById(Integer id);


    @Insert("insert into dept values (#{id},#{name},#{createTime},#{updateTime})")
    void addDept(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id =#{id}")
    Dept selectDeptById(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
