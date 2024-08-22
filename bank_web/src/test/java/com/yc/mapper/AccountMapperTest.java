package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.Accounts;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void testAccountMapper(){
        Accounts a = accountMapper.selectById(2);
        System.out.println(a);
    }

    @Test
    public void testAccountMapperInsert(){
        Accounts a = new Accounts();
        a.setBalance(22.0);
        a.setEmail("123@qq.com");
        int insert = accountMapper.insert(a);
        Assert.assertEquals(1,insert);
        System.out.println(a);
    }

    @Test
    public void testAccountMapperSelect(){
        QueryWrapper<Accounts> wrapper = new QueryWrapper<Accounts>();
    }
}