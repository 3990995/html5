package com.liao27.model.dto;

import com.liao27.model.Action;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
     * 游戏 id
     */
    private Long gameId;

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
