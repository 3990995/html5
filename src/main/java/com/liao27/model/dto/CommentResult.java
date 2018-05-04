package com.liao27.model.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
@Data
public class CommentResult {
    /**
     * 返回状态
     */
    private AckBean ack;

    /**
     * 评论列表
     */
    private List<CommentBean> commentBeanlist = Lists.newArrayList();

    /**
     * 分页对象
     */
    private PageBean pb;

    public CommentResult setAck(AckBean ack){
        this.ack = ack;
        return this;
    }

}
