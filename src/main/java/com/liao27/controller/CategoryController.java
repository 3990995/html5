package com.liao27.controller;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.CategoryBean;
import com.liao27.model.dto.CategoryReq;
import com.liao27.model.dto.GameBean;
import com.liao27.model.entity.Category;
import com.liao27.services.CategoryService;
import com.liao27.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by main on 2018/4/27.
 */
@Slf4j
@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryBean currentCategoryBean;

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("allCategories")
    public List<CategoryBean> categoryBeanList() {
        return this.categoryService.findAll();
    }

    @RequestMapping
    public ModelAndView category(ModelAndView model) {
        if (this.currentCategoryBean == null){
            List<CategoryBean> list = this.categoryService.findAll();
            if (list.size() > 0){
                this.currentCategoryBean = list.get(0);
            }
        }
        List<GameBean> gblist = this.gameService.findAllByCategoryId(this.currentCategoryBean.getId());
        model.addObject("gameList",gblist);
        model.addObject("currentCategoryBean",this.currentCategoryBean);
        model.setViewName("category");
        return model;
    }

    @RequestMapping("/list/{categoryId}")
    public ModelAndView gameListByCategoryId(@PathVariable("categoryId") Long categoryId,ModelAndView model){
        this.currentCategoryBean = this.categoryService.getCategory(categoryId);
        model.setViewName("redirect:/category");
        return model;
    }

    @RequestMapping(value = "/list")
    public ModelAndView manage(final CategoryReq categoryReq, ModelAndView model) {
        model.addObject("options", categoryService.getIndexs());
        model.setViewName("/category_manage");
        return model;
    }


    @RequestMapping(value = "/save", method = {RequestMethod.POST})
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
        model.addObject("options", categoryService.getIndexs());
        model.setViewName("redirect:/category/list");
        return model;
    }

    @RequestMapping(value = "/remove", params = {"removeId"})
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
        model.addObject("options", categoryService.getIndexs());
        model.setViewName("redirect:/category/list");
        return model;
    }

}
