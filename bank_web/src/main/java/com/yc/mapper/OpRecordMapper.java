package com.yc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.OpRecord;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface OpRecordMapper extends BaseMapper<OpRecord> {
}
