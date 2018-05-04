package com.liao27.controller;

import com.liao27.model.dto.CategoryReq;
import com.liao27.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("category")
    public String category() {
        return "category";
    }

    @RequestMapping("details")
    public String details() {
        return "details";
    }

}
