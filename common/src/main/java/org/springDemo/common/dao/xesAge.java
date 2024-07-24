package org.springDemo.common.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xes_age")
@Accessors(chain = true)
public class xesAge {
    private int age;
    private Integer id;
    private int status;
}
