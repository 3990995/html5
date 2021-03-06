package com.liao27.services;

import com.liao27.model.dto.IndexBean;
import com.liao27.model.entity.Index;

/**
 * Created by main on 2018/5/8.
 */
public interface IndexService {

    /**
     * 获取默认首页内容
     * @return 首页数据
     */
    IndexBean getIndex();

    /**
     * 配置首页内容
     * @param indexBean 首页内容
     * @return 首页内容
     */
    IndexBean config(IndexBean indexBean);
}
