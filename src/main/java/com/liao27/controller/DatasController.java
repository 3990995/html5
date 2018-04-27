package com.liao27.controller;

import com.google.common.collect.Maps;
import com.liao27.model.dto.AckBean;
import com.liao27.model.dto.CategoryBean;
import com.liao27.model.dto.CategoryReq;
import com.liao27.model.dto.Constants;
import com.liao27.model.entity.Category;
import com.liao27.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by main on 2018/4/27.
 */
@Slf4j
@Controller
@RequestMapping("/data")
public class DatasController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/save-category", method = {RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public AckBean addCategory(@RequestBody CategoryReq categoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryReq,category);

        try {
            category = categoryService.addCategory(category);

            CategoryBean bean = new CategoryBean();
            BeanUtils.copyProperties(category,bean);

            AckBean ack = AckBean.build(Constants.success);
            Map<String, Object> map = Maps.newHashMap();
            map.put("category", bean);
            ack.setExtraMap(map);

            return ack;
        }catch (Exception e) {
            log.error(e.getMessage());
        }

        return AckBean.build(Constants.err);
    }

}
