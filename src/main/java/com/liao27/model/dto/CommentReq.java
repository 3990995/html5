package com.liao27.model.dto;

import com.liao27.model.Action;
import com.liao27.model.Type;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

/**
 * 评论请求参数
 * <p>
 * Created by main on 2017/10/10.
 */
@Slf4j
@Data
public class CommentReq {

    /**
     * 头信息
     */
    private HeadBean hb;

    /**
     * 分页对象
     */
    private PageBean pb;

    /**
     * 操作,删除还是新增评论
     */
    private Action action;

    /**
     * 玩家昵称
     */
    private String name;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 打分
     */
    private Float star;

    /**
     * 游戏 id
     */
    private Long gameId;

    /**
     * 评论类别
     */
    private Type type;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论某一条评论的 id
     */
    private Long commentId;

    /**
     * 评论 root 的 id
     */
    private Long grandpaId;
}
