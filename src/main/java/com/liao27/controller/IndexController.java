package com.liao27.controller;

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
        IndexBean ib = this.indexService.getIndex();
        model.addObject("indexBean",ib);
        model.addObject("allCategories", this.categoryService.findAll());
        List<GameBean> list = this.gameService.findAll();
        for (GameBean gb : list) {
            for (GameBean g1: ib.getGameList1()) {
                if (g1.getId() != null && g1.getId().equals(gb.getId())){
                     gb.setChecked1(true);
                }
            }
            for (GameBean g2: ib.getGameList2()){
                if (g2.getId() != null && g2.getId().equals(gb.getId())){
                    gb.setChecked2(true);
                }
            }
        }
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
