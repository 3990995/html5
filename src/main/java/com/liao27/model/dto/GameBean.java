package com.liao27.model.dto;

import com.google.common.collect.Lists;
import com.liao27.model.entity.Comment;
import com.liao27.model.entity.Game;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * 游戏介绍
 * Created by main on 2018/5/4.
 */
@Slf4j
@Data
public class GameBean implements Comparable<GameBean> {

    private Long id;

    /**
     * 游戏名称
     */
    private String name;

    /**
     * 游戏图标
     */
    private String logo;

    /**
     * 游戏大小
     */
    private String size;

    /***
     * 简单描述
     */
    private String details;

    /**
     * 游戏内容介绍
     */
    private String descriptions;

    /**
     * 总评分
     */
    private Float starTotal;

    /**
     * 版本信息
     */
    private String versionInfo;

    /**
     * 所属类别
     */
    private CategoryBean category;

    /**
     * 视频地址
     */
    private String video;

    /**
     * 游戏下载地址
     */
    private String download;

    /**
     * 上传的图片或者视频文件名
     */
    private Set<String> images;

    /**
     * 评论列表
     */
    private List<CommentBean> commentList = Lists.newArrayList();


    public String getVersionInfo(){
        if (Strings.isEmpty(this.versionInfo)){
            return "最新版本：9.2 近7天：9.3 Android：9.5 iOS：9.4";
        }
        return this.versionInfo;
    }

    public Float getStarTotal(){
        if (this.starTotal == null){
            return 10f;
        }
        return this.starTotal;
    }

    public static GameBean build(Game game) {
        if (game == null) {
            return null;
        }
        GameBean gameBean = new GameBean();
        BeanUtils.copyProperties(game, gameBean,"commentList");
        if (game.getCommentList() != null && game.getCommentList().size() > 0){
            List<CommentBean> list = Lists.newArrayList();
            for (Comment c: game.getCommentList()) {
                if (c != null){
                    list.add(CommentBean.build(c));
                }
            }
            gameBean.setCommentList(list);
        }
        if (game.getCategory() != null){
            gameBean.setCategory(CategoryBean.build(game.getCategory()));
        }
        return gameBean;
    }

    public static List<GameBean> builds(Collection<Game> games) {
        if (games == null) {
            return null;
        }
        List<GameBean> list = Lists.newArrayList();
        for (Game game : games) {
            list.add(GameBean.build(game));
        }
        list.sort(Comparator.comparing(GameBean::getId));
        return list;
    }

    @Override
    public int compareTo(GameBean o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if(obj instanceof GameBean){
            GameBean gb = (GameBean)obj;
            if (gb.getId() == null){
                return false;
            }
            if (gb.getId().equals(this.getId())){
                return true;
            }
        }
        return false;
    }

}
