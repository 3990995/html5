package com.liao27.services;

import com.liao27.model.entity.Category;

/**
 * Created by main on 2018/4/27.
 */
public interface CategoryService {

    /**
     * 保存一个新的 分类
     * @param category 分类
     * @return 分类实体
     */
    Category addCategory(Category category);
}
