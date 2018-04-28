package com.liao27.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by main on 2018/4/28.
 */
public class BusinessExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String msg = "未知异常";
        if (ex instanceof BusinessException){
            msg = ((BusinessException)ex).getMessage();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("error");
        return mv;
    }
}
