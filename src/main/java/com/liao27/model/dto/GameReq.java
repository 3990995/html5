package com.liao27.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created by main on 2018/5/4.
 */
@Slf4j
@Data
public class GameReq {
    /**
     * 头信息
     */
    private HeadBean hb;

    /**
     * 分页对象
     */
    private PageBean pb;

    private Long id;

    @NotEmpty(message="游戏名: 不能为空")
    @Length(max = 10,message = "长度不能超过10个字符")
    private String name;

    /**
     * 游戏图标
     */
    private String logo;
    private String video;

    /**
     * 游戏大小
     */
    private String size;

    /***
     * 简单描述
     */
    private String details;

    /**
     * 游戏内容介绍
     */
    private String descriptions;

    /**
     * 类别 id
     */
    private Long categoryId;
}
