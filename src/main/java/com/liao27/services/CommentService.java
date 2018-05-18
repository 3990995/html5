package com.liao27.services;

import com.liao27.exceptions.BusinessException;
import com.liao27.model.Type;
import com.liao27.model.dto.CommentBean;
import com.liao27.model.dto.CommentReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 评论服务接口
 * Created by main on 2018/5/14.
 */
public interface CommentService {

    /**
     * 根据游戏 id 评论类型查找所有评论
     *
     * @param gameId 游戏 id
     * @param type   评论类型
     * @return 评论列表
     */
    List<CommentBean> findAllByGameIdAndType(Long gameId, Type type);

    /**
     * 添加评论
     *
     * @param headImage 头像
     * @param type      评论类型
     * @param name      评论者名称
     * @param content   评论内容
     * @param star      评分
     * @param gameId    关联游戏 id
     * @return 评论数据
     */
    CommentBean addComment(MultipartFile headImage, Type type, String name, String content, Float star, Long gameId) throws BusinessException, IOException;
}
