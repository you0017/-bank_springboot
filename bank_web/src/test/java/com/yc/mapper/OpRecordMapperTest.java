package com.yc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.OpRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OpRecordMapperTest {

    @Autowired
    private OpRecordMapper opRecordMapper;
    @Test
    public void selectByAccountId() {
        List<OpRecord> record = opRecordMapper.selectByAccountId("2");
        record.forEach(System.out::println);
    }

    @Test
    public void Page(){
        Page<OpRecord> page = new Page<>(1,5);
        LambdaQueryWrapper<OpRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OpRecord::getAccountid,"2").eq(OpRecord::getOpType,"deposite").orderByDesc(OpRecord::getId);
        Page<OpRecord> opRecordPage = opRecordMapper.selectPage(page, wrapper);
        opRecordPage.getRecords().forEach(System.out::println);
        System.out.println(opRecordPage.getTotal());
    }
}