package com.liao27.controller;

import com.liao27.exceptions.BusinessException;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by main on 2018/4/27.
 */
@Slf4j
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("options")
    public ArrayList<String> options() {
        return categoryService.getIndexs();
    }

    @ModelAttribute("allCategories")
    public List<CategoryBean> categoryBeanList() {
        return this.categoryService.findAll();
    }

    @RequestMapping(value = "/category/list")
    public ModelAndView manage(final CategoryReq categoryReq, ModelAndView model) {
        model.addObject("options", categoryService.getIndexs());
        model.setViewName("/category_manage");
        return model;
    }


    @RequestMapping(value = "/category/save", method = {RequestMethod.POST})
    public ModelAndView addCategory(ModelAndView model, @Valid @ModelAttribute(value = "categoryReq") CategoryReq categoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryReq, category);

        try {
            categoryService.addCategory(category);
            model.addObject("msg_add", "分类 " + category.getName() + " 添加成功");
        } catch (BusinessException e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        }
        model.setViewName("redirect:/category/list");
        return model;
    }

    @RequestMapping(value = "/category/remove", params = {"removeId"})
    public ModelAndView removeRow(ModelAndView model, final CategoryReq categoryReq, final HttpServletRequest req) {
        boolean flag;
        final Long removeId = Long.valueOf(req.getParameter("removeId"));
        try {

            flag = this.categoryService.remove(removeId);
            if (flag) {
                model.addObject("msg_remove", "删除分类成功");
            } else {
                model.addObject("error_remove", "删除分类失败");
            }
        } catch (IllegalArgumentException e) {
            model.addObject("error_remove", "删除分类失败:" + e.getMessage());
        } catch (BusinessException e) {
            model.addObject("error_remove", "删除分类失败:" + e.getMessage());
        }
        model.setViewName("redirect:/category/list");
        return model;
    }

}