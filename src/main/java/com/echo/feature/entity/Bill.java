package com.echo.feature.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售单主档
 * xx_bill_1
 */
@Data
@TableName("xx_bill_2")
public class Bill implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.NONE)
    private Long id;

    /**
     * 流水号（唯一）
     */
    private String serialNo;

    /**
     * 单据编号
     */
    private String billNo;

    /**
     * 销方税号
     */
    private String sellerTaxNo;

    /**
     * 销方名称
     */
    private String sellerName;

    /**
     * 销货方地址
     */
    private String sellerAddress;

    /**
     * 销货方电话
     */
    private String sellerPhone;

    /**
     * 销货方银行账号（银行和账号以; 分割）
     */
    private String sellerBankAccount;

    /**
     * 购方名称
     */
    private String buyerName;

    /**
     * 购货方识别号 企业消费，如果填写识别号，需要传输过来
     */
    private String buyerTaxNo;

    /**
     * 购货方地址
     */
    private String buyerAddress;

    /**
     * 购货方电话
     */
    private String buyerPhone;

    /**
     * 购房邮箱
     */
    private String buyerEmail;

    /**
     * 购货方银行账号（银行和账号以; 分割）
     */
    private String buyerBankAccount;

    /**
     * 开票点编码
     */
    private String terminalCode;

    /**
     * 开票员
     */
    private String drawer;

    /**
     * 收款员
     */
    private String payee;

    /**
     * 复核人
     */
    private String checker;

    /**
     * 发票类型10电子票（默认），04（增普），01(增专)
     */
    private String invoiceType;

    /**
     * 原发票单据号
     */
    private String originalBillNo;

    /**
     * 退货时原发票代码，退货必传
     */
    private String originalInvoiceCode;

    /**
     * 退货时原发票编码，退货必传
     */
    private String originalInvoiceNo;

    /**
     * 操作代码 10正票正常开具 11正票错票重开20 退货折让红票、21 错票重开红票、22换票冲红（全冲红电子发票，开具纸质发票）
     */
    private String operationCode;

    /**
     * 价税合计金额
     */
    private BigDecimal totalPriceTax;

    /**
     * 合计不含税金额。所有商品行不含税金额之和。
     */
    private BigDecimal totalAmount;

    /**
     * 合计税额。所有商品行税额之和。
     */
    private BigDecimal totalTax;

    /**
     * 是否成功开票 0未开（默认）1已开
     */
    private String openStatus;

    /**
     * 错误次数
     */
    private Long errorTimes;

    /**
     * 错误原因
     */
    private String errorDescription;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建人接口进来默认interface，其它登陆loginName
     */
    private String createBy;

    /**
     * 插入时间
     */
    private Date createDate;

    /**
     * 状态：0初始状态、1已预制2：部分开票3、已开票4、作废;5、审核在途
     */
    private String billStatus;

    /**
     * 门店号
     */
    private String storeCode;

    /**
     * 小票金额
     */
    private BigDecimal billAmount;

    /**
     * 接口返回信息
     */
    private String msg;

    /**
     * 开票服务：1.航信  2.百旺
     */
    private String billingService;

    /**
     * 开票来源（客户小票/供应商）1.接口；2.手工开具；3.导入
     */
    private String invoiceSource;

    /**
     * 平台号
     */
    private String platformNo;

    /**
     * 数据库分区标识（yyyymm）
     */
    private Integer ldate;

    /**
     * 开票类型（1：蓝票2：红票）
     */
    private String billType;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 审核状态1：待审批2：审批未通过3：审批通过4审批在途
     */
    private String auditStatus;

    /**
     * 是否移除0：正常，1：删除
     */
    private String isRemoved;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 1:并单2：拆单
     */
    private String opType;

    /**
     * 审核权限1：一级权限（默认）;2：二级权限;3：三级权限
     */
    private String auditAuthority;

    /**
     * 单据生成时间
     */
    private Date billDate;

    /**
     * 红冲原因
     */
    private String redReason;

    /**
     * 红字通知单号
     */
    private String redAdviceNum;

    /**
     * 拆分或合并状态（0.原始单据；1.合并后的单据；-1.被拆分后的单据）
     */
    private String splitMergeStatus;

    /**
     * 过期单据定时开票失败信息
     */
    private String timingInvoiceMsg;

    /**
     * 单据合并与拆分时的删除状态 0：正常，1：删除
     */
    private String isRemoved2;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 收票手机号
     */
    private String receiveInvoicePhoneNum;

    /**
     * 红字确认单uuid
     */
    private String redConfirmNum;

    /**
     * 发票类别(0-税控发票;1-数电发票)
     */
    private String invoiceCategory;

    /**
     * 征税方式（0-普通征税；2-差额征税）
     */
    private String taxMethod;

    /**
     * 开票说明
     */
    private String invoicingInstructions;

    /**
     * 机器编码
     */
    private String machineNo;

    /**
     * 特殊票种
     */
    private String specialInvoice;

    /**
     * 单据拆分合并确认状态。0：未确认，1:已确认
     */
    private String splitMergeConfirm;

    /**
     * 关联状态：1 全部关联 2部分关联
     */
    private String linkStatus;

    /**
     * 回推优蓝指南针系统状态：1 成功 2失败
     */
    private Short syncCompassStatus;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人
     */
    private String modifyBy;

    /**
     * 录入人
     */
    private String enteredBy;

    /**
     * 录入时间
     */
    private Date enteredTime;

    /**
     * 开票人账号
     */
    private String taxLoginAccount;

    private static final long serialVersionUID = 1L;
}