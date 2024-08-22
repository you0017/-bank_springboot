package com.yc.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Data
//@ManagedResource(objectName = "com.yc:name=OpRecord")
//@Component
@TableName("oprecord")
public class OpRecord {
    @TableId(value="id", type = IdType.AUTO)
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    //@TableField(value = "optime")
    @TableField(value = "optime",fill = FieldFill.INSERT)
    private String opTime;
    @TableField(value = "optype")
    private OpType opType;
    @TableField(value = "transferid")
    private Integer transferid;
}
