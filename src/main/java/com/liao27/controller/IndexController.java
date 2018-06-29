package com.liao27.controller;

import com.google.common.collect.Lists;
import com.liao27.model.dto.GameBean;
import com.liao27.model.dto.IndexBean;
import com.liao27.model.entity.Index;
import com.liao27.permission.PermissionConstants;
import com.liao27.permission.RequiredPermission;
import com.liao27.services.CategoryService;
import com.liao27.services.GameService;
import com.liao27.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by main on 2018/4/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IndexService indexService;

    @RequestMapping
    public ModelAndView index(ModelAndView model) {
        model.addObject("indexBean",this.indexService.getIndex());
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/index")
    @RequiredPermission(PermissionConstants.ADMIN_PAGE)
    public ModelAndView manage(ModelAndView model){
        model.addObject("allCategories", this.categoryService.findAll());
        List<GameBean> list = this.gameService.findAll();

        IndexBean ib = this.indexService.getIndex();

        List<GameBean> list1 = Lists.newArrayList();
        List<GameBean> list2 = Lists.newArrayList();

        for (GameBean gb : list){
            if (ib.getGameList1().contains(gb)){
                list1.add(gb);
            }else{
                list1.add(new GameBean());
            }
            if (ib.getGameList2().contains(gb)){
                list2.add(gb);
            }else{
                list2.add(new GameBean());
            }
        }
        ib.setGameList1(list1);
        ib.setGameList2(list2);
        model.addObject("indexBean",ib);
        model.addObject("allGames", list);
        model.setViewName("index_manage");
        return model;
    }

    @RequestMapping("/index/config")
    @RequiredPermission(PermissionConstants.ADMIN_PAGE)
    public ModelAndView config(@Valid @ModelAttribute IndexBean indexBean, ModelAndView model, BindingResult br){
        if(br.hasErrors()) {
            //如果有错误直接跳转到add视图
            FieldError fieldError= br.getFieldError();
            System.out.println(fieldError.getDefaultMessage());

            List<FieldError> list = br.getFieldErrors();
            for (FieldError fieldError2 : list) {
                System.out.println(fieldError2.getDefaultMessage());
            }
            List<ObjectError> ls=br.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                System.out.println("error:"+ls.get(i).getDefaultMessage());
            }
        }else{
            indexBean = this.indexService.config(indexBean);
        }
        model.addObject("indexBean",indexBean);
        model.setViewName("redirect:/index");
        return model;
    }



}
