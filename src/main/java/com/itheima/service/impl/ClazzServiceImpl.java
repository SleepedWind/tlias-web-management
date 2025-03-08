package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryDto;
import com.itheima.pojo.QueryResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public QueryResult<Clazz> find(ClazzQueryDto clazzQueryDto) {
        //1.查询班级总记录数
        Long total = clazzMapper.count();

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
}
