package com.liao27.controller;

import com.liao27.model.dto.GameBean;
import com.liao27.model.dto.IndexBean;
import com.liao27.model.entity.Index;
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
    public ModelAndView manage(ModelAndView model){
        model.addObject("indexBean",this.indexService.getIndex());
        model.addObject("allCategories", this.categoryService.findAll());
        model.addObject("allGames", this.gameService.findAll());
        model.setViewName("index_manage");
        return model;
    }

    @RequestMapping("/index/config")
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
