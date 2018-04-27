package com.liao27.model.dto;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public final class Constants {

    /**
     * 协议version
     */
    public static final int protocolversion = 1;

    /**
     * 成功
     */
    public static final int success = 200;

    /**
     * 失败
     */
    public static final int err = 400;

    /**
     * 请求参数不正确
     */
    public static final String T1001 = "T1001";

    /**
     * session过期
     */
    public static final String T1002 = "T1002";

    /**
     * 您暂无权限操作
     */
    public static final String T1003 = "T1003";

    /**
     * 用户不存在
     */
    public static final String T1004 = "T1004";

    /**
     * 验证码请求过于频繁
     */
    public static final String T1005 = "T1005";

    /**
     * 验证码错误
     */
    public static final String T1006 = "T1006";

    /**
     * 微信开放平台appid,网页应用
     */
    public final static String WX_APPID = "wx0c87c9712575ca0a";

    /**
     * 微信公众号平台appid
     */
    public final static String WX_APPID_MP = "wx7b06533686c8503e";

    /**
     * 微信开放平台appsecret 网页应用
     */
    public final static String WX_APPSECRET = "01f3d5d782b62290196116b456471b58";

    /**
     * 微信公众号平台appsecret
     */
    public final static String WX_APPSECRET_MP = "08d30be9263baa241de3a48a3e41f062";

    /**
     * 状态列表
     */
    public static final Map<String, String> statusMap = new HashMap<String, String>();

    static {
        statusMap.put("T1001", "请求参数不正确");
        statusMap.put("T1002", "session过期");
        statusMap.put("T1003", "您暂无权限操作");
        statusMap.put("T1004", "用户不存在");
        statusMap.put("T1005", "验证码请求过于频繁");
        statusMap.put("T1006", "验证码错误");
    }

    /**
     * 加密验证 key值
     */
    public final static String AUTHKEY = "cc.fgt.www";

    /**
     * 七牛AccessKey
     */
    public static final String ACCESS_KEY = "Ll9UFGhXxFqsN8KS-UUdlA2XIspjjkF6Qb52Cc1G";

    /**
     * 七牛SecretKey
     */
    public static final String SECRET_KEY = "RwY8Ls6F_1Ogb55CVU1y3qUaFy3-ohPGIktNffvX";

    /**
     * 图片空间
     */
    public static final String BUCKET_IMAGE = "nerapp-image";
    public static final String DOMAIN_IMAGE_CDN = "http://images.fgt.cc/";

    /**
     * 图片空间
     */
    public static final String BUCKET_VIDEO = "nerapp-video";
    public static final String DOMAIN_VIDEO_CDN = "http://video.fgt.cc/";

    /**
     * 七牛 buckets
     */
    public static final List BUCKETS = Lists.newArrayList(BUCKET_IMAGE, BUCKET_VIDEO);


    /**
     * 默认返回每页条数
     */
    public final static int ROWNUMBER = 5;
}
