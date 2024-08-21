package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Data
@ManagedResource(objectName = "com.yc:name=OpRecord")
@Component
@TableName("oprecord")
public class OpRecord {
    @TableId(value="id", type = IdType.AUTO)
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private String datetime;
    private OpType optype;
    private Integer transferid;
}
