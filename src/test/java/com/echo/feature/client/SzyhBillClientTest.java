package com.echo.feature.client;

import com.echo.feature.BaseTest;
import com.echo.feature.entity.DTO.SzyhResultDTO;
import com.echo.feature.entity.DTO.bill.ViewBillPageIdsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class SzyhBillClientTest extends BaseTest {

    @Autowired
    private SzyhBillClient szyhBillClient;

    @Test
    public void testViewBillPageIds() {
        SzyhResultDTO<ViewBillPageIdsDTO> result = szyhBillClient.viewBillPageIds(253226L, 1);
        assert Objects.nonNull(result);
    }
}