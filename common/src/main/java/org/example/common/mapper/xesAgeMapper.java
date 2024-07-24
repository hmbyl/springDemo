package org.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.dao.xesAge;

import java.util.List;

public interface xesAgeMapper extends BaseMapper<xesAge> {
    List<xesAge> selectByIds(List<Integer> ids);
}
