package com.liao27.controller;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.dto.CategoryBean;
import com.liao27.model.dto.CategoryReq;
import com.liao27.model.dto.GameBean;
import com.liao27.model.dto.GameReq;
import com.liao27.model.entity.Category;
import com.liao27.model.entity.Game;
import com.liao27.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by main on 2018/5/4.
 */
@Slf4j
@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @ModelAttribute("allGames")
    public List<GameBean> gameBeanList() {
        return this.gameService.findAll();
    }

    @RequestMapping(value = "/game/list")
    public ModelAndView manage(final GameReq gameReq, ModelAndView model) {
        model.setViewName("/game_manage");
        return model;
    }

    @RequestMapping(value = "/game/save", method = {RequestMethod.POST})
    public ModelAndView addGame(ModelAndView model, @Valid @ModelAttribute(value = "gameReq") GameReq gameReq, @RequestParam("logo") MultipartFile logo, @RequestParam("images") MultipartFile[] images) {
        Game game = new Game();
        BeanUtils.copyProperties(gameReq, game);

        try {
            gameService.addGame(game);
            model.addObject("msg_add", "分类 " + game.getName() + " 添加成功");
        } catch (BusinessException e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        }
        model.setViewName("redirect:/game/list");
        return model;
    }

    @RequestMapping(value = "/game/remove", params = {"removeId"})
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
        model.setViewName("redirect:/game/list");
        return model;
    }

}
