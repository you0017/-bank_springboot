package com.yc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.Accounts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper extends BaseMapper<Accounts> {

    @Select("select * from accounts where accountid=#{accountid}")
    public Accounts selectByAccountId(String accountId);
}
