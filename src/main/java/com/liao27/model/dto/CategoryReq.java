package com.liao27.model.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

/**
 * 请求参数
 * Created by main on 2018/4/27.
 */
@Slf4j
@Data
public class CategoryReq {
    /**
     * 头信息
     */
    private HeadBean hb;

    /**
     * 分页对象
     */
    private PageBean pb;

    private Long id;

    @NotEmpty(message="类别名: 不能为空")
    private String name;

    private Long index;
}

