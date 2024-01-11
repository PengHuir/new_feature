package com.echo.feature.boot.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.feature.boot.BaseTest;
import com.echo.feature.boot.entity.Bill;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Slf4j
public class BillMapperTest extends BaseTest {

    @Autowired
    private BillMapper mapper;

    @Test
    public void testGetById() {
        Bill bill = mapper.selectById(196641L);
        log.info("bill = {}", bill);
        assert Objects.nonNull(bill);
    }

    @Test
    public void testFindPage() {
        Page<Bill> billPage = new Page<>(1, 10);
        IPage<Bill> pageRes = mapper.selectPage(billPage, null);
        log.info("pageRes = {}", pageRes);
        log.info("size = {}", pageRes.getRecords().size());
    }
}