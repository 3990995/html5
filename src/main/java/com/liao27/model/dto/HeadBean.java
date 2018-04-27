package com.liao27.model.dto;

import lombok.Data;

/**
 * 请求头信息
 * Created by Administrator on 2017/6/4 0004.
 */
@Data
public class HeadBean {

    /**
     * 设备信息
     */
    private String device;

    /**
     * 平台  1、android 2、ios 3、html5
     */
    private int platform;

    /**
     * 版本  客户端版本
     */
    private String version;

    /**
     * 渠道
     */
    private String chl;

    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 加密字符串 密钥：password@teshehui.com
     */
    private String input;

    /**
     * 加密结果
     */
    private String div;

    /**
     * 语言  简体中文zh-cn  美国英文cn-us
     */
    private String language;

    /**
     * 手机设备IMEI
     */
    private String imei;

    /**
     * 设备token
     */
    private String token;
}
