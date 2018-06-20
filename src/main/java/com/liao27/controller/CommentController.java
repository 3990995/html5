package com.liao27.controller;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.Type;
import com.liao27.model.dto.CommentBean;
import com.liao27.model.dto.CommentReq;
import com.liao27.repositories.CommentRepository;
import com.liao27.services.CommentService;
import com.liao27.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

import static com.liao27.model.Type.GROUP;
import static com.liao27.model.Type.USER;

/**
 * Created by main on 2018/5/14.
 */
@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private GameService gameService;

    private static final String[] stars = new String[]{"0", "0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0",
            "4.5", "5.0"};

    @ModelAttribute("stars")
    public String[] getStars() {
        return stars;
    }

    @ModelAttribute("types")
    public Type[] getTypes() {
        return Type.values();
    }


    @RequestMapping("/game/{gameId}")
    public ModelAndView showGame(ModelAndView model, @PathVariable("gameId") Long gameId) {
        model.addObject("game", this.gameService.getGame(gameId));
        model.addObject("userComments", this.commentService.findAllByGameIdAndType(gameId, USER));
        model.addObject("groupComments", this.commentService.findAllByGameIdAndType(gameId, GROUP));
        model.setViewName("details_manage");
        return model;
    }

    @RequestMapping(value = "/save/{gameId}", method = {RequestMethod.POST})
    public ModelAndView addComment(@RequestParam("headImage") MultipartFile headImage,
                                   @RequestParam("type") Type type,
                                   @RequestParam("name") String name,
                                   @RequestParam("content") String content,
                                   @RequestParam("star") Float star,
                                   @PathVariable("gameId") Long gameId,
                                   ModelAndView model) {
        try {
            CommentBean gb = this.commentService.addComment(headImage, type, name, content, star, gameId);
            model.addObject("commentBean",gb);
        } catch (BusinessException | IOException | IllegalArgumentException e) {
            log.error(e.getMessage());
            model.addObject("error_add", e.getMessage());
        }
        model.setViewName("redirect:/comment/game/" + gameId);
        return model;
    }

}
