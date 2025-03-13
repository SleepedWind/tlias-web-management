package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> findAllEmp(String name, Integer gender, LocalDate begin, LocalDate end);

    @Select("select * from emp")
    List<Emp> empList();

    Integer totalEmp(String name, Integer gender, LocalDate begin, LocalDate end);

    void addEmp(Emp emp);

    void addEmpExpr(List<EmpExpr> exprList);

    void deleteEmp(List<Integer> ids);

    void deleteEmpExpr(List<Integer> ids);


    Emp selectEmpById(Integer id);

    void updateEmp(Emp emp);

    @Select("select id,username,name from emp where username =#{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
