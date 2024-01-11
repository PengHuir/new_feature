package com.echo.feature.boot.entity.DTO.bill;

import lombok.Data;

import java.io.Serializable;

/**
 * Name: SopAuthCallBackDTO
 * Description:
 * Author: zhangph
 * Date: 2024/1/11 14:36
 */
@Data
public class SopAuthCallBackDTO implements Serializable {

    /**
     * 流水号
     */
    private String serialNum;

    /**
     * 公司纳税人识别号
     */
    private String xsfnsrsbh;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 税局登录账号
     */
    private String account;

    /**
     * 税局登录人姓名
     */
    private String name;

    /**
     * 登录税局状态，00：未登录 01：已登录
     */
    private String loginStatus;

    /**
     * 账号状态, 00：无需认证 01：登录身份认证 02：扫码人脸识别身份认证 03：风险+登录认证
     */
    private String authMethod;

    /**
     * 认证方式
     * 00：无需认证；01：需扫码认证； 02：需扫码或短信认证；03：需短信认证
     */
    private String operType;

    /**
     * 描述
     */
    private String desc;

    /**
     * 实名认证二维码
     */
    private String qrcodeContent;

    /**
     * 二维码认证 id
     */
    private String authId;

    /**
     * 实名认证二维码类型，01：税务APP
     */
    private String qrcodeType;

    /**
     * 年月日时分秒
     * yyyy-mm-dd HH:mm:ss
     */
    private String qrcodeGenTime;

    /**
     * 通知创建时间，年月日时分秒
     * yyyy-mm-dd HH:mm:ss
     */
    private String createTime;

    /**
     * 二维码到期时间 yyyy-MM-dd HH:mm:ss
     */
    private String expireTime;

    /**
     * 最后一次认证成功时间
     * yyyy-mm-dd HH:mm:ss
     */
    private String lastAuthSuccTime;

    /**
     * 认证类型:
     * 0：登录认证
     * 1：风险认证
     */
    private String authType;
}
