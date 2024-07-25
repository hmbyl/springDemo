package org.springDemo.common.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

@TableName("xes_age")
public class XesAge {

    @TableId(type = IdType.AUTO)
    private Long id;

    private int age;

    private int status;
}
