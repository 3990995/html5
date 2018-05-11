package com.liao27.model.entity;

import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

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
     * 总评分
     */
    @Column
    private Float starTotal;

    /**
     * 版本信息
     */
    @Column
    private String versionInfo;

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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_game_images", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "images")
    private Set<String> images = Sets.newHashSet();

    /**
     * 第一部分推荐
     */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "index1_id")
    private Index index1;


    /**
     * 第2部分推荐
     */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "index2_id")
    private Index index2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Game game = (Game) o;

        if (id != null ? !id.equals(game.id) : game.id != null) return false;
        if (name != null ? !name.equals(game.name) : game.name != null) return false;
        if (logo != null ? !logo.equals(game.logo) : game.logo != null) return false;
        if (size != null ? !size.equals(game.size) : game.size != null) return false;
        if (details != null ? !details.equals(game.details) : game.details != null) return false;
        if (descriptions != null ? !descriptions.equals(game.descriptions) : game.descriptions != null) return false;
        if (category != null ? !category.equals(game.category) : game.category != null) return false;
        return video != null ? video.equals(game.video) : game.video == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (descriptions != null ? descriptions.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        return result;
    }
}
