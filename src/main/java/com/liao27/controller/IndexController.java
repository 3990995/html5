package com.liao27.controller;

import com.liao27.model.dto.IndexBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by main on 2018/4/17.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/index")
    public String manage(final IndexBean indexBean){
        return "index_manage";
    }

    @RequestMapping("/index/config")
    public ModelAndView config(ModelAndView model){

        model.setViewName("index_manage");
        return model;
    }

    @RequestMapping("details")
    public String details() {
        return "details";
    }

}
