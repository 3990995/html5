package com.liao27.controller;

import com.liao27.model.dto.CategoryBean;
import com.liao27.model.dto.CategoryReq;
import com.liao27.model.entity.Category;
import com.liao27.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by main on 2018/4/27.
 */
@Slf4j
@Controller
@RequestMapping("/data")
public class DatasController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/save-category", method = {RequestMethod.POST})
    public ModelAndView addCategory(ModelAndView model, @Valid @ModelAttribute(value="categoryReq") CategoryReq categoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryReq,category);

        try {
            category = categoryService.addCategory(category);

            CategoryBean bean = new CategoryBean();
            BeanUtils.copyProperties(category,bean);
            model.addObject("category",bean);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        model.setViewName("/form");
        return model;
    }

}
