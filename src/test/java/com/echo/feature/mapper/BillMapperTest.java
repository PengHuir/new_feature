package com.echo.feature.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.feature.BaseTest;
import com.echo.feature.entity.Bill;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Objects;

@Slf4j
public class BillMapperTest extends BaseTest {

    @Resource
    private BillMapper billMapper;

    @Test
    public void testGetById() {
        Bill bill = billMapper.selectById(196641L);
        log.info("bill = {}", bill);
        assert Objects.nonNull(bill);
    }

    @Test
    public void testFindPage() {
        Page<Bill> billPage = new Page<>(1, 10);
        IPage<Bill> pageRes = billMapper.selectPage(billPage, null);
        log.info("pageRes = {}", pageRes);
        log.info("size = {}", pageRes.getRecords().size());
    }
}