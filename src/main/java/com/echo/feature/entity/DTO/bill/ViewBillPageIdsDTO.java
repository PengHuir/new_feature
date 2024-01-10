package com.echo.feature.entity.DTO.bill;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author ph.zhang
 * Created by on 2023/12/27 10:25
 */
@Data
public class ViewBillPageIdsDTO implements Serializable {

    /**
     * 订单明细页面单据id列表
     */
    private List<String> billPageIds;

    /**
     * 初始订单明细单据id列表
     */
    private List<String> initialPageIds;


    /**
     * 发票id列表
     */
    private List<String> invoiceIds;
}
