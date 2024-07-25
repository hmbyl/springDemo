package org.springDemo.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springDemo.common.dao.XesAge;

import java.util.List;

@Mapper
public interface XesAgeMapper extends BaseMapper<XesAge> {

    List<XesAge> selectByIds(List<Long> ids);
}
