package com.liao27.services;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.CategoryBean;
import com.liao27.model.entity.Category;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by main on 2018/4/27.
 */
public interface CategoryService {

    /**
     * 保存一个新的 分类
     * @param category 分类
     * @return 分类实体
     */
    Category addCategory(Category category) throws BusinessException;

    /**
     * 获取options 集合
     * @return 集合
     */
    ArrayList<String> getIndexs();

    /**
     * 查找所有分类数据
     * @return 分类列表
     */
    List<CategoryBean> findAll();

    /**
     * 删除分类
     * @param id 分类 id
     * @return 删除是否成功
     */
    boolean remove(Long id) throws BusinessException;

    /**
     * 根据类别查找类别数据
     * @param categoryId 类别 id
     * @return 类别数据
     */
    CategoryBean getCategory(Long categoryId);
}
