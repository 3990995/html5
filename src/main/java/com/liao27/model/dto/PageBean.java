package com.liao27.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 分页对象
 * Created by Administrator on 2017/5/25 0025.
 */
@Slf4j
@Data
public class PageBean {
    /**
     * 每页多少条
     */
    private int rowNumber;

    /**
     * 第几页
     */
    private int pageNumber;

    /**
     * 最后一条记录的id
     */
    private long lastFlagInt;

    @JsonIgnore
    private String lastFlagStr;

    @JsonIgnore
    private long lastFlagInt2;

    @JsonIgnore
    private String lastFlagStr2;


}
