package org.springDemo.common.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("xes_test")
@Accessors(chain = true)
public class xesTest {
    private Integer id;
    private String name;
    private Integer status;
}
