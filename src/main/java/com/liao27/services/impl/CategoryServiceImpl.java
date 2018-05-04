package com.liao27.services.impl;

import com.google.common.collect.Lists;
import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.CategoryBean;
import com.liao27.model.entity.Category;
import com.liao27.repositories.CategoryRepository;
import com.liao27.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by main on 2018/4/27.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) throws BusinessException {
        if (categoryRepository.findFirstByName(category.getName()) != null){
            throw new BusinessException("分类已经存在");
        }
        return categoryRepository.save(category);
    }

    @Override
    public ArrayList<String> getIndexs() {
        ArrayList<String> indexs = Lists.newArrayList();
        for (int i = 1; i <= 10; i++){
            indexs.add(i+"");
        }
        return indexs;
    }

    @Override
    public List<CategoryBean> findAll() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryBean> beans = Lists.newArrayList();
        for (Category category:list){
            CategoryBean bean = new CategoryBean();
            BeanUtils.copyProperties(category,bean);
            beans.add(bean);
        }
        return beans;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Long id) throws BusinessException {
        if (id != null && id > 0){
            try{
                categoryRepository.deleteById(id);
                return true;
            }catch (EmptyResultDataAccessException e){
                throw new BusinessException("记录不存在");
            }
        }else{
            throw new IllegalArgumentException("id 参数无效");
        }
    }
}
