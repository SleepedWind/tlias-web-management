package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzAddParam;
import com.itheima.pojo.ClazzQueryDto;
import com.itheima.pojo.QueryResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     *分页查询班级列表数据
     */
    @Override
    public QueryResult<Clazz> find(ClazzQueryDto clazzQueryDto) {
        //1.查询班级总记录数
        Long total = clazzMapper.count(clazzQueryDto.getName(),clazzQueryDto.getBegin(),clazzQueryDto.getEnd());

        //2.根据条件查询班级记录列表
        //2.1设置当前时间
        LocalDate now = LocalDate.now();
        //2.2使用PageHelper进行分页处理
        PageHelper.startPage(clazzQueryDto.getPage(), clazzQueryDto.getPageSize());
        List<Clazz> rows = clazzMapper.selectByNameOrTime(clazzQueryDto.getName(),clazzQueryDto.getBegin(),clazzQueryDto.getEnd());
        //2.3根据当前时间设置课程状态值
        if(!CollectionUtils.isEmpty(rows)){

            rows.forEach(row ->{
                if (now.isAfter(row.getEndDate())) {
                    row.setStatus("已结课");
                }else if (now.isBefore(row.getBeginDate())){
                    row.setStatus("未开班");
                }else {
                    row.setStatus("在读中");
                }
            });
        }

        //3.将查询结果封装返回
        QueryResult clazzQueryResult = new QueryResult(total,rows);
        return clazzQueryResult;
    }

    /**
     * 查询全部班级信息
     * @return
     */
    @Override
    public List<Clazz> list() {
        //返回全部班级信息
        return clazzMapper.findAll();
    }

    /**
     * 新增班级
     */
    @Override
    public void add(ClazzAddParam clazzAddParam) {
        LocalDateTime now = LocalDateTime.now();
        clazzAddParam.setCreateTime(now);
        clazzAddParam.setUpdateTime(now);
        clazzMapper.add(clazzAddParam);
    }

    /**
     * 根据id查询班级信息
     */
    @Override
    public Clazz queryById(Integer id) {
        return clazzMapper.queryById(id);
    }

    @Override
    public void updateClazz(Clazz clazzInfo) {
        //设置更新时间
        clazzInfo.setUpdateTime(LocalDateTime.now());
        //调用mapper根据id更新班级信息
         clazzMapper.updateClazzById(clazzInfo);
    }

    @Override
    public void deleteClazz(Integer id) {
        //调用mapper根据id删除班级信息
        clazzMapper.deleteClazzById(id);
    }
}
