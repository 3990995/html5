package com.liao27.model.dto;

import com.google.common.base.Strings;
import com.liao27.model.entity.Comment;
import com.liao27.utils.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 评论的DTO
 * Created by Administrator on 2017/10/11 0011.
 */
@Slf4j
@Data
public class CommentBean {

    /**
     * 评论id
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 游戏id
     */
    private Long gameId;

    /**
     * 评论的评论id
     */
    private Long parentId;

    /***
     * 评论 root 的 id
     */
    private Long grandpaId;

    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 用户看到的评论时间
     */
    private String commentTimeString;


    /**
     * 返回用户看到的评论时间
     * @return 时间
     */
    public String getCommentTimeString() {
        if (!Strings.isNullOrEmpty(this.commentTimeString)) {
            return this.commentTimeString;
        }
        this.commentTimeString = DateUtil.betweenWithDate(this.commentTime);
        return this.commentTimeString;
    }

    /**
     * 构造评论DTO的工厂方法，
     * @param comment 评论实体对象
     * @return DTO
     */
    public static CommentBean build(Comment comment) {
        if (comment == null){
            return null;
        }
        CommentBean cb = new CommentBean();
        BeanUtils.copyProperties(comment,cb);
        if (comment.getParent() != null){
            cb.parentId = comment.getParent().getId();
        }
        if (comment.getGrandpa() != null){
            cb.grandpaId = comment.getGrandpa().getId();
        }

        return cb;
    }
}
