package com.echo.feature.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.feature.boot.entity.Bill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillMapper extends BaseMapper<Bill> {
}
