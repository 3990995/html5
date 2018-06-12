package com.liao27.services.impl;

import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;
import com.liao27.exceptions.BusinessException;
import com.liao27.model.Type;
import com.liao27.model.dto.CommentBean;
import com.liao27.model.dto.CommentReq;
import com.liao27.model.entity.Comment;
import com.liao27.model.entity.Game;
import com.liao27.repositories.CommentRepository;
import com.liao27.repositories.GameRepository;
import com.liao27.services.CommentService;
import com.liao27.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by main on 2018/5/14.
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GameRepository gameRepository;

    /**
     * 存储路径
     */
    @Value("${upload.location}")
    private String uploadLocation;

    @Override
    public List<CommentBean> findAllByGameIdAndType(Long gameId, Type type) {
        List<CommentBean> blist = Lists.newArrayList();
        if (gameId != null && gameId > 0) {
            List<Comment> list = commentRepository.findAllByGameIdAndType(gameId, type);
            for (Comment c : list) {
                CommentBean cb = CommentBean.build(c);
                if (cb != null) {
                    blist.add(cb);
                }
            }
        }
        return blist;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CommentBean addComment(MultipartFile headImage, Type type, String name, String content, Float star, Long gameId) throws BusinessException, IOException {
        if (gameId == null || gameId <= 0){
            throw new IllegalArgumentException("gameId 参数值无效");
        }
        if (Strings.isEmpty(name)){
            throw new IllegalArgumentException("昵称不能为空");
        }
        if (Strings.isEmpty(content)){
            throw new IllegalArgumentException("评论内容不能为空");
        }
        if (star == null){
            star = 0f;
        }
        if (headImage == null || Strings.isEmpty(headImage.getOriginalFilename())){
            throw new  IllegalArgumentException("头像数据无效");
        }

        Game game = gameRepository.getOne(gameId);
        if (game == null){
            throw new BusinessException("游戏不存在");
        }

        Comment c = new Comment();
        c.setType(type);
        c.setGame(game);
        c.setName(name);
        c.setContent(content);
        c.setStar(star);
        c.setHeadImage(FileUtil.saveFile(this.uploadLocation,headImage));
        c.setCommentTime(new Date());

        commentRepository.save(c);
        return CommentBean.build(c);
    }

}
