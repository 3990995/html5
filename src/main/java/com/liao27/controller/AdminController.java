package com.liao27.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by main on 2018/6/13.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${admin.password}")
    private String adminPassword;

    @RequestMapping
    public ModelAndView admin(ModelAndView model, @RequestParam(value = "next", required = false) String next) {
        model.addObject("next", next); //把拦截器带来的参数放在model层中去
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/login")
    public String login(@RequestParam("password") String password, @RequestParam("next") String next, HttpSession httpSession) {
        if (Strings.isNotBlank(password) && Strings.isNotEmpty(this.adminPassword)) {
            if (this.adminPassword.equals(password)) {
                httpSession.setAttribute("hasPermission", "YES");
            }
        }

        if (Strings.isNotBlank(next)) {
            String contextPath = httpSession.getServletContext().getContextPath();
            next = next.replaceAll(contextPath,"");
            return "redirect:" + next;//登陆成功后跳转
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("hasPermission");
        return "login";
    }
}
