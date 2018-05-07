package com.liao27.model.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

/**
 * 游戏介绍
 * Created by main on 2018/5/4.
 */
@Slf4j
@Data
@Entity
@Table(name = "t_game")
@NamedEntityGraph(
        name = "graph.Game.images",
        attributeNodes = {
                @NamedAttributeNode(value = "images")
        }
)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", nullable = false)
    private Long id;

    /**
     * 游戏名称
     */
    @Column(unique = true)
    private String name;

    /**
     * 游戏图标
     */
    @Column
    private String logo;

    /**
     * 游戏大小
     */
    @Column
    private String size;

    /***
     * 简单描述
     */
    @Column
    private String details;

    /**
     * 游戏内容介绍
     */
    @Lob
    @Column
    private String descriptions;

    /**
     * 所属类别
     */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * 视频地址
     */
    @Column
    private String video;

    /**
     * 上传的图片或者视频文件名
     */
    @ElementCollection
    @CollectionTable(name = "t_game_images", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "images")
    private List<String> images = Lists.newArrayList();
}
