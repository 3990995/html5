package com.liao27.model.dto;

import com.google.common.collect.Lists;
import com.liao27.model.entity.Game;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

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
     * 所属类别
     */
    private CategoryBean category;

    /**
     * 视频地址
     */
    private String video;

    /**
     * 上传的图片或者视频文件名
     */
    private List<String> images;

    public static GameBean build(Game game) {
        if (game == null) {
            return null;
        }
        GameBean gameBean = new GameBean();
        BeanUtils.copyProperties(game, gameBean);
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
}
