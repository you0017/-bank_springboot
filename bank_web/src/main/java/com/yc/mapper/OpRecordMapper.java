package com.yc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.Accounts;
import com.yc.bean.OpRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface OpRecordMapper extends BaseMapper<OpRecord> {

    @Select("select * from oprecord where accountid=#{accountid}")
    public List<OpRecord> selectByAccountId(String accountId);
}
