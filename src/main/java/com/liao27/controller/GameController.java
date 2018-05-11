package com.liao27.controller;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.GameReq;
import com.liao27.services.CategoryService;
import com.liao27.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by main on 2018/5/4.
 */
@Slf4j
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = {"/list","/",""})
    public ModelAndView manage(ModelAndView model) {
        model.addObject("allGames", this.gameService.findAll());
        model.addObject("allCategories", this.categoryService.findAll());
        model.setViewName("/game_manage");
        return model;
    }


    @RequestMapping("/show/{gameId}")
    public ModelAndView showGame(ModelAndView model, @PathVariable("gameId") Long gameId){
        model.addObject("game",this.gameService.getGame(gameId));
        model.setViewName("details");
        return model;
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ModelAndView addGame(
            @RequestParam("logo") MultipartFile logo,
            @RequestParam("video") MultipartFile video,
            @RequestParam("images") MultipartFile[] images,
            @RequestParam String name,
            @RequestParam Long categoryId,
            @RequestParam String size,
            @RequestParam String details,
            @RequestParam String descriptions,
            ModelAndView model
    ) {
        try {
            gameService.addGame(logo, video, images, name, categoryId, size, details, descriptions);
            model.addObject("msg_add", "分类 " + name + " 添加成功");
        } catch (BusinessException e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        }

        model.setViewName("redirect:/game");
        return model;
    }

    @RequestMapping(value = "/remove", params = {"removeId"})
    public ModelAndView removeRow(ModelAndView model, final GameReq gameReq, final HttpServletRequest req) {
        boolean flag;
        final Long removeId = Long.valueOf(req.getParameter("removeId"));
        try {

            flag = this.gameService.remove(removeId);
            if (flag) {
                model.addObject("msg_remove", "删除游戏成功");
            } else {
                model.addObject("error_remove", "删除游戏失败");
            }
        } catch (IllegalArgumentException e) {
            model.addObject("error_remove", "删除游戏失败:" + e.getMessage());
        } catch (BusinessException e) {
            model.addObject("error_remove", "删除游戏失败:" + e.getMessage());
        }
        model.setViewName("redirect:/game");
        return model;
    }

}
