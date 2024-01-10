package com.echo.feature.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.feature.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper extends BaseMapper<Bill> {
}
