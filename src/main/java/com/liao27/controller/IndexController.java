package com.liao27.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("form")
    public String form() {
        return "form";
    }
}
